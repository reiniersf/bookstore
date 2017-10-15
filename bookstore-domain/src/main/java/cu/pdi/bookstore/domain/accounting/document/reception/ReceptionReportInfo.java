package cu.pdi.bookstore.domain.accounting.document.reception;

import cu.pdi.bookstore.domain.accounting.document.AccountingInfo;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@Embeddable
public class ReceptionReportInfo extends AccountingInfo{

    private final InvoiceNumber invoiceNumber;
    private final Plan plan;
    private final SourceWarehouse sourceWarehouse;

    public ReceptionReportInfo(Consecutive consecutive, InvoiceNumber invoiceNumber, Plan plan, SourceWarehouse sourceWarehouse) {
        super(consecutive);
        this.invoiceNumber = invoiceNumber;
        this.plan = plan;
        this.sourceWarehouse = sourceWarehouse;
    }


}
