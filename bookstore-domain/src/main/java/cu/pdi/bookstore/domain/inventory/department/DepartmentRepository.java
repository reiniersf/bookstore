package cu.pdi.bookstore.domain.inventory.department;

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
