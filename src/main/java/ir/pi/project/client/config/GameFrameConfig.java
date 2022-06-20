package ir.pi.project.client.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GameFrameConfig {

    private final MainConfig mainConfigPath=new MainConfig();
    private Integer width;
    private Integer height;
    private String title;


    public GameFrameConfig(){
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath.getGameFrameConfig());
        properties.load(fileReader);
        width = Integer.parseInt((String) properties.get("width"));
        height = Integer.parseInt((String) properties.get("height"));
        title = (String) properties.get("title");

    }

    public Integer getWidth() { return width; }

    public Integer getHeight() { return height; }

    public String getTitle() { return title; }
}
