package cu.pdi.bookstore.domain.kernel.title;

import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Stock;

import java.util.Set;

public interface TitleSet {
    Set<ISBN> titlesISBN();

    Stock getStockForTitle(ISBN title);
}
