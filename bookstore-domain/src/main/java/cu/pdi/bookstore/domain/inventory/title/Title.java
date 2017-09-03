package cu.pdi.bookstore.domain.inventory.title;


import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Plan;
import lombok.Builder;
import lombok.Getter;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Builder
@Getter
public class Title {
    String description;
    Author writtenBy;
    Editorial editedBy;
    Category category;
    EditionYear editionYear;
    ISBN isbn;
    Plan plan;

}