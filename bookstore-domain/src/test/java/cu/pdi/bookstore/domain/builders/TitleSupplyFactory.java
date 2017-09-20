package cu.pdi.bookstore.domain.builders;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.Title;

import java.util.Random;
import java.util.stream.Stream;

public class TitleSupplyFactory {
    public static TitleSupply createTitleSupplyForTitles(Title ... titles){
        TitleSupply titleSupply = new TitleSupply();
        Stream.of(titles).forEach(title -> titleSupply.includeTitle(title, new Random().nextInt(50)));
        return titleSupply;
    }
}
