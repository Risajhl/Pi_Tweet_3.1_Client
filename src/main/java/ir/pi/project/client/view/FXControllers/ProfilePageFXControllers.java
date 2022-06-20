package ir.pi.project.client.view.FXControllers;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.event.otherEvents.ProfilePageEvent;
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

import java.net.URL;
import java.util.ResourceBundle;

public class ProfilePageFXControllers implements Initializable {

    EventListener listener;
    FXControllers fxControllers;
    Loop loop;
    ImageConvertor imageConvertor=new ImageConvertor();
    Image profileImage;

    @FXML
    Label nameLabel,userNameLabel,followingStateLabel, lastSeenLabel,blockStateLabel,
            emailLabel,birthDateLabel,phoneNumberLabel,followersNumLabel,followingsNumLabel;
    @FXML
    TextArea biographyArea;

    @FXML
    Button followButton,blockButton,messageButton,muteButton,reportButton,tweetsButton;

    @FXML
    Circle profileCircle;

    @FXML
    Rectangle bigImageRect;



    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }

    public void setLoop(Loop loop) { this.loop = loop; }


    public void update(HelpUser user, String followButtonText, String blockButtonText, String muteButtonText, String blockStateText, String followingStateText, String lastSeenText){
        if(user.getFirstName()!=null & user.getLastName()!=null)
        nameLabel.setText(user.getFirstName()+" "+user.getLastName());
        userNameLabel.setText(user.getUserName());
        followersNumLabel.setText(user.getFollowers().size()+"");
        followingsNumLabel.setText(user.getFollowings().size()+"");
        if(user.getBiography()!=null && !user.getBiography().equals(""))biographyArea.setText(user.getBiography());

        if(user.isEPBCanSee()) {

                emailLabel.setText(user.getEmail());
                phoneNumberLabel.setText(user.getPhoneNumber());
                birthDateLabel.setText(user.getBirthDate());
        }

        blockButton.setText(blockButtonText);
        followButton.setText(followButtonText);
        muteButton.setText(muteButtonText);
        lastSeenLabel.setText(lastSeenText);
        followingStateLabel.setText(followingStateText);
        blockStateLabel.setText(blockStateText);
        setProfileImage(user.getProfileImageInString());

    }


    public void setProfileImage(String imageInString){
        if(imageInString!=null) {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(imageInString), null);
            profileImage=image;
            profileCircle.setFill(new ImagePattern(image));
        }
        else
            profileCircle.setFill(Color.BLACK);
    }


    public void mute(){
        listener.listen(new ProfilePageEvent(ProfilePage.MUTE));
    }
    public void follow(){
        listener.listen(new ProfilePageEvent(ProfilePage.FOLLOW));
    }
    public void report(){
        listener.listen(new ProfilePageEvent(ProfilePage.REPORT));
    }
    public void block(){
        listener.listen(new ProfilePageEvent(ProfilePage.BLOCK));
    }
    public void message(){ listener.listen(new ProfilePageEvent(ProfilePage.MESSAGE)); }
    public void tweets(){
        listener.listen(new ProfilePageEvent(ProfilePage.TWEETS));
    }
    public void showImage() {
        if (profileImage != null) {
            bigImageRect.setVisible(true);
            bigImageRect.setFill(new ImagePattern(profileImage));
        }
    }
    public void closeImage(){ bigImageRect.setVisible(false); }


    public void back() {
        GraphicalAgent.back();
        loop.stop();
        fxControllers.setProfilePageFXControllers(null);
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bigImageRect.setVisible(false);
        emailLabel.setVisible(false);
        phoneNumberLabel.setVisible(false);
        birthDateLabel.setVisible(false);
    }
}
