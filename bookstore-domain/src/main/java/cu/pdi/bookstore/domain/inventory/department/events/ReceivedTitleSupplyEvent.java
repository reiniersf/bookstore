package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.event.BookstoreEvent;
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
    TitleSupply titleSupply;
}
