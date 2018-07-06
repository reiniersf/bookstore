package cu.pdi.bookstore.domain.kernel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Embeddable
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class ISBN implements Serializable {
    private String isbnCode;

    public ISBN(String isbnCode){
        Assert.notNull(isbnCode,"ISBN must not be null");
        this.isbnCode = isbnCode;
    }

    public static ISBN of(String isbnCode){
        return new ISBN(isbnCode);
    }

    @Override
    public String toString() {
        return isbnCode;
    }
}
