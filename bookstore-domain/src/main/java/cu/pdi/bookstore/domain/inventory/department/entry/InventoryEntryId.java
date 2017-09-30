package cu.pdi.bookstore.domain.inventory.department.entry;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
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
    @Embedded
    @AttributeOverride(name = "isbnCode", column = @Column(name = "isbn_code"))
    private ISBN title;
    @AttributeOverride(name = "code", column = @Column(name = "department"))
    @Embedded private DepartmentCode department;


}
