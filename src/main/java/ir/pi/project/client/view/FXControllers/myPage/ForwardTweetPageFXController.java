package ir.pi.project.client.view.FXControllers.myPage;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.myPageEvents.ForwardTweetEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ForwardTweetPageFXController {
    EventListener listener;

    @FXML
    Label errorLabel;

    @FXML
    TextField usernameField;

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void forward(){
        listener.listen(new ForwardTweetEvent(usernameField.getText()));
        usernameField.setText(null);

    }

    public void back(){
        GraphicalAgent.back();
    }
    public void mainMenu(){
        listener.listen(new GetMainMenu());
    }
}
