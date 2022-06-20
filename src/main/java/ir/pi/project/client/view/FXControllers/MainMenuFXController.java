package ir.pi.project.client.view.FXControllers;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.otherEvents.MainMenuPageEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuFXController {
   EventListener listener;
    @FXML
    Button myPageButton;
    @FXML
    Button timeLineButton;
    @FXML
    Button explorerButton;
    @FXML
    Button messagesButton;
    @FXML
    Button settingsButton;


    public void setListener(EventListener listener) { this.listener = listener; }

    public void myPage(){
     listener.listen(new MainMenuPageEvent(MainMenuPage.MY_PAGE));
    }
    public void timeLine(){ listener.listen(new MainMenuPageEvent(MainMenuPage.TIME_LINE));}
    public void explorer(){ listener.listen(new MainMenuPageEvent(MainMenuPage.EXPLORER)); }
    public void messages(){listener.listen(new MainMenuPageEvent(MainMenuPage.MESSAGES)); }
    public void settings(){listener.listen(new MainMenuPageEvent(MainMenuPage.SETTINGS));}



}
