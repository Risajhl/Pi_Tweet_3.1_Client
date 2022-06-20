package ir.pi.project.client.view.pageLoader;

import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.FXControllers.settings.DeleteAccountPageFXController;
import ir.pi.project.client.view.FXControllers.settings.PrivacySettingsPageFXController;
import ir.pi.project.client.view.FXControllers.settings.SettingsPageFXController;
import ir.pi.project.client.view.GraphicalAgent;
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

public class SettingsLoader {
    private final FXMLConfig fxmlConfig = new FXMLConfig();
    FXControllers fxControllers;
    List<Loop> loops;
    Stage stage;
    List<Scene> scenes;
    EventListener listener;

    public SettingsLoader(FXControllers fxControllers, List<Loop> loops, EventListener listener) {
        this.fxControllers = fxControllers;
        this.loops = loops;
        this.stage = GraphicalAgent.stage;
        this.scenes = GraphicalAgent.scenes;
        this.listener = listener;
    }


    public void loadSettingsPage() {
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getSettingsPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    SettingsPageFXController settingsPageFXController = fxmlLoader.getController();
                    settingsPageFXController.setListener(listener);
                }
        );
    }

    public void loadPrivacySettingsPage(HelpUser helpUser) {
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getPrivacySettingsPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    PrivacySettingsPageFXController privacySettingsPageFXController = fxmlLoader.getController();
                    fxControllers.setPrivacySettingsPageFXController(privacySettingsPageFXController);
                    privacySettingsPageFXController.update(helpUser);
                    privacySettingsPageFXController.setListener(listener);
                }
        );

    }

    public void loadCurrentPassword() {
        Platform.runLater(
                () -> {
                    PrivacySettingsPageFXController privacySettingsPageFXController = fxControllers.getPrivacySettingsPageFXController();
                    privacySettingsPageFXController.loadCurrentPassword();
                }
        );
    }

    public void loadNewPassword() {
        Platform.runLater(
                () -> {
                    PrivacySettingsPageFXController privacySettingsPageFXController = fxControllers.getPrivacySettingsPageFXController();
                    privacySettingsPageFXController.loadNewPassword();
                }
        );
    }

    public void loadDeleteAccountPage() {
        Platform.runLater(
                () -> {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getDeleteAccountPage()));
                    Parent root = null;
                    try {
                        root = fxmlLoader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    scenes.add(scene);
                    DeleteAccountPageFXController deleteAccountPageFXController = fxmlLoader.getController();
                    deleteAccountPageFXController.setListener(listener);
                }
        );
    }

}
