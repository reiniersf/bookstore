package cu.pdi.bookstore.fx.components.ui.events;

public class WorkspaceRenderEvent<T> {

    private final T fxmlToShow;

    WorkspaceRenderEvent(T fxmlToShow) {

        this.fxmlToShow = fxmlToShow;
    }


}
