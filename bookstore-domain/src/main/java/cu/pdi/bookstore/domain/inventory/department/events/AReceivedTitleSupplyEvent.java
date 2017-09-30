package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;

import java.util.Set;

/**
 * Created by taiyou
 * on 9/3/17.
 */
public class AReceivedTitleSupplyEvent {
    public static ReceivedTitleSupplyEvent withInfo(DepartmentCode from, DepartmentCode to, TitleSupply titles){
        return new ReceivedTitleSupplyEvent(from, to, titles);
    }
}
