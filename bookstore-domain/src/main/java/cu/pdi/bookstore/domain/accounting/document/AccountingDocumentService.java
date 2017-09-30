package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;

import java.util.List;

public interface AccountingDocumentService {
    List<AccountingDocument> listDocuments();
    AccountingDocument registerAccountingDocumentForTransferLogs(List<TransferLog> transferLogs);
}
