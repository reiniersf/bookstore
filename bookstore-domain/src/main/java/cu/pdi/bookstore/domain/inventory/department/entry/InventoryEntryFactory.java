package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public class InventoryEntryFactory {
    public static InventoryEntry createEntryForTitle(ISBN title, Stock stock, DepartmentCode departmentCode){
        return new InventoryEntry(title, stock, departmentCode);
    }
}
