package cu.pdi.bookstore.domain.shared.specification;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;

import java.util.List;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public class StockAvailabilitySpecification implements Specification<Department> {

    private TitleSupply titleSupply;

    private StockAvailabilitySpecification(TitleSupply titleSupply) {
        this.titleSupply = titleSupply;
    }

    public static StockAvailabilitySpecification of(TitleSupply titleSupply) {
        return new StockAvailabilitySpecification(titleSupply);
    }

    @Override
    public boolean isSatisfiedBy(Department department) {
        if (ExternalDepartmentSpecification.INSTANCE.isSatisfiedBy(department)) {
            return true;
        } else {
            List<InventoryEntry> inventoryEntries = department.listExistentEntriesForTitles(titleSupply.titlesISBN());

            return inventoryEntries.stream()
                    .anyMatch((InventoryEntry inventoryEntry) ->
                            inventoryEntry.getCurrentStock().isGreaterEqualThan(titleSupply.getStockForTitle(inventoryEntry.getTitle())));
        }
    }
}
