package cu.pdi.bookstore.domain.accounting.reception;


import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentType;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentInfo;
import cu.pdi.bookstore.domain.accounting.document.AccountingInfoHolder;
import cu.pdi.bookstore.domain.accounting.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reception_report")
@DiscriminatorValue("2")
public class ReceptionReport extends DeliveryVoucher implements AccountingInfoHolder {

    @Column(name = "invoice_number")
    private InvoiceNumber invoiceNumber;
    @Embedded
    @AttributeOverride(name = "planName", column = @Column(name = "plan_name"))
    private Plan plan;
    @Embedded
    @AttributeOverride(name = "warehouseName", column = @Column(name = "warehouse_name"))
    private SourceWarehouse sourceWarehouse;

    public ReceptionReport() {
        super(AccountingDocumentType.RECEPTION_REPORT);
    }

    @Override
    public void includeAccountingInfo(AccountingDocumentInfo accountingDocumentInfo) {
        ReceptionReportInfo receptionReportInfo = ((ReceptionReportInfo) accountingDocumentInfo);
        this.invoiceNumber = receptionReportInfo.getInvoiceNumber();
        this.plan = receptionReportInfo.getPlan();
        this.sourceWarehouse = receptionReportInfo.getSourceWarehouse();
        this.consecutive = receptionReportInfo.getConsecutive();
    }
}
