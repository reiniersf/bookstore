package cu.pdi.bookstore.domain.inventory.department;

import java.util.List;

/**
 * Created by taiyou
 * on 9/6/17.
 */
public interface DepartmentService {
    void enableDepartment(Department department);

    List<Department> listEnableDepartments();

    Department getDepartmentByCode(DepartmentCode departmentCode);
}
