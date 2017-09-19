package cu.pdi.bookstore.domain.inventory.department;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * Created by taiyou
 * on 8/29/17.
 */
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class DepartmentCode implements Serializable {

    public final static DepartmentCode WAREHOUSE_CODE = new DepartmentCode("00");
    public final static DepartmentCode BOOKDEPOT_CODE = new DepartmentCode("01");
    public final static DepartmentCode SALESROOM_CODE = new DepartmentCode("02");

    @Getter
    private String code;

    private DepartmentCode(String code) {
        Assert.notNull(code, "Department code must not be null");

        this.code = code;
    }

    public static DepartmentCode forCode(String codeValue){
        return new DepartmentCode(codeValue);
    }
}
