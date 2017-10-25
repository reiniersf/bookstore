package cu.pdi.bookstore.domain.accounting.sales;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentInfo;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentType;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.transfer.DeliveryVoucher;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "sales_summary")
@DiscriminatorValue("1")
public class SalesSummary extends DeliveryVoucher {

    @Embedded
    @AttributeOverride(name = "saleVoucherNumber",
            column = @Column(name = "initial_sale_voucher_number", unique = true))
    private SaleVoucherNumber initialSaleVoucherNumber;
    @Embedded
    @AttributeOverride(name = "saleVoucherNumber",
            column = @Column(name = "final_sale_voucher_number", unique = true))
    private SaleVoucherNumber finalSaleVoucherNumber;
    @Column(name = "total_sales_income")
    private Double totalSalesIncome;


    public SalesSummary() {
        super(AccountingDocumentType.SALES_SUMMARY);
    }


    @Override
    public void includeTransferLogs(List<TransferLog> transferLogs) {
        super.includeTransferLogs(transferLogs);
        totalSalesIncome = transferLogs.stream()
                .mapToDouble(TransferLog::saleIncome)
                .sum();
    }

    @Override
    public void includeAccountingInfo(AccountingDocumentInfo accountingDocumentInfo) {
        SalesSummaryInfo salesSummaryInfo = (SalesSummaryInfo) accountingDocumentInfo;

        this.consecutive = salesSummaryInfo.getConsecutive();
        this.initialSaleVoucherNumber = salesSummaryInfo.getInitialSaleVoucherNumber();
        this.finalSaleVoucherNumber = salesSummaryInfo.getFinalSaleVoucherNumber();
    }
}
