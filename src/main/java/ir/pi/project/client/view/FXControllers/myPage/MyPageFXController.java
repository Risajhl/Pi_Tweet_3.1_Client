package ir.pi.project.client.view.FXControllers.myPage;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.myPageEvents.MyPageEvent;
import ir.pi.project.shared.event.myPageEvents.NewTweetEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.util.Loop;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;import java.util.ResourceBundle;

public class MyPageFXController implements Initializable {


    EventListener listener;
    Loop loop;
    FXControllers fxControllers;
    ImageConvertor imageConvertor=new ImageConvertor();

    BufferedImage tweetBufferedImage;

    @FXML
    Label userNameLabel,nameLabel,emailLabel,birthDateLabel,errorLabel,
            phoneNumberLabel,followersNumLabel,followingsNumLabel;

    @FXML
    Button newTweetButton,tweetsButton, editButton,followersButton,uploadImage,
            followingsButton,notificationsButton,requestsButton,pendingsButton,blackListButton;

    @FXML
    TextArea tweetArea,biographyArea;

    @FXML
    Circle profileCircle;

    @FXML
    Rectangle imageRect;



    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void setLoop(Loop loop) { this.loop = loop; }

    public void setFxControllers(FXControllers fxControllers) {
        this.fxControllers = fxControllers;
    }

    public void newTweet(){
        listener.listen(new NewTweetEvent(tweetArea.getText(),imageConvertor.toString(tweetBufferedImage,"png")));
        tweetArea.setText("");
        imageRect.setFill(null);
        imageRect.setVisible(false);
        tweetBufferedImage=null;
    }


    public void editInfo(){ listener.listen(new MyPageEvent(MyPage.EDIT_PROFILE)); }

    public void tweets(){
        listener.listen(new MyPageEvent(MyPage.TWEETS));
    }

    public void followers(){listener.listen(new MyPageEvent(MyPage.FOLLOWERS));}
    public void followings(){listener.listen(new MyPageEvent(MyPage.FOLLOWINGS));}
    public void blackList(){listener.listen(new MyPageEvent(MyPage.BLACKLIST));}
    public void notifications(){listener.listen(new MyPageEvent(MyPage.NOTIFICATIONS)); }
    public void requests(){listener.listen(new MyPageEvent(MyPage.REQUESTS));}
    public void pendings(){listener.listen(new MyPageEvent(MyPage.PENDING));}
    public void uploadImage(){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterPNG);
            File file = fileChooser.showOpenDialog(null);

            tweetBufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(tweetBufferedImage, null);
            setTweetImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProfileImage(String profileImageInString){
        if(profileImageInString!=null) {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(profileImageInString), null);
            profileCircle.setFill(new ImagePattern(image));
        }
        else
            profileCircle.setFill(Color.BLACK);
    }


    public void setTweetImage(Image image){
        if (image==null) {
            imageRect.setFill(null);
            imageRect.setVisible(false);
        }
        else {
            imageRect.setFill(new ImagePattern(image));
            imageRect.setVisible(true);
        }
    }

    public void back(){
        loop.stop();
        fxControllers.setMyPageFXController(null);
        GraphicalAgent.back();
    }
    public void mainMenu(){
        listener.listen(new GetMainMenu());
    }


    public void update(HelpUser helpUser){
        userNameLabel.setText(helpUser.getUserName());
        nameLabel.setText(helpUser.getFirstName()+" "+helpUser.getLastName());
        phoneNumberLabel.setText("Phone Number: "+helpUser.getPhoneNumber());
        emailLabel.setText("Email: "+helpUser.getEmail());
        birthDateLabel.setText("Birth Date: "+helpUser.getBirthDate());
        if(helpUser.getBiography()!=null && !helpUser.getBiography().equals(""))
            biographyArea.setText(helpUser.getBiography());

        followersNumLabel.setText(helpUser.getFollowers().size()+"");
        followingsNumLabel.setText(helpUser.getFollowings().size()+"");
        errorLabel.setVisible(false);
        setProfileImage(helpUser.getProfileImageInString());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { imageRect.setVisible(false); }
}
