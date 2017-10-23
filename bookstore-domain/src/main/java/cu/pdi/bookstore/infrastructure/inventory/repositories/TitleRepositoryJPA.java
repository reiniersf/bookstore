package cu.pdi.bookstore.infrastructure.inventory.repositories;

import cu.pdi.bookstore.domain.accounting.title.TitleAccountingInfo;
import cu.pdi.bookstore.domain.inventory.title.TitleInventoryInfo;
import cu.pdi.bookstore.domain.inventory.title.TitleRepository;
import cu.pdi.bookstore.domain.kernel.ISBN;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Repository
public class TitleRepositoryJPA implements TitleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TitleInventoryInfo> findRegisteredTitlesIn(Set<ISBN> supplyISBNList) {
        return entityManager.createQuery("select title from TitleInventoryInfo title where title.isbn in :isbnList", TitleInventoryInfo.class)
                .setParameter("isbnList", supplyISBNList)
                .getResultList();
    }

    @Override
    public void saveInventoryInfo(TitleInventoryInfo titleInventoryInfo) {
        entityManager.persist(titleInventoryInfo);
    }

    @Override
    public void saveAccountingInfo(TitleAccountingInfo titleAccountingInfo) {
        entityManager.persist(titleAccountingInfo);
    }
}
