package ir.pi.project.client.view.FXControllers.settings;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.event.settingsEvents.DeleteAccountEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DeleteAccountPageFXController {

    EventListener listener;

    @FXML
    TextField passwordField;

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void delete(){listener.listen(new DeleteAccountEvent(passwordField.getText()));}

    public void back(){ GraphicalAgent.back(); }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }

}
