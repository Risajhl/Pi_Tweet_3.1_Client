package ir.pi.project.client.view.FXControllers.messages.multipleMessaging;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.messages.AddToMultiplesEvent;
import ir.pi.project.shared.event.messages.SendMultipleMessageEvent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MultipleToGroupsPageFXController implements Initializable {
    EventListener listener;
    FXControllers fxControllers;

    ImageConvertor imageConvertor=new ImageConvertor();
    BufferedImage messageBufferedImage;

    @FXML
    TextField groupNameField;

    @FXML
    TextArea groupNamesArea,messageArea;

    @FXML
    Rectangle imageRect;


    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }

    public void add(){
        listener.listen(new AddToMultiplesEvent(MessagesPage.TO_GROUPS,groupNameField.getText()));
        groupNameField.setText(null);

    }
    public void send(){
        listener.listen(new SendMultipleMessageEvent(MessagesPage.TO_GROUPS, messageArea.getText(),imageConvertor.toString(messageBufferedImage,"png")));
        messageArea.setText(null);
        groupNamesArea.setText(null);
        imageRect.setVisible(false);

    }

    public void updateGroupNames(List<String> groupNames){
        groupNamesArea.setText(null);
        if(!groupNames.isEmpty()) {
            for (String groupName : groupNames) {
                if (groupNamesArea.getText()==null || groupNamesArea.getText().equals(""))
                    groupNamesArea.setText(groupName);
                else
                    groupNamesArea.setText(groupNamesArea.getText() + "\n" + groupName);
            }
        }
    }



    public void uploadImage(){
        try {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
            fileChooser.getExtensionFilters().addAll(extFilterPNG);
            File file = fileChooser.showOpenDialog(null);

            messageBufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(messageBufferedImage, null);
            setImage(image);

        } catch (IOException e) {
            e.printStackTrace();
        }    }

    public void setImage(Image image){
        if (image==null) {

            imageRect.setFill(null);
            imageRect.setVisible(false);
        }
        else {
            imageRect.setFill(new ImagePattern(image));
            imageRect.setVisible(true);
        }
    }

    public void back() {
        GraphicalAgent.back();
        fxControllers.setMultipleToGroupsPageFXController(null);
    }
    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageRect.setVisible(false);
    }
}
