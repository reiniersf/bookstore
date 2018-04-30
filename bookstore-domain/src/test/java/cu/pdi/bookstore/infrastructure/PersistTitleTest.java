package cu.pdi.bookstore.infrastructure;

import cu.pdi.bookstore.application.config.AppConfig;
import cu.pdi.bookstore.domain.builders.TitleInfoBuilder;
import cu.pdi.bookstore.domain.inventory.title.*;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.title.TitleInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("test")
public class PersistTitleTest {
    @Autowired
    private TitleService titleService;

    @Test
    public void shouldPersistTheTitle() {
        TitleInfo titleInfo = TitleInfoBuilder.createTitle()
                .withISBN(ISBN.of("5648795214354"))
                .withDescription("Marvelous World")
                .writtenBy(new Author("Sam Hustling"))
                .inCategory(new Category("Infantil"))
                .build();
        titleService.registerNewTitle(titleInfo);

    }

}
