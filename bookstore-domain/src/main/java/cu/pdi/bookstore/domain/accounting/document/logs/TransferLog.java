package cu.pdi.bookstore.domain.accounting.document.logs;



import cu.pdi.bookstore.domain.accounting.sales.IncomeCalculator;
import cu.pdi.bookstore.domain.accounting.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.Stock;
import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@FieldDefaults(level = AccessLevel.PROTECTED)
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
    @AttributeOverride(name = "stockAmount", column = @Column(name = "stock_quantity"))
    Stock stock;
    @Column(name = "created_at")
    LocalDateTime createdAt;

    @Setter
    @ManyToOne
    DeliveryVoucher accountingDocument;

    @Transient
    IncomeCalculator saleIncomeCalculator;

    @Builder
    TransferLog(DepartmentCode from, DepartmentCode to,
                ISBN isbn, Stock stockForTitle, IncomeCalculator incomeCalculator) {
        this.from = from;
        this.to = to;
        this.title = isbn;
        this.stock = stockForTitle;
        this.createdAt = LocalDateTime.now();
        this.saleIncomeCalculator = incomeCalculator;
    }

    public double saleIncome() {
        return saleIncomeCalculator.ofTransfer(this);
    }
}
