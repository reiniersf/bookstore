package cu.pdi.bookstore.domain.inventory.title;


import cu.pdi.bookstore.domain.shared.ISBN;
import cu.pdi.bookstore.domain.shared.Plan;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Entity
@Builder
@Getter
public class Title {
    @EmbeddedId
    ISBN isbn;

    String description;
    @Embedded
    Author writtenBy;
    @Embedded
    Editorial editedBy;
    @Embedded
    Category category;
    @Embedded
    EditionYear editionYear;
    @Embedded
    Plan plan;

}