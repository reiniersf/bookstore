package cu.pdi.bookstore.domain.accounting.sales;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentInfo;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;
import lombok.Getter;

@Getter
public class SalesSummaryInfo extends AccountingDocumentInfo{

    private SaleVoucherNumber initialSaleVoucherNumber;
    private SaleVoucherNumber finalSaleVoucherNumber;

    public SalesSummaryInfo(Consecutive consecutive, SaleVoucherNumber initialSaleVoucherNumber, SaleVoucherNumber finalSaleVoucherNumber) {
        super(consecutive);
        this.initialSaleVoucherNumber = initialSaleVoucherNumber;
        this.finalSaleVoucherNumber = finalSaleVoucherNumber;
    }
}
