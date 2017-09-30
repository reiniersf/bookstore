package cu.pdi.bookstore.domain.accounting.document.specs;

import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

public class InternalTransferSpecification implements Specification<TransferLog>{
    @Override
    public boolean isSatisfiedBy(TransferLog transferLog) {
        return (transferLog.getFrom().equals(DepartmentCode.BOOKDEPOT_CODE)
                && transferLog.getTo().equals(DepartmentCode.SALESROOM_CODE))
                || (transferLog.getFrom().equals(DepartmentCode.SALESROOM_CODE)
                && transferLog.getTo().equals(DepartmentCode.BOOKDEPOT_CODE));
    }

    public static InternalTransferSpecification instance(){
        return new InternalTransferSpecification();
    }
}
