package cu.pdi.bookstore.fx.gui.inventory;

import cu.pdi.bookstore.domain.inventory.title.Author;
import cu.pdi.bookstore.domain.inventory.title.Category;
import cu.pdi.bookstore.domain.inventory.title.TitleInventoryInfo;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.title.TitleInfo;
import cu.pdi.bookstore.fx.components.ui.I18nHandler;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InventoryController implements Initializable {

    @FXML
    private
    Button btnCleanFilter;
    @FXML
    private
    CheckBox chkSelectAll;
    @FXML
    private
    TextField txFilter;
    @FXML
    private
    TableView<TitleInventoryInfo> tbBooksInventory;
    @FXML
    private
    TableColumn<TitleInventoryInfo, String> tcCode;
    @FXML
    private
    TableColumn<TitleInventoryInfo, String> tcTitle;
    @FXML
    private
    TableColumn<TitleInventoryInfo, String> tcAuthor;
    @FXML
    private
    TableColumn<TitleInventoryInfo, String> tcCategory;

    private BooleanProperty update;

    private final I18nHandler i18nHandler;

    @Autowired
    public InventoryController(I18nHandler i18nHandler) {
        this.i18nHandler = i18nHandler;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcCode.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("description"));
        tcAuthor.setCellValueFactory(new PropertyValueFactory<>("writtenBy"));
        tcCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        tbBooksInventory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tbBooksInventory.setItems(FXCollections.observableArrayList(
                TitleInfo.builder()
                        .isbn(ISBN.of("374292003902"))
                        .description("Some title for a book")
                        .category(new Category("Infantil"))
                        .writtenBy(new Author("Someone et al."))
                        .build()
                        .forInventoryPurpose()));
        txFilter.setPromptText(i18nHandler.labelForKey("tx.filter"));

        btnCleanFilter.setOnAction(this::restoreFilterField);

        chkSelectAll.selectedProperty().addListener(this::selectAllListener);
    }

    private void restoreFilterField(ActionEvent actionEvent) {
        txFilter.setText("");
        txFilter.setPromptText(i18nHandler.labelForKey("tx.filter"));
    }

    private void selectAllListener(ObservableValue<? extends Boolean> ov, Boolean oldValue, Boolean newValue) {
        if (newValue) {
            tbBooksInventory.getSelectionModel().selectAll();
            tbBooksInventory.requestFocus();
        } else {
            tbBooksInventory.getSelectionModel().clearSelection();
        }
    }
}
