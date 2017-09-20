package cu.pdi.bookstore.domain.inventory.department;

import cu.pdi.bookstore.domain.kernel.event.BookstoreEvent;
import cu.pdi.bookstore.domain.kernel.event.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Component
public class DepartmentEventHandler implements EventHandler {
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public DepartmentEventHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void handle(BookstoreEvent bookstoreEvent) {
        applicationEventPublisher.publishEvent(bookstoreEvent);
    }
}
