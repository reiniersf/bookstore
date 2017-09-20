package cu.pdi.bookstore.domain.inventory.title;

import cu.pdi.bookstore.domain.kernel.ISBN;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface TitleService {

    void registerNewTitle(Title title);

    List<ISBN> getRegisteredTitlesIn(Set<ISBN> supplyISBNList);

    List<Title> getTitlesInfo(List<ISBN> isbnList);
}
