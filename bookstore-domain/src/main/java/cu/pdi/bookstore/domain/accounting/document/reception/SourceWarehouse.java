package cu.pdi.bookstore.domain.accounting.document.reception;

import lombok.Value;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.util.Objects;

@Value
@Embeddable
public class SourceWarehouse {
    private String warehouseName;

    public SourceWarehouse() {
        this.warehouseName = "";
    }

    public SourceWarehouse(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public static SourceWarehouse withName(String sourceWarehouseName) {
        Objects.requireNonNull(sourceWarehouseName, "Debe proveer un nombre para el almacén de destino");
        Assert.isTrue(!sourceWarehouseName.isEmpty(), "El nombre para el almacén de destino no pude ser vacío");

        return new SourceWarehouse(sourceWarehouseName);
    }
}
