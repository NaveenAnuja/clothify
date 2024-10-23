package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {

    @FXML
    public void btnUserManageOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../management/userManagement.fxml"))));
        stage.show();
    }
    @FXML
    void btnOrderManageOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../management/orderManagement.fxml"))));
        stage.show();
    }

    @FXML
    void btnProductManageOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../management/productManagement.fxml"))));
        stage.show();
    }

    @FXML
    void btnSupplierManageOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../management/supplierManagement.fxml"))));
        stage.show();
    }
}