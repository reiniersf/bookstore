package cu.pdi.bookstore.fx.components.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.pdi.bookstore.fx.enums.Roles;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MenuAssembler {
    private final ResourceLocator resourceLocator;
    private final ObjectMapper jsonMapper;
    private JsonNode menuConfig;

    @Autowired
    public MenuAssembler(ResourceLocator resourceLocator, ObjectMapper jsonMapper) {
        this.resourceLocator = resourceLocator;
        this.jsonMapper = jsonMapper;
        init();
    }

    private void init() {
        menuConfig = resourceLocator.locateJSONConfig("menu-def.json")
                .map(inputStream -> {
                    try {
                        return jsonMapper.readTree(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseThrow(()-> new RuntimeException("No menu configuration provided."));
    }

    public List<MenuItem> assembleForRole(Roles role) {
        return null;
    }
}
