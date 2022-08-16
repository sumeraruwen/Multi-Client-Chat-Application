package lk.ijse.LiveChat.controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtName;
    public JFXPasswordField pwdPassword;

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtName.getText().equals("sumera") && pwdPassword.getText().equals("1234")) {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Client01Form.fxml"))));
            stage.show();

        } else if (txtName.getText().equals("viki") && pwdPassword.getText().equals("1234")) {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Client02Form.fxml"))));
            stage.show();

        } else if (txtName.getText().equals("supipi") && pwdPassword.getText().equals("1234")) {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/Client03Form.fxml"))));
            stage.show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Please Try Again").show();
        }
    }
}
