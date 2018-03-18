package cu.pdi.bookstore.fx.components.ui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import cu.pdi.bookstore.fx.components.security.Role;
import cu.pdi.bookstore.fx.components.ui.events.WorkspaceRenderEvents;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MenuAssembler {


    private final ResourceLocator resourceLocator;
    private final ObjectMapper jsonMapper;
    private ActionRetriever actionRetriever;
    private final I18nHandler i18nHandler;
    private final ApplicationEventPublisher applicationEventPublisher;


    private BiFunction<String, List<JsonNode>, Menu> CLASSIC_MENU_GRAPHIC_MAPPER = null;

    @Autowired
    public MenuAssembler(ResourceLocator resourceLocator, ObjectMapper jsonMapper, I18nHandler i18nHandler,
                         ApplicationEventPublisher applicationEventPublisher) {
        this.resourceLocator = resourceLocator;
        this.jsonMapper = jsonMapper;
        this.i18nHandler = i18nHandler;
        this.applicationEventPublisher = applicationEventPublisher;
        init();
        CLASSIC_MENU_GRAPHIC_MAPPER = (section, jsonNodes) -> {
            Menu sectionMenu = new Menu(i18nHandler.labelForKey(section.concat(".title")));
            List<MenuItem> sectionMenuItems = jsonNodes.stream().map(jsonNode -> {
                MenuItem menuItem = new MenuItem(i18nHandler.labelForKey(jsonNode.get("name").asText().concat(".title")));

                menuItem.setOnAction(actionEvent ->
                        applicationEventPublisher.publishEvent(WorkspaceRenderEvents.with(jsonNode.get("fxml").asText())));
                return menuItem;

            }).collect(Collectors.toList());

            sectionMenu.getItems().addAll(sectionMenuItems);

            return sectionMenu;
        };
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

    public List<Menu> assembleForRole(Role role) {
        return assembleForRole(role, CLASSIC_MENU_GRAPHIC_MAPPER);
    }

    public <R> List<R> assembleForRole(Role role, BiFunction<String, List<JsonNode>, R> graphicStrategy) {
        Map<String, List<JsonNode>> jsonNodesBySection = actionRetriever.allowedByRole(role)
                .collect(Collectors.groupingBy(
                        (JsonNode jsonNode) -> jsonNode.get("section").asText(), Collectors.toList()
                ));

        List<R> assembledMenu = new ArrayList<>();
        jsonNodesBySection.forEach((section, jsonNodes) -> assembledMenu.add(graphicStrategy.apply(section, jsonNodes)));

        return assembledMenu;

    }

    private class ActionRetriever {
        private final JsonNode menuConfig;

        private ActionRetriever(JsonNode menuConfig) {
            this.menuConfig = menuConfig;
        }

        Stream<JsonNode> allowedByRole(Role role) {

            List<String> allowedActionNames = actionsStreamForRole(role)
                    .map(jsonNode -> jsonNode.get("name").asText())
                    .collect(Collectors.toList());

            if (allowedActionNames.contains("ALL")) {
                return availableActions();
            }

            return availableActions().filter(jsonNode -> allowedActionNames.contains(jsonNode.get("name").asText()));

        }

        private Stream<JsonNode> actionsStreamForRole(Role role) {
            return Stream.of(menuConfig.get(role.name()).get("allowed").iterator())
                    .flatMap(jsonNodeIterator -> {
                        List<JsonNode> jsonNodeList = new ArrayList<>();
                        while (jsonNodeIterator.hasNext()) {
                            jsonNodeList.add(jsonNodeIterator.next());
                        }
                        return jsonNodeList.stream();
                    });
        }

        private Stream<JsonNode> availableActions() {
            return Stream.of(menuConfig.get("AVAILABLE").get("actions").elements())
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
