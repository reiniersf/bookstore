package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface InventoryEntryRepository {

    void saveEntryForNewTitle(ISBN isbn, Stock stockForTitle, DepartmentCode code);

    boolean hasEntriesForAllTitlesIn(Set<ISBN> isbnList);

    List<InventoryEntry> searchExistentEntriesAmong(Set<ISBN> isbnList);

    List<InventoryEntry> getEntriesForTitlesIn(Set<ISBN> isbnList);

    void increaseStock(InventoryEntry inventoryEntry, Stock stockForTitle);

    void decreaseStock(InventoryEntry inventoryEntry, Stock stockForTitle);

}

