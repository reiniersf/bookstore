package cu.pdi.bookstore.domain.accounting.document.logs;


import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.Stock;
import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by taiyou
 * 8/28/17.
 */
@Entity
@Getter
@Table(name = "transfer_entry")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransferLog {
    @Setter
    @Id
    @GeneratedValue
    Long consecutive;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "origin"))
    DepartmentCode from;
    @Embedded
    @AttributeOverride(name = "code", column = @Column(name = "destination"))
    DepartmentCode to;
    @Embedded
    @AttributeOverride(name = "isbnCode", column = @Column(name = "isbn_code"))
    ISBN title;
    @Embedded
    Stock amount;
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Setter
    @ManyToOne
    DeliveryVoucher accountingDocument;


    TransferLog(DepartmentCode from, DepartmentCode to, ISBN isbn, Stock stockForTitle) {
        this.from = from;
        this.to = to;
        this.title = isbn;
        this.amount = stockForTitle;
        this.createdAt = LocalDateTime.now();
    }
}
