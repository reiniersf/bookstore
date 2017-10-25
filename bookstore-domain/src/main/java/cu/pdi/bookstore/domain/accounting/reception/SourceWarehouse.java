package cu.pdi.bookstore.domain.accounting.reception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SourceWarehouse {
    private String warehouseName;

    private SourceWarehouse(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public static SourceWarehouse withName(String sourceWarehouseName) {
        Objects.requireNonNull(sourceWarehouseName, "Debe proveer un nombre para el almacén de destino");
        Assert.isTrue(!sourceWarehouseName.isEmpty(), "El nombre para el almacén de destino no pude ser vacío");

        return new SourceWarehouse(sourceWarehouseName);
    }
}
