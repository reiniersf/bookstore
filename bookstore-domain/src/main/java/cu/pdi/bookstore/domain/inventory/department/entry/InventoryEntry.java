package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.Stock;
import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/29/17.
 */
@Entity
@Table(name = "inventory_entry")
@NamedQueries(
        @NamedQuery(name = "AllEntriesInTitleList",
                query = "Select ie from InventoryEntry ie where ie.inventoryEntryId.title.codigoISBN IN :isbnList and ie.inventoryEntryId.department = :departmentCode")
)
@Getter
@NoArgsConstructor
public class InventoryEntry implements Serializable {
    @EmbeddedId
    private InventoryEntryId inventoryEntryId;
    @Embedded
    private Stock currentStock;


    public InventoryEntry(ISBN title, Stock currentStock, DepartmentCode code) {
        Assert.notNull(title, "A valid inventory entry must have an ISBN code");
        Assert.notNull(currentStock, "A valid inventory entry must have a positive stock");
        Assert.notNull(code, "A valid inventory entry must belong to a department");
        this.inventoryEntryId = new InventoryEntryId(title, code);
        this.currentStock = currentStock;
    }

    public boolean isStockAvailable() {
        return currentStock.isGreaterEqualThan(new Stock(1));
    }

    public InventoryEntry increaseStock(Stock stockForTitle) {
        return new InventoryEntry(this.inventoryEntryId.getTitle(),
                this.currentStock.add(stockForTitle), this.inventoryEntryId.getDepartment());

    }

    public InventoryEntry decreaseStock(Stock stockForTitle) {
        return new InventoryEntry(this.inventoryEntryId.getTitle(),
                this.currentStock.minus(stockForTitle), this.inventoryEntryId.getDepartment());
    }

    public ISBN getTitle() {
        return this.inventoryEntryId.getTitle();
    }
}
