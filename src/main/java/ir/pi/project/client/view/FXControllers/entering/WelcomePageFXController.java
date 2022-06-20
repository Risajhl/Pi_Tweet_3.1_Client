package ir.pi.project.client.view.FXControllers.entering;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.enteringEvents.WelcomePageEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class WelcomePageFXController {
    EventListener listener;

    @FXML
    Button signUpButton;
    @FXML
    Button logInButton;


    public void setListener(EventListener listener) { this.listener = listener; }

    public void SignUp(){
        listener.listen(new WelcomePageEvent(WelcomePage.SIGN_UP));
    }

    public void logIn(){
        listener.listen(new WelcomePageEvent(WelcomePage.LOG_IN));
    }



}
