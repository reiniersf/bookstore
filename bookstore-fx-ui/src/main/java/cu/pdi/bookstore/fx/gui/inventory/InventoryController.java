package cu.pdi.bookstore.fx.gui.inventory;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class InventoryController implements Initializable {

    @FXML
    Button btnNextPage;
    @FXML
    Button btnPrevPage;
    @FXML
    Button btnCleanFilter;
    @FXML
    CheckBox chkSelectAll;
    @FXML
    TextField txFilter;
    @FXML
    ProgressBar pbPages;
    @FXML
    TableView tbBooksInventory;
    @FXML
    TableColumn tcCodigo;
    @FXML
    TableColumn tcTitulo;
    @FXML
    TableColumn tcAutor;
    @FXML
    TableColumn tcEditorial;
    @FXML
    TableColumn tcAnnoEdicion;
    @FXML
    TableColumn tcPlan;
    @FXML
    TableColumn tcCategoria;
    @FXML
    TableColumn tcCantidadAlmacen;
    @FXML
    TableColumn tcCantidadSalon;
    @FXML
    TableColumn tcPrecioVenta;

    public BooleanProperty actualizar;

    @FXML
    private void cleanFilterField() {

    }

    @FXML
    private void selectAllBooks() {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        tcCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
//        tcTitulo.setCellValueFactory(new PropertyValueFactory("titulo"));
//        tcAutor.setCellValueFactory(new PropertyValueFactory("autor"));
//        tcEditorial.setCellValueFactory(new PropertyValueFactory("editorial"));
//        tcAnnoEdicion.setCellValueFactory(new PropertyValueFactory("annoEdicion"));
//        tcPlan.setCellValueFactory(new PropertyValueFactory("plan"));
//        tcCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
//        tcCantidadAlmacen.setCellValueFactory(new PropertyValueFactory("cantidadAlmacen"));
//        tcCantidadSalon.setCellValueFactory(new PropertyValueFactory("cantidadSalon"));
//        tcPrecioVenta.setCellValueFactory(new PropertyValueFactory("precioVenta"));
//
//        SortedList<LibroObjeto> filtereableCollection = UIUtils.initFiltereableCollection(FXCollections.observableList(LibroManager.getInstance().obtenerLibroPorInventarioInicial((ENUM_LUGAR) LibreriaUCI.getSesion().get("lugar"))), txFilter);
//        filtereableCollection.comparatorProperty().bind(tbBooksInventory.comparatorProperty());
//        tbBooksInventory.setItems(filtereableCollection);
//
//        txFilter.setPromptText("Introduzca el título a filtrar...");
//
//        actualizar = new SimpleBooleanProperty(null, "actualizar", false);
//        actualizar.addListener((ov, t, t1) -> {
//            if (t1 && Mediator.getInstance().isScreenOpen("Libros.inventario.fxml")) {
//                SortedList<LibroObjeto> initFiltereableCollection = UIUtiles.initFiltereableCollection(FXCollections.observableList(LibroManager.getInstance().obtenerLibroPorInventarioInicial((ENUM_LUGAR) LibreriaUCI.getSesion().get("lugar"))), txFilter);
//                initFiltereableCollection.comparatorProperty().bind(tbBooksInventory.comparatorProperty());
//                tbBooksInventory.setItems(initFiltereableCollection);
//
//                actualizar.set(false);
//            }
//        });
//        Mediator.registerSharedObject("updateInventarioProperty", actualizar);
//        btnCleanFilter.setOnAction(actionEvent -> {
//            txFilter.setText("");
//            txFilter.setPromptText("Introduzca el título a filtrar...");
//        });
    }
}
