package cu.pdi.bookstore.domain.kernel.event;

import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

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
