package cu.pdi.bookstore.domain.shared;

import org.springframework.util.Assert;

import javax.persistence.Embeddable;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Embeddable
public class ISBN {
    String codigoISBN;

    public ISBN(String codigoISBN){
        Assert.notNull(codigoISBN,"El ISBN ");
        this.codigoISBN = codigoISBN;
    }
}
