package cu.pdi.bookstore.domain.inventory.supply;

import cu.pdi.bookstore.domain.inventory.title.Title;
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
public class TitleSupply {

    private Map<String, TitleTuple> supply;

    public TitleSupply(){
        supply = new HashMap<>();
    }

    public Set<ISBN> titlesISBN() {
        return supply.keySet().stream().map(ISBN::new).collect(Collectors.toSet());
    }

    public void includeTitle(Title title, Integer amount) {
        supply.put(title.getIsbn().getCodigoISBN(), new TitleTuple(title, amount));
    }

    public Stock getStockForTitle(ISBN title) {
        return supply.get(title.getCodigoISBN()).amount();
    }

    public Title getTitleForISBN(ISBN isbn) {
        return supply.get(isbn.getCodigoISBN()).titleInfo();
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
