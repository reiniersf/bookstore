package cu.pdi.bookstore.infrastructure;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentType;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.infrastructure.exceptions.InvalidTransferException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

public class AccountingInfrastructureTest {


    @Test
    public void shouldSelect_INTERNAL_TRANSFER_AccountingDocumentType() {
        //GIVEN
        TransferLog transferLog_INTERNAL = TransferLog.builder()
                .from(DepartmentCode.BOOKDEPOT_CODE)
                .to(DepartmentCode.SALESROOM_CODE)
                .build();

        //WHEN
        AccountingDocumentType accountingDocumentType_INTERNAL = AccountingDocumentType.forTransfersLike(transferLog_INTERNAL);

        //THEN
        assertThat(accountingDocumentType_INTERNAL).isEqualTo(AccountingDocumentType.DELIVERY_VOUCHER);
    }

    @Test
    public void shouldSelect_RECEPTION_AccountingDocumentType() {
        //GIVEN
        TransferLog transferLog_RECEPTION = TransferLog.builder()
                .from(DepartmentCode.WAREHOUSE_CODE)
                .to(DepartmentCode.BOOKDEPOT_CODE)
                .build();
        //WHEN
        AccountingDocumentType accountingDocumentType_RECEPTION = AccountingDocumentType.forTransfersLike(transferLog_RECEPTION);

        //THEN
        assertThat(accountingDocumentType_RECEPTION).isEqualTo(AccountingDocumentType.RECEPTION_REPORT);
    }

    @Test
    public void shouldSelect_SALES_AccountingDocumentType() {
        //GIVEN
        TransferLog transferLog_SALES = TransferLog.builder()
                .from(DepartmentCode.SALESROOM_CODE)
                .to(DepartmentCode.SOLD)
                .build();

        //WHEN
        AccountingDocumentType accountingDocumentType_SALES = AccountingDocumentType.forTransfersLike(transferLog_SALES);

        //THEN
        assertThat(accountingDocumentType_SALES).isEqualTo(AccountingDocumentType.SALES_SUMMARY);
    }

    @Test
    public void shouldThrowInvalidTransferExceptionForInvalidOriginNDestinationDepartmentCombination() {
        //GIVEN
        TransferLog transferLog_INVALID = TransferLog.builder()
                .from(DepartmentCode.SALESROOM_CODE)
                .to(DepartmentCode.WAREHOUSE_CODE)
                .build();

        //THEN
        assertThatExceptionOfType(InvalidTransferException.class).isThrownBy(()-> AccountingDocumentType.forTransfersLike(transferLog_INVALID));
    }

}
