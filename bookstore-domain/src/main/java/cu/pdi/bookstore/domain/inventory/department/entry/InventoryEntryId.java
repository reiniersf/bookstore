package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 9/5/17.
 */
@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryEntryId implements Serializable{
    @Embedded private ISBN title;
    @Embedded private DepartmentCode department;


}
