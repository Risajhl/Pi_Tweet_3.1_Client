package ir.pi.project.client.view.FXControllers.messages;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.MessagesPageEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MessagesPageFXController {
    EventListener listener;

    @FXML
    Button groupChatsButton,directChatsButton,toUsersButton,toGroupsButton;


    public void setListener(EventListener listener) { this.listener = listener; }

    public void groupChats(){listener.listen(new MessagesPageEvent(MessagesPage.GROUP_CHATS));}
    public void directChats(){listener.listen(new MessagesPageEvent(MessagesPage.DIRECT_CHATS)); }
    public void toUsers(){listener.listen(new MessagesPageEvent(MessagesPage.TO_USERS)); }
    public void toGroups(){ listener.listen(new MessagesPageEvent(MessagesPage.TO_GROUPS)); }
    public void showGroups(){ listener.listen(new MessagesPageEvent(MessagesPage.SHOW_GROUPS)); }
    public void savedMessages(){listener.listen(new MessagesPageEvent(MessagesPage.SAVED_MESSAGES));}
    public void savedTweets(){listener.listen(new MessagesPageEvent(MessagesPage.SAVED_TWEETS));}


    public void back() {
        GraphicalAgent.back();
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }
}
