package cu.pdi.bookstore.domain.inventory.title;

import cu.pdi.bookstore.domain.accounting.title.TitleAccountingInfo;
import cu.pdi.bookstore.domain.kernel.ISBN;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface TitleRepository {

    List<TitleInventoryInfo> findRegisteredTitlesIn(Set<ISBN> supplyISBNList);

    void saveInventoryInfo(TitleInventoryInfo titleInventoryInfo);

    void saveAccountingInfo(TitleAccountingInfo titleAccountingInfo);
}
