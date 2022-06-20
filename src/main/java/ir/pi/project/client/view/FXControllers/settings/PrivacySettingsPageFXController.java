package ir.pi.project.client.view.FXControllers.settings;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.event.settingsEvents.ChangePasswordEvent;
import ir.pi.project.shared.event.settingsEvents.CheckPassword;
import ir.pi.project.shared.event.settingsEvents.SavePrivacySettingsEvent;
import ir.pi.project.shared.model.help.HelpUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class PrivacySettingsPageFXController {

    EventListener listener;

    @FXML
    RadioButton activeRadioButton, inactiveRadioButton, privateRadioButton,
            publicRadioButton, everyOneRadioButton, noOneRadioButton, followersRadioButton;

    @FXML
    TextField currentPasswordField, newPasswordField;

    @FXML
    Button checkButton;

    public void setListener(EventListener listener) {
        this.listener = listener;
    }

    public void deselectPrivacy(ActionEvent e) {
        if (e.getSource().equals(privateRadioButton))
            publicRadioButton.setSelected(false);
        else privateRadioButton.setSelected(false);
    }

    public void deselectActivity(ActionEvent e) {
        if (e.getSource().equals(activeRadioButton))
            inactiveRadioButton.setSelected(false);
        else activeRadioButton.setSelected(false);
    }

    public void deselectLastSeen(ActionEvent e) {
        if (e.getSource().equals(noOneRadioButton)) {
            everyOneRadioButton.setSelected(false);
            followersRadioButton.setSelected(false);
        } else if (e.getSource().equals(everyOneRadioButton)) {
            noOneRadioButton.setSelected(false);
            followersRadioButton.setSelected(false);
        } else {
            noOneRadioButton.setSelected(false);
            everyOneRadioButton.setSelected(false);
        }
    }

    public void back() {
        GraphicalAgent.back();
    }

    public void mainMenu() {
        listener.listen(new GetMainMenu());
    }

    public void save() {
        boolean isActive = activeRadioButton.isSelected();
        boolean isPublic = publicRadioButton.isSelected();
        String lastSeen;
        if (everyOneRadioButton.isSelected()) lastSeen = "EveryOne";
        else if (noOneRadioButton.isSelected()) lastSeen = "NoOne";
        else lastSeen = "Followers";

        String newPassword = newPasswordField.getText();

        listener.listen(new SavePrivacySettingsEvent(isActive,isPublic,lastSeen,newPassword));
    }

    public void check() {
        listener.listen(new CheckPassword(currentPasswordField.getText()));
    }

    public void changePassword() {
        listener.listen(new ChangePasswordEvent());
    }

    public void update(HelpUser helpUser) {
        currentPasswordField.setVisible(false);
        newPasswordField.setVisible(false);
        checkButton.setVisible(false);

        if (helpUser.isActive()) activeRadioButton.setSelected(true);
        else inactiveRadioButton.setSelected(true);

        if (helpUser.isPublic()) publicRadioButton.setSelected(true);
        else privateRadioButton.setSelected(true);

        if (helpUser.getLastSeenState().equals("NoOne"))
            noOneRadioButton.setSelected(true);
        else if (helpUser.getLastSeenState().equals("EveryOne")) everyOneRadioButton.setSelected(true);
        else followersRadioButton.setSelected(true);
    }

    public void loadCurrentPassword(){
        currentPasswordField.setVisible(true);
        checkButton.setVisible(true);
    }

    public void loadNewPassword(){
        newPasswordField.setVisible(true);
    }

}
