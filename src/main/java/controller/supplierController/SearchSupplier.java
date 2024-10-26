package controller.supplierController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Supplier;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;

public class SearchSupplier {

    @FXML
    private TextField txtsupplierAddress;

    @FXML
    private TextField txtsupplierCompany;

    @FXML
    private TextField txtsupplierEmail;

    @FXML
    private TextField txtsupplierID;

    @FXML
    private TextField txtsupplierItem;

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

    public void txtIdOnAction(ActionEvent actionEvent) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.Supplier);
        Supplier supplier = supplierService.searchSupplier(txtsupplierID.getText());
        setTextToValues(supplier);
        editText();
    }

    private void editText() {
        txtsupplierName.setEditable(false);
        txtsupplierItem.setEditable(false);
        txtsupplierAddress.setEditable(false);
        txtsupplierEmail.setEditable(false);
        txtsupplierCompany.setEditable(false);
    }

    private void setTextToValues(Supplier supplier) {
        if(supplier!=null){
            txtsupplierID.setText(supplier.getId());
            txtsupplierName.setText(supplier.getName());
            txtsupplierItem.setText(supplier.getItem());
            txtsupplierAddress.setText(supplier.getAddress());
            txtsupplierEmail.setText(supplier.getEmail());
            txtsupplierCompany.setText(supplier.getCompany());
        }
    }
}
