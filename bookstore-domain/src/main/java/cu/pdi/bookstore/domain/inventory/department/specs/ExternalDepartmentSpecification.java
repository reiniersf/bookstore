package cu.pdi.bookstore.domain.inventory.department.specs;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public class ExternalDepartmentSpecification implements Specification<Department> {

    public static ExternalDepartmentSpecification instance() {
        return new ExternalDepartmentSpecification();
    }

    @Override
    public boolean isSatisfiedBy(Department department) {
        return department.equals(DepartmentFactory.WAREHOUSE);
    }

}
