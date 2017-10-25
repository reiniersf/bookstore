package cu.pdi.bookstore.domain.inventory.department.sales;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.events.ASoldTitleEvent;
import cu.pdi.bookstore.domain.inventory.department.events.DepartmentEventHandler;
import cu.pdi.bookstore.domain.inventory.department.specs.StockAvailabilitySpecification;
import cu.pdi.bookstore.domain.kernel.title.TitleSale;

import java.time.LocalDateTime;

public class PointOfSaleDepartment {

    private Department pointOfSaleDepartment;
    private DepartmentEventHandler departmentEventHandler;

    PointOfSaleDepartment(Department department, DepartmentEventHandler departmentEventHandler) {
        this.departmentEventHandler = departmentEventHandler;
        this.pointOfSaleDepartment = department;
    }

    public void registerSales(TitleSale titleSale) {
        if (StockAvailabilitySpecification.of(titleSale).isSatisfiedBy(pointOfSaleDepartment)) {
            this.pointOfSaleDepartment.decreaseDepartmentStock(titleSale);
            departmentEventHandler.notify(
                    ASoldTitleEvent.withInfo(pointOfSaleDepartment.getCode(), titleSale, LocalDateTime.now()));
        }

    }
}
