package cu.pdi.bookstore.domain.inventory.title;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface TitleRepository {
    void saveTitle(Title titleForISBN);
}
