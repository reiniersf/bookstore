package cu.pdi.bookstore.domain.kernel.title;

import cu.pdi.bookstore.domain.accounting.title.*;
import cu.pdi.bookstore.domain.inventory.title.*;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TitleInfo {

    private ISBN isbn;
    private String description;
    private Author writtenBy;
    private Category category;
    private Editorial editedBy;
    private EditionYear editionYear;
    private Plan plan;
    private Price price;
    private Amount amount;

    public TitleInventoryInfo forInventoryPurpose() {
        return new TitleInventoryInfo(isbn, description, writtenBy, category, editedBy, editionYear, plan);
    }

    public TitleAccountingInfo forAccountingPurpose() {
        return new TitleAccountingInfo(isbn, price, amount);
    }


}
