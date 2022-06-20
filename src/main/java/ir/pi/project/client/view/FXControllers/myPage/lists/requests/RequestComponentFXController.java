package ir.pi.project.client.view.FXControllers.myPage.lists.requests;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.others.RequestComponent;
import ir.pi.project.shared.event.myPageEvents.lists.requests.RequestComponentEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class RequestComponentFXController {
    EventListener listener;
    VBox content;
    ImageConvertor imageConvertor=new ImageConvertor();

    @FXML
    AnchorPane pane;

    @FXML
    Label usernameLabel;

    @FXML
    Circle profileCircle;

    public void setListener(EventListener listener) { this.listener = listener; }
    public void setContent(VBox content) {
        this.content = content;
    }


    public void delete(){
        listener.listen(new RequestComponentEvent(usernameLabel.getText(), RequestComponent.DELETE));
        content.getChildren().remove(pane);
    }
    public void accept(){
        listener.listen(new RequestComponentEvent(usernameLabel.getText(),RequestComponent.ACCEPT));

        content.getChildren().remove(pane);
    }
    public void deleteAndInform(){
        listener.listen(new RequestComponentEvent(usernameLabel.getText(),RequestComponent.DELETE_AND_INFORM));
        content.getChildren().remove(pane);

    }


    public void update(String username,String imageInByte){
        usernameLabel.setText(username);
        setProfileImage(imageInByte);
    }

    public void setProfileImage(String imageInString){
        if(!imageInString.equals("empty")) {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(imageInString), null);
            profileCircle.setFill(new ImagePattern(image));
        }
        else
            profileCircle.setFill(Color.BLACK);
    }

}
