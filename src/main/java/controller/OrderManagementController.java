package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrderManagementController {

    @FXML
    void btnAddOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../dashBoard/dash_board.fxml"))));
        stage.show();
    }

    @FXML
    void btnDeleteOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOrderOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewOrderOnAction(ActionEvent event) {

    }

}
