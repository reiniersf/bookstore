package cu.pdi.bookstore.infrastructure.inventory.service;

import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.supply.TransferEntryService;
import org.springframework.stereotype.Service;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Service
public class LocalTransferEntryService implements TransferEntryService {
    @Override
    public void logTransfers(Department from, Department to, TitleSupply titleSupply) {

    }
}
