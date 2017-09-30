package cu.pdi.bookstore.domain.inventory.title;


import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by taiyou
 * on 8/27/17.
 */
@Entity
@Table(name = "title")
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Title implements Serializable {
    @EmbeddedId
    @NonNull
    @AttributeOverride(name = "isbnCode", column = @Column(name = "isbn_code"))
    ISBN isbn;
    @NonNull
    String description;
    @Embedded
    @NonNull
    Author writtenBy;
    @Embedded
    @NonNull
    @AttributeOverride(name = "categoryName", column = @Column(name = "category_name"))
    Category category;
    @Embedded
    @AttributeOverride(name = "editorialName", column = @Column(name = "editorial_name"))
    Editorial editedBy;
    @Embedded
    @AttributeOverride(name = "editionYear", column = @Column(name = "edition_year"))
    EditionYear editionYear;
    @Embedded
    @AttributeOverride(name = "planName", column = @Column(name = "plan_name"))
    Plan plan;


    public Title(ISBN isbn) {
        this.isbn = isbn;
    }
}