package ir.pi.project.client.config.chatConfig;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class MyMessageConfig {
    ChatConfig chatConfig=new ChatConfig();
    private int rootPrefWidth,rootPrefHeightSpace,textFlowX,textLabelColorR,
            textLabelColorG,textLabelColorB,textLabelPrefWidth,textLabelRadius,
            textLabelInsets,lineSpace,nullImageWidth, imageRectX,imageRectY,
            imageRectWidth,imageRectHeight, buttonsYSPACE,buttonsPrefWidth,
            buttonsPrefHeight,buttonsColorR, buttonsColorG,buttonsColorB,buttonsColorO,
            buttonsInsets,buttonsRadius, saveButtonX,editButtonX, deleteButtonX,labelsYSPACE,
            timeLabelX,statusLabelX;

    public MyMessageConfig() {
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(chatConfig.getMyMessage());
        properties.load(fileReader);
        rootPrefWidth=Integer.parseInt((String) properties.get("rootPrefWidth"));
        rootPrefHeightSpace=Integer.parseInt((String) properties.get("rootPrefHeightSpace"));
        textFlowX=Integer.parseInt((String) properties.get("textFlowX"));
        textLabelColorR=Integer.parseInt((String) properties.get("textLabelColorR"));
        textLabelColorG=Integer.parseInt((String) properties.get("textLabelColorG"));
        textLabelColorB=Integer.parseInt((String) properties.get("textLabelColorB"));
        textLabelPrefWidth=Integer.parseInt((String) properties.get("textLabelPrefWidth"));
        textLabelRadius=Integer.parseInt((String) properties.get("textLabelRadius"));
        textLabelInsets=Integer.parseInt((String) properties.get("textLabelInsets"));
        lineSpace=Integer.parseInt((String) properties.get("lineSpace"));
        nullImageWidth=Integer.parseInt((String) properties.get("nullImageWidth"));
        imageRectX=Integer.parseInt((String) properties.get("imageRectX"));
        imageRectY=Integer.parseInt((String) properties.get("imageRectY"));
        imageRectWidth=Integer.parseInt((String) properties.get("imageRectWidth"));
        imageRectHeight=Integer.parseInt((String) properties.get("imageRectHeight"));
        buttonsYSPACE=Integer.parseInt((String) properties.get("buttonsYSPACE"));
        buttonsPrefWidth=Integer.parseInt((String) properties.get("buttonsPrefWidth"));
        buttonsPrefHeight=Integer.parseInt((String) properties.get("buttonsPrefHeight"));
        buttonsColorR=Integer.parseInt((String) properties.get("buttonsColorR"));
        buttonsColorG=Integer.parseInt((String) properties.get("buttonsColorG"));
        buttonsColorB=Integer.parseInt((String) properties.get("buttonsColorB"));
        buttonsColorO=Integer.parseInt((String) properties.get("buttonsColorO"));
        buttonsInsets=Integer.parseInt((String) properties.get("buttonsInsets"));
        buttonsRadius=Integer.parseInt((String) properties.get("buttonsRadius"));

        saveButtonX=Integer.parseInt((String) properties.get("saveButtonX"));
        editButtonX=Integer.parseInt((String) properties.get("editButtonX"));
        deleteButtonX=Integer.parseInt((String) properties.get("deleteButtonX"));
        labelsYSPACE=Integer.parseInt((String) properties.get("labelsYSPACE"));
        timeLabelX=Integer.parseInt((String) properties.get("timeLabelX"));
        statusLabelX=Integer.parseInt((String) properties.get("statusLabelX"));

    }


    public int getRootPrefWidth() {
        return rootPrefWidth;
    }

    public int getRootPrefHeightSpace() {
        return rootPrefHeightSpace;
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

    public int getTextLabelPrefWidth() {
        return textLabelPrefWidth;
    }

    public int getTextLabelRadius() {
        return textLabelRadius;
    }

    public int getTextLabelInsets() {
        return textLabelInsets;
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

    public int getButtonsYSPACE() {
        return buttonsYSPACE;
    }

    public int getButtonsPrefWidth() {
        return buttonsPrefWidth;
    }

    public int getButtonsPrefHeight() {
        return buttonsPrefHeight;
    }

    public int getButtonsColorR() {
        return buttonsColorR;
    }

    public int getButtonsColorG() {
        return buttonsColorG;
    }

    public int getButtonsColorB() {
        return buttonsColorB;
    }

    public int getButtonsColorO() {
        return buttonsColorO;
    }

    public int getButtonsInsets() {
        return buttonsInsets;
    }

    public int getButtonsRadius() {
        return buttonsRadius;
    }

    public int getSaveButtonX() {
        return saveButtonX;
    }

    public int getEditButtonX() {
        return editButtonX;
    }

    public int getDeleteButtonX() {
        return deleteButtonX;
    }

    public int getLabelsYSPACE() {
        return labelsYSPACE;
    }

    public int getTimeLabelX() {
        return timeLabelX;
    }

    public int getStatusLabelX() {
        return statusLabelX;
    }
}
