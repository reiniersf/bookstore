package cu.pdi.bookstore.domain.shared;

import org.springframework.util.Assert;

/**
 * Created by taiyou
 * on 8/28/17.
 */
public class Stock {
    private Integer amount;

    public Stock(Integer amount){
        Assert.notNull(amount, "The stock must not be null");
        Assert.isTrue(amount>=0, "The stock must be greater than 0");
        this.amount =amount;
    }

    public boolean isGreaterEqualThan(Stock stockForTitle) {
        return amount >= stockForTitle.amount;
    }

}
