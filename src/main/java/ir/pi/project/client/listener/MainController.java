package ir.pi.project.client.listener;

import ir.pi.project.client.view.FXControllers.entering.WelcomePageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.others.Buttons;
import ir.pi.project.shared.event.Event;
import ir.pi.project.shared.model.Tweet;
import ir.pi.project.shared.model.help.HelpMessage;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.response.Response;
import ir.pi.project.shared.response.ResponseVisitor;
import ir.pi.project.shared.util.Loop;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainController implements ResponseVisitor {
    private static final Object lock=new Object();
    private final EventSender eventSender;
    private final List<Event> events;
    private final Loop loop;
    private final GraphicalAgent graphicalAgent;
    private int authToken;

    public MainController(EventSender eventSender, WelcomePageFXController welcomePageController) {
        this.eventSender = eventSender;
        this.events = new LinkedList<>();
        this.loop = new Loop(10, this::sendEvents);
        this.graphicalAgent = new GraphicalAgent(this::addEvent,welcomePageController);
        this.authToken=0;
    }

    public void start() {
        loop.start();
    }

    private void addEvent(Event event) {

        synchronized (lock) {
            event.setAuthToken(authToken);
            events.add(event);
        }
    }

    private void sendEvents() {
        List<Event> temp;
        synchronized (lock) {
            temp = new LinkedList<>(events);
            events.clear();

            for (Event event : temp) {
                Response response = eventSender.send(event);
                response.visit(this);
            }
        }
    }

    @Override
    public void showMessage(String s) {
            JOptionPane.showMessageDialog(null, s);
    }

    @Override
    public void loadWelcomePage() {
        graphicalAgent.gotoWelcomePage();
        synchronized (events) { events.clear(); }
    }


    @Override
    public void loadSignUpPage() {
        graphicalAgent.gotoSignUpPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadLoginPage() {
        graphicalAgent.gotoLoginPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadMainMenu(int authToken) {
        this.authToken=authToken;
        graphicalAgent.gotoMainMenuPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadMyPage(HelpUser helpUser) {
        graphicalAgent.gotoMyPage(helpUser);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadEditProfilePage(HelpUser helpUser) {
        graphicalAgent.gotoEditProfilePage(helpUser);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadNotificationsPage(List<String> list) {
        graphicalAgent.gotoNotificationsPage(list);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadPendingListPage(List<String> list) {
        graphicalAgent.gotoPendingListPage(list);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadBlackList(Map<String,String> blacklist) {
        graphicalAgent.gotoBlackList(blacklist);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadRequestsList(Map<String,String> list) {
        graphicalAgent.gotoRequestsList(list);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadFollowersList(Map<String,String> list) {
        graphicalAgent.gotoFollowersList(list);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadFollowingsList(Map<String,String> list) {
        graphicalAgent.gotoFollowingsList(list);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadTweet(Tweet tweet,HelpUser writer,HelpUser retweetedBy) {
        graphicalAgent.loadTweet(tweet,writer,retweetedBy);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadForwardPage() {
        graphicalAgent.gotoForwardPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadExplorerPage() {
        graphicalAgent.gotoExplorerPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadProfilePage(HelpUser user,String followButtonText,String blockButtonText,String muteButtonText,String blockStateText,String followingStateText,String lastSeenText) {
        graphicalAgent.gotoProfilePage(user,followButtonText,blockButtonText,muteButtonText,blockStateText,followingStateText,lastSeenText);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadMessagesPage() {
        graphicalAgent.gotoMessagesPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadMultipleToUsersPage(List<String> usernames) {
        graphicalAgent.gotoMultipleToUsersPage(usernames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadMultipleToGroupsPage(List<String> groupNames) {
        graphicalAgent.gotoMultipleToGroupsPage(groupNames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadGroupsPage(List<String> groupNames) {
        graphicalAgent.gotoGroupsPage(groupNames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadEditGroupPage(List<String> groupNames) {
        graphicalAgent.gotoEditGroupPage(groupNames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadDirectChatsListPage(Map<String,Integer> unreadUsernames, List<String> alreadyReadUsernames) {
        graphicalAgent.gotoDirectChatsListPage(unreadUsernames,alreadyReadUsernames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadGroupChatsListPage(Map<String,Integer> unreadGroupChats, List<String> alreadyReadGroupChats) {
        graphicalAgent.gotoToGroupChatsListPage(unreadGroupChats,alreadyReadGroupChats);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadNewGroupChatPage(List<String > usernames) {
        graphicalAgent.gotoNewGroupChatPage(usernames);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadChatPage(List<HelpMessage> helpMessages,String name,boolean isDirect, int myId) {
        graphicalAgent.gotoChatPage(helpMessages,name,isDirect,myId);
        synchronized (events) { events.clear(); }
    }




    @Override
    public void loadSettingsPage() {
        graphicalAgent.gotoSettingsPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadDeleteAccountPage() {
        graphicalAgent.gotoDeleteAccountPage();
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadPrivacySettingsPage(HelpUser helpUser) {
        graphicalAgent.gotoPrivacySettingsPage(helpUser);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void loadCurrentPassword() {
        graphicalAgent.loadCurrentPassword();
        synchronized (events) { events.clear(); }
    }
    @Override
    public void loadNewPassword() {
        graphicalAgent.loadNewPassword();
        synchronized (events) { events.clear(); }
    }


    @Override
    public void loadButton(Buttons buttons) {
        graphicalAgent.loadButton(buttons);
        synchronized (events) { events.clear(); }
    }

    @Override
    public void nothing() {
        System.out.println(":)");
    }


    public void close(){
        graphicalAgent.close();
    }

}
