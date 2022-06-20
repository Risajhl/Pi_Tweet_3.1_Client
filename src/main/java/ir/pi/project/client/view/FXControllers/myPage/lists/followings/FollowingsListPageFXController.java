package ir.pi.project.client.view.FXControllers.myPage.lists.followings;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.FXControllers.myPage.lists.followers.FollowersListPageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.util.Loop;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.Map;

public class FollowingsListPageFXController {
    FXMLConfig fxmlConfig=new FXMLConfig();
    EventListener listener;
    Loop loop;
    FXControllers fxControllers;


    @FXML
    ScrollPane scroller;


    public void setListener(EventListener listener) { this.listener = listener; }
    public void setLoop(Loop loop) { this.loop = loop; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }


    public void update(Map<String,String> followings) {
        try {

            VBox content = new VBox(5);
            scroller.setContent(content);
            scroller.setFitToWidth(true);

            for (Map.Entry<String, String> entry : followings.entrySet()){
                System.out.println(entry.getKey()+"...");
                FXMLLoader fxmlLoader=new FXMLLoader(FollowersListPageFXController.class.getResource(fxmlConfig.getFollowingsComponent()));
                AnchorPane anchorPane=fxmlLoader.load();
                content.getChildren().add(anchorPane);
                FollowingsComponentFXController followingsComponentFXController=fxmlLoader.getController();
                followingsComponentFXController.setContent(content);
                followingsComponentFXController.update(entry.getKey(),entry.getValue());
                followingsComponentFXController.setListener(listener);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public void back(){
        GraphicalAgent.back();
        loop.stop();
        fxControllers.setFollowingsListPageFXController(null);
    }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }
}
