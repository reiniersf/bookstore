package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface InventoryEntryRepository {

    void saveInventoryEntry(InventoryEntry inventoryEntry);

    List<InventoryEntry> getEntriesForTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode);

    void updateInventoryEntry(InventoryEntry inventoryEntry);

}

