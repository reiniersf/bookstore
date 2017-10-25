package cu.pdi.bookstore.domain.kernel.title;

import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 8/30/17.
 */
public class TitleSupply implements TitleSet {

    private Map<ISBN, TitleTuple> supply;

    public TitleSupply() {
        supply = new HashMap<>();
    }

    public Set<ISBN> titlesISBN() {
        return supply.keySet();
    }

    public void includeTitleInfo(TitleInfo titleInfo, Integer amount) {
        supply.put(titleInfo.getIsbn(), new TitleTuple(titleInfo, amount));
    }

    public Stock getStockForTitle(ISBN isbn) {
        return supply.get(isbn).stock();
    }

    public TitleInfo getTitleInfoForISBN(ISBN isbn) {
        return supply.get(isbn).titleInfo();
    }

    private class TitleTuple {
        private Stock stock;
        private TitleInfo titleInfo;

        TitleTuple(TitleInfo titleInfo, Integer stock) {
            this.stock = new Stock(stock);
            this.titleInfo = titleInfo;
        }

        Stock stock() {
            return stock;
        }

        TitleInfo titleInfo() {
            return titleInfo;
        }
    }
}
