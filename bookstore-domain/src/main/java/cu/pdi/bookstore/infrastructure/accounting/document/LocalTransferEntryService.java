package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.accounting.document.TransferEntryService;
import org.springframework.stereotype.Service;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Service
public class LocalTransferEntryService implements TransferEntryService {
    @Override
    public void logTransfer(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply) {

    }
}
