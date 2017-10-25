package cu.pdi.bookstore.domain.kernel.title;


import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TitleSale implements TitleSet{

    private final Map<ISBN, Stock> sales = new HashMap<>();

    @Override
    public Set<ISBN> titlesISBN() {
        return sales.keySet();
    }

    @Override
    public Stock getStockForTitle(ISBN isbn) {
        return sales.get(isbn);
    }

    public void includeTitleSale(ISBN isbn, Integer amount){
        sales.put(isbn, Stock.of(amount));
    }
}
