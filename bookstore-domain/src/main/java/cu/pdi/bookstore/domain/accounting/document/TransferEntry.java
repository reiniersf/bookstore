package cu.pdi.bookstore.domain.accounting.document;


import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.shared.Stock;
import cu.pdi.bookstore.domain.shared.ISBN;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by taiyou
 * 8/28/17.
 */
@Entity
@Table(name = "transfer_entry")
public class TransferEntry {
    @Id
    @GeneratedValue
    Long consecutivo;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "origin"))
    DepartmentCode from;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "destination"))
    DepartmentCode to;
    @Embedded
    ISBN title;
    @Embedded
    Stock amount;
    LocalDateTime dateTime;
}
