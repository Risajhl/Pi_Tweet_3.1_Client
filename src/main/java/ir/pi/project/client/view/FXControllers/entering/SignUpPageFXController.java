package ir.pi.project.client.view.FXControllers.entering;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.enteringEvents.SignUpEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpPageFXController implements Initializable {
    EventListener listener;

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField userNameField;
    @FXML
    TextField passwordField;
    @FXML
    TextField emailField;
    @FXML
    TextField birthDateField;
    @FXML
    TextField phoneNumberField;

    @FXML
    Label signUpError;

    @FXML
    RadioButton EPBRadioButton;

    public String getUserNameField() { return userNameField.getText(); }

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void signItUp(){
        listener.listen(new SignUpEvent(firstNameField.getText(),
                lastNameField.getText(),
                userNameField.getText(),
                passwordField.getText(),
                emailField.getText(),
                phoneNumberField.getText(),
                birthDateField.getText(),
                EPBRadioButton.isSelected()));

    }

    public void back() throws IOException { GraphicalAgent.back(); }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
