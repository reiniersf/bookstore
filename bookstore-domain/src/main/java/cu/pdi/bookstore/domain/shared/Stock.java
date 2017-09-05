package cu.pdi.bookstore.domain.shared;

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
    private Integer amount;

    public Stock(Integer amount) {
        Assert.notNull(amount, "The stock must not be null");
        Assert.isTrue(amount >= 0, "The stock must be greater than 0");
        this.amount = amount;
    }

    public boolean isGreaterEqualThan(Stock stockForTitle) {
        return amount >= stockForTitle.amount;
    }

    public Stock add(Stock stockForTitle) {
        return new Stock(this.amount + stockForTitle.amount);
    }

    public Stock minus(Stock stockForTitle) {
        return new Stock(this.amount - stockForTitle.amount);
    }
}
