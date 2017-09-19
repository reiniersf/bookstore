package cu.pdi.bookstore.infrastructure.inventory.service;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryRepository;
import cu.pdi.bookstore.domain.inventory.department.entry.InventoryEntryService;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/4/17.
 */
@Service
public class LocalInventoryEntryService implements InventoryEntryService {

    private InventoryEntryRepository inventoryEntryRepository;

    @Autowired
    public LocalInventoryEntryService(InventoryEntryRepository inventoryEntryRepository){
        this.inventoryEntryRepository = inventoryEntryRepository;
    }

    @Transactional
    @Override
    public void saveEntryForNewTitle(InventoryEntry inventoryEntry) {
        inventoryEntryRepository.saveInventoryEntry(inventoryEntry);
    }

    @Override
    public List<InventoryEntry> searchEntriesForTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode) {
        return inventoryEntryRepository.getEntriesForTitlesIn(isbnList, departmentCode);
    }

    @Override
    public List<InventoryEntry> allEntriesInDepartment(DepartmentCode departmentCode) {
        return inventoryEntryRepository.getEntriesForDepartment(departmentCode);
    }

    @Override
    public boolean hasEntriesForAllTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode) {
        return inventoryEntryRepository.getEntriesForTitlesIn(isbnList, departmentCode).size() == isbnList.size();
    }

    @Transactional
    @Override
    public void increaseStock(InventoryEntry inventoryEntry, Stock stockForTitle) {
        inventoryEntryRepository.updateInventoryEntry(inventoryEntry.increaseStock(stockForTitle));
    }

    @Transactional
    @Override
    public void decreaseStock(InventoryEntry inventoryEntry, Stock stockForTitle) {
        inventoryEntryRepository.updateInventoryEntry(inventoryEntry.decreaseStock(stockForTitle));
    }
}
