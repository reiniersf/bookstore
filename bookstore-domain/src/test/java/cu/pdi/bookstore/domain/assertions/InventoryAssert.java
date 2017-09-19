package cu.pdi.bookstore.domain.assertions;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import org.assertj.core.api.AbstractAssert;

/**
 * Created by taiyou
 * on 8/30/17.
 */
public class InventoryAssert extends AbstractAssert<InventoryAssert, Department> {

    private InventoryAssert(Department department) {
        super(department, InventoryAssert.class);
    }

    public static InventoryAssert assertThat(Department department){
        return new InventoryAssert(department);
    }

    public void hasInventoryEntriesForAllTitles(TitleSupply titleSupply){
        isNotNull();

        if(!this.actual.hasEntriesForTitles(titleSupply.titlesISBN())){
            failWithMessage("Not all titles got its inventory entry");
        }
    }

    public void hasEmptiedStockForSuppliedTitles(TitleSupply titleSupply) {
        isNotNull();

        if(this.actual.listExistentEntriesForTitles(titleSupply.titlesISBN()).stream()
                .anyMatch(InventoryEntry::isStockAvailable)){
            failWithMessage("Not all inventory entries has been updated");
        }
    }
}
