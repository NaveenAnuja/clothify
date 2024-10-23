package controller.userController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class UserManagementController {

    @FXML
    void btnAddCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/add_User.fxml"))));
        stage.show();
    }

    @FXML
    void btnUpdateCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/update_User.fxml"))));
        stage.show();
    }

    @FXML
    void btnSearchCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/search_User.fxml"))));
        stage.show();
    }

    @FXML
    void btnDeleteCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/delete_User.fxml"))));
        stage.show();
    }

    @FXML
    void btnViewCustomerOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/view_User.fxml"))));
        stage.show();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/dashBoard/dash_board.fxml"))));
        stage.show();
    }
}
