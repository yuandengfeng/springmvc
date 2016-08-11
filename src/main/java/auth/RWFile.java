package auth;

import com.es.util.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public class RWFile {

    //  将文件夹传入,读取文件内容
    public static List<String> readFilePool (String filePool)
    {

        List<String> list = new ArrayList();

        FileReader fr =null;
        BufferedReader bufr=null;

        File f = new File(filePool);
        if(!f.isDirectory())
        {
            System.out.println(f);
            System.out.println("not a Directory");
//            System.out.println("你输入的不是一个文件夹，请检查路径是否有误！！");
        }
        else
        {
            File[] fileList=f.listFiles();
//                    for(int i=0;i<fileList.length;i++)
            for(File fi:fileList)
            {
                try {

                    System.out.println(fi);
//                            fr= new FileReader(filePool+"/"+fileList[i].getName());
                    fr=new FileReader(fi);
                    bufr = new BufferedReader(fr);
                    String line = null;
                    String[] arr=null;
                    while((line=bufr.readLine())!=null)
                    {
                        System.out.println(line);
//                        if(line.startsWith("mqqid")||line.startsWith("qqid"))
//                        {
//                            arr=line.split(",");
//                            String key =arr[0]+","+arr[3].split("\\|")[1];
////                                 System.out.println(key+"::::::"+arr[1]);
//                        }
//                        else if(line.startsWith("weixin"))
//                        {
//                            arr=line.split(",");
//                            String key =arr[0]+","+arr[3];
////                                 System.out.println(key+"::::::"+arr[1]);
//                        }
                    }
                } catch (IOException ex)
                {
                    ex.printStackTrace();
                }finally
                {
                    try {
                        fr.close();
                        bufr.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }

        }
        return list;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String filepool= Global.getConfig("filepool");
        System.out.println(new String(filepool.getBytes("ISO8859-1"),"utf-8"));
//        List<String> list =readFilePool (new String(filepool.getBytes("ISO8859-1"),"utf-8"));
    }


}
