package cu.pdi.bookstore.domain.accounting.document.logs;


import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

/**
 * Created by taiyou
 * on 8/29/17.
 */
public interface TransferLogRepository {
    TransferLog logTransfer(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle);
}
