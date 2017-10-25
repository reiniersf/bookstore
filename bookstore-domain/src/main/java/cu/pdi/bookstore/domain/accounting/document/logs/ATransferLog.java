package cu.pdi.bookstore.domain.accounting.document.logs;

import cu.pdi.bookstore.domain.accounting.sales.IncomeCalculator;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ATransferLog {
    final
    IncomeCalculator saleIncomeCalculator;

    @Autowired
    public ATransferLog(IncomeCalculator saleIncomeCalculator) {
        this.saleIncomeCalculator = saleIncomeCalculator;
    }

    public TransferLog withInfo(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle) {
        return new TransferLog(from, to, isbn, stockForTitle, saleIncomeCalculator);
    }
}
