package ir.pi.project.client.view.FXControllers.messages.direct;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.otherEvents.ExplorerPageEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DirectChatBarComponentFXController {
    EventListener listener;
    @FXML
    Label unreadNumLabel,usernameLabel;

    public void setListener(EventListener listener) { this.listener = listener; }

    public void updateNum(int num, boolean isOn){
        unreadNumLabel.setVisible(isOn);
        unreadNumLabel.setText(num+"");
    }
    public void updateUsername(String username){
        usernameLabel.setText(username);
    }

    public void showUser(){ listener.listen(new ExplorerPageEvent(ExplorerPage.SHOW_PROFILE,usernameLabel.getText())); }

}
