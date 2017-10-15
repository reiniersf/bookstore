package cu.pdi.bookstore.domain.accounting.document.logs;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

public class ATransferLog {
    public static TransferLog withInfo(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle) {
        return new TransferLog(from, to, isbn, stockForTitle);
    }
}
