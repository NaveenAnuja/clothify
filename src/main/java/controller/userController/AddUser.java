package controller.userController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;
import service.ServiceFactory;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddUser {

    public TextField txtPassword;
    @FXML
    private TextField txtUserAddress;

    @FXML
    private TextField txtUserEmail;

    @FXML
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserNumber;

    @FXML
    void btnAddOnAction(ActionEvent event) {

        boolean b = valuesOk();
        if(!b){
            new Alert(Alert.AlertType.ERROR,"Please fill in all fields").show();
            return;
        }
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.User);

        User user = new User(
                txtUserID.getText(),
                txtUserName.getText(),
                txtUserAddress.getText(),
                txtUserEmail.getText(),
                txtPassword.getText(),
                txtUserNumber.getText()
        );

        if (userService.addUser(user)) {
            new Alert(Alert.AlertType.INFORMATION, "User Added Successfully !").show();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Added !").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/userManagement/userManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

    private void clearTextFields(){
        txtUserID.setText(null);
        txtUserName.setText(null);
        txtUserAddress.setText(null);
        txtUserEmail.setText(null);
        txtPassword.setText(null);
        txtUserNumber.setText(null);
    }

    private boolean valuesOk() {
        if(txtUserID.getText() == null || txtUserID.getText().isEmpty() ||
                txtUserName.getText() == null || txtUserName.getText().isEmpty() ||
                txtUserAddress.getText() == null || txtUserAddress.getText().isEmpty() ||
                txtUserEmail.getText() == null || txtUserEmail.getText().isEmpty() ||
                txtPassword.getText() == null || txtPassword.getText().isEmpty() ||
                txtUserNumber.getText() == null || txtUserNumber.getText().isEmpty()){
            return false;
        }
        return true;
    }
}
