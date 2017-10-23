package cu.pdi.bookstore.domain.builders;

import cu.pdi.bookstore.domain.accounting.title.Amount;
import cu.pdi.bookstore.domain.accounting.title.Price;
import cu.pdi.bookstore.domain.inventory.title.*;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.Plan;
import cu.pdi.bookstore.domain.kernel.TitleInfo;

import java.util.Random;

public class TitleInfoBuilder {

    private ISBN isbn;
    private String description;
    private Author writtenBy;
    private Editorial editedBy;
    private Category category;
    private EditionYear editionYear;
    private Plan plan;
    Price price;
    Amount amount;

    private TitleInfoBuilder() {
        this.isbn = new ISBN(String.valueOf(new Random(11111111111L).nextLong()));
        this.description = "The Book TitleInventoryInfo";
        this.writtenBy = new Author("Anonymous");
        this.editedBy = new Editorial("Cannaime");
        this.category = new Category("Universal");
        this.editionYear = new EditionYear(2010);
        this.plan = new Plan("Regular");
        this.price = new Price(10d);
        this.amount = new Amount(7d);
    }

    public static TitleInfoBuilder createTitle() {
        return new TitleInfoBuilder();
    }

    public TitleInfoBuilder withISBN(ISBN isbn) {
        this.isbn = isbn;
        return this;
    }

    public TitleInfoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public TitleInfoBuilder writtenBy(Author authors) {
        this.writtenBy = authors;
        return this;
    }

    public TitleInfoBuilder inCategory(Category category) {
        this.category = category;
        return this;
    }

    public TitleInfoBuilder editedBy(Editorial editorial) {
        this.editedBy = editorial;
        return this;
    }

    public TitleInfoBuilder editedInYear(EditionYear editionYear) {
        this.editionYear = editionYear;
        return this;
    }

    public TitleInfoBuilder withPrice(Price price) {
        this.price = price;
        return this;
    }

    public TitleInfoBuilder withAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public TitleInfo build() {
        return new TitleInfo(this.isbn, this.description, this.writtenBy, this.category,
                this.editedBy, this.editionYear, this.plan, price, amount);
    }
}
