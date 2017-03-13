package tool.util;

import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandengfeng on 2017/3/8.
 */
public class Main2 {


    public static void main(String[] args) throws ZipException, IOException {

        File dirFile = new File("G:\\坤腾\\超汇VIPLog接口\\超汇VIPlog程序\\开发\\2017227");
        File[] files = dirFile.listFiles();
        List<String> li=new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {

          File[] unzipfile=CompressUtil.unzip(files[i],"G:\\坤腾\\超汇VIPLog接口\\超汇VIPlog程序\\开发\\2017227",null);

            files[i].delete();
            for(int j=0;j<unzipfile.length;j++){
                if(unzipfile[j].getName().endsWith("csv")){
                    li.addAll(FileUtils.readLines(unzipfile[j]));
                }
            }
        }

        for(String str:li){
//            if(str.endsWith("时间戳")){
                System.out.println(str);
//            }

        }



    }



}
