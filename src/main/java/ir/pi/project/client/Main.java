package ir.pi.project.client;

import ir.pi.project.client.config.GameFrameConfig;
import ir.pi.project.client.config.SocketConfig;
import ir.pi.project.client.config.addresses.FXMLConfig;
import ir.pi.project.client.listener.MainController;
import ir.pi.project.client.listener.network.SocketEventSender;
import ir.pi.project.client.view.GraphicalAgent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.Socket;

public class Main extends Application {
    public static void main(String[] args) { launch(args); }
    private final FXMLConfig fxmlConfig =new FXMLConfig();
    private final SocketConfig socketConfig=new SocketConfig();
    private final GameFrameConfig gameFrameConfig=new GameFrameConfig();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Socket socket = new Socket(socketConfig.getHost(), socketConfig.getPort());
        GraphicalAgent.stage=primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlConfig.getWelcomePage()));
        Parent root = fxmlLoader.load();
        MainController controller = new MainController(new SocketEventSender(socket),fxmlLoader.getController());
        controller.start();
        Scene scene = new Scene(root);
        GraphicalAgent.scenes.add(scene);
        primaryStage.setScene(scene);
        primaryStage.setTitle(gameFrameConfig.getTitle());
        primaryStage.setHeight(gameFrameConfig.getHeight());
        primaryStage.setWidth(gameFrameConfig.getWidth());
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> controller.close());

        primaryStage.show();

    }
}
