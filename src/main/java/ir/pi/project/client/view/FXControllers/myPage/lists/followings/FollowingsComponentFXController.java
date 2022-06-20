package ir.pi.project.client.view.FXControllers.myPage.lists.followings;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.event.myPageEvents.lists.followings.UnFollowEvent;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class FollowingsComponentFXController {
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


    public void unfollow(){
        listener.listen(new UnFollowEvent(usernameLabel.getText()));
        content.getChildren().remove(pane);
    }

    public void update(String following,String imageInString){
        usernameLabel.setText(following);
        setProfileImage(imageInString);
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
