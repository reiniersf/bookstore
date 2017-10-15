package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.*;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;
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
    public List<DeliveryVoucher> listDocuments() {
        return accountingDocumentRepository.findAll();
    }

    @Transactional
    @Override
    public DeliveryVoucher registerAccountingDocumentForTransferLogs(List<TransferLog> transferLogs) {
        DeliveryVoucher deliveryVoucher = AccountingDocumentType.forTransfersLike(transferLogs.get(0)).createAccountingDocument();
        deliveryVoucher = accountingDocumentRepository.saveAccountingDocument(deliveryVoucher);
        deliveryVoucher.includeTransferLogs(transferLogs);
        deliveryVoucher = accountingDocumentRepository.updateAccountingDocument(deliveryVoucher);

        return deliveryVoucher;
    }

    @Transactional
    @Override
    public void completeDocument(DeliveryVoucher deliveryVoucher, AccountingInfo accountingInfo) {
        deliveryVoucher.includeAccountingInfo(accountingInfo);
        accountingDocumentRepository.updateAccountingDocument(deliveryVoucher);
    }

    @Override
    public DeliveryVoucher findDocumentWithConsecutive(Consecutive consecutive) {
        return accountingDocumentRepository.findByConsecutive(consecutive)
                .get();
    }


}
