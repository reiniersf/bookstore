package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;

import java.util.Set;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public class AReceivedTitleSupplyEvent {
    public static ReceivedTitleSupplyEvent withInfo(DepartmentCode from, DepartmentCode to, Set<ISBN> titles){
        return new ReceivedTitleSupplyEvent(from, to, titles);
    }
}
