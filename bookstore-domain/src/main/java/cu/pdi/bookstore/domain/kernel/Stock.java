package cu.pdi.bookstore.domain.kernel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/28/17.
 */
@Embeddable
@NoArgsConstructor
@Getter
public class Stock implements Serializable {
    private Integer stockAmount;

    public Stock(Integer stockAmount) {
        Assert.notNull(stockAmount, "The stock must not be null");
        Assert.isTrue(stockAmount >= 0, "The stock must be greater than 0");
        this.stockAmount = stockAmount;
    }

    public static Stock of(Integer stock){
        return new Stock(stock);
    }

    public boolean isGreaterEqualThan(Stock stockForTitle) {
        return stockAmount >= stockForTitle.stockAmount;
    }

    public Stock add(Stock stockForTitle) {
        return new Stock(this.stockAmount + stockForTitle.stockAmount);
    }

    public Stock minus(Stock stockForTitle) {
        return new Stock(this.stockAmount - stockForTitle.stockAmount);
    }
}
