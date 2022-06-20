package ir.pi.project.client.view.FXControllers.settings;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.enums.Pages.*;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.event.settingsEvents.SettingsPageEvent;

public class SettingsPageFXController {
    EventListener listener;

    public void setListener(EventListener listener) { this.listener = listener; }

    public void privacy(){
        listener.listen(new SettingsPageEvent(SettingsPage.PRIVACY));
    }
    public void logOut(){
        listener.listen(new SettingsPageEvent(SettingsPage.LOG_OUT));
    }
    public void deleteAccount(){
        listener.listen(new SettingsPageEvent(SettingsPage.DELETE_ACCOUNT));
    }

    public void back(){ GraphicalAgent.back(); }

    public void mainMenu(){listener.listen(new GetMainMenu());}

}
