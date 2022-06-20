package ir.pi.project.client.view.FXControllers.messages.groups;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.GroupsPageEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.List;

public class EditGroupPageFXController {
    EventListener listener;
    FXControllers fxControllers;


    @FXML
    TextField addUsernameField,removeUsernameField;

    @FXML
    TextArea usernamesArea;

    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }


    public void add(){
        listener.listen(new GroupsPageEvent( GroupsPage.ADD,addUsernameField.getText()));
        addUsernameField.setText(null);

    }

    public void remove(){
        listener.listen(new GroupsPageEvent( GroupsPage.REMOVE,removeUsernameField.getText()));
        removeUsernameField.setText(null);

    }


    public void update(List<String> usernames){
        usernamesArea.setText(null);
        if(!usernames.isEmpty()) {
            for (String username :
                    usernames) {
                if (usernamesArea.getText()==null || usernamesArea.getText().equals(""))
                    usernamesArea.setText(username);
                else
                    usernamesArea.setText(usernamesArea.getText() + "\n" + username);
            }
        }
    }

    public void back() {
        GraphicalAgent.back();
        fxControllers.setEditGroupPageFXController(null);
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }
}
