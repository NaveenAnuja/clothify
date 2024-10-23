package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SupplierManagementController {

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../dashBoard/dash_board.fxml"))));
        stage.show();
    }
}
