package cu.pdi.bookstore.domain.inventory.supply;

import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by taiyou
 * on 8/30/17.
 */
public class TitleSupply {

    private Map<ISBN, TitleTuple> supply;

    public TitleSupply(){
        supply = new HashMap<>();
    }

    public Set<ISBN> titlesISBN() {
        return supply.keySet();
    }

    public void includeTitle(Title title, Integer amount) {
        supply.put(title.getIsbn(), new TitleTuple(title, amount));
    }

    public Stock getStockForTitle(ISBN title) {
        return supply.get(title).amount();
    }

    public Title getTitleForISBN(ISBN isbn) {
        return supply.get(isbn).titleInfo();
    }

    private class TitleTuple {
        private Stock amount;
        private Title title;

        TitleTuple(Title title, Integer amount){
            this.amount = new Stock(amount);
            this.title = title;
        }

        Stock amount(){
            return amount;
        }

        Title titleInfo(){
            return title;
        }
    }
}
