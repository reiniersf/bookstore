package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;

import java.util.List;

public interface AccountingDocumentService {
    List<DeliveryVoucher> listDocuments();

    DeliveryVoucher registerAccountingDocumentForTransferLogs(List<TransferLog> transferLogs);

    void completeDocument(DeliveryVoucher deliveryVoucher, AccountingInfo accountingInfo);

    DeliveryVoucher findDocumentWithConsecutive(Consecutive consecutive);
}
