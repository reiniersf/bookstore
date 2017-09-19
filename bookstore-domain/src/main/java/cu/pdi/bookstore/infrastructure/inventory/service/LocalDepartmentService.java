package cu.pdi.bookstore.infrastructure.inventory.service;

import cu.pdi.bookstore.domain.inventory.department.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by taiyou
 * on 9/6/17.
 */
@Service
public class LocalDepartmentService implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private DepartmentFactory departmentFactory;

    @Autowired
    public LocalDepartmentService(DepartmentRepository departmentRepository, DepartmentFactory departmentFactory) {
        this.departmentRepository = departmentRepository;
        this.departmentFactory = departmentFactory;
    }

    @Transactional
    @Override
    public void enableDepartment(Department department) {
        departmentRepository.saveDepartment(department);
    }

    @Override
    public List<Department> listEnableDepartments() {
        return departmentRepository.findAllDepartments();
    }

    @Override
    public Department getDepartmentByCode(DepartmentCode departmentCode) {
        return departmentFactory.createDepartmentFrom(
                departmentRepository.findDepartmentByCode(departmentCode));
    }
}
