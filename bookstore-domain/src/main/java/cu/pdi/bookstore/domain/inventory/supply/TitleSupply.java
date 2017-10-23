package cu.pdi.bookstore.domain.inventory.supply;

import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;
import cu.pdi.bookstore.domain.kernel.TitleInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 8/30/17.
 */
public class TitleSupply {

    private Map<String, TitleTuple> supply;

    public TitleSupply(){
        supply = new HashMap<>();
    }

    public Set<ISBN> titlesISBN() {
        return supply.keySet().stream().map(ISBN::new).collect(Collectors.toSet());
    }

    public void includeTitleInfo(TitleInfo titleInfo, Integer amount) {
        supply.put(titleInfo.getIsbn().getIsbnCode(), new TitleTuple(titleInfo, amount));
    }

    public Stock getStockForTitle(ISBN title) {
        return supply.get(title.getIsbnCode()).stock();
    }

    public TitleInfo getTitleInfoForISBN(ISBN isbn) {
        return supply.get(isbn.getIsbnCode()).titleInfo();
    }

    private class TitleTuple {
        private Stock amount;
        private TitleInfo titleInfo;

        TitleTuple(TitleInfo titleInfo, Integer amount){
            this.amount = new Stock(amount);
            this.titleInfo = titleInfo;
        }

        Stock stock(){
            return amount;
        }

        TitleInfo titleInfo(){
            return titleInfo;
        }
    }
}
