package cu.pdi.bookstore.domain.inventory.supply;

import cu.pdi.bookstore.domain.inventory.department.Department;

/**
 * Created by taiyou
 * on 8/31/17.
 */
public interface TransferEntryService {
    void logTransfers(Department from, Department to, TitleSupply titleSupply);
}
