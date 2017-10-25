package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryService;
import cu.pdi.bookstore.domain.inventory.department.events.DepartmentEventHandler;
import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by taiyou
 * on 8/29/17.
 */
@Component
public class DepartmentFactory {

    public final static Department WAREHOUSE = new Department(DepartmentCode.WAREHOUSE_CODE, "Warehouse") {
        @Override
        public void receiveTitles(Department from, TitleSupply titleSupply) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }

        @Override
        public boolean hasEntriesForTitles(Set<ISBN> isbnList) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }


    };
    public final static Department SOLD = new Department(DepartmentCode.SOLD, "Sold") {
        @Override
        public void receiveTitles(Department from, TitleSupply titleSupply) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }

        @Override
        public boolean hasEntriesForTitles(Set<ISBN> isbnList) {
            throw new UnsupportedOperationException("This operation is unsupported by this department");
        }


    };

    private final DepartmentEventHandler departmentEventHandler;
    private final TitleService titleService;
    private final InventoryEntryService inventoryEntryService;

    public DepartmentFactory(DepartmentEventHandler departmentEventHandler, TitleService titleService, InventoryEntryService inventoryEntryService) {
        this.departmentEventHandler = departmentEventHandler;
        this.titleService = titleService;
        this.inventoryEntryService = inventoryEntryService;
    }

    public Department createDepartment(DepartmentCode departmentCode, String departmentName) {
        return new Department(departmentCode, departmentName,
                this.departmentEventHandler, this.titleService, this.inventoryEntryService);
    }

    public Department reassembleDepartment(Department department) {
        return new Department(department.getCode(), department.getDepartmentName(),
                this.departmentEventHandler, this.titleService, this.inventoryEntryService);

    }
}