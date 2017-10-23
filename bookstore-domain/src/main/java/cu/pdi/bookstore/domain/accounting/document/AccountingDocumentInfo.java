package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.reception.InvoiceNumber;
import cu.pdi.bookstore.domain.accounting.reception.ReceptionReportInfo;
import cu.pdi.bookstore.domain.accounting.reception.SourceWarehouse;
import cu.pdi.bookstore.domain.accounting.transfer.DeliveryVoucherInfo;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.Getter;

@Getter
public abstract class AccountingDocumentInfo {

    private Consecutive consecutive;

    protected AccountingDocumentInfo(Consecutive consecutive){
        this.consecutive = consecutive;
    }

    public static AccountingDocumentInfo forReceptionReport(Consecutive consecutive, InvoiceNumber invoiceNumber, Plan plan, SourceWarehouse sourceWarehouse) {
        return new ReceptionReportInfo(consecutive, invoiceNumber, plan, sourceWarehouse);
    }

    public static AccountingDocumentInfo forDeliveryVoucher(Consecutive consecutive) {
        return new DeliveryVoucherInfo(consecutive);
    }
}
