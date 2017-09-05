package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.event.BookstoreEvent;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Getter
@RequiredArgsConstructor
public class ReceivedTitleSupplyEvent implements BookstoreEvent{
    @NonNull
    DepartmentCode from;
    @NonNull
    DepartmentCode to;
    @NonNull
    Set<ISBN> isbnList;
}
