package ir.pi.project.client.config.chatConfig;

import ir.pi.project.client.config.MainConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ChatConfig {
    private final MainConfig mainConfigPath=new MainConfig();

    private String myMessageConfig;
    private String othersMessageConfig;
    private String chatDetailsConfig;

    public ChatConfig() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath.getChat());
        properties.load(fileReader);
        myMessageConfig = (String) properties.get("myMessageConfig");
        othersMessageConfig = (String) properties.get("othersMessageConfig");
        chatDetailsConfig = (String) properties.get("chatDetailsConfig");

    }

    public String getMyMessage() {
        return myMessageConfig;
    }

    public String getOthersMessage() {
        return othersMessageConfig;
    }
    public String getChatDetailsConfig() {
        return chatDetailsConfig;
    }

}
