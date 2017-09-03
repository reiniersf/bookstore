package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.accounting.document.TransferEntryService;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.shared.ISBN;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by taiyou
 * on 8/29/17.
 */
@Component
public class DepartmentFactory {

    public final static Department WAREHOUSE = new Department(DepartmentCode.WAREHOUSE_CODE, "Warehouse"){
        @Override
        public void receiveTitles(Department from, TitleSupply titleSupply) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }

        @Override
        public boolean hasEntriesForTitles(Set<ISBN> isbnList) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }


    };
    private final TransferEntryService transferEntryService;
    private final TitleService titleService;
    private final InventoryEntryRepository inventoryEntryRepository;

    public DepartmentFactory(TransferEntryService transferEntryService, TitleService titleService, InventoryEntryRepository inventoryEntryRepository) {
        this.transferEntryService = transferEntryService;
        this.titleService = titleService;
        this.inventoryEntryRepository = inventoryEntryRepository;
    }

    public Department createDepartment(DepartmentCode departmentCode, String departmentName) {
        return new Department(departmentCode, departmentName, this.transferEntryService, this.titleService, this.inventoryEntryRepository);
    }
}