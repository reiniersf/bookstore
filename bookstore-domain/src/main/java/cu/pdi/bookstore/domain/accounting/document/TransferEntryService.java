package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;

/**
 * Created by taiyou
 * on 8/31/17.
 */
public interface TransferEntryService {
    void logTransfer(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply);
}
