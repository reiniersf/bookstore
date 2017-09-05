package cu.pdi.bookstore.domain.shared.event;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public interface EventHandler {
    void handle(BookstoreEvent bookstoreEvent);
}
