package ir.pi.project.client.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SocketConfig {

    private final MainConfig mainConfigPath=new MainConfig();
    private Integer port;
    private String host;


    public SocketConfig(){
        try {
            setProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProperties() throws IOException {
        Properties properties = new Properties();
        FileReader fileReader = new FileReader(mainConfigPath.getSocketConfig());
        properties.load(fileReader);
        port = Integer.parseInt((String) properties.get("port"));
        host = (String) properties.get("host");

    }

    public Integer getPort() {
        return port;
    }
    public String getHost(){return host;}
}
