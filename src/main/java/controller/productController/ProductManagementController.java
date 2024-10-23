package controller.productController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductManagementController {

    @FXML
    void btnAddProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateProductOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../dashBoard/dash_board.fxml"))));
        stage.show();
    }

}
