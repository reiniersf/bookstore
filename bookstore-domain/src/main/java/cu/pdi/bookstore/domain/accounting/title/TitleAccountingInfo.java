package cu.pdi.bookstore.domain.accounting.title;

import cu.pdi.bookstore.domain.kernel.ISBN;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "title_accounting_info")
@AllArgsConstructor
@NoArgsConstructor
public class TitleAccountingInfo {
    @Id
    ISBN isbn;
    Price price;
    Amount amount;
}
