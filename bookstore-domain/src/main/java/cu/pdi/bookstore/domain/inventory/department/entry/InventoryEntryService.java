package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/4/17.
 */
public interface InventoryEntryService {
    void saveEntryForNewTitle(InventoryEntry inventoryEntry);

    List<InventoryEntry> searchEntriesForTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode);

    List<InventoryEntry> allEntriesInDepartment(DepartmentCode departmentCode);

    boolean hasEntriesForAllTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode);

    void increaseStock(InventoryEntry inventoryEntry, Stock stockForTitle);

    void decreaseStock(InventoryEntry inventoryEntry, Stock stockForTitle);
}
