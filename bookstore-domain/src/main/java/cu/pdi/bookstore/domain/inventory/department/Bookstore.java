package cu.pdi.bookstore.domain.inventory.department;


import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Bookstore {
    private final DepartmentRepository departmentRepository;
    private DepartmentFactory departmentFactory;

    @Autowired
    public Bookstore(DepartmentRepository departmentRepository, DepartmentFactory departmentFactory) {
        this.departmentRepository = departmentRepository;
        this.departmentFactory = departmentFactory;
    }

    @Transactional
    public void enableDepartment(Department department) {
        departmentRepository.saveDepartment(department);
    }

    public List<Department> listEnableDepartments() {
        return departmentRepository.findAllDepartments();
    }

    public Department getDepartmentByCode(DepartmentCode departmentCode) {
        return departmentFactory.reassembleDepartment(
                departmentRepository.findDepartmentByCode(departmentCode));
    }
}

