package cu.pdi.bookstore.domain.accounting.document;


import java.util.List;

public interface AccountingDocumentRepository {
    List<AccountingDocument> findAll();

    AccountingDocument saveAccountingDocument(AccountingDocument accountingDocument);

    AccountingDocument updateTransferLogs(AccountingDocument accountingDocument);
}
