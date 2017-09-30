package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.*;
import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocalDocumentService implements AccountingDocumentService {

    private final AccountingDocumentRepository accountingDocumentRepository;

    @Autowired
    public LocalDocumentService(AccountingDocumentRepository accountingDocumentRepository) {
        this.accountingDocumentRepository = accountingDocumentRepository;
    }

    @Override
    public List<AccountingDocument> listDocuments() {
        return accountingDocumentRepository.findAll();
    }

    @Transactional
    @Override
    public AccountingDocument registerAccountingDocumentForTransferLogs(List<TransferLog> transferLogs) {
        AccountingDocument accountingDocument = AccountingDocumentFactory.createAccountingDocument(
                AccountingDocumentType.forTransfersLike(transferLogs.get(0)));
        accountingDocument = accountingDocumentRepository.saveAccountingDocument(accountingDocument);
        accountingDocument.includeTransferLogs(transferLogs);
        accountingDocument = accountingDocumentRepository.updateTransferLogs(accountingDocument);

        return accountingDocument;
    }


}
