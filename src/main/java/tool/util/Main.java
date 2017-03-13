package tool.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuandengfeng on 2016/11/23.
 */
public class Main {

    public static void main(String args[]) throws IOException {







        /**
         *
        File  mac = new File("H:\\下载\\昆山三一南区宿舍(1).csv");
        File clients = new File("H:\\下载\\TucDElq8.csv");
        File newFile = new File("H:\\下载\\mac.csv");

        List<String> onmac=FileUtils.readLines(mac);
        List<String> cl =FileUtils.readLines(clients);

        List<String> macs = new ArrayList<String>();


//        on.removeAll(cl);
////        on.retainAll(cl);
//
//        System.out.println(on.size());
//
        for(int i=0;i<onmac.size();i++)
        {
//            System.out.println(onmac.get(i).split(",")[0]);
            macs.add(onmac.get(i).split(",")[0]);
        }



//        System.out.println(cl.size());
//
        for(int i=0;i<cl.size();i++)
        {
            if(macs.contains(cl.get(i).split(",")[8]))
            {
                FileUtils.writeStringToFile(newFile,cl.get(i)+"\n",true);

            }
        }

         *
         */

    }



}
