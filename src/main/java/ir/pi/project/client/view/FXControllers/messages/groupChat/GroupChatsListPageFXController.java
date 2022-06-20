package ir.pi.project.client.view.FXControllers.messages.groupChat;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.MessagesPageEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.util.Loop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupChatsListPageFXController {
    FXMLConfig fxmlConfig=new FXMLConfig();

    EventListener listener;
    FXControllers fxControllers;
    Loop loop;
    @FXML
    ScrollPane scroller;

    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }
    public void setLoop(Loop loop) { this.loop = loop; }

    public void update(Map<String,Integer> unreadGroupChats, List<String> alreadyReadGroupChats){
        try {

            VBox content = new VBox(5);
            scroller.setContent(content);
            scroller.setFitToWidth(true);

            if(!unreadGroupChats.isEmpty()){
                for (HashMap.Entry<String,Integer> entry : unreadGroupChats.entrySet()) {
                    FXMLLoader fxmlLoader=new FXMLLoader(GroupChatsListPageFXController.class.getResource(fxmlConfig.getGroupChatBarComponent()));
                    AnchorPane anchorPane=fxmlLoader.load();
                    content.getChildren().add(anchorPane);
                    GroupChatBarComponentFXController groupChatBarComponentFXController=fxmlLoader.getController();
                    groupChatBarComponentFXController.updateNum(entry.getValue(),true);
                    groupChatBarComponentFXController.updateGroupName(entry.getKey());
                    groupChatBarComponentFXController.setListener(listener);
                }
            }


            if(!alreadyReadGroupChats.isEmpty()){
                for (String groupChatName: alreadyReadGroupChats) {
                    FXMLLoader fxmlLoader=new FXMLLoader(GroupChatsListPageFXController.class.getResource(fxmlConfig.getGroupChatBarComponent()));
                    AnchorPane anchorPane=fxmlLoader.load();
                    content.getChildren().add(anchorPane);
                    GroupChatBarComponentFXController groupChatBarComponentFXController=fxmlLoader.getController();
                    groupChatBarComponentFXController.updateNum(-1,false);
                    groupChatBarComponentFXController.updateGroupName(groupChatName);
                    groupChatBarComponentFXController.setListener(listener);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void newGroupChat(){
        listener.listen(new MessagesPageEvent(MessagesPage.NEW_GROUP_CHAT));
    }


    public void back(){
        loop.stop();
        fxControllers.setGroupChatsListPageFXController(null);
        GraphicalAgent.back();
    }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }
}
