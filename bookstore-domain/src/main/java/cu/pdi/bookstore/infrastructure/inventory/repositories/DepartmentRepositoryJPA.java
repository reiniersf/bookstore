package cu.pdi.bookstore.infrastructure.inventory.repositories;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 9/6/17.
 */
@Repository
public class DepartmentRepositoryJPA implements DepartmentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final DepartmentFactory departmentFactory;

    @Autowired
    public DepartmentRepositoryJPA(DepartmentFactory departmentFactory) {
        this.departmentFactory = departmentFactory;
    }

    @Override
    public void saveDepartment(Department department) {
        entityManager.persist(department);
    }

    @Override
    public Department findDepartmentByCode(DepartmentCode departmentCode) {
        return departmentFactory.reassembleDepartment(entityManager.find(Department.class, departmentCode));
    }

    @Override
    public List<Department> findAllDepartments() {
        return entityManager.createQuery("select d from Department d", Department.class)
                .getResultList()
                .stream()
                .map(departmentFactory::reassembleDepartment)
                .collect(Collectors.toList());
    }
}
