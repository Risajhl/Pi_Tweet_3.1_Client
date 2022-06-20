package ir.pi.project.client.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MainConfig {

    private String addresses;
    private String chat;
    private String showChatConfig;
    private String socketConfig;
    private String gameFrameConfig;

    public MainConfig() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        String mainConfigPath = "src\\main\\resources\\config\\mainConfig";
        FileReader fileReader = new FileReader(mainConfigPath);
        properties.load(fileReader);
        addresses = (String) properties.get("addresses");
        chat = (String) properties.get("chat");
        showChatConfig = (String) properties.get("showChatConfig");
        socketConfig = (String) properties.get("socketConfig");
        gameFrameConfig = (String) properties.get("gameFrameConfig");

    }


    public String getAddresses() {
        return addresses;
    }

    public String getChat() {
        return chat;
    }

    public String getShowChatConfig() {
        return showChatConfig;
    }

    public String getSocketConfig() {
        return socketConfig;
    }

    public String getGameFrameConfig() {
        return gameFrameConfig;
    }
}
