package cu.pdi.bookstore.domain.accounting.sales;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;

public interface IncomeCalculator {

    double ofTransfer(TransferLog transferLog);
}
