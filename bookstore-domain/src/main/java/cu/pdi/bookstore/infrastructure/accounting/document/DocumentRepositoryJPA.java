package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocument;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DocumentRepositoryJPA implements AccountingDocumentRepository {

    @PersistenceContext private EntityManager entityManager;

    @Override
    public List<AccountingDocument> findAll() {
        return entityManager.createQuery("SELECT d FROM AccountingDocument d", AccountingDocument.class).getResultList();
    }

    @Override
    public AccountingDocument saveAccountingDocument(AccountingDocument accountingDocument) {
        entityManager.persist(accountingDocument);
        return accountingDocument;
    }

    @Override
    public AccountingDocument updateTransferLogs(AccountingDocument accountingDocument) {
        return entityManager.merge(accountingDocument);
    }
}
