package tool.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by yuandengfeng on 2016/11/23.
 */
public class Main {

    public static void main(String args[]) throws IOException {
        File onLineMac = new File("G:\\坤腾\\yunAC版本发布\\onlinemac.csv");
        File clients = new File("G:\\坤腾\\yunAC版本发布\\cl.csv");

        List on=FileUtils.readLines(onLineMac);
        List cl =FileUtils.readLines(clients);


        on.removeAll(cl);
//        on.retainAll(cl);

        System.out.println(on.size());

        for(int i=0;i<on.size();i++)
        {
            System.out.println(on.get(i));
        }

//        System.out.println(cl.size());
//
//        for(int i=0;i<cl.size();i++)
//        {
//            System.out.println(cl.get(i));
//        }



    }



}
