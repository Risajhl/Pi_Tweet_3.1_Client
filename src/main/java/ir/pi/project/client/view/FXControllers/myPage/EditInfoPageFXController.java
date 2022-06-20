package ir.pi.project.client.view.FXControllers.myPage;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.event.myPageEvents.SaveInfoEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.model.help.HelpUser;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class EditInfoPageFXController {

    EventListener listener;

    BufferedImage bufferedImage=null;
    Image image;
    ImageConvertor imageConvertor=new ImageConvertor();

    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField userNameField;
    @FXML
    TextField emailField;
    @FXML
    TextField birthDateField;
    @FXML
    TextField phoneNumberField;
    @FXML
    TextArea biographyArea;
    @FXML
    RadioButton EPBRadioButton;
    @FXML
    Label editInfoError;

    @FXML
    Circle profileCircle;

    public void setListener(EventListener listener) { this.listener = listener; }

    public void save(){
        listener.listen(new SaveInfoEvent(firstNameField.getText(),
                lastNameField.getText(),userNameField.getText(),
                emailField.getText(),phoneNumberField.getText(),
                birthDateField.getText(),biographyArea.getText(),
                EPBRadioButton.isSelected(),imageConvertor.toString(bufferedImage,"png")));
    }

    public void changeImage(){
        try {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if(file!=null) {
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            setImage(image);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeImage(){
        bufferedImage=null;
        setImage(null);
    }

    public void setImage(Image image){
        if (image==null)
            profileCircle.setFill(null);
        else
            profileCircle.setFill(new ImagePattern(image));
    }


    public void back(){
        GraphicalAgent.back();
    }
    public void mainMenu(){
        listener.listen(new GetMainMenu());
    }


    public void update(HelpUser helpUser){
        firstNameField.setText(helpUser.getFirstName());
        lastNameField.setText(helpUser.getLastName());
        userNameField.setText(helpUser.getUserName());
        emailField.setText(helpUser.getEmail());
        birthDateField.setText(helpUser.getBirthDate());
        phoneNumberField.setText(helpUser.getPhoneNumber());
        EPBRadioButton.setSelected(helpUser.isEPBCanSee());
        if(helpUser.getBiography()!=null){
            biographyArea.setText(helpUser.getBiography());
        }
        if(helpUser.getProfileImageInString()!=null) {
            image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(helpUser.getProfileImageInString()), null);
            bufferedImage = SwingFXUtils.fromFXImage(image, null);
        }
        setImage(image);
    }

}
