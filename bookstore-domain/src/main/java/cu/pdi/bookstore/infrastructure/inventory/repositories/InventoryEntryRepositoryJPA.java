package cu.pdi.bookstore.infrastructure.inventory.repositories;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.department.InventoryEntryRepository;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/2/17.
 */
@Repository
public class InventoryEntryRepositoryJPA implements InventoryEntryRepository{


    private EntityManagerFactory entityManagerFactory;

    public InventoryEntryRepositoryJPA(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void saveEntryForNewTitle(ISBN isbn, Stock stockForTitle, DepartmentCode code) {

    }

    @Override
    public boolean hasEntriesForAllTitlesIn(Set<ISBN> isbnList) {
        return false;
    }

    @Override
    public List<InventoryEntry> searchExistentEntriesAmong(Set<ISBN> isbnList) {
        return new ArrayList<>();
    }

    @Override
    public List<InventoryEntry> getEntriesForTitlesIn(Set<ISBN> isbnList) {
        return null;
    }

    @Override
    public void increaseStock(InventoryEntry inventoryEntry, Stock stockForTitle) {

    }

    @Override
    public void decreaseStock(InventoryEntry inventoryEntry, Stock stockForTitle) {

    }
}
