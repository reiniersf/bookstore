package cu.pdi.bookstore.domain.inventory.department;

import org.springframework.util.Assert;

import javax.persistence.Embeddable;


/**
 * Created by taiyou
 * on 8/29/17.
 */
@Embeddable
public class DepartmentCode {

    public final static DepartmentCode WAREHOUSE_CODE = new DepartmentCode("00");
    public final static DepartmentCode BOOKDEPOT_CODE = new DepartmentCode("01");
    public final static DepartmentCode SALESROOM_CODE = new DepartmentCode("02");

    private String code;

    DepartmentCode(String code) {
        Assert.notNull(code, "Department code must not be null");

        this.code = code;
    }
}
