package ir.pi.project.client.view.FXControllers.entering;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.enteringEvents.LogInEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import java.io.IOException;

public class LoginPageFXController {
    EventListener listener;
    @FXML
    TextField userNameField;
    @FXML
    TextField passwordField;


    public String getUserNameField() { return userNameField.getText(); }

    public void setListener(EventListener listener) { this.listener = listener; }

    public void logIn(){
        listener.listen(new LogInEvent(userNameField.getText(),passwordField.getText()));

    }

    public void back() throws IOException {
        GraphicalAgent.back();
    }
}
