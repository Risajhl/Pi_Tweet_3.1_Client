package ir.pi.project.client.view.FXControllers.messages.groupChat;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.others.NewGroupChat;
import ir.pi.project.shared.event.messages.NewGroupChatEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.List;

public class NewGroupChatPageFXController {
    EventListener listener;
    FXControllers fxControllers;

    @FXML
    TextField usernameField,groupChatNameField;

    @FXML
    TextArea usernamesArea;

    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }


    public void add(){
        listener.listen(new NewGroupChatEvent(NewGroupChat.ADD,usernameField.getText()));
        usernameField.setText(null);
    }

    public void make(){
        listener.listen(new NewGroupChatEvent(NewGroupChat.MAKE,groupChatNameField.getText()));
        groupChatNameField.setText(null);
        usernamesArea.setText(null);
    }


    public void update(List<String> usernames){
        if(!usernames.isEmpty()) {
            usernamesArea.setText(null);
            for (String username : usernames) {
                if (usernamesArea.getText()==null || usernamesArea.getText().equals(""))
                    usernamesArea.setText(username);
                else
                    usernamesArea.setText(usernamesArea.getText() + "\n" + username);
            }
        }
    }




    public void back() {
        GraphicalAgent.back();
        fxControllers.setNewGroupChatPageFXController(null);
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }
}
