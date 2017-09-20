package cu.pdi.bookstore.domain.inventory.title;


import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.*;

import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Entity
@Table(name="title")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Title implements Serializable {
    @EmbeddedId
    @NonNull
    ISBN isbn;
    @NonNull
    String description;
    @Embedded
    @NonNull
    Author writtenBy;
    @Embedded
    @NonNull
    Category category;
    @Embedded
    Editorial editedBy;
    @Embedded
    EditionYear editionYear;
    @Embedded
    Plan plan;


    public Title(ISBN isbn) {
        this.isbn = isbn;
    }
}