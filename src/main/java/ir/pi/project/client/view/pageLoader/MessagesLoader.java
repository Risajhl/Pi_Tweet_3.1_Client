package ir.pi.project.client.view.pageLoader;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.FXControllers.messages.ChatPageFXController;
import ir.pi.project.client.view.FXControllers.messages.MessagesPageFXController;
import ir.pi.project.client.view.FXControllers.messages.direct.DirectChatsListPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groupChat.GroupChatsListPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groupChat.NewGroupChatPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groups.EditGroupPageFXController;
import ir.pi.project.client.view.FXControllers.messages.groups.GroupsPageFXController;
import ir.pi.project.client.view.FXControllers.messages.multipleMessaging.MultipleToGroupsPageFXController;
import ir.pi.project.client.view.FXControllers.messages.multipleMessaging.MultipleToUsersPageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.enums.others.MessagesUpReqs;
import ir.pi.project.shared.event.messages.MessagesUpReqEvent;
import ir.pi.project.shared.model.help.HelpMessage;
import ir.pi.project.shared.util.Loop;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MessagesLoader {
    private final FXMLConfig fxmlConfig = new FXMLConfig();
    FXControllers fxControllers;
    List<Loop> loops;
    Stage stage;
    List<Scene> scenes;
    EventListener listener;

    public MessagesLoader(FXControllers fxControllers, List<Loop> loops, EventListener listener) {
        this.fxControllers = fxControllers;
        this.loops = loops;
        this.stage = GraphicalAgent.stage;
        this.scenes = GraphicalAgent.scenes;
        this.listener = listener;
    }

    public void loadMessagesPage() {
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getMessagesPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    MessagesPageFXController messagesPageFXController = fxmlLoader.getController();
                    messagesPageFXController.setListener(listener);
                });
    }


    public void loadMultipleToUsersPage(List<String> usernames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getMultipleToUsersPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getMultipleToUsersPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        MultipleToUsersPageFXController multipleToUsersPageFXController = fxmlLoader.getController();
                        multipleToUsersPageFXController.setListener(listener);
                        multipleToUsersPageFXController.setFxControllers(fxControllers);
                        fxControllers.setMultipleToUsersPageFXController(multipleToUsersPageFXController);
                    } else fxControllers.getMultipleToUsersPageFXController().updateUsername(usernames);
                });
    }

    public void loadMultipleToGroupsPage(List<String> groupNames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getMultipleToGroupsPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getMultipleToGroupsPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        MultipleToGroupsPageFXController multipleToGroupsPageFXController = fxmlLoader.getController();
                        multipleToGroupsPageFXController.setListener(listener);
                        multipleToGroupsPageFXController.setFxControllers(fxControllers);
                        fxControllers.setMultipleToGroupsPageFXController(multipleToGroupsPageFXController);
                    } else fxControllers.getMultipleToGroupsPageFXController().updateGroupNames(groupNames);
                });
    }

    public void loadGroupsPage(List<String> groupNames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getGroupsPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getShowGroupsPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        GroupsPageFXController groupsPageFXController = fxmlLoader.getController();
                        groupsPageFXController.setListener(listener);
                        groupsPageFXController.setFxControllers(fxControllers);
                        groupsPageFXController.update(groupNames);
                        fxControllers.setGroupsPageFXController(groupsPageFXController);
                    } else fxControllers.getGroupsPageFXController().update(groupNames);
                });
    }

    public void loadEditGroupPage(List<String> usernames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getEditGroupPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getEditGroupPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        EditGroupPageFXController editGroupPageFXController = fxmlLoader.getController();
                        editGroupPageFXController.setListener(listener);
                        editGroupPageFXController.setFxControllers(fxControllers);
                        editGroupPageFXController.update(usernames);
                        fxControllers.setEditGroupPageFXController(editGroupPageFXController);
                    } else fxControllers.getEditGroupPageFXController().update(usernames);
                });
    }

    public void loadDirectChatsListPage(Map<String, Integer> unreadUsernames, List<String> alreadyReadUsernames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getDirectChatsListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getDirectChatsPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        DirectChatsListPageFXController directChatsListPageFXController = fxmlLoader.getController();
                        directChatsListPageFXController.setListener(listener);
                        directChatsListPageFXController.setFxControllers(fxControllers);
                        directChatsListPageFXController.update(unreadUsernames, alreadyReadUsernames);
                        fxControllers.setDirectChatsListPageFXController(directChatsListPageFXController);
                        Loop directsLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new MessagesUpReqEvent(MessagesUpReqs.DIRECTS));
                            }
                        });
                        loops.add(directsLoop);
                        directChatsListPageFXController.setLoop(directsLoop);
                        directsLoop.start();
                    } else {
                        fxControllers.getDirectChatsListPageFXController().update(unreadUsernames, alreadyReadUsernames);
                    }   });
    }

    public void loadGroupChatsListPage(Map<String, Integer> unreadGroupChats, List<String> alreadyReadGroupChats) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getGroupChatsListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getGroupChatsPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        GroupChatsListPageFXController groupChatsListPageFXController = fxmlLoader.getController();
                        groupChatsListPageFXController.setListener(listener);
                        groupChatsListPageFXController.setFxControllers(fxControllers);
                        groupChatsListPageFXController.update(unreadGroupChats, alreadyReadGroupChats);
                        fxControllers.setGroupChatsListPageFXController(groupChatsListPageFXController);
                        Loop groupChatsLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new MessagesUpReqEvent(MessagesUpReqs.GROUP_CHATS));
                            }
                        });
                        loops.add(groupChatsLoop);
                        groupChatsListPageFXController.setLoop(groupChatsLoop);
                        groupChatsLoop.start();
                    } else
                        fxControllers.getGroupChatsListPageFXController().update(unreadGroupChats, alreadyReadGroupChats);
                });
    }

    public void loadNewGroupChatPage(List<String> usernames) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getNewGroupChatPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getNewGroupChatPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        NewGroupChatPageFXController newGroupChatPageFXController = fxmlLoader.getController();
                        newGroupChatPageFXController.setListener(listener);
                        newGroupChatPageFXController.setFxControllers(fxControllers);
                        newGroupChatPageFXController.update(usernames);
                        fxControllers.setNewGroupChatPageFXController(newGroupChatPageFXController);
                    } else fxControllers.getNewGroupChatPageFXController().update(usernames);
                });
    }


    public void loadChatPage(List<HelpMessage> helpMessages, String name, boolean isDirect, int myId) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getChatPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getShowChat()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        ChatPageFXController chatPageFXController = fxmlLoader.getController();
                        if (isDirect) chatPageFXController.setMessagesPage(MessagesPage.DIRECT_CHATS);
                        else {
                            chatPageFXController.setMessagesPage(MessagesPage.GROUP_CHATS);
                            chatPageFXController.setLeaveButton(true);
                            chatPageFXController.setAddMemberButton(true);
                            chatPageFXController.setAddMemberField(true);
                        }
                        chatPageFXController.setName(name);
                        chatPageFXController.setFxControllers(fxControllers);
                        chatPageFXController.setListener(listener);
                        chatPageFXController.jumpToBottom();
                        updateMessages(chatPageFXController, helpMessages, myId);
                        fxControllers.setChatPageFXController(chatPageFXController);
                        Loop chatLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                if (isDirect)
                                    listener.listen(new MessagesUpReqEvent(MessagesUpReqs.DIRECT_CHAT_PAGE));
                                else listener.listen(new MessagesUpReqEvent(MessagesUpReqs.GROUP_CHAT_PAGE));
                            }
                        });
                        loops.add(chatLoop);
                        chatPageFXController.setLoop(chatLoop);
                        chatLoop.start();
                    } else updateMessages(fxControllers.getChatPageFXController(), helpMessages, myId);
                });
    }

    private void updateMessages(ChatPageFXController chatPageFXController, List<HelpMessage> helpMessages, int myId) {
        chatPageFXController.empty();
        for (HelpMessage helpMessage : helpMessages) {
            String time = helpMessage.getTime().getYear() + " " + helpMessage.getTime().getMonth() + " " + helpMessage.getTime().getDayOfMonth() +
                    "  " + helpMessage.getTime().getHour() + ":" + helpMessage.getTime().getMinute();
            if (helpMessage.getSenderId() == myId) {
                chatPageFXController.showMyMessage(helpMessage, time);
            } else {
                chatPageFXController.showOthersMessage(helpMessage, time);
            }
        }
    }

}
