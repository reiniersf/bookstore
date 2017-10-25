package cu.pdi.bookstore.domain.inventory.title;

import cu.pdi.bookstore.domain.accounting.title.TitleAccountingInfo;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.title.TitleInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by taiyou
 * on 9/1/17.
 */
public interface TitleService {

    void registerNewTitle(TitleInfo titleInfo);

    List<ISBN> getRegisteredTitlesIn(Set<ISBN> supplyISBNList);

    List<TitleInventoryInfo> getTitlesInfo(List<ISBN> isbnList);

    TitleAccountingInfo accountingInfoForTitle(ISBN isbn);
}
