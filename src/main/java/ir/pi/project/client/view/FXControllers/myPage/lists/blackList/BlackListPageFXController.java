package ir.pi.project.client.view.FXControllers.myPage.lists.blackList;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.util.Map;

public class BlackListPageFXController {
    FXMLConfig fxmlConfig=new FXMLConfig();
    EventListener listener;

    @FXML
    ScrollPane scroller;


    public void setListener(EventListener listener) { this.listener = listener; }

    public void update(Map<String,String> blacklist){
        try {
            VBox content = new VBox(5);
            scroller.setContent(content);
            scroller.setFitToWidth(true);

            for (Map.Entry<String, String> entry : blacklist.entrySet()){
                FXMLLoader fxmlLoader = new FXMLLoader(BlackListPageFXController.class.getResource(fxmlConfig.getBlacklistComponent()));
                AnchorPane anchorPane = fxmlLoader.load();
                content.getChildren().add(anchorPane);
                BlackListComponentFXController blackListComponentFXController = fxmlLoader.getController();
                blackListComponentFXController.update(entry.getKey(),entry.getValue());
                blackListComponentFXController.setContent(content);
                blackListComponentFXController.setListener(listener);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void back(){ GraphicalAgent.back(); }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }
}
