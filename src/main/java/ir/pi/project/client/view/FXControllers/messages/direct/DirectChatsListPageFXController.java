package ir.pi.project.client.view.FXControllers.messages.direct;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.ShowChatEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.util.Loop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DirectChatsListPageFXController {
    FXMLConfig fxmlConfig=new FXMLConfig();

    EventListener listener;
    FXControllers fxControllers;
    Loop loop;

    @FXML
    ScrollPane scroller;

    @FXML
    TextField usernameField;

    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }
    public void setLoop(Loop loop) { this.loop = loop; }


    public void openChat(){
        listener.listen(new ShowChatEvent(MessagesPage.DIRECT_CHATS,usernameField.getText()));
        usernameField.setText(null);
    }


    public void update(Map<String,Integer> unreadUsernames, List<String> alreadyReadUsernames) {
        try {

            VBox content = new VBox(5);
            scroller.setContent(content);
            scroller.setFitToWidth(true);

            if(!unreadUsernames.isEmpty()){
                for (HashMap.Entry<String,Integer> entry : unreadUsernames.entrySet()) {
                    FXMLLoader fxmlLoader=new FXMLLoader(DirectChatsListPageFXController.class.getResource(fxmlConfig.getDirectChatBarComponent()));
                    AnchorPane anchorPane=fxmlLoader.load();
                    content.getChildren().add(anchorPane);
                    DirectChatBarComponentFXController directChatBarComponentFXController=fxmlLoader.getController();
                    directChatBarComponentFXController.updateNum(entry.getValue(), true);
                    directChatBarComponentFXController.updateUsername(entry.getKey());
                    directChatBarComponentFXController.setListener(listener);
                }
            }


            if(!alreadyReadUsernames.isEmpty()){
                for (String username: alreadyReadUsernames) {
                    FXMLLoader fxmlLoader=new FXMLLoader(DirectChatsListPageFXController.class.getResource(fxmlConfig.getDirectChatBarComponent()));
                    AnchorPane anchorPane=fxmlLoader.load();
                    content.getChildren().add(anchorPane);
                    DirectChatBarComponentFXController directChatBarComponentFXController=fxmlLoader.getController();
                    directChatBarComponentFXController.updateNum(-1, false);
                    directChatBarComponentFXController.updateUsername(username);
                    directChatBarComponentFXController.setListener(listener);
                }
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void back(){
        loop.stop();
        fxControllers.setDirectChatsListPageFXController(null);
        GraphicalAgent.back();
    }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }


}
