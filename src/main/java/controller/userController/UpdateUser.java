package controller.userController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class UpdateUser {

    @FXML
    private TextField txtPasssword;
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
    void btnUpdateOnAction(ActionEvent event) {
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
                txtPasssword.getText(),
                txtUserNumber.getText()
        );

        if (userService.updateUser(user)) {
            new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully !").show();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not Update !").show();
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

    public void txtIdOnAction(ActionEvent actionEvent) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.User);
        User user = userService.searchUser(txtUserID.getText());
        setTextToValues(user);
    }

    private void setTextToValues(User user) {
        if(user!=null){
            txtUserID.setText(user.getId());
            txtUserName.setText(user.getName());
            txtUserAddress.setText(user.getAddress());
            txtUserEmail.setText(user.getEmail());
            txtPasssword.setText(user.getPassword());
            txtUserNumber.setText(user.getContact());
        }
    }

    private boolean valuesOk() {
        if(txtUserID.getText() == null || txtUserID.getText().isEmpty() ||
                txtUserName.getText() == null || txtUserName.getText().isEmpty() ||
                txtUserAddress.getText() == null || txtUserAddress.getText().isEmpty() ||
                txtUserEmail.getText() == null || txtUserEmail.getText().isEmpty() ||
                txtPasssword.getText() == null || txtPasssword.getText().isEmpty() ||
                txtUserNumber.getText() == null || txtUserNumber.getText().isEmpty()){
            return false;
        }
        return true;
    }

    private void clearTextFields(){
        txtUserID.setText(null);
        txtUserName.setText(null);
        txtUserAddress.setText(null);
        txtUserEmail.setText(null);
        txtPasssword.setText(null);
        txtUserNumber.setText(null);
    }
}
