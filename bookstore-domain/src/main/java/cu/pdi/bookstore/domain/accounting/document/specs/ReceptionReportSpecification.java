package cu.pdi.bookstore.domain.accounting.document.specs;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

public class ReceptionReportSpecification implements Specification<TransferLog> {
    @Override
    public boolean isSatisfiedBy(TransferLog transferLog) {
        return transferLog.getFrom().equals(DepartmentCode.WAREHOUSE_CODE);
    }

    public static ReceptionReportSpecification instance(){
        return new ReceptionReportSpecification();
    }
}
