package cu.pdi.bookstore.domain.inventory.department.sheet;

import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.kernel.Stock;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by taiyou on 9/7/17.
 */
@NoArgsConstructor
public class InventorySheet {

    private DepartmentCode code;
    private List<InventorySheetEntry> entries;

    private InventorySheet(DepartmentCode code) {
        this.code = code;
        this.entries = new ArrayList<>();

    }

    static InventorySheet ofDepartment(DepartmentCode code) {
        return new InventorySheet(code);

    }

    public List<InventorySheetEntry> entries() {
        return Collections.unmodifiableList(this.entries);
    }

    void addEntry(Stock inventoryEntryStock, Title title) {
        entries.add(new InventorySheetEntry(title.getIsbn().getIsbnCode(),
                title.getDescription(),
                title.getWrittenBy().getAuthors(),
                title.getCategory().getCategoryName(),
                inventoryEntryStock.getAmount()));
    }
}
