package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.supply.TransferEntryService;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.specification.ExternalDepartmentSpecification;
import cu.pdi.bookstore.domain.shared.specification.StockAvailabilitySpecification;

import static cu.pdi.bookstore.domain.shared.specification.Specification.not;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 8/28/17.
 */
public class Department {
    private DepartmentCode code;
    private String name;
    private TransferEntryService transferEntryService;
    private TitleService titleService;
    private InventoryEntryRepository inventoryEntryRepository;

    Department(DepartmentCode departmentCode, String departmentName) {
        this.code = departmentCode;
        this.name = departmentName;
    }

    Department(DepartmentCode departmentCode, String departmentName,
               TransferEntryService transferEntryService,
               TitleService titleService,
               InventoryEntryRepository inventoryEntryRepository) {
        this.code = departmentCode;
        this.name = departmentName;
        this.transferEntryService = transferEntryService;
        this.titleService = titleService;
        this.inventoryEntryRepository = inventoryEntryRepository;
    }

    public void receiveTitles(Department originDepartment, TitleSupply titleSupply) {

        if (StockAvailabilitySpecification.of(titleSupply).isSatisfiedBy(originDepartment)) {
            Set<ISBN> supplyISBNList = titleSupply.titlesISBN();
            //Update existent inventory entries stock
            List<InventoryEntry> existentInventoryEntries = inventoryEntryRepository.searchExistentEntriesAmong(supplyISBNList);
            existentInventoryEntries
                    .forEach((InventoryEntry inventoryEntry)
                            -> inventoryEntryRepository.increaseStock(inventoryEntry, titleSupply.getStockForTitle(inventoryEntry.getTitle())));

            //Register the new titles
            List<ISBN> existentISBN = existentInventoryEntries.stream().map(InventoryEntry::getTitle).collect(Collectors.toList());
            supplyISBNList.stream()
                    .filter((ISBN isbn) -> !existentISBN.contains(isbn))
                    .forEach((ISBN isbn) -> {
                        titleService.registerNewTitle(titleSupply.getTitleForISBN(isbn));
                        inventoryEntryRepository.saveEntryForNewTitle(isbn, titleSupply.getStockForTitle(isbn), this.code);
                    });

            //Then log all operations done...
            transferEntryService.logTransfers(originDepartment, this, titleSupply);
            //...and update stock on origin department
            if (not(ExternalDepartmentSpecification.INSTANCE).isSatisfiedBy(originDepartment))
                originDepartment.updateStock(titleSupply);
        }
    }

    private void updateStock(TitleSupply titleSupply) {
        List<InventoryEntry> selectedInventoryEntries = inventoryEntryRepository.getEntriesForTitlesIn(titleSupply.titlesISBN());
        selectedInventoryEntries.forEach((InventoryEntry inventoryEntry) -> inventoryEntryRepository.decreaseStock(inventoryEntry, titleSupply.getStockForTitle(inventoryEntry.getTitle())));
    }

    public boolean hasEntriesForTitles(Set<ISBN> isbnList) {
        return inventoryEntryRepository.hasEntriesForAllTitlesIn(isbnList);
    }


    public List<InventoryEntry> listExistentEntriesForTitles(Set<ISBN> isbnList) {
        return inventoryEntryRepository.searchExistentEntriesAmong(isbnList);
    }
}
