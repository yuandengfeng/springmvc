package tool.dynamicFile;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2016/4/22.
 */
public class ConfigManager {

    private static ConfigManager configManager;
    //properties.load(InputStream);读取属性文件
    private static Properties properties;
    //设置动态读取的文件路径
    private static String configPath = "E:\\NetBeansProjects\\dynamicFile\\Web\\conf";
    //设置动态读取的文件名
    private static String pathName = "/configuration.properties";

    InputStream in = null;

    static {
        try {
            // 构造观察类主要提供要观察的文件或目录，当然还有详细信息的filter
            FileAlterationObserver observer = new FileAlterationObserver(
                    new File(configPath), null, null);
            // 构造收听类
            FileListenerAdaptor listener = new FileListenerAdaptor();
            // 为观察对象添加收听对象
            observer.addListener(listener);
            // 配置Monitor，第一个参数单位是毫秒，是监听的间隔；第二个参数就是绑定我们之前的观察对象。
            FileAlterationMonitor fileMonitor = new FileAlterationMonitor(
                    1000, new FileAlterationObserver[]{observer});

            // 启动开始监听
            fileMonitor.start();

        } catch (Exception ex) {
            Logger.getLogger(FileListenerAdaptor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ConfigManager() {
//        String configFile = "/opt/soft/apache-tomcat-8.0.12/webapps/gn/conf/configuration.properties";
        String configFile = configPath + pathName;
        properties = new Properties();

        try {
//            System.out.println(configPath + pathName);
            in = new FileInputStream(configFile);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ConfigManager getInstance() {
        if (configManager == null) {
            configManager = new ConfigManager();
        }
        return configManager;
    }

    public String getString(String key) {
        return properties.getProperty(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static void main(String[] args) {
        String ip = ConfigManager.getInstance().getString("ip");
        int prot = ConfigManager.getInstance().getInt("port");
        System.out.println("ip:" + ip + "\tport:" + prot);


    }
}
