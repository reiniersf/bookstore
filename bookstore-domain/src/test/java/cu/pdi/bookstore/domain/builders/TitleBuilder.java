package cu.pdi.bookstore.domain.builders;

import cu.pdi.bookstore.domain.inventory.title.*;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;

import java.util.Random;

public class TitleBuilder {

    private ISBN isbn;
    private String description;
    private Author writtenBy;
    private Editorial editedBy;
    private Category category;
    private EditionYear editionYear;
    private Plan plan;

    private TitleBuilder() {
        this.isbn = new ISBN(String.valueOf(new Random(11111111111L).nextLong()));
        this.description = "The Book Title";
        this.writtenBy = new Author("Anonymous");
        this.editedBy = new Editorial("Cannaime");
        this.category = new Category("Universal");
        this.editionYear = new EditionYear(2010);
        this.plan = new Plan("Regular");
    }

    public static TitleBuilder createTitle() {
        return new TitleBuilder();
    }

    public TitleBuilder withISBN(ISBN isbn) {
        this.isbn = isbn;
        return this;
    }

    public TitleBuilder withTitle(String description) {
        this.description = description;
        return this;
    }

    public TitleBuilder writtenBy(Author authors) {
        this.writtenBy = authors;
        return this;
    }

    public TitleBuilder inCategory(Category category) {
        this.category = category;
        return this;
    }

    public TitleBuilder editedBy(Editorial editorial) {
        this.editedBy = editorial;
        return this;
    }

    public TitleBuilder editedInYear(EditionYear editionYear) {
        this.editionYear = editionYear;
        return this;
    }

    public Title build() {
        return new Title(this.isbn, this.description, this.writtenBy, this.category,
                this.editedBy, this.editionYear, this.plan);
    }
}
