package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;

import java.util.List;

/**
 * Created by taiyou
 * on 9/6/17.
 */
public interface DepartmentRepository {
    void saveDepartment(Department department);

    Department findDepartmentByCode(DepartmentCode departmentCode);

    List<Department> findAllDepartments();

}
