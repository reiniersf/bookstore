package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.shared.Stock;
import cu.pdi.bookstore.domain.shared.ISBN;
import lombok.Getter;
import org.springframework.util.Assert;

/**
 * Created by taiyou
 * on 8/29/17.
 */
@Getter
public class InventoryEntry {

    private ISBN title;
    private Stock currentStock;
    private DepartmentCode code;

    public InventoryEntry(ISBN title, Stock currentStock, DepartmentCode code) {
        Assert.notNull(title, "A valid inventory entry must have an ISBN code");
        Assert.notNull(currentStock, "A valid inventory entry must have a positive stock");
        Assert.notNull(code, "A valid inventory entry must belong to a department");
        this.title = title;
        this.currentStock = currentStock;
        this.code = code;
    }

    public boolean isStockAvailable() {
        return currentStock.isGreaterEqualThan(new Stock(1));
    }

}
