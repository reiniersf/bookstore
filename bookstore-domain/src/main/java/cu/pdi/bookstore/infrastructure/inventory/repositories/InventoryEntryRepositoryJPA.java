package cu.pdi.bookstore.infrastructure.inventory.repositories;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.department.InventoryEntry;
import cu.pdi.bookstore.domain.inventory.department.InventoryEntryRepository;
import cu.pdi.bookstore.domain.shared.ISBN;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 9/2/17.
 */
@Repository
public class InventoryEntryRepositoryJPA implements InventoryEntryRepository{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveInventoryEntry(InventoryEntry inventoryEntry) {
        entityManager.persist(inventoryEntry);
    }

    @Override
    public List<InventoryEntry> getEntriesForTitlesIn(Set<ISBN> isbnList, DepartmentCode departmentCode) {
        return entityManager
                .createNamedQuery("AllEntriesInTitleList", InventoryEntry.class)
                .setParameter("isbnList", isbnList.stream()
                        .map(ISBN::getCodigoISBN).collect(Collectors.toList()))
                .setParameter("departmentCode", departmentCode)
                .getResultList();
    }

    @Override
    public void updateInventoryEntry(InventoryEntry inventoryEntry) {
        entityManager.merge(inventoryEntry);
    }

}
