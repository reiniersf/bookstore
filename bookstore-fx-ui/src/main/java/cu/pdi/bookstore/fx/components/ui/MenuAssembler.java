package cu.pdi.bookstore.fx.components.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.pdi.bookstore.fx.enums.Role;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class MenuAssembler {
    private final ResourceLocator resourceLocator;
    private final ObjectMapper jsonMapper;
    private ActionRetriever actionRetriever;

    @Autowired
    public MenuAssembler(ResourceLocator resourceLocator, ObjectMapper jsonMapper) {
        this.resourceLocator = resourceLocator;
        this.jsonMapper = jsonMapper;
        init();
    }

    private void init() {
        actionRetriever = new ActionRetriever(resourceLocator.locateJSONConfig("menu-def.json")
                .map(inputStream -> {
                    try {
                        return jsonMapper.readTree(inputStream);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).orElseThrow(() -> new RuntimeException("No menu configuration provided.")));
    }

    public List<MenuItem> assembleForRole(Role role) {

        return null;
    }

    private class ActionRetriever {
        private final JsonNode menuConfig;

        private ActionRetriever(JsonNode menuConfig) {
            this.menuConfig = menuConfig;
        }

        Stream<JsonNode> allowedByRole(Role role) {
            return Stream.of(menuConfig.get(role.name()).get("allowed").iterator())
                    .flatMap(jsonNodeIterator -> {
                        List<JsonNode> jsonNodeList = new ArrayList<>();
                        while (jsonNodeIterator.hasNext()) {
                            jsonNodeList.add(jsonNodeIterator.next());
                        }
                        return jsonNodeList.stream();
                    });
        }

    }
}
