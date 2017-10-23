package cu.pdi.bookstore.domain.builders;

import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.inventory.title.TitleInventoryInfo;
import cu.pdi.bookstore.domain.kernel.TitleInfo;

import java.util.Random;
import java.util.stream.Stream;

public class TitleSupplyFactory {
    public static TitleSupply createTitleSupplyForTitles(TitleInfo... titleInfo){
        TitleSupply titleSupply = new TitleSupply();
        Stream.of(titleInfo).forEach(title -> titleSupply.includeTitleInfo(title, new Random().nextInt(50)));
        return titleSupply;
    }
}
