package cu.pdi.bookstore.domain.accounting.reception;

import lombok.Value;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Value
@Embeddable
public class InvoiceNumber {
    private String invoiceNumber;

    public InvoiceNumber(){
        this.invoiceNumber = "";
    }

    public InvoiceNumber(String invoiceNumberValue) {
        this.invoiceNumber = invoiceNumberValue;
    }

    public static InvoiceNumber of(String invoiceNumberValue) {
        Objects.requireNonNull(invoiceNumberValue, "Debe proveer un valor para el número de factura");
        Assert.isTrue(!invoiceNumberValue.isEmpty(), "El valor para el número de factura no pude ser vacío");
        return new InvoiceNumber(invoiceNumberValue);
    }
}
