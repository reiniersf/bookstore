package cu.pdi.bookstore.domain;

import cu.pdi.bookstore.config.AppConfig;
import cu.pdi.bookstore.domain.accounting.document.*;
import cu.pdi.bookstore.domain.accounting.document.reception.InvoiceNumber;
import cu.pdi.bookstore.domain.accounting.document.reception.ReceptionReport;
import cu.pdi.bookstore.domain.accounting.document.reception.SourceWarehouse;
import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.builders.TitleBuilder;
import cu.pdi.bookstore.domain.builders.TitleSupplyFactory;
import cu.pdi.bookstore.domain.inventory.department.Bookstore;
import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentFactory;
import cu.pdi.bookstore.domain.inventory.department.DepartmentRepository;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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

    @Autowired
    private AccountingDocumentService documentService;


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

    /**
     * This happen when Book Depot department is supplied by the main Warehouse,
     * the supply comes forReceptionReport an amount of books for each Title and Titles info.
     * As a result each title get its own Inventory Entry and Transfer Entry
     * in Book Depot department depending on if the Title is new or has a previous inventory entry.
     * Beside a Reception Report is created as evidence of the reception at the Bookstore
     */
    @Test
    public void shouldReceiveASupplyOfTitlesFromMainWarehouse() {
        //GIVEN
        Department bookDepot = departmentFactory.createDepartment(DepartmentCode.BOOKDEPOT_CODE, "Book Depot");
        bookstore.enableDepartment(bookDepot);

        Department warehouse = DepartmentFactory.WAREHOUSE;
        TitleSupply titleSupply = TitleSupplyFactory.createTitleSupplyForTitles(
                TitleBuilder.createTitle().withISBN(new ISBN("90238127823"))
                        .withDescription("The Hollow")
                        .build(),
                TitleBuilder.createTitle().withISBN(new ISBN("937238292201"))
                        .withDescription("The Lighter")
                        .build()
        );
        int lastDocumentIndex, currentDocumentAmount;
        lastDocumentIndex = currentDocumentAmount = documentService.listDocuments().size();

        //WHEN
        bookDepot.receiveTitles(warehouse, titleSupply);

        //THEN
        assertThat(bookDepot).hasInventoryEntriesForAllTitles(titleSupply);

        List<DeliveryVoucher> deliveryVouchers = documentService.listDocuments();
        Assertions.assertThat(deliveryVouchers).hasSize(currentDocumentAmount + 1);

        DeliveryVoucher receptionReport = deliveryVouchers.get(lastDocumentIndex);
        Assertions.assertThat(receptionReport.associatedTransferLogs()).hasSize(2);
        Assertions.assertThat(receptionReport).isInstanceOf(ReceptionReport.class);
        Assertions.assertThat(receptionReport.getAccountingDocumentType()).isEqualTo(AccountingDocumentType.RECEPTION_REPORT);

        //AND WHEN
        documentService.completeDocument(receptionReport,
                AccountingInfo.forReceptionReport(Consecutive.of("1"),
                        InvoiceNumber.of("21938123"),
                        Plan.withName("Regular"),
                        SourceWarehouse.withName("Central")));
        //THEN
        receptionReport = documentService.findDocumentWithConsecutive(receptionReport.getConsecutive());
        Assertions.assertThat(((ReceptionReport) receptionReport).getInvoiceNumber()).isEqualTo(InvoiceNumber.of("21938123"));
    }


    /**
     * This happen when Sales Room department is supplied by the Book Depot department or vice versa,
     * the supply comes with an amount of books for each Title and Titles info.
     * As a result each title get its own Transfer Entry
     * in Sales Room and Inventory Entry department depending on if the Title is new or has a previous inventory entry.
     * Beside a Delivery Voucher is created as evidence of the transfer between departments
     */
    @Test
    public void shouldTransferTitlesFromOneDepartmentToAnother() {


    }

    @Test
    public void shouldRegisterSoldTitlesFromSalesDepartment() {


    }
}
