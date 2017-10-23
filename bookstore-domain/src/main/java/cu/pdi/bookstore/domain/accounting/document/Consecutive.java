package cu.pdi.bookstore.domain.accounting.document;

import lombok.EqualsAndHashCode;
import lombok.Value;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Value
@Embeddable
@EqualsAndHashCode
public class Consecutive {

    private String consecutiveValue;

    public Consecutive() {
        this.consecutiveValue = "";

    }

    public Consecutive(String consecutiveValue) {
        this.consecutiveValue = consecutiveValue;
    }

    public static Consecutive of(String consecutiveValue){
        Objects.requireNonNull(consecutiveValue, "Debe proveer un valor para el consecutivo del documento");
        Assert.isTrue(!consecutiveValue.isEmpty(), "El valor para el consecutivo del documento no pude ser vacío");

        return new Consecutive(consecutiveValue);
    }
}
