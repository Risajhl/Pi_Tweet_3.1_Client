package ir.pi.project.client.view.pageLoader;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.*;
import ir.pi.project.client.view.FXControllers.myPage.ForwardTweetPageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.others.Buttons;
import ir.pi.project.shared.event.otherEvents.ProfilePageUpReq;
import ir.pi.project.shared.model.Tweet;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.util.Loop;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class OthersLoader {
    private final FXMLConfig fxmlConfig = new FXMLConfig();
    FXControllers fxControllers;
    List<Loop> loops;
    Stage stage;
    Stack<Scene> scenes;
    EventListener listener;

    public OthersLoader(FXControllers fxControllers, List<Loop> loops, EventListener listener) {
        this.fxControllers = fxControllers;
        this.loops = loops;
        this.stage = GraphicalAgent.stage;
        this.scenes = GraphicalAgent.scenes;
        this.listener = listener;
    }

    public void loadMainMenu(){

        Platform.runLater(
                () -> {
                    scenes.removeAllElements();
                    fxControllers.removeAll();
                    for (Loop loop : loops) {
                        loop.stop();
                    }
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getMainMenu()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    MainMenuFXController mainMenuFXController = fxmlLoader.getController();
                    mainMenuFXController.setListener(listener);
                });
    }

    public void loadExplorerPage(){
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getExplorerPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    ExplorerPageFXController explorerPageFXController = fxmlLoader.getController();
                    explorerPageFXController.setListener(listener);
                });
    }

    public void loadTweet(Tweet tweet,HelpUser writer,HelpUser retweetedBy){
        Platform.runLater(
                () -> {
                    if (fxControllers.getTweetComponentFXController() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getTweetComponent()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);
                        TweetComponentFXController tweetComponentFXController = fxmlLoader.getController();
                        fxControllers.setTweetComponentFXController(tweetComponentFXController);
                        tweetComponentFXController.setListener(listener);
                        tweetComponentFXController.setFxControllers(fxControllers);
                        tweetComponentFXController.update(tweet,writer,retweetedBy);
//                        tweetComponentFXController.setTweetImage(tweet.getImageInByte());
//                        Loop tweetComponentLoop = new Loop(1, new Runnable() {
//                            @Override
//                            public void run() { listener.listen(new ShowTweetUpReq()); }});
//
//                        tweetComponentFXController.setLoop(tweetComponentLoop);
//                        tweetComponentLoop.start();
                    } else fxControllers.getTweetComponentFXController().update(tweet,writer,retweetedBy);
                }
        );
    }

    public void loadForwardPage(){
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getForwardTweetPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    ForwardTweetPageFXController forwardTweetPageFXController = fxmlLoader.getController();
                    forwardTweetPageFXController.setListener(listener);
                });
    }

    public void loadProfilePage(HelpUser user, String followButtonText, String blockButtonText, String muteButtonText, String blockStateText, String followingStateText, String lastSeenText){
        Platform.runLater(
                () -> {
                    if (fxControllers.getProfilePageFXControllers() == null) {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getProfilePage()));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        scenes.add(scene);

                        ProfilePageFXControllers profilePageFXControllers = fxmlLoader.getController();
                        profilePageFXControllers.setListener(listener);
                        profilePageFXControllers.setFxControllers(fxControllers);
                        fxControllers.setProfilePageFXControllers(profilePageFXControllers);
                        profilePageFXControllers.update(user, followButtonText, blockButtonText, muteButtonText, blockStateText, followingStateText, lastSeenText);
                        Loop profilePageLoop = new Loop(1, new Runnable() {
                            @Override
                            public void run() {
                                listener.listen(new ProfilePageUpReq());
                            }
                        });
                        loops.add(profilePageLoop);
                        profilePageFXControllers.setLoop(profilePageLoop);
                        profilePageLoop.start();
                    } else
                        fxControllers.getProfilePageFXControllers().update(user, followButtonText, blockButtonText, muteButtonText, blockStateText, followingStateText, lastSeenText);

                });
    }

    public void loadButtons(Buttons buttons){
        if (buttons.equals(Buttons.EDIT_BUTTON))
            fxControllers.getChatPageFXController().setEditButton(true);
    }
}
