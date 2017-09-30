package cu.pdi.bookstore.domain.kernel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public ISBN(String codigoISBN){
        Assert.notNull(codigoISBN,"ISBN must not be null");
        this.isbnCode = codigoISBN;
    }

    /*
    @Override
    public boolean equals(Object isbn) {
        return isbn instanceof ISBN && this.isbnCode.equals(((ISBN)isbn).getIsbnCode());
    }*/
}
