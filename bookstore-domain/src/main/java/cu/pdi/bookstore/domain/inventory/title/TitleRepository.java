package cu.pdi.bookstore.domain.inventory.title;

import cu.pdi.bookstore.domain.kernel.ISBN;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface TitleRepository {
    void saveTitle(Title titleForISBN);

    List<Title> findRegisteredTitlesIn(Set<ISBN> supplyISBNList);
}
