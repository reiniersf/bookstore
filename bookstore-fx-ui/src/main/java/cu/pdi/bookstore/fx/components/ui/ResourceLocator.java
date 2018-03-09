package cu.pdi.bookstore.fx.components.ui;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;

@Component
public class ResourceLocator {

    public Optional<String> urlForImage(String imageName) {
        return Optional.ofNullable(getClass().getClassLoader().getResource("images/".concat(imageName)))
                .map(URL::toExternalForm);
    }

}
