package cu.pdi.bookstore.domain.accounting.document.transfer;

import cu.pdi.bookstore.domain.accounting.document.AccountingInfo;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;

public class DeliveryVoucherInfo extends AccountingInfo {
    public DeliveryVoucherInfo(Consecutive consecutive) {
        super(consecutive);
    }
}
