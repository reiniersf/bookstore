package cu.pdi.bookstore.domain;

import cu.pdi.bookstore.config.AppConfig;
import cu.pdi.bookstore.domain.inventory.department.Bookstore;
import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;
import cu.pdi.bookstore.domain.inventory.department.DepartmentRepository;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static cu.pdi.bookstore.domain.assertions.InventoryAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("dev")
public class BookstoreTest {

    @Autowired
    private DepartmentFactory departmentFactory;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Bookstore bookstore;


    /**
     * This happen when a new place is ready to receive books.
     * It should be created and added to the Department list, identified by its department code.
     */
    @Test
    public void shouldEnableANewDepartment() {
        //GIVEN
        Department bookDepot = departmentFactory.createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Book Depot");
        //WHEN
        bookstore.enableDepartment(bookDepot);
        //THEN
        assertThat(departmentRepository.findDepartmentByCode(bookDepot.getCode())).isNotNull();
    }
}
