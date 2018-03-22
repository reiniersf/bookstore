package cu.pdi.bookstore.fx.components.ui;

import cu.pdi.bookstore.fx.components.ui.events.WorkspaceRenderEvent;
import cu.pdi.bookstore.fx.gui.main.WorkspaceController;
import javafx.scene.layout.BorderPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class WorkspaceRenderer {

    private final WorkspaceController workspaceController;
    private final FXMLLocator fxmlLocator;

    @Autowired
    public WorkspaceRenderer(WorkspaceController workspaceController, FXMLLocator fxmlLocator) {
        this.workspaceController = workspaceController;
        this.fxmlLocator = fxmlLocator;
    }

    @EventListener
    private void processWorkspaceRenderEvent(WorkspaceRenderEvent renderEvent) {
        workspaceController.renderContent(fxmlLocator.getFXML(renderEvent.getFxmlToShow()));
    }
}
