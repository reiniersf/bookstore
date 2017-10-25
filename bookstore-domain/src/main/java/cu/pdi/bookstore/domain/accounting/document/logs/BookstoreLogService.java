package cu.pdi.bookstore.domain.accounting.document.logs;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.title.TitleSale;
import cu.pdi.bookstore.domain.kernel.title.TitleSupply;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by taiyou
 * on 8/31/17.
 */
public interface BookstoreLogService {
    List<TransferLog> logTransfers(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply);
    List<TransferLog> logSales(DepartmentCode from, TitleSale titleSale, LocalDateTime soldAt);
}
