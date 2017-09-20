package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryFactory;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryService;
import cu.pdi.bookstore.domain.inventory.department.events.AReceivedTitleSupplyEvent;
import cu.pdi.bookstore.domain.inventory.department.sheet.AnInventorySheet;
import cu.pdi.bookstore.domain.inventory.department.sheet.InventorySheet;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.inventory.department.specs.ExternalDepartmentSpecification;
import cu.pdi.bookstore.domain.inventory.department.specs.StockAvailabilitySpecification;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static cu.pdi.bookstore.domain.kernel.specification.Specification.not;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 8/28/17.
 */
@Entity
@Table(name = "department")
@NoArgsConstructor
public class Department implements Serializable {
    @EmbeddedId
    @Getter
    private DepartmentCode code;
    @Getter
    @Column(name = "department_name")
    private String departmentName;
    @Transient
    private DepartmentEventHandler departmentEventHandler;
    @Transient
    private TitleService titleService;
    @Transient
    private InventoryEntryService inventoryEntryService;

    Department(DepartmentCode departmentCode, String departmentName) {
        this.code = departmentCode;
        this.departmentName = departmentName;
    }

    Department(DepartmentCode departmentCode, String departmentName,
               DepartmentEventHandler departmentEventHandler,
               TitleService titleService,
               InventoryEntryService inventoryEntryService) {
        this.code = departmentCode;
        this.departmentName = departmentName;
        this.departmentEventHandler = departmentEventHandler;
        this.titleService = titleService;
        this.inventoryEntryService = inventoryEntryService;
    }

    public void receiveTitles(Department originDepartment, TitleSupply titleSupply) {

        if (StockAvailabilitySpecification.of(titleSupply).isSatisfiedBy(originDepartment)) {
            Set<ISBN> supplyISBNList = titleSupply.titlesISBN();
            //Update existent inventory entries stock
            List<InventoryEntry> existentInventoryEntries = inventoryEntryService.searchEntriesForTitlesIn(supplyISBNList, this.code);
            existentInventoryEntries
                    .forEach((InventoryEntry inventoryEntry)
                            -> inventoryEntryService
                            .increaseStock(inventoryEntry, titleSupply.getStockForTitle(inventoryEntry.getTitle())));

            //Register the new titles
            List<ISBN> existentISBNInventoryEntries = existentInventoryEntries.stream()
                    .map(InventoryEntry::getTitle)
                    .collect(Collectors.toList());

            List<ISBN> registeredTitles = titleService.getRegisteredTitlesIn(supplyISBNList);

            supplyISBNList.stream()
                    .filter((ISBN isbn) -> !existentISBNInventoryEntries.contains(isbn))
                    .forEach((ISBN isbn) -> {
                        if (!registeredTitles.contains(isbn)) {
                            titleService.registerNewTitle(titleSupply.getTitleForISBN(isbn));
                        }
                        inventoryEntryService
                                .saveEntryForNewTitle(InventoryEntryFactory
                                        .createEntryForTitle(isbn, titleSupply.getStockForTitle(isbn), this.code));
                    });

            // update stock on origin department
            if (not(ExternalDepartmentSpecification.instance()).isSatisfiedBy(originDepartment))
                originDepartment.updateStock(titleSupply);

            //...and notifies that all operations was done...
            departmentEventHandler.handle(
                    AReceivedTitleSupplyEvent.withInfo(originDepartment.getCode(), this.code, titleSupply.titlesISBN()));
        }
    }

    private void updateStock(TitleSupply titleSupply) {
        List<InventoryEntry> selectedInventoryEntries = inventoryEntryService
                .searchEntriesForTitlesIn(titleSupply.titlesISBN(), this.code);

        selectedInventoryEntries.forEach((InventoryEntry inventoryEntry)
                -> inventoryEntryService.decreaseStock(inventoryEntry,
                titleSupply.getStockForTitle(inventoryEntry.getTitle())));
    }

    public boolean hasEntriesForTitles(Set<ISBN> isbnList) {
        return inventoryEntryService.hasEntriesForAllTitlesIn(isbnList, this.code);
    }

    public List<InventoryEntry> listExistentEntriesForTitles(Set<ISBN> isbnList) {
        return inventoryEntryService.searchEntriesForTitlesIn(isbnList, this.code);
    }

    public InventorySheet generateCurrentInventorySheet() {
        return AnInventorySheet.instance(inventoryEntryService, titleService).forDepartmentWihCode(this.code);
    }
}
