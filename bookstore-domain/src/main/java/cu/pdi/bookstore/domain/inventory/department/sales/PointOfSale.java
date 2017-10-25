package cu.pdi.bookstore.domain.inventory.department.sales;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.events.DepartmentEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PointOfSale {
    private final DepartmentEventHandler departmentEventHandler;

    @Autowired
    public PointOfSale(DepartmentEventHandler departmentEventHandler) {
        this.departmentEventHandler = departmentEventHandler;
    }

    public PointOfSaleDepartment atDepartment(Department department) {

        return new PointOfSaleDepartment(department, this.departmentEventHandler);
    }


}
