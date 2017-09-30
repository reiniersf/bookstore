package cu.pdi.bookstore.infrastructure.accounting.transfer;

import cu.pdi.bookstore.domain.accounting.document.transfer.ATransferLog;
import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLogRepository;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TransferLogRepositoryJPA implements TransferLogRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public TransferLog logTransfer(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle) {
        TransferLog transferLog = ATransferLog.withInfo(from, to, isbn, stockForTitle);
        entityManager.persist(transferLog);
        return transferLog;
    }
}
