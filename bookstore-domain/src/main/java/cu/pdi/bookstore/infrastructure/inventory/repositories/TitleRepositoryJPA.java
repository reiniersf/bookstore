package cu.pdi.bookstore.infrastructure.inventory.repositories;

import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.inventory.title.TitleRepository;
import cu.pdi.bookstore.domain.shared.ISBN;
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
    public void saveTitle(Title titleForISBN) {
        entityManager.persist(titleForISBN);
    }

    @Override
    public List<Title> findRegisteredTitlesIn(Set<ISBN> supplyISBNList) {
        return entityManager.createQuery("select title from Title title where title.isbn in :isbnList", Title.class)
                .setParameter("isbnList", supplyISBNList)
                .getResultList();
    }
}
