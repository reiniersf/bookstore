package cu.pdi.bookstore.domain.accounting.document.specs;

import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.specification.Specification;

public class SalesSummarySpecification implements Specification<TransferLog>{
    @Override
    public boolean isSatisfiedBy(TransferLog transferLog) {
        return transferLog.getTo().equals(DepartmentCode.SOLD);
    }

    public static SalesSummarySpecification instance(){
        return new SalesSummarySpecification();
    }
}
