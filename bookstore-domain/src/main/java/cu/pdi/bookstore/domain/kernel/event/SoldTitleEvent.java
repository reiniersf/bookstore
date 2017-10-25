package cu.pdi.bookstore.domain.kernel.event;

import cu.pdi.bookstore.domain.kernel.title.TitleSale;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public class SoldTitleEvent implements BookstoreEvent{
    @NonNull
    private DepartmentCode from;
    @NonNull
    private TitleSale titleSale;
    @NonNull
    LocalDateTime soldAt;
}
