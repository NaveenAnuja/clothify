package controller.userController;

import entity.UserEntity;
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
import service.SuperService;
import service.custom.UserService;
import util.ServiceType;

import java.io.IOException;

public class DeleteUser {

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
    void btnDeleteOnAction(ActionEvent event) {
        UserService userService = ServiceFactory.getInstance().getServiceType(ServiceType.User);

        if(userService.deleteUser(txtUserID.getText())){
            new Alert(Alert.AlertType.INFORMATION, "User delete Successfully !").show();
            clearTextFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Not deleted !").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event){
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
        editText();
    }

    private void editText() {
        txtUserName.setEditable(false);
        txtUserEmail.setEditable(false);
        txtUserAddress.setEditable(false);
        txtUserNumber.setEditable(false);
    }

    private void setTextToValues(User user) {
        if(user!=null){
            txtUserID.setText(user.getId());
            txtUserName.setText(user.getName());
            txtUserAddress.setText(user.getAddress());
            txtUserEmail.setText(user.getEmail());
            txtUserNumber.setText(user.getContact());
        }
    }

    private void clearTextFields(){
        txtUserID.setText(null);
        txtUserName.setText(null);
        txtUserAddress.setText(null);
        txtUserEmail.setText(null);
        txtUserNumber.setText(null);
    }
}
