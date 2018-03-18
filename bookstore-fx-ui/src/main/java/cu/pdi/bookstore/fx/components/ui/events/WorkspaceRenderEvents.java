package cu.pdi.bookstore.fx.components.ui.events;

public class WorkspaceRenderEvents {
    public static WorkspaceRenderEvent with(String fxmlToShow){
        return new WorkspaceRenderEvent<>(fxmlToShow);
    }
}
