package cu.pdi.bookstore.domain.accounting.document.specs;

import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

public class ReceptionInformSpecification implements Specification<TransferLog> {
    @Override
    public boolean isSatisfiedBy(TransferLog transferLog) {
        return transferLog.getFrom().equals(DepartmentCode.WAREHOUSE_CODE);
    }

    public static ReceptionInformSpecification instance(){
        return new ReceptionInformSpecification();
    }
}
