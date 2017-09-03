package cu.pdi.bookstore.domain;

import cu.pdi.bookstore.config.AppConfig;
import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.shared.ISBN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static cu.pdi.bookstore.domain.assertions.InventoryAssert.*;

/**
 * Created by taiyou
 * on 8/28/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class InventoryDomainTest {
    @Autowired
    private DepartmentFactory departmentFactory;

    /**
     * This happen when Book Depot department is supplied by the main Warehouse,
     * the supply comes with an amount of books for each Title and Titles info.
     * As a result each title get its own Inventory Entry and Transfer Entry
     * in Book Depot department depending on if the Title is new or has a previous inventory entry.
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromMainWarehouse() {
        //GIVEN
        Department bookDepot = departmentFactory
                .createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Books Depot");
        Department warehouse = DepartmentFactory.WAREHOUSE;
        TitleSupply titleSupply = new TitleSupply();
        titleSupply.includeTitle(Title.builder().isbn(new ISBN("90238127823")).build(), 5);
        titleSupply.includeTitle(Title.builder().isbn(new ISBN("937238292201")).build(), 7);

        //WHEN
        bookDepot.receiveTitles(warehouse, titleSupply);

        //THEN
        assertThat(bookDepot).hasInventoryEntriesForAllTitles(titleSupply);

    }

    /**
     * This happen when Book Depot department supply the Sales Room department,
     * the supply comes with an amount of books for each Title and Titles info.
     * As a result each title get its own Inventory Entry and Transfer Entry
     * in Sales Room department depending on if the Title is new or has a previous inventory entry,
     * also Book Depot department must update stock for each Title transferred.
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromBookDepot() {
        //GIVEN
        Department bookDepot = departmentFactory
                .createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Books Depot");
        Department salesRoom = departmentFactory
                .createDepartment(DepartmentCode.SALESROOM_CODE, "Books Depot");;
        TitleSupply titleSupply = new TitleSupply();
        titleSupply.includeTitle(Title.builder().isbn(new ISBN("90238127823")).build(), 5);
        titleSupply.includeTitle(Title.builder().isbn(new ISBN("937238292201")).build(), 7);

        bookDepot.receiveTitles(DepartmentFactory.WAREHOUSE, titleSupply);

        //WHEN
        salesRoom.receiveTitles(bookDepot, titleSupply);

        //THEN
        assertThat(bookDepot).hasInventoryEntriesForAllTitles(titleSupply);
        assertThat(salesRoom).hasInventoryEntriesForAllTitles(titleSupply);
        assertThat(bookDepot).hasEmptiedStockForSuppliedTitles(titleSupply);


    }
}
