package cu.pdi.bookstore.infrastructure;

import cu.pdi.bookstore.config.AppConfig;
import cu.pdi.bookstore.domain.inventory.title.*;
import cu.pdi.bookstore.domain.shared.ISBN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PersistTitleTest {
    @Autowired
    private TitleService titleService;

    @Test
    public void shouldPersistTheTitle(){
        Title title = new Title(new ISBN("5648795214354"),
                "Marvelous World", new Author("Sam Hustling"),
                new Category("Infantil"));
        titleService.registerNewTitle(title);

    }

}
