package ir.pi.project.client.view;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.*;
import ir.pi.project.client.view.FXControllers.entering.WelcomePageFXController;
import ir.pi.project.client.view.pageLoader.*;
import ir.pi.project.shared.enums.others.Buttons;
import ir.pi.project.shared.event.otherEvents.CloseEvent;
import ir.pi.project.shared.model.Tweet;
import ir.pi.project.shared.model.help.HelpMessage;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.util.Loop;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;

public class GraphicalAgent {
    public List<Loop> loops;
    public static Stage stage;
    public static Stack<Scene> scenes = new Stack<>();

    EnteringLoader enteringPagesLoader;
    MyPageLoader myPageLoader;
    SettingsLoader settingsLoader;
    MessagesLoader messagesLoader;
    OthersLoader othersLoader;

    EventListener listener;

    public GraphicalAgent(EventListener listener, WelcomePageFXController welcomePageFXController) {
       this.listener=listener;
        this.loops = new ArrayList<>();

        welcomePageFXController.setListener(listener);

        FXControllers fxControllers = new FXControllers();

        enteringPagesLoader = new EnteringLoader(fxControllers, loops, listener);
        myPageLoader = new MyPageLoader(fxControllers, loops, listener);
        settingsLoader = new SettingsLoader(fxControllers, loops, listener);
        messagesLoader = new MessagesLoader(fxControllers, loops, listener);
        othersLoader = new OthersLoader(fxControllers, loops, listener);
    }


    public static void back() {
        stage.setScene(scenes.pop());
        stage.setScene(scenes.peek());
    }


    public void gotoWelcomePage() {
        enteringPagesLoader.loadWelcomePage();
    }

    public void gotoSignUpPage() {
        enteringPagesLoader.loadSignUpPage();
    }

    public void gotoLoginPage() {
        enteringPagesLoader.loadLogInPage();
    }


    public void gotoMainMenuPage() {
        othersLoader.loadMainMenu();
    }

    public void gotoMyPage(HelpUser helpUser) {
        myPageLoader.loadMyPage(helpUser);
    }

    public void gotoEditProfilePage(HelpUser helpUser) {
        myPageLoader.loadEditProfilePage(helpUser);
    }

    public void gotoNotificationsPage(List<String> notifications) {
        myPageLoader.loadNotificationsPage(notifications);
    }

    public void gotoPendingListPage(List<String> pendingList) {
        myPageLoader.loadPendingListPage(pendingList);
    }

    public void gotoBlackList(Map<String,String> blacklist) {
        myPageLoader.loadBlackListPage(blacklist);
    }

    public void gotoRequestsList(Map<String,String> request) {
        myPageLoader.loadRequestsListPage(request);
    }

    public void gotoFollowersList(Map<String,String> followers) {
        myPageLoader.loadFollowersList(followers);
    }

    public void gotoFollowingsList(Map<String,String> followings) {
        myPageLoader.loadFollowingsListPage(followings);
    }

    public void loadTweet(Tweet tweet,HelpUser writer,HelpUser retweetedBy) {
       othersLoader.loadTweet(tweet,writer,retweetedBy);
    }

    public void gotoForwardPage() {
        othersLoader.loadForwardPage();
    }

    public void gotoExplorerPage() {
        othersLoader.loadExplorerPage();
    }

    public void gotoProfilePage(HelpUser user, String followButtonText, String blockButtonText, String muteButtonText, String blockStateText, String followingStateText, String lastSeenText) {
        othersLoader.loadProfilePage(user,followButtonText,blockButtonText,muteButtonText,blockStateText,followingStateText,lastSeenText);
    }

    public void gotoSettingsPage() {
        settingsLoader.loadSettingsPage();
    }

    public void gotoPrivacySettingsPage(HelpUser helpUser) {
        settingsLoader.loadPrivacySettingsPage(helpUser);
    }

    public void loadCurrentPassword() {
        settingsLoader.loadCurrentPassword();
    }

    public void loadNewPassword() {
        settingsLoader.loadNewPassword();
    }

    public void gotoDeleteAccountPage() {
        settingsLoader.loadDeleteAccountPage();
    }


    public void gotoMessagesPage() {
        messagesLoader.loadMessagesPage();
    }

    public void gotoMultipleToUsersPage(List<String> usernames) {
        messagesLoader.loadMultipleToUsersPage(usernames);
    }

    public void gotoMultipleToGroupsPage(List<String> groupNames) {
        messagesLoader.loadMultipleToGroupsPage(groupNames);
    }

    public void gotoGroupsPage(List<String> groupNames) {
        messagesLoader.loadGroupsPage(groupNames);
    }

    public void gotoEditGroupPage(List<String> usernames) {
        messagesLoader.loadEditGroupPage(usernames);
    }


    public void gotoDirectChatsListPage(Map<String, Integer> unreadUsernames, List<String> alreadyReadUsernames) {
        messagesLoader.loadDirectChatsListPage(unreadUsernames, alreadyReadUsernames);
    }

    public void gotoToGroupChatsListPage(Map<String, Integer> unreadGroupChats, List<String> alreadyReadGroupChats) {
        messagesLoader.loadGroupChatsListPage(unreadGroupChats, alreadyReadGroupChats);
    }

    public void gotoNewGroupChatPage(List<String> usernames) {
        messagesLoader.loadNewGroupChatPage(usernames);
    }


    public void gotoChatPage(List<HelpMessage> helpMessages, String name, boolean isDirect, int myId) {
        messagesLoader.loadChatPage(helpMessages, name, isDirect, myId);
    }


    public void loadButton(Buttons buttons) {
        othersLoader.loadButtons(buttons);
    }



    public void close() {
        listener.listen(new CloseEvent());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

}
