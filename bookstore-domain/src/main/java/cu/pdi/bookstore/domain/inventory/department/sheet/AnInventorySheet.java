package cu.pdi.bookstore.domain.inventory.department.sheet;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryService;
import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AnInventorySheet {

    private InventoryEntryService inventoryEntryService;
    private TitleService titleService;

    private AnInventorySheet(InventoryEntryService inventoryEntryService, TitleService titleService) {
        this.inventoryEntryService = inventoryEntryService;
        this.titleService = titleService;
    }

    public static AnInventorySheet instance(InventoryEntryService inventoryEntryService, TitleService titleService) {
        return new AnInventorySheet(inventoryEntryService, titleService);
    }

    public InventorySheet forDepartmentWihCode(DepartmentCode code) {
        InventorySheet inventorySheet = InventorySheet.ofDepartment(code);

        List<InventoryEntry> inventoryEntries = inventoryEntryService.allEntriesInDepartment(code);

        Map<ISBN, Stock> inventoryEntriesForDepartment = inventoryEntries
                .stream()
                .collect(Collectors.toMap(InventoryEntry::getTitle, InventoryEntry::getCurrentStock));

        Map<ISBN, Title> registeredTitlesInDepartment = titleService.getTitlesInfo(inventoryEntries.stream()
                .map(InventoryEntry::getTitle)
                .collect(Collectors.toList()))
                .stream()
                .collect(Collectors.toMap(Title::getIsbn, __ -> __));



        inventoryEntries.forEach(inventoryEntry -> inventorySheet.addEntry(inventoryEntriesForDepartment.get(inventoryEntry.getTitle()), registeredTitlesInDepartment.get(inventoryEntry.getTitle())));

        return inventorySheet;
    }
}
