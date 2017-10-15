package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.Consecutive;
import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Repository
public class DocumentRepositoryJPA implements AccountingDocumentRepository {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<DeliveryVoucher> findAll() {
        return entityManager.createQuery("SELECT d FROM DeliveryVoucher d", DeliveryVoucher.class).getResultList();
    }

    @Override
    public DeliveryVoucher saveAccountingDocument(DeliveryVoucher deliveryVoucher) {
        entityManager.persist(deliveryVoucher);
        return deliveryVoucher;
    }

    @Override
    public DeliveryVoucher updateAccountingDocument(DeliveryVoucher deliveryVoucher) {
        return entityManager.merge(deliveryVoucher);
    }

    @Override
    public Optional<DeliveryVoucher> findByConsecutive(Consecutive consecutive) {
        List<DeliveryVoucher> deliveryVouchers = entityManager
                .createQuery("SELECT d FROM DeliveryVoucher d WHERE d.consecutive = :consecutive", DeliveryVoucher.class)
                .setParameter("consecutive", consecutive)
                .getResultList();

        return Optional.of(deliveryVouchers.get(0));

    }


}
