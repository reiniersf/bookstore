package cu.pdi.bookstore.domain.accounting.document.transfer;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;

import java.util.List;

/**
 * Created by taiyou
 * on 8/31/17.
 */
public interface TransferLogService {
    List<TransferLog> logTransfers(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply);
}
