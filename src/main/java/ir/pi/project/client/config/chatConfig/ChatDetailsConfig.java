package ir.pi.project.client.config.chatConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ChatDetailsConfig {

    ChatConfig chatConfig=new ChatConfig();

    String othersLeftLabelText,MyLeftLabelText;

    int leftLabelX,leftLabelFontSize,vboxSpace,hours,minutes;

    public ChatDetailsConfig() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(chatConfig.getChatDetailsConfig());
        properties.load(fileReader);

        othersLeftLabelText = (String) properties.get("othersLeftLabelText");
        MyLeftLabelText = (String) properties.get("MyLeftLabelText");



        leftLabelX=Integer.parseInt((String) properties.get("leftLabelX"));
        leftLabelFontSize=Integer.parseInt((String) properties.get("leftLabelFontSize"));
        vboxSpace=Integer.parseInt((String) properties.get("vboxSpace"));
        hours=Integer.parseInt((String) properties.get("hours"));
        minutes=Integer.parseInt((String) properties.get("minutes"));


    }

    public String getOthersLeftLabelText() {
        return othersLeftLabelText;
    }

    public String getMyLeftLabelText() {
        return MyLeftLabelText;
    }

    public int getLeftLabelX() {
        return leftLabelX;
    }

    public int getLeftLabelFontSize() {
        return leftLabelFontSize;
    }

    public int getVboxSpace() {
        return vboxSpace;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}
