package ir.pi.project.client.view.FXControllers.messages.groupChat;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.ShowChatEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GroupChatBarComponentFXController {
    EventListener listener;
    @FXML
    Label unreadNumLabel,groupNameLabel;

    public void setListener(EventListener listener) { this.listener = listener; }

    public void updateNum(int num, boolean isOn){
        unreadNumLabel.setVisible(isOn);
        unreadNumLabel.setText(num+"");
    }
    public void updateGroupName(String groupName){
        groupNameLabel.setText(groupName);
    }

    public void showGroupChat(){
        listener.listen(new ShowChatEvent(MessagesPage.GROUP_CHATS,groupNameLabel.getText()));
    }

}
