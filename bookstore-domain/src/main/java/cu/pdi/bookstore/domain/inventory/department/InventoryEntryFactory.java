package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public class InventoryEntryFactory {
    static InventoryEntry createEntryForTitle(ISBN title, Stock stock, DepartmentCode departmentCode){
        return new InventoryEntry(title, stock, departmentCode);
    }
}
