package ir.pi.project.client.view.pageLoader;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.FXControllers.entering.LoginPageFXController;
import ir.pi.project.client.view.FXControllers.entering.SignUpPageFXController;
import ir.pi.project.client.view.FXControllers.entering.WelcomePageFXController;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.util.Loop;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class EnteringLoader {
    private final FXMLConfig fxmlConfig=new FXMLConfig();
    FXControllers fxControllers;
    List<Loop> loops;
    Stage stage;
    List<Scene> scenes;
    EventListener listener;
    public EnteringLoader(FXControllers fxControllers, List<Loop> loops, EventListener listener){
    this.fxControllers=fxControllers;
    this.loops=loops;
    this.stage= GraphicalAgent.stage;
    this.scenes=GraphicalAgent.scenes;
    this.listener=listener;
    }

    public void loadWelcomePage(){
        Platform.runLater(
                () -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getWelcomePage()));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scenes.add(scene);
        WelcomePageFXController welcomePageFXController=fxmlLoader.getController();
        welcomePageFXController.setListener(listener);
                }
        );
    }

    public void loadSignUpPage(){
        Platform.runLater(
                () -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getSignUpPage()));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scenes.add(scene);
        SignUpPageFXController signUpPageFXController=fxmlLoader.getController();
        signUpPageFXController.setListener(listener);
                }
        );
    }

    public void loadLogInPage(){
        Platform.runLater(
                () -> {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getLogInPage()));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scenes.add(scene);
        LoginPageFXController loginPageFXController = fxmlLoader.getController();
        loginPageFXController.setListener(listener);
                }
        );
    }
}
