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

public class GroupsPageFXController {
    EventListener listener;
    FXControllers fxControllers;

    @FXML
    TextField editGroupField,newGroupField;

    @FXML
    TextArea groupsListArea;


    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }


    public void update(List<String> groupNames){
        System.out.println(groupNames+":000");
        StringBuilder text= new StringBuilder();
        if(!groupNames.isEmpty()) {
            for (String groupName : groupNames) {
                text.append(groupName).append("\n");
            }
        }
        groupsListArea.setText(text.toString());
    }

    public void edit(){
        listener.listen(new GroupsPageEvent( GroupsPage.EDIT,editGroupField.getText()));
        editGroupField.setText(null);
    }

    public void make(){
        listener.listen(new GroupsPageEvent( GroupsPage.MAKE,newGroupField.getText()));
        newGroupField.setText(null);
    }


    public void back() {
        GraphicalAgent.back();
        fxControllers.setGroupsPageFXController(null);
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }
}
