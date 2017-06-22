package tool.dynamicFile;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/4/22.
 */
public class FileListenerAdaptor extends FileAlterationListenerAdaptor {
    @Override
    public void onFileCreate(File file) {
        System.out.println("create:" + file.getAbsolutePath());
        try {
//            打印文件内容
            List<String> li= FileUtils.readLines(file,"UTF-8");
            for(String str:li){
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onFileChange(File file) {
        System.out.println("modify:" + file.getAbsolutePath());
//        new ConfigManager();
//        会打印ip两次，有一次在main方法中
        ConfigManager configManager = new ConfigManager();
        System.out.println(configManager.getInstance().getString("ip"));
    }

    @Override
    public void onFileDelete(File file) {
        System.out.println("delete" + file.getAbsolutePath());
    }

    public static void main(String[] args) {
         try {
        // 构造观察类主要提供要观察的文件或目录，当然还有详细信息的filter,只处理csv文件
        FileAlterationObserver observer = new FileAlterationObserver(
                new File("E:\\NetBeansProjects\\dynamicFile\\Web\\conf"),
                FileFilterUtils.suffixFileFilter(".csv"),null);

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
//            Logger.getLogger(FileListenerAdaptor.class
//                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
