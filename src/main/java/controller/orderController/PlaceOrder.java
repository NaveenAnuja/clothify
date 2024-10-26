package controller.orderController;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CartTm;
import model.Item;
import model.Order;
import service.ServiceFactory;
import service.SuperService;
import service.custom.ItemService;
import service.custom.OrderService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class PlaceOrder implements Initializable {

    public Label lblTime;
    public Label lblDate;
    public Label lblID;
    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQTY;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblDate2;

    @FXML
    private Label lblID2;

    @FXML
    private Label lblNetTotal1;

    @FXML
    private Label lblTime2;

    @FXML
    private TableView<CartTm> tblCart;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtStock;

    @FXML
    private TextField txtUnitPrice;

    ObservableList<CartTm> cartTMS = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        if (!valuesOk()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in all fields").show();
            return;
        }
        String name = txtName.getText();
        String id = cmbItemId.getValue();
        String description = txtDesc.getText();
        int quantity = Integer.parseInt(txtQty.getText());

        int qty;
        double unitPrice;

        try {
            qty = Integer.parseInt(txtQty.getText());
            unitPrice = Double.parseDouble(txtUnitPrice.getText());
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid input format. Please check your entries.").show();
            return;
        }

        if (Integer.parseInt(txtStock.getText()) < qty) {
            new Alert(Alert.AlertType.INFORMATION, "Invalid Quantity").show();
        } else {
            double total = unitPrice * qty;
            cartTMS.add(new CartTm(name,id, description, quantity, unitPrice, total));
            System.out.println("hiiii "+cartTMS);
            calcNetTotal();
        }

        loadTable();
        tblCart.setItems(cartTMS);
        tblCart.refresh();

    }

    private void calcNetTotal (){
        Double netTotal = 0.0;

        for(CartTm cartTM : cartTMS){
            netTotal+=cartTM.getTotal();
        }
        lblNetTotal1.setText(netTotal.toString()+"/=");
    }


    @FXML
    void btnClearTableOnAction(ActionEvent event) {
        tblCart.setItems(null);
        txtName.setText(null);
        txtQty.setText(null);
        txtDesc.setText(null);
        txtUnitPrice.setText(null);
        txtEmail.setText(null);
        cmbItemId.setValue(null);
        txtStock.setText(null);
    }

    @FXML
    void btnDeleteOrderOnAction(ActionEvent event) {
        CartTm selectedCart = tblCart.getSelectionModel().getSelectedItem();

        if (selectedCart != null) {
            tblCart.getItems().remove(selectedCart);
            tblCart.refresh();

            new Alert(Alert.AlertType.INFORMATION, "Order deleted successfully!").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Please select a order to delete!").show();
        }
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        OrderService orderService = ServiceFactory.getInstance().getServiceType(ServiceType.Order);

        Order order = new Order(
                lblID.getText(),
                lblDate.getText(),
                lblTime.getText(),
                txtName.getText(),
                txtEmail.getText(),
                cmbItemId.getValue().toString(),
                txtDesc.getText(),
                txtStock.getText(),
                txtUnitPrice.getText(),
                txtQty.getText()
        );

        if(orderService.addOrder(order)){
            new Alert(Alert.AlertType.INFORMATION, "Order Added Successfully !").show();
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

    private boolean valuesOk() {
        if(txtName.getText() == null || txtName.getText().isEmpty() ||
                txtDesc.getText() == null || txtDesc.getText().isEmpty() ||
                txtUnitPrice.getText() == null || txtUnitPrice.getText().isEmpty() ||
                txtQty.getText() == null || txtQty.getText().isEmpty() ||
                txtEmail.getText() == null || txtEmail.getText().isEmpty() ||
                cmbItemId.getValue() == null){
            return false;
        }
        return true;
    }

    private void clearTextFields(){
        txtName.setText(null);
        txtDesc.setText(null);
        txtUnitPrice.setText(null);
        txtQty.setText(null);
        txtStock.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);
        loadDateTime();
        cmbItemId.setItems(itemService.getAllItemIds());

        cmbItemId.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {
            if (newVal != null){
                setTextValues(newVal);
            }
        });
    }

    public void loadTable(){
        colName.setCellValueFactory(new PropertyValueFactory<>("cusName"));
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
    }

    private void setTextValues(String newVal) {

        ItemService itemService = ServiceFactory.getInstance().getServiceType(ServiceType.Item);
        Item item = itemService.searchItem(newVal);
        txtDesc.setText(item.getDescription());
        txtStock.setText(item.getQty());
        txtUnitPrice.setText(item.getUnitPrice());
    }

    private void loadDateTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String dateNow = f.format(date);

        lblDate.setText(dateNow);

        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour() + " : " + now.getMinute() + " : " + now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

}