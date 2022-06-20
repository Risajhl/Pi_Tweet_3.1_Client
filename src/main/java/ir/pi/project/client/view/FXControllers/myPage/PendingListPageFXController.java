package ir.pi.project.client.view.FXControllers.myPage;

import ir.pi.project.client.listener.EventListener;
import ir.pi.project.client.view.FXControllers.FXControllers;
import ir.pi.project.client.view.GraphicalAgent;
import ir.pi.project.shared.event.otherEvents.GetMainMenu;
import ir.pi.project.shared.util.Loop;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.List;

public class PendingListPageFXController {
    EventListener listener;
    FXControllers fxControllers;
    Loop loop;

    @FXML
    TextArea pendingArea;

    public void setListener(EventListener listener) { this.listener = listener; }

    public void setLoop(Loop loop) { this.loop = loop; }

    public void setFxControllers(FXControllers fxControllers) {
        this.fxControllers = fxControllers;
    }

    public void update(List<String> pendingList){
        System.out.println(pendingList);
        StringBuilder s= new StringBuilder();
        for (int i=pendingList.size()-1;i>=0;i--){
            s.append(pendingList.get(i)).append("\n");
        }
        pendingArea.setText(s.toString());
    }

    public void back(){
        GraphicalAgent.back();
        loop.stop();
        fxControllers.setPendingListPageFXController(null);
    }
    public void mainMenu(){ listener.listen(new GetMainMenu()); }
}
