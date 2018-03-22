package cu.pdi.bookstore.fx.components.ui.events;

public class WorkspaceRenderEvent implements RenderEvent<String> {

    private final String fxmlToShow;

    WorkspaceRenderEvent(String fxmlToShow) {

        this.fxmlToShow = fxmlToShow;
    }

    public String getFxmlToShow() {
        return fxmlToShow;
    }
}
