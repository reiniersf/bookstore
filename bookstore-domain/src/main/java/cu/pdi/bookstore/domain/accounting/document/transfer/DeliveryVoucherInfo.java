package cu.pdi.bookstore.domain.accounting.document.transfer;

import cu.pdi.bookstore.domain.accounting.document.AccountingInfo;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;

public class DeliveryVoucherInfo extends AccountingInfo {
    protected DeliveryVoucherInfo(Consecutive consecutive) {
        super(consecutive);
    }
}
