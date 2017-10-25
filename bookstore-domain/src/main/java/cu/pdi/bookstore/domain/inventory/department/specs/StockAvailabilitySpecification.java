package cu.pdi.bookstore.domain.inventory.department.specs;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntry;
import cu.pdi.bookstore.domain.kernel.title.TitleSet;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

import java.util.List;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public class StockAvailabilitySpecification implements Specification<Department> {

    private TitleSet titleSet;

    private StockAvailabilitySpecification(TitleSet titleSet) {
        this.titleSet = titleSet;
    }

    public static StockAvailabilitySpecification of(TitleSet titleSupply) {
        return new StockAvailabilitySpecification(titleSupply);
    }

    @Override
    public boolean isSatisfiedBy(Department department) {
        if (ExternalDepartmentSpecification.instance().isSatisfiedBy(department)) {
            return true;
        } else {
            List<InventoryEntry> inventoryEntries = department.listExistentEntriesForTitles(titleSet.titlesISBN());

            return inventoryEntries.stream()
                    .anyMatch((InventoryEntry inventoryEntry) ->
                            inventoryEntry.getCurrentStock().isGreaterEqualThan(titleSet.getStockForTitle(inventoryEntry.getTitle())));
        }
    }
}
