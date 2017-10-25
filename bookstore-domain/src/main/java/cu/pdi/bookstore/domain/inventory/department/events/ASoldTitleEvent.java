package cu.pdi.bookstore.domain.inventory.department.events;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.event.BookstoreEvent;
import cu.pdi.bookstore.domain.kernel.event.SoldTitleEvent;
import cu.pdi.bookstore.domain.kernel.title.TitleSale;

import java.time.LocalDateTime;

public class ASoldTitleEvent {
    public static SoldTitleEvent withInfo(DepartmentCode code, TitleSale titleSale, LocalDateTime soldAt) {
        return new SoldTitleEvent(code, titleSale, soldAt);
    }
}
