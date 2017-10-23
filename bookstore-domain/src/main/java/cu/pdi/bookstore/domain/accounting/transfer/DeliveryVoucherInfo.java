package cu.pdi.bookstore.domain.accounting.transfer;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentInfo;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;

public class DeliveryVoucherInfo extends AccountingDocumentInfo {
    public DeliveryVoucherInfo(Consecutive consecutive) {
        super(consecutive);
    }
}
