package cu.pdi.bookstore.domain.inventory.supply;


import cu.pdi.bookstore.domain.inventory.department.Department;
import cu.pdi.bookstore.domain.shared.Stock;
import cu.pdi.bookstore.domain.shared.ISBN;

import java.time.LocalDateTime;

/**
 * Created by taiyou
 * 8/28/17.
 */
public class TransferEntry {
    Department from;
    Department to;
    ISBN title;
    Stock amount;
    LocalDateTime date;
}
