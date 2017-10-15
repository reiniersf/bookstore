package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.reception.InvoiceNumber;
import cu.pdi.bookstore.domain.accounting.document.reception.ReceptionReportInfo;
import cu.pdi.bookstore.domain.accounting.document.reception.SourceWarehouse;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.Getter;

@Getter
public abstract class AccountingInfo {

    private Consecutive consecutive;

    protected AccountingInfo(Consecutive consecutive){
        this.consecutive = consecutive;
    }

    public static AccountingInfo forReceptionReport(Consecutive consecutive, InvoiceNumber invoiceNumber, Plan plan, SourceWarehouse sourceWarehouse) {
        return new ReceptionReportInfo(consecutive, invoiceNumber, plan, sourceWarehouse);
    }
}
