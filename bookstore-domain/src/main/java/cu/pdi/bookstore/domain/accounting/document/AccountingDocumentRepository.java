package cu.pdi.bookstore.domain.accounting.document;


import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;

import java.util.List;
import java.util.Optional;

public interface AccountingDocumentRepository {
    List<DeliveryVoucher> findAll();

    DeliveryVoucher saveAccountingDocument(DeliveryVoucher deliveryVoucher);

    DeliveryVoucher updateAccountingDocument(DeliveryVoucher deliveryVoucher);


    Optional<DeliveryVoucher> findByConsecutive(Consecutive consecutive);
}
