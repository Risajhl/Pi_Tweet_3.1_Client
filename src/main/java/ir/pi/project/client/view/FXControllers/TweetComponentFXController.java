package ir.pi.project.client.view.FXControllers;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.others.TweetComponent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.event.otherEvents.NewCommentEvent;
import ir.pi.project.shared.event.otherEvents.TweetComponentEvent;
import ir.pi.project.shared.model.Tweet;
import ir.pi.project.shared.model.help.HelpUser;
import ir.pi.project.shared.util.Loop;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;


public class TweetComponentFXController implements Initializable {

    EventListener listener;
    Loop loop;
    FXControllers fxControllers;

    ImageConvertor imageConvertor=new ImageConvertor();
    BufferedImage commentBufferedImage;

    @FXML
    Label userNameLabel, dateLabel,retweetedLabel,retweeterLabel;

    @FXML
    TextArea tweetArea, commentArea;

    @FXML
    Circle profileCircle;

    @FXML
    Rectangle imageRect,commentImageRect;


    public void setListener(EventListener listener) {
        this.listener = listener;
    }
    public void setLoop(Loop loop) {
        this.loop = loop;
    }
    public void setFxControllers(FXControllers fxControllers) {
        this.fxControllers = fxControllers;
    }


    public void next(){
        listener.listen(new TweetComponentEvent(TweetComponent.NEXT));
    }
    public void previous(){
        listener.listen(new TweetComponentEvent(TweetComponent.PREVIOUS));
    }
    public void like(){
        listener.listen(new TweetComponentEvent(TweetComponent.LIKE));
    }
    public void retweet(){
        listener.listen(new TweetComponentEvent(TweetComponent.RETWEET));
    }
    public void save(){
        listener.listen(new TweetComponentEvent(TweetComponent.SAVE));
    }
    public void forward(){
        listener.listen(new TweetComponentEvent(TweetComponent.FORWARD));
    }
    public void comments(){
        listener.listen(new TweetComponentEvent(TweetComponent.COMMENTS));
    }
    public void report(){
        listener.listen(new TweetComponentEvent(TweetComponent.REPORT));
    }
    public void comment(){
        listener.listen(new NewCommentEvent(commentArea.getText(),imageConvertor.toString(commentBufferedImage,"png")));
        commentArea.setText(null);
        commentBufferedImage=null;
        commentImageRect.setVisible(false);
    }

    public void uploadCommentImage(){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterPNG);
            File file = fileChooser.showOpenDialog(null);

            commentBufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(commentBufferedImage, null);
            setCommentImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void update(Tweet tweet ,HelpUser writer,HelpUser retweetedBy){
        retweetedLabel.setVisible(tweet.isRetweeted());
        retweeterLabel.setVisible(tweet.isRetweeted());
        if(retweetedBy!=null) {
            if(retweetedBy.getUserName().equals(writer.getUserName()))
                retweeterLabel.setText("you");
            else retweeterLabel.setText(retweetedBy.getUserName());
        }
        retweetedLabel.setVisible(tweet.isRetweeted());
        userNameLabel.setText(writer.getUserName());
        tweetArea.setText(tweet.getText());
        String date=tweet.getTime().getYear()+" "+tweet.getTime().getMonth()+" "+tweet.getTime().getDayOfMonth()+
                "  "+tweet.getTime().getHour()+":"+tweet.getTime().getMinute();
        dateLabel.setText(date);
        if(tweet.getImageInString()!=null){
            setTweetImage(tweet.getImageInString());
        }else setTweetImage(null);
        setProfileImage(writer.getProfileImageInString());
    }



    public void setProfileImage(String profileImageInString){
        if(profileImageInString!=null) {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(profileImageInString), null);
            profileCircle.setFill(new ImagePattern(image));
        }
        else
            profileCircle.setFill(Color.BLACK);
    }

    public void setTweetImage(String tweetImageInString){
        if (tweetImageInString==null) {
            tweetArea.setMaxWidth(770);
            imageRect.setFill(null);
            imageRect.setVisible(false);
        }
        else {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(tweetImageInString), null);
            tweetArea.setMaxWidth(480);
            imageRect.setFill(new ImagePattern(image));
            imageRect.setVisible(true);
        }
    }

    public void setCommentImage(Image image){
        if (image==null) {
            commentImageRect.setFill(null);
            commentImageRect.setVisible(false);
        }
        else {
            commentImageRect.setFill(new ImagePattern(image));
            commentImageRect.setVisible(true);
        }
    }



    public void back(){
        GraphicalAgent.back();
        listener.listen(new TweetComponentEvent(TweetComponent.BACK));
        fxControllers.setTweetComponentFXController(null);
    }
    public void mainMenu(){
        listener.listen(new GetMainMenu());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageRect.setVisible(false);
        commentImageRect.setVisible(false);
        retweetedLabel.setVisible(false);
        retweeterLabel.setVisible(false);
    }
}
