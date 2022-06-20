package ir.pi.project.client.view.FXControllers.messages;

import ir.pi.project.client.config.chatConfig.ChatDetailsConfig;
import ir.pi.project.client.config.chatConfig.MyMessageConfig;
import ir.pi.project.client.config.chatConfig.OthersMessageConfig;
import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.client.view.ImageConvertor;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.enums.others.MessageOnWork;
import ir.pi.project.shared.enums.others.MessageStatus;
import ir.pi.project.shared.enums.others.NewGroupChat;
import ir.pi.project.shared.event.messages.*;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.model.help.HelpMessage;
import ir.pi.project.shared.util.Loop;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class ChatPageFXController implements Initializable {
    private final MyMessageConfig myMessageConfig=new MyMessageConfig();
    private final OthersMessageConfig othersMessageConfig=new OthersMessageConfig();
    private final ChatDetailsConfig chatDetailsConfig=new ChatDetailsConfig();
    EventListener listener;
    FXControllers fxControllers;
    Loop loop;
    MessagesPage messagesPage;

    BufferedImage messageBufferedImage;
    ImageConvertor imageConvertor=new ImageConvertor();


    public void setListener(EventListener listener) { this.listener = listener; }
    public void setFxControllers(FXControllers fxControllers) { this.fxControllers = fxControllers; }
    public void setLoop(Loop loop) { this.loop = loop; }
    public void setMessagesPage(MessagesPage messagesPage) { this.messagesPage = messagesPage; }

    VBox content;

    @FXML
    ScrollPane scroller;

    @FXML
    TextArea messageField;

    @FXML
    TextField addMemberField;

    @FXML
    Label chatNameLabel,hourLabel,minuteLabel;

    @FXML
    ChoiceBox<Integer> hourChoiceBox,minuteChoiceBox;

    @FXML
    DatePicker datePicker;

    @FXML
    Button editButton,sendButton,imageButton,leaveButton,sendScheduleButton,addMemberButton;

    @FXML
    Rectangle imageRect;

    public void send(){
        jumpToBottom();
        listener.listen(new NewMessageEvent(messagesPage,messageField.getText(),imageConvertor.toString(messageBufferedImage,"png")));
        messageField.setText(null);
        messageBufferedImage=null;
        imageRect.setVisible(false);
    }

    public void edit() {
        listener.listen(new ChatPageEvent(messagesPage,ChatPage.EDIT,messageField.getText()));
        editButton.setVisible(false);
        messageField.setText(null);
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
        }
    }

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

    public void leave(){
        listener.listen(new ChatPageEvent(messagesPage,ChatPage.LEAVE,chatNameLabel.getText()));
        loop.stop();

        Label label=new Label();
        label.setText(chatDetailsConfig.getMyLeftLabelText());
        label.setFont(new Font(chatDetailsConfig.getLeftLabelFontSize()));
        label.setLayoutX(chatDetailsConfig.getLeftLabelX());
        AnchorPane root=new AnchorPane();
        root.getChildren().add(label);
        content.getChildren().add(root);

        sendButton.setVisible(false);
        messageField.setVisible(false);
        imageButton.setVisible(false);
        leaveButton.setVisible(false);
        addMemberButton.setVisible(false);
        addMemberField.setVisible(false);



    }

    public void schedule(){
        datePicker.setVisible(true);
        hourChoiceBox.setVisible(true);
        minuteChoiceBox.setVisible(true);
        sendScheduleButton.setVisible(true);
        hourLabel.setVisible(true);
        minuteLabel.setVisible(true);
    }

    public void sendSchedule(){
        LocalDate localDate=datePicker.getValue();
        LocalDateTime localDateTime=localDate.atTime(hourChoiceBox.getValue(),minuteChoiceBox.getValue());
        listener.listen(new ScheduleMessageEvent(messagesPage,localDateTime,messageField.getText(),imageConvertor.toString(messageBufferedImage,"png")));
        datePicker.setVisible(false);
        hourChoiceBox.setVisible(false);
        minuteChoiceBox.setVisible(false);
        sendScheduleButton.setVisible(false);
        hourLabel.setVisible(false);
        minuteLabel.setVisible(false);
        messageField.setText(null);
        imageRect.setVisible(false);
        messageBufferedImage=null;
    }



    public void addMember(){
        listener.listen(new NewGroupChatEvent(NewGroupChat.ADD_AFTER,addMemberField.getText()));
        addMemberField.setText(null);
    }

    public void showMyMessage(HelpMessage message,String time) {

        String messageText=message.getText();
        if (messageText.charAt(messageText.length() - 1) == '\n') messageText = messageText.substring(0, messageText.length() - 1);

        AnchorPane root = new AnchorPane();

        //TEXT
        TextFlow textFlow = new TextFlow();
        textFlow.setLayoutX(myMessageConfig.getTextFlowX());
        Label textLabel = new Label();
        Text text = new Text(messageText);
        textLabel.setText(text.getText());
        textLabel.setBackground(new Background(new BackgroundFill(
                Color.rgb(myMessageConfig.getTextLabelColorR(), myMessageConfig.getTextLabelColorG(), myMessageConfig.getTextLabelColorB()),
                new CornerRadii(myMessageConfig.getTextLabelRadius()),
                new Insets(myMessageConfig.getTextLabelInsets(), myMessageConfig.getTextLabelInsets(), myMessageConfig.getTextLabelInsets(), myMessageConfig.getTextLabelInsets()))));
        textLabel.setWrapText(true);
        textLabel.setPrefWidth(myMessageConfig.getTextLabelPrefWidth());

        textFlow.getChildren().add(textLabel);
        root.getChildren().add(textFlow);


        int lines = (int) text.getLayoutBounds().getWidth() / (myMessageConfig.getTextLabelPrefWidth() - 10);
        double n = text.getLayoutBounds().getHeight() + (lines * myMessageConfig.getLineSpace());

        //IMAGE
        int imageWidth = myMessageConfig.getNullImageWidth();
        if (message.getImageInString() != null) {
            Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(message.getImageInString()), null);
            Rectangle imageRect1 = new Rectangle();
            imageRect1.setVisible(true);
            imageRect1.setFill(new ImagePattern(image));
            imageRect1.setX(myMessageConfig.getImageRectX());
            imageRect1.setY(myMessageConfig.getImageRectY());
            imageRect1.setWidth(myMessageConfig.getImageRectWidth());
            imageRect1.setHeight(myMessageConfig.getImageRectHeight());
            imageWidth = myMessageConfig.getImageRectWidth();
            root.getChildren().add(imageRect1);
        }

        textFlow.setLayoutY(myMessageConfig.getLineSpace() + imageWidth);

        root.setPrefWidth(myMessageConfig.getRootPrefWidth());
        root.setPrefHeight(n + myMessageConfig.getRootPrefHeightSpace() + imageWidth);

        //SAVE_BUTTON
        Button saveButton = new Button();
        saveButton.setLayoutY(n + myMessageConfig.getButtonsYSPACE() + imageWidth);
        saveButton.setLayoutX(myMessageConfig.getSaveButtonX());
        saveButton.setPrefHeight(myMessageConfig.getButtonsPrefHeight());
        saveButton.setPrefWidth(myMessageConfig.getButtonsPrefWidth());
        saveButton.setText("Save");
        saveButton.setBackground(new Background(
                new BackgroundFill(Color.rgb(myMessageConfig.getButtonsColorR(), myMessageConfig.getButtonsColorG(), myMessageConfig.getButtonsColorB(), myMessageConfig.getButtonsColorO()),
                        new CornerRadii(myMessageConfig.getButtonsRadius()),
                        new Insets(myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets()))));
        saveButton.setOnAction(event -> listener.listen(new MessageOnWorkEvent(MessageOnWork.SAVE, message.getId())));
        root.getChildren().add(saveButton);

        //EDIT_BUTTON
        Button editButton = new Button();
        editButton.setLayoutY(n + myMessageConfig.getButtonsYSPACE() + imageWidth);
        editButton.setLayoutX(myMessageConfig.getEditButtonX());
        editButton.setPrefHeight(myMessageConfig.getButtonsPrefHeight());
        editButton.setPrefWidth(myMessageConfig.getButtonsPrefWidth());
        editButton.setText("Edit");
        editButton.setBackground(new Background(
                new BackgroundFill(Color.rgb(myMessageConfig.getButtonsColorR(), myMessageConfig.getButtonsColorG(), myMessageConfig.getButtonsColorB(), myMessageConfig.getButtonsColorO()),
                        new CornerRadii(myMessageConfig.getButtonsRadius()),
                        new Insets(myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets()))));
        editButton.setOnAction(event -> {
            listener.listen(new MessageOnWorkEvent(MessageOnWork.EDIT, message.getId()));
            messageField.setText(null);
        });
        root.getChildren().add(editButton);

        //DELETE_BUTTON
        Button deleteButton = new Button();
        deleteButton.setLayoutY(n + myMessageConfig.getButtonsYSPACE() + imageWidth);
        deleteButton.setLayoutX(myMessageConfig.getDeleteButtonX());
        deleteButton.setPrefHeight(myMessageConfig.getButtonsPrefHeight());
        deleteButton.setPrefWidth(myMessageConfig.getButtonsPrefWidth());
        deleteButton.setText("Delete");
        deleteButton.setBackground(new Background(
                new BackgroundFill(Color.rgb(myMessageConfig.getButtonsColorR(), myMessageConfig.getButtonsColorG(), myMessageConfig.getButtonsColorB(), myMessageConfig.getButtonsColorO()),
                        new CornerRadii(myMessageConfig.getButtonsRadius()),
                        new Insets(myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets(), myMessageConfig.getButtonsInsets()))));
        deleteButton.setOnAction(event -> listener.listen(new MessageOnWorkEvent(MessageOnWork.DELETE, message.getId())));
        root.getChildren().add(deleteButton);

        //DATE_AND_TIME
        Label dateAndTime = new Label();
        dateAndTime.setText(time);
        dateAndTime.setLayoutY(n + myMessageConfig.getLabelsYSPACE() + imageWidth);
        dateAndTime.setLayoutX(myMessageConfig.getTimeLabelX());
        root.getChildren().add(dateAndTime);

        //STATUS
        if (messagesPage.equals(MessagesPage.DIRECT_CHATS)) {
        Label statusLabel = new Label();
        statusLabel.setLayoutY(n + myMessageConfig.getLabelsYSPACE() + imageWidth);
        statusLabel.setLayoutX(myMessageConfig.getStatusLabelX());
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setText(">>");
        if (message.getMessageStatus().equals(MessageStatus.SENT))
            statusLabel.setText(">");
        else if (message.getMessageStatus().equals(MessageStatus.SEEN))
            statusLabel.setTextFill(Color.GREEN);


        root.getChildren().add(statusLabel);

        }

        content.getChildren().add(root);
    }

    public void showOthersMessage(HelpMessage message,String time) {
        //TextCheck
        String messageText = message.getText();
        if (messageText.charAt(messageText.length() - 1) == '\n')
            messageText = messageText.substring(0, messageText.length() - 1);
        if (messageText.equals(chatDetailsConfig.getOthersLeftLabelText())) {
            Label label = new Label();
            label.setText(message.getSenderUsername()+" "+chatDetailsConfig.getOthersLeftLabelText());
            label.setFont(new Font(chatDetailsConfig.getLeftLabelFontSize()));
            label.setLayoutX(chatDetailsConfig.getLeftLabelX());
            AnchorPane root = new AnchorPane();
            root.getChildren().add(label);
            content.getChildren().add(root);
        } else {


            AnchorPane root = new AnchorPane();


            //ProfileImage
            Circle circle = new Circle(othersMessageConfig.getProfileCircleInsets(), othersMessageConfig.getProfileCircleInsets(), othersMessageConfig.getProfileCircleInsets());
            circle.setLayoutX(othersMessageConfig.getProfileCircleXY());
            circle.setLayoutY(othersMessageConfig.getProfileCircleXY());
            root.getChildren().add(circle);

            if (message.getSenderImageInString() != null) {
                Image profileImage = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(message.getSenderImageInString()), null);
                circle.setFill(new ImagePattern(profileImage));
            }


            //Username
            Label usernameLabel = new Label();
            usernameLabel.setText(message.getSenderUsername());
            usernameLabel.setLayoutX(othersMessageConfig.getUserNameLabelX());
            usernameLabel.setLayoutY(othersMessageConfig.getUserNameLabelY());
            root.getChildren().add(usernameLabel);



            //TextLabel
            TextFlow textFlow = new TextFlow();
            textFlow.setLayoutX(othersMessageConfig.getTextFlowX());
            Text text = new Text(messageText);

            Label label = new Label();
            label.setText(text.getText());
            label.setBackground(new Background(new BackgroundFill(Color.rgb(othersMessageConfig.getTextLabelColorR(), othersMessageConfig.getTextLabelColorG(), othersMessageConfig.getTextLabelColorB()),
                    new CornerRadii(5.0), new Insets(-5, -5, -5, -5))));
            label.setWrapText(true);
            label.setPrefWidth(othersMessageConfig.getTextLabelWidth());

            textFlow.getChildren().add(label);
            root.getChildren().add(textFlow);


            int lines = (int) text.getLayoutBounds().getWidth() / othersMessageConfig.getTextLabelWidth();
            double n = text.getLayoutBounds().getHeight() + (lines * othersMessageConfig.getLineSpace());


            //Image
            int imageWidth = othersMessageConfig.getNullImageWidth();
            if (message.getImageInString() != null) {

                Image image = SwingFXUtils.toFXImage(imageConvertor.toBufferedImage(message.getImageInString()), null);
                Rectangle imageRect1 = new Rectangle();
                imageRect1.setVisible(true);
                imageRect1.setFill(new ImagePattern(image));
                imageRect1.setX(othersMessageConfig.getImageRectX());
                imageRect1.setY(othersMessageConfig.getImageRectY());
                imageRect1.setWidth(othersMessageConfig.getImageRectWidth());
                imageRect1.setHeight(othersMessageConfig.getImageRectHeight());
                imageWidth = othersMessageConfig.getImageWidth();
                root.getChildren().add(imageRect1);
            }

            textFlow.setLayoutY(othersMessageConfig.getTextFlowYSpace() + imageWidth);

            root.setPrefWidth(othersMessageConfig.getRootPrefWidth());
            root.setPrefHeight(n + othersMessageConfig.getRootPrefHeightSpace() + imageWidth);


            //SaveButton
            Button saveButton = new Button();
            saveButton.setLayoutY(n + othersMessageConfig.getSaveButtonYSpace() + imageWidth);
            saveButton.setLayoutX(othersMessageConfig.getSaveButtonX());
            saveButton.setPrefHeight(othersMessageConfig.getSaveButtonHeight());
            saveButton.setPrefWidth(othersMessageConfig.getSaveButtonWidth());
            saveButton.setText("Save");
            saveButton.setBackground(new Background(
                    new BackgroundFill(Color.rgb(othersMessageConfig.getSaveButtonColorR(), othersMessageConfig.getSaveButtonColorG(), othersMessageConfig.getSaveButtonColorB(), othersMessageConfig.getSaveButtonColorO()),
                            new CornerRadii(othersMessageConfig.getSaveButtonRadius()),
                            new Insets(othersMessageConfig.getSaveButtonsInsets(), othersMessageConfig.getSaveButtonsInsets(), othersMessageConfig.getSaveButtonsInsets(), othersMessageConfig.getSaveButtonsInsets()))));
            saveButton.setOnAction(event -> listener.listen(new MessageOnWorkEvent(MessageOnWork.SAVE, message.getId())));
            root.getChildren().add(saveButton);


            //Time
            Label dateAndTime = new Label();
            dateAndTime.setText(time);
            dateAndTime.setLayoutY(n + othersMessageConfig.getTimeLabelYSpace() + imageWidth);
            dateAndTime.setLayoutX(othersMessageConfig.getTimeLabelX());
            root.getChildren().add(dateAndTime);



            content.getChildren().add(root);

        }
    }




    public void setName(String name){
        chatNameLabel.setText(name);
    }
    public void setEditButton(boolean isVisible){
        editButton.setVisible(isVisible);
    }
    public void setLeaveButton(boolean isVisible){ leaveButton.setVisible(isVisible); }
    public void setAddMemberButton(boolean isVisible){ addMemberButton.setVisible(isVisible); }
    public void setAddMemberField(boolean isVisible){ addMemberField.setVisible(isVisible); }

    public void jumpToBottom(){
        scroller.setVvalue(scroller.getVmax());
    }
    public void empty(){
        content.getChildren().clear();
    }


    public void back(){
        loop.stop();
        fxControllers.setChatPageFXController(null);
        GraphicalAgent.back();
    }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.content = new VBox(chatDetailsConfig.getVboxSpace());
        scroller.setContent(content);
        scroller.setFitToWidth(true);
        editButton.setVisible(false);
        leaveButton.setVisible(false);
        imageRect.setVisible(false);
        datePicker.setVisible(false);
        hourLabel.setVisible(false);
        minuteLabel.setVisible(false);
        hourChoiceBox.setVisible(false);
        minuteChoiceBox.setVisible(false);
        sendScheduleButton.setVisible(false);
        addMemberButton.setVisible(false);
        addMemberField.setVisible(false);
        datePicker.setValue(LocalDate.now());
        for(int i=0;i<chatDetailsConfig.getHours();i++)
            hourChoiceBox.getItems().add(i);
        for(int i=0;i<chatDetailsConfig.getMinutes();i++)
            minuteChoiceBox.getItems().add(i);
        hourChoiceBox.setValue(LocalDateTime.now().getHour());
        minuteChoiceBox.setValue(LocalDateTime.now().getMinute());
    }

}
