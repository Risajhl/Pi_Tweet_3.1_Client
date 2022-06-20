package ir.pi.project.client.config.chatConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class OthersMessageConfig {
    ChatConfig chatConfig=new ChatConfig();


    int     profileCircleXY,profileCircleInsets,rootPrefWidth,rootPrefHeightSpace,userNameLabelX,
            userNameLabelY,textFlowX,textLabelColorR,textLabelColorG,textLabelColorB,
            textLabelWidth,lineSpace,nullImageWidth,imageRectX,imageRectY,imageRectWidth,
            imageRectHeight,imageWidth,textFlowYSpace,saveButtonYSpace,saveButtonX,
            saveButtonHeight,saveButtonWidth,saveButtonColorR,saveButtonColorG,
            saveButtonColorB,saveButtonColorO,saveButtonsInsets,saveButtonRadius,
            timeLabelYSpace,timeLabelX;

    public OthersMessageConfig() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(chatConfig.getOthersMessage());
        properties.load(fileReader);
        profileCircleXY=Integer.parseInt((String) properties.get("profileCircleXY"));
        profileCircleInsets=Integer.parseInt((String) properties.get("profileCircleInsets"));
        rootPrefWidth=Integer.parseInt((String) properties.get("rootPrefWidth"));
        rootPrefHeightSpace=Integer.parseInt((String) properties.get("rootPrefHeightSpace"));
        userNameLabelX=Integer.parseInt((String) properties.get("userNameLabelX"));
        userNameLabelY=Integer.parseInt((String) properties.get("userNameLabelY"));
        textFlowX=Integer.parseInt((String) properties.get("textFlowX"));
        textLabelColorR=Integer.parseInt((String) properties.get("textLabelColorR"));
        textLabelColorG=Integer.parseInt((String) properties.get("textLabelColorG"));
        textLabelColorB=Integer.parseInt((String) properties.get("textLabelColorB"));
        textLabelWidth=Integer.parseInt((String) properties.get("textLabelWidth"));
        lineSpace=Integer.parseInt((String) properties.get("lineSpace"));
        nullImageWidth=Integer.parseInt((String) properties.get("nullImageWidth"));
        imageRectX=Integer.parseInt((String) properties.get("imageRectX"));
        imageRectY=Integer.parseInt((String) properties.get("imageRectY"));
        imageRectWidth=Integer.parseInt((String) properties.get("imageRectWidth"));
        imageRectHeight=Integer.parseInt((String) properties.get("imageRectHeight"));
        imageWidth=Integer.parseInt((String) properties.get("imageWidth"));
        textFlowYSpace=Integer.parseInt((String) properties.get("textFlowYSpace"));
        saveButtonYSpace=Integer.parseInt((String) properties.get("saveButtonYSpace"));
        saveButtonX=Integer.parseInt((String) properties.get("saveButtonX"));
        saveButtonHeight=Integer.parseInt((String) properties.get("saveButtonHeight"));
        saveButtonWidth=Integer.parseInt((String) properties.get("saveButtonWidth"));

        saveButtonColorR=Integer.parseInt((String) properties.get("saveButtonColorR"));
        saveButtonColorG=Integer.parseInt((String) properties.get("saveButtonColorG"));
        saveButtonColorB=Integer.parseInt((String) properties.get("saveButtonColorB"));
        saveButtonColorO=Integer.parseInt((String) properties.get("saveButtonColorO"));
        saveButtonsInsets=Integer.parseInt((String) properties.get("saveButtonsInsets"));
        saveButtonRadius=Integer.parseInt((String) properties.get("saveButtonRadius"));

        timeLabelYSpace=Integer.parseInt((String) properties.get("timeLabelYSpace"));
        timeLabelX=Integer.parseInt((String) properties.get("timeLabelX"));

    }


    public int getProfileCircleXY() {
        return profileCircleXY;
    }
    public int getProfileCircleInsets() {
        return profileCircleInsets;
    }

    public int getRootPrefWidth() {
        return rootPrefWidth;
    }

    public int getRootPrefHeightSpace() {
        return rootPrefHeightSpace;
    }

    public int getUserNameLabelX() {
        return userNameLabelX;
    }

    public int getUserNameLabelY() {
        return userNameLabelY;
    }

    public int getTextFlowX() {
        return textFlowX;
    }

    public int getTextLabelColorR() {
        return textLabelColorR;
    }

    public int getTextLabelColorG() {
        return textLabelColorG;
    }

    public int getTextLabelColorB() {
        return textLabelColorB;
    }

    public int getTextLabelWidth() {
        return textLabelWidth;
    }

    public int getLineSpace() {
        return lineSpace;
    }

    public int getNullImageWidth() {
        return nullImageWidth;
    }

    public int getImageRectX() {
        return imageRectX;
    }

    public int getImageRectY() {
        return imageRectY;
    }

    public int getImageRectWidth() {
        return imageRectWidth;
    }

    public int getImageRectHeight() {
        return imageRectHeight;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getTextFlowYSpace() {
        return textFlowYSpace;
    }

    public int getSaveButtonYSpace() {
        return saveButtonYSpace;
    }

    public int getSaveButtonX() {
        return saveButtonX;
    }

    public int getSaveButtonHeight() {
        return saveButtonHeight;
    }

    public int getSaveButtonWidth() {
        return saveButtonWidth;
    }

    public int getSaveButtonColorR() {
        return saveButtonColorR;
    }

    public int getSaveButtonColorG() {
        return saveButtonColorG;
    }

    public int getSaveButtonColorB() {
        return saveButtonColorB;
    }

    public int getSaveButtonColorO() {
        return saveButtonColorO;
    }

    public int getSaveButtonsInsets() {
        return saveButtonsInsets;
    }

    public int getSaveButtonRadius() {
        return saveButtonRadius;
    }

    public int getTimeLabelYSpace() {
        return timeLabelYSpace;
    }

    public int getTimeLabelX() {
        return timeLabelX;
    }
}
