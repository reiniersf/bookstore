package cu.pdi.bookstore.domain.inventory.department.sheet;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by taiyou
 * on 9/7/17.
 */
@Getter
@AllArgsConstructor
 class InventorySheetEntry {
    private String isbn;
    private String title;
    private String authors;
    private String category;
    private Integer stock;



}
