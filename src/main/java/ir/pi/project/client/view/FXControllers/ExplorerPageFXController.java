package ir.pi.project.client.view.FXControllers;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.otherEvents.ExplorerPageEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ExplorerPageFXController {
    EventListener listener;

    @FXML
    TextField usernameField;

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void search() {
        listener.listen(new ExplorerPageEvent(ExplorerPage.SHOW_PROFILE, usernameField.getText()));
        usernameField.setText(null);
    }

    public void world() {
        listener.listen(new ExplorerPageEvent(ExplorerPage.WORLD, usernameField.getText()));
    }

    public void back() {
        GraphicalAgent.back();
    }

    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }


}
