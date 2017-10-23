package cu.pdi.bookstore.infrastructure;

import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.domain.inventory.department.*;
import static org.assertj.core.api.Assertions.*;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by taiyou
 * on 9/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class InventoryInfrastructureTest {

    @Autowired
    private DepartmentFactory departmentFactory;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Bookstore bookstore;

    @Test
    public void shouldReconstituteDepartmentWithAllDependencies(){
        //GIVEN
        Department bookDepot = departmentFactory.createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Book Depot");
        bookstore.enableDepartment(bookDepot);

        //WHEN
        Department restoredDepartmentThroughService = bookstore.getDepartmentByCode(DepartmentCode.BOOKDEPOT_CODE);
        Department restoredDepartment = departmentFactory.reassembleDepartment(
                departmentRepository.findDepartmentByCode(DepartmentCode.BOOKDEPOT_CODE));

        //THEN
        assertThat(restoredDepartment).hasNoNullFieldsOrProperties();
        assertThat(restoredDepartmentThroughService).hasNoNullFieldsOrProperties();

    }

}
