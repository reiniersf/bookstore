package cu.pdi.bookstore.domain;

import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.domain.accounting.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentService;
import cu.pdi.bookstore.domain.accounting.document.logs.BookstoreLogService;
import cu.pdi.bookstore.domain.builders.TitleInfoBuilder;
import cu.pdi.bookstore.domain.builders.TitleSetFactory;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.events.DepartmentEventHandler;
import cu.pdi.bookstore.domain.inventory.department.events.AReceivedTitleSupplyEvent;
import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.Author;
import cu.pdi.bookstore.domain.inventory.title.EditionYear;
import cu.pdi.bookstore.domain.kernel.ISBN;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("dev")
public class AccountingDomainTest {

    @Autowired
    private DepartmentEventHandler departmentEventHandler;

    @Autowired
    private BookstoreLogService transferEntryService;

    @Autowired
    private AccountingDocumentService documentService;

    /**
     * A transfer is logged each time a INTERNAL department of the bookstore receive
     * a supply of books. As evidence of the transfer a document is created.
     */
    @Test
    public void shouldCreateADocumentWhenATransferHappen() {
        //GIVEN
        TitleSupply titleSupply = TitleSetFactory.createTitleSupplyForTitles(
                TitleInfoBuilder.createTitle()
                        .withISBN(ISBN.of("901238381293"))
                        .withDescription("A new way")
                        .editedInYear(new EditionYear(2000))
                        .build(),
                TitleInfoBuilder.createTitle()
                        .withDescription("A new word")
                        .writtenBy(new Author("Allens et al"))
                        .build());
        int currentDocumentAmount = documentService.listDocuments().size();
        //WHEN
        departmentEventHandler.notify(AReceivedTitleSupplyEvent.withInfo(
                DepartmentCode.BOOKDEPOT_CODE,
                DepartmentCode.SALESROOM_CODE,
                titleSupply
        ));
        //THEN
        List<DeliveryVoucher> deliveryVouchers = documentService.listDocuments();
        assertThat(deliveryVouchers).hasSize(currentDocumentAmount+1);

        DeliveryVoucher deliveryVoucher = deliveryVouchers.get(0);
        assertThat(deliveryVoucher.associatedTransferLogs()).hasSize(2);

    }



}
