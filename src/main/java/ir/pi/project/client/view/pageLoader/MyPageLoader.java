package ir.pi.project.client.view.pageLoader;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.FXControllers.myPage.EditInfoPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.MyPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.NotificationsPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.PendingListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.blackList.BlackListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.followers.FollowersListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.followings.FollowingsListPageFXController;
import ir.pi.project.client.view.FXControllers.myPage.lists.requests.RequestsListPageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.myPageEvents.MyPageUpReq;
import ir.pi.project.shared.event.myPageEvents.NotificationsUpReq;
import ir.pi.project.shared.event.myPageEvents.PendingListUpReq;
import ir.pi.project.shared.event.myPageEvents.lists.followers.FollowersListUpReq;
import ir.pi.project.shared.event.myPageEvents.lists.followings.FollowingsListUpReq;
import ir.pi.project.shared.event.myPageEvents.lists.requests.RequestsListUpReq;
import ir.pi.project.shared.model.User;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.util.Loop;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MyPageLoader {
    private final FXMLConfig fxmlConfig = new FXMLConfig();
    FXControllers fxControllers;
    List<Loop> loops;
    Stage stage;
    List<Scene> scenes;
    EventListener listener;

    public MyPageLoader(FXControllers fxControllers, List<Loop> loops, EventListener listener) {
        this.fxControllers = fxControllers;
        this.loops = loops;
        this.stage = GraphicalAgent.stage;
        this.scenes = GraphicalAgent.scenes;
        this.listener = listener;
    }

    public void loadMyPage(HelpUser helpUser) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getMyPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getMyPagePage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        MyPageFXController myPageFXController = fxmlLoader.getController();
                        fxControllers.setMyPageFXController(myPageFXController);
                        myPageFXController.setListener(listener);
                        myPageFXController.setFxControllers(fxControllers);
                        myPageFXController.update(helpUser);
                        Loop myPageLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new MyPageUpReq());
                            }
                        });
                        loops.add(myPageLoop);
                        myPageFXController.setLoop(myPageLoop);
                        myPageLoop.start();
                    } else fxControllers.getMyPageFXController().update(helpUser);
                }
        );
    }


    public void loadEditProfilePage(HelpUser helpUser) {
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getEditInfoPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    EditInfoPageFXController editInfoPageFXController = fxmlLoader.getController();
                    editInfoPageFXController.update(helpUser);
                    editInfoPageFXController.setListener(listener);
                }
        );
    }

    public void loadNotificationsPage(List<String> notifications) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getNotificationsPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getNotificationsPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        NotificationsPageFXController notificationsPageFXController = fxmlLoader.getController();
                        notificationsPageFXController.setListener(listener);
                        notificationsPageFXController.setFxControllers(fxControllers);
                        notificationsPageFXController.update(notifications);
                        fxControllers.setNotificationsPageFXController(notificationsPageFXController);
                        Loop notificationsLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new NotificationsUpReq());
                            }
                        });
                        loops.add(notificationsLoop);
                        notificationsPageFXController.setLoop(notificationsLoop);
                        notificationsLoop.start();
                    } else fxControllers.getNotificationsPageFXController().update(notifications);
                }
        );

    }


    public void loadPendingListPage(List<String> pendingList) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getPendingListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getPendingListPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        PendingListPageFXController pendingListPageFXController = fxmlLoader.getController();
                        pendingListPageFXController.setListener(listener);
                        pendingListPageFXController.setFxControllers(fxControllers);
                        pendingListPageFXController.update(pendingList);
                        fxControllers.setPendingListPageFXController(pendingListPageFXController);
                        Loop pendingLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new PendingListUpReq());
                            }
                        });
                        loops.add(pendingLoop);
                        pendingListPageFXController.setLoop(pendingLoop);
                        pendingLoop.start();
                    } else fxControllers.getPendingListPageFXController().update(pendingList);
                }
        );

    }


    public void loadBlackListPage(Map<String,String> blacklist) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getBlackListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getBlacklistPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        BlackListPageFXController blackListPageFXController = fxmlLoader.getController();
                        blackListPageFXController.setListener(listener);
                        blackListPageFXController.update(blacklist);
                        fxControllers.setBlackListPageFXController(blackListPageFXController);
                    } else fxControllers.getBlackListPageFXController().update(blacklist);
                }
        );

    }

    public void loadRequestsListPage(Map<String,String> request) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getRequestsListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getRequestsListPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        RequestsListPageFXController requestsListPageFXController = fxmlLoader.getController();
                        requestsListPageFXController.setListener(listener);
                        requestsListPageFXController.setFxControllers(fxControllers);
                        requestsListPageFXController.update(request);
                        fxControllers.setRequestsListPageFXController(requestsListPageFXController);
                        Loop requestsLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new RequestsListUpReq());
                            }
                        });
                        loops.add(requestsLoop);
                        requestsListPageFXController.setLoop(requestsLoop);
                        requestsLoop.start();
                    } else fxControllers.getRequestsListPageFXController().update(request);
                }
        );

    }


    public void loadFollowersList(Map<String,String> followers) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getFollowersListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getFollowersListPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        FollowersListPageFXController followersListPageFXController = fxmlLoader.getController();
                        followersListPageFXController.setListener(listener);
                        followersListPageFXController.setFxControllers(fxControllers);
                        followersListPageFXController.update(followers);
                        fxControllers.setFollowersListPageFXController(followersListPageFXController);
                        Loop followersLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new FollowersListUpReq());
                            }
                        });
                        loops.add(followersLoop);
                        followersListPageFXController.setLoop(followersLoop);
                        followersLoop.start();
                    } else fxControllers.getFollowersListPageFXController().update(followers);
                }
        );

    }


    public void loadFollowingsListPage(Map<String,String> followings) {
        Platform.runLater(
                () -> {
                    if (fxControllers.getFollowingsListPageFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getFollowingsListPage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        FollowingsListPageFXController followingsListPageFXController = fxmlLoader.getController();
                        followingsListPageFXController.setListener(listener);
                        followingsListPageFXController.setFxControllers(fxControllers);
                        followingsListPageFXController.update(followings);
                        fxControllers.setFollowingsListPageFXController(followingsListPageFXController);
                        Loop followingsLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new FollowingsListUpReq());
                            }
                        });
                        loops.add(followingsLoop);
                        followingsListPageFXController.setLoop(followingsLoop);
                        followingsLoop.start();
                    } else fxControllers.getFollowingsListPageFXController().update(followings);
                }
        );

    }
}
