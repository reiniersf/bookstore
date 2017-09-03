package cu.pdi.bookstore.domain.shared.specification;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public class ExternalDepartmentSpecification implements Specification<Department> {

    public static ExternalDepartmentSpecification INSTANCE = new ExternalDepartmentSpecification();

    @Override
    public boolean isSatisfiedBy(Department department) {
        return department.equals(DepartmentFactory.WAREHOUSE);
    }

}
