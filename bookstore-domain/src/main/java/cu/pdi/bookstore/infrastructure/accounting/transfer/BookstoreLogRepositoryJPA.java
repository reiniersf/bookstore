package cu.pdi.bookstore.infrastructure.accounting.transfer;

import cu.pdi.bookstore.domain.accounting.document.logs.ATransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.BookstoreLogRepository;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookstoreLogRepositoryJPA implements BookstoreLogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private final ATransferLog aTransferLog;

    @Autowired
    public BookstoreLogRepositoryJPA(ATransferLog aTransferLog) {
        this.aTransferLog = aTransferLog;
    }

    @Override
    public TransferLog logTransfer(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle) {
        TransferLog transferLog = aTransferLog.withInfo(from, to, isbn, stockForTitle);
        entityManager.persist(transferLog);
        return transferLog;
    }
}
