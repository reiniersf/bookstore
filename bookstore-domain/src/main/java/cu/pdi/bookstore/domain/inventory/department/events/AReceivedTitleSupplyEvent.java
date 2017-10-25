package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.event.ReceivedTitleSupplyEvent;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public class AReceivedTitleSupplyEvent {
    public static ReceivedTitleSupplyEvent withInfo(DepartmentCode from, DepartmentCode to, TitleSupply titles){
        return new ReceivedTitleSupplyEvent(from, to, titles);
    }
}
