package cu.pdi.bookstore.domain;

import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.domain.builders.TitleInfoBuilder;
import cu.pdi.bookstore.domain.builders.TitleSupplyFactory;
import cu.pdi.bookstore.domain.inventory.department.*;
import cu.pdi.bookstore.domain.inventory.department.sheet.InventorySheet;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.Author;
import cu.pdi.bookstore.domain.inventory.title.Category;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static cu.pdi.bookstore.domain.assertions.InventoryAssert.assertThat;


/**
 * Created by taiyou
 * on 8/28/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("dev")
public class InventoryDomainTest {

    @Autowired
    private DepartmentFactory departmentFactory;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private Bookstore bookstore;

    /**
     * This happen when Book Depot department is supplied by the main Warehouse,
     * the supply comes with an amount of books for each TitleInventoryInfo and Titles info.
     * As a result each title get its own Inventory Entry and Transfer Entry
     * in Book Depot department depending on if the TitleInventoryInfo is new or has a previous inventory entry.
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromMainWarehouse() {
        //GIVEN
        Department bookDepot = departmentFactory
                .createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Books Depot");
        Department warehouse = DepartmentFactory.WAREHOUSE;
        TitleSupply titleSupply = TitleSupplyFactory.createTitleSupplyForTitles(
                TitleInfoBuilder.createTitle().withISBN(new ISBN("90238127823"))
                .withDescription("The Hollow")
                .build(),
                TitleInfoBuilder.createTitle().withISBN(new ISBN("937238292201"))
                .withDescription("The Lighter")
                .build()
        );

        //WHEN
        bookDepot.receiveTitles(warehouse, titleSupply);

        //THEN
        assertThat(bookDepot).hasInventoryEntriesForAllTitles(titleSupply);

    }

    /**
     * This happen when Book Depot department supply the Sales Room department,
     * the supply comes with an amount of books for each TitleInventoryInfo and Titles info.
     * As a result each title get its own Inventory Entry and Transfer Entry
     * in Sales Room department depending on if the TitleInventoryInfo is new or has a previous inventory entry,
     * also Book Depot department must update stock for each TitleInventoryInfo transferred.
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromBookDepot() {
        //GIVEN
        Department bookDepot = departmentFactory
                .createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Books Depot");
        Department salesRoom = departmentFactory
                .createDepartment(DepartmentCode.SALESROOM_CODE, "Sales Room");
        TitleSupply titleSupply = TitleSupplyFactory.createTitleSupplyForTitles(
                TitleInfoBuilder.createTitle().withISBN(new ISBN("90230007823"))
                        .withDescription("The Book")
                        .build(),
                TitleInfoBuilder.createTitle().withISBN(new ISBN("93720002201"))
                        .withDescription("The Newspaper")
                        .build()
        );

        bookDepot.receiveTitles(DepartmentFactory.WAREHOUSE, titleSupply);

        //WHEN
        salesRoom.receiveTitles(bookDepot, titleSupply);

        //THEN
        assertThat(salesRoom).hasInventoryEntriesForAllTitles(titleSupply);
        assertThat(bookDepot).hasEmptiedStockForSuppliedTitles(titleSupply);

    }

    /**
     * This happen when Sales Room department supply in a devolution way the Book Depot department,
     * the supply comes with an amount of books for each TitleInventoryInfo.
     * As a result each title get a Transfer Entry from Sales Room department to Book Depot department
     * and update stock for each TitleInventoryInfo transferred.
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromSalesRoom() {
        //GIVEN
        Department bookDepot = departmentFactory
                .createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Books Depot");
        Department salesRoom = departmentFactory
                .createDepartment(DepartmentCode.SALESROOM_CODE, "Sales Room");
        TitleSupply titleSupply = TitleSupplyFactory.createTitleSupplyForTitles(
                TitleInfoBuilder.createTitle().withISBN(new ISBN("90230707823"))
                        .withDescription("The Moon")
                        .build(),
                TitleInfoBuilder.createTitle().withISBN(new ISBN("93720702201"))
                        .withDescription("The Sun")
                        .build()
        );

        bookDepot.receiveTitles(DepartmentFactory.WAREHOUSE, titleSupply);
        salesRoom.receiveTitles(bookDepot, titleSupply);
        //WHEN
        bookDepot.receiveTitles(salesRoom, titleSupply);

        //THEN
        assertThat(bookDepot).hasInventoryEntriesForAllTitles(titleSupply);
        assertThat(salesRoom).hasEmptiedStockForSuppliedTitles(titleSupply);

    }

    @Test
    public void shouldRetrieveTheInventorySheet() {

        //GIVEN
        DepartmentCode newDepartmentCode = DepartmentCode.forCode("04");
        bookstore.enableDepartment(departmentFactory.createDepartment(newDepartmentCode, "Coffee Saloon"));
        Department coffeeSaloon = bookstore.getDepartmentByCode(newDepartmentCode);
        TitleSupply titleSupply = TitleSupplyFactory.createTitleSupplyForTitles(
                TitleInfoBuilder.createTitle().withISBN(new ISBN("90231707823"))
                        .withDescription("Who's afraid of the wolf")
                        .inCategory(new Category("Infantil"))
                        .build(),
                TitleInfoBuilder.createTitle().withISBN(new ISBN("93721702201"))
                        .withDescription("A great reward")
                        .writtenBy(new Author("Finn Clive, Sarah Monk"))
                        .inCategory(new Category("Policial"))
                        .build()
        );

        coffeeSaloon.receiveTitles(DepartmentFactory.WAREHOUSE, titleSupply);
        //WHEN
        InventorySheet inventorySheet= coffeeSaloon.generateCurrentInventorySheet();
        //THEN
        Assertions.assertThat(inventorySheet).isNotNull();
        Assertions.assertThat(inventorySheet.entries()).hasSize(2);

    }
}
