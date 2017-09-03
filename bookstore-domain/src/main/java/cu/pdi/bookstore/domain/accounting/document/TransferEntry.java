package cu.pdi.bookstore.domain.accounting.document;


import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.inventory.department.DepartmentCode;
import cu.pdi.bookstore.domain.shared.Stock;
import cu.pdi.bookstore.domain.shared.ISBN;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by taiyou
 * 8/28/17.
 */
@Entity
public class TransferEntry {
    @Id
    @GeneratedValue
    Long consecutivo;
    @Embedded
    DepartmentCode from;
    @Embedded
    DepartmentCode to;
    @Embedded
    ISBN title;
    @Embedded
    Stock amount;
    LocalDateTime dateTime;
}
