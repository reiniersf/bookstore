package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.shared.Stock;
import cu.pdi.bookstore.domain.shared.ISBN;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by taiyou
 * on 8/29/17.
 */
@Entity
@Getter
public class InventoryEntry {
    @EmbeddedId
    private ISBN title;
    @Embedded
    private Stock currentStock;
    @Embedded
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
