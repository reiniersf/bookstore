package cu.pdi.bookstore.domain.builders;

import cu.pdi.bookstore.domain.kernel.title.TitleSale;
import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.kernel.title.TitleInfo;

import java.util.Random;
import java.util.stream.Stream;

public class TitleSetFactory {
    public static TitleSupply createTitleSupplyForTitles(TitleInfo... titleInfo){
        TitleSupply titleSupply = new TitleSupply();
        Stream.of(titleInfo).forEach(title -> titleSupply.includeTitleInfo(title, new Random().nextInt(50)));
        return titleSupply;
    }

    public static TitleSale createTitleSaleBasedOnSupply(TitleSupply titleSupply){
        TitleSale titleSale = new TitleSale();
        titleSupply.titlesISBN().forEach(isbn -> titleSale.includeTitleSale(isbn, titleSupply.getStockForTitle(isbn).getStockAmount()));
        return titleSale;
    }
}
