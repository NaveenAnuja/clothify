package controller.ItemController;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.User;
import service.ServiceFactory;
import service.SuperService;
import service.custom.ItemService;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemDetails implements Initializable {

    @FXML
    private TableColumn<?, ?> colDesc;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private TableView<Item> tblItem;

    @FXML
    private TextField txtCode;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtPacketSize;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        boolean b = valuesOk();
        if(!b){
            new Alert(Alert.AlertType.ERROR,"Please fill in all fields").show();
            return;
        }

        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);

        Item item = new Item(
                txtCode.getText(),
                txtDesc.getText(),
                txtUnitPrice.getText(),
                txtQty.getText(),
                txtPacketSize.getText()
        );

        if(itemService.addItem(item)){
            new Alert(Alert.AlertType.INFORMATION, "Item Added Successfully !").show();
            loadTable();
            clearTextFields();
        }else{
            new Alert(Alert.AlertType.ERROR, "Item Not Added !").show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);

        if(itemService.deleteUser(txtCode.getText())){
            new Alert(Alert.AlertType.INFORMATION, "Item delete Successfully !").show();
            loadTable();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Item Not deleted !").show();
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);
        Item item = itemService.searchItem(txtCode.getText());
        setTextValues(item);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean b = valuesOk();
        if(!b){
            new Alert(Alert.AlertType.ERROR,"Please fill in all fields").show();
            return;
        }

        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);

        Item item = new Item(
                txtCode.getText(),
                txtDesc.getText(),
                txtUnitPrice.getText(),
                txtQty.getText(),
                txtPacketSize.getText()
        );

        if(itemService.updateItem(item)){
            new Alert(Alert.AlertType.INFORMATION, "Item Added Successfully !").show();
            loadTable();
            clearTextFields();
        }else{
            new Alert(Alert.AlertType.ERROR, "Item Not Added !").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/dashBoard/owner_dash_board.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private void clearTextFields(){
        txtCode.setText(null);
        txtDesc.setText(null);
        txtUnitPrice.setText(null);
        txtQty.setText(null);
        txtPacketSize.setText(null);
    }

    private boolean valuesOk() {
        if(txtCode.getText() == null || txtCode.getText().isEmpty() ||
                txtDesc.getText() == null || txtDesc.getText().isEmpty() ||
                txtUnitPrice.getText() == null || txtUnitPrice.getText().isEmpty() ||
                txtQty.getText() == null || txtQty.getText().isEmpty() ||
                txtPacketSize.getText() == null || txtPacketSize.getText().isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        loadTable();
        tblItem.getSelectionModel().selectedItemProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (newValue != null){
                setTextValues(newValue);
            }
        }));


    }

    private void setTextValues(Item newValue) {
        txtCode.setText(newValue.getCode());
        txtDesc.setText(newValue.getDescription());
        txtPacketSize.setText(newValue.getSize());
        txtUnitPrice.setText(newValue.getUnitPrice());
        txtQty.setText(newValue.getQty());
    }

    public void loadTable(){
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);
        ObservableList<Item> items = itemService.getAll();
        tblItem.setItems(items);
    }
}
