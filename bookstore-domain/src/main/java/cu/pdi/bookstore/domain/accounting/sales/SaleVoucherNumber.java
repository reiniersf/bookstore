package cu.pdi.bookstore.domain.accounting.sales;

import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SaleVoucherNumber {
    private String saleVoucherNumber;

    public static SaleVoucherNumber of(String saleVoucherNumber) {
        Objects.requireNonNull(saleVoucherNumber, "Debe proveer un valor para el número del vale de venta");
        Assert.isTrue(!saleVoucherNumber.isEmpty(), "El valor para el número del vale de venta no pude ser vacío");
        return new SaleVoucherNumber(saleVoucherNumber);
    }
}
