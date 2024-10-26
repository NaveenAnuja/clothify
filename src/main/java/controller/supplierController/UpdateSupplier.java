package controller.supplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Supplier;
import model.User;
import service.ServiceFactory;
import service.custom.SupplierService;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class UpdateSupplier {

    @FXML
    private TextField txtSupplierItem;

    @FXML
    private TextField txtsupplierAddress;

    @FXML
    private TextField txtsupplierCompany;

    @FXML
    private TextField txtsupplierEmail;

    @FXML
    private TextField txtsupplierID;

    @FXML
    private TextField txtsupplierName;

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/supplierManagement/supplierManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean b = valuesOk();
        if(!b){
            new Alert(Alert.AlertType.ERROR,"Please fill in all fields").show();
            return;
        }
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);

        Supplier supplier = new Supplier(
                txtsupplierID.getText(),
                txtsupplierName.getText(),
                txtSupplierItem.getText(),
                txtsupplierAddress.getText(),
                txtsupplierEmail.getText(),
                txtsupplierCompany.getText()
        );

        if (supplierService.updateSupplier(supplier)) {
            new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully !").show();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Updated !").show();
        }
    }

    private void clearTextFields(){
        txtsupplierID.setText(null);
        txtsupplierName.setText(null);
        txtSupplierItem.setText(null);
        txtsupplierAddress.setText(null);
        txtsupplierEmail.setText(null);
        txtsupplierCompany.setText(null);
    }

    private boolean valuesOk() {
        if(txtsupplierID.getText() == null || txtsupplierID.getText().isEmpty() ||
                txtsupplierName.getText() == null || txtsupplierName.getText().isEmpty() ||
                txtSupplierItem.getText() == null || txtSupplierItem.getText().isEmpty() ||
                txtsupplierAddress.getText() == null || txtsupplierAddress.getText().isEmpty() ||
                txtsupplierEmail.getText() == null || txtsupplierEmail.getText().isEmpty() ||
                txtsupplierCompany.getText() == null || txtsupplierCompany.getText().isEmpty()){
            return false;
        }
        return true;
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        Supplier supplier = supplierService.searchSupplier(txtsupplierID.getText());
        setTextToValues(supplier);
    }

    private void setTextToValues(Supplier supplier) {
        if(supplier!=null){
            txtsupplierID.setText(supplier.getId());
            txtsupplierName.setText(supplier.getName());
            txtSupplierItem.setText(supplier.getItem());
            txtsupplierAddress.setText(supplier.getAddress());
            txtsupplierEmail.setText(supplier.getEmail());
            txtsupplierCompany.setText(supplier.getCompany());
        }
    }
}
