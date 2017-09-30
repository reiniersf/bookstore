package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface InventoryEntryRepository {

    void saveInventoryEntry(InventoryEntry inventoryEntry);

    List<InventoryEntry> getEntriesForTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode);

    List<InventoryEntry> getEntriesForDepartment(DepartmentCode departmentCode);

    void updateInventoryEntry(InventoryEntry inventoryEntry);

}

