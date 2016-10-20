/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;

/**
 *
 * @author Administrator
 */
public class RWFile2 {
      static FileReader fr =null;
//     static InputStreamReader fr = null;
     static BufferedReader bufr=null;
     static  OutputStreamWriter fw = null;
//      static FileWriter  fw = null;
     static  BufferedWriter bufw =null;
//     private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config.config");
//     static String detectIp =bundle.getString("detectIp");
//     static String resultFile =bundle.getString("resultFile");
//     static String ipPoolFold=bundle.getString("ipPoolFold");
     static String detectIp =filekey("detectIp");
     static String resultFile =filekey("resultFile");
     static String ipPoolFold=filekey("ipPoolFold");
    static  MapSet othersip= new  MapSet();
    static int othersiptimes=0;
    public static void main(String[] args)
    {
        //昨天日期
        String date =getYestertoday();
        String day =getDay();
        //获取ip地址池
         MultiMap testmap =readIpPool(ipPoolFold);
//        writeResultFile(testmap,detectIp);
        //生成详细文件
//         luceneDResultFile(testmap,detectIp+"dominate"+date,resultFile+"WIFIAGENT_details_"+date+".csv");
         luceneDResultFile(testmap,detectIp+"dominate"+date,resultFile+"WIFIAGENT_details_"+day+".csv");
         System.out.println(detectIp+"dominate"+date);
        //根据详细文件生成按ac和城市统计的文件
//         countByAcName (resultFile+"WIFIAGENT_details_"+date+".csv",resultFile+"WIFIAGENT_AC_"+date+".csv",resultFile+"WIFIAGENT_city_"+date+".csv");
          countByAcName (resultFile+"WIFIAGENT_details_"+day+".csv",resultFile+"WIFIAGENT_AC_"+day+".csv",resultFile+"WIFIAGENT_city_"+day+".csv");
        
//        addCity ("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\源\\沈阳.txt");
    }
//    统计ac name  城市  打击用户数  打击次数 日期,统计城市  打击用户数  打击次数  日期
      public static void countByAcName (String resultFile,String countAcNameFile,String countCityNameFile)
    {
//         FileReader frr =null;
         InputStreamReader  in = null;
         BufferedReader bufrr=null;
         //按AC统计用户数
         MapSet countuser =new MapSet();
         //按AC统计打击次数
         MapInteger times = new MapInteger();
         //按城市统计用户数
         MapSet cityCountuser =new MapSet();
         //按城市统计打击次数
         MapInteger cityTimes = new MapInteger();
        try {
            in = new InputStreamReader(new FileInputStream(resultFile),"GB2312");
             bufrr = new BufferedReader(in);
//              frr= new FileReader(resultFile);
//              bufrr = new BufferedReader(frr);
//              String linetrim = null;
              String acvalue=null;
              String cityAcvalue=null;
              String line=null;
              bufrr.readLine();
                while((line=bufrr.readLine())!=null)
                {
//                    line=linetrim.replaceAll("\\?", "");
                    // 统计ac name  城市  打击用户数  打击次数 日期

//                    acvalue=line.split("\\,")[6]+","+line.split("\\,")[1]+","+line.split("\\,")[3];
//                    countuser.put(acvalue, line.split("\\,")[0]);
//                    times.put(acvalue);
                    
                    //统计城市  打击用户数  打击次数  日期
//                    cityAcvalue=line.split("\\,")[6]+","+line.split("\\,")[3];
//                    cityCountuser.put(cityAcvalue, line.split("\\,")[0]);
                    cityAcvalue=line.split("\\,")[3]+","+line.split("\\,")[1];
                    cityCountuser.put(cityAcvalue, line.split("\\,")[0]);
                    cityTimes.put(cityAcvalue);
//                    System.out.println(cityAcvalue+"====="+line.split("\\,")[0]);
//                 writeFile(countCityNameFile,citykey.trim()+","+ cityCountuser.get(citykey).size()+","+cityTimes.get(citykey));
                }
               
            } catch (IOException ex) {
               ex.printStackTrace();
            }finally
        {
           try {
//               frr.close();
               in.close();
               bufrr.close();
           } catch (IOException ex) {
               Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
        //根据ACName统计
//         writeFile(countAcNameFile,"日期,AC Name,城市,打击用户数,打击次数");
//        for(String ACkey:countuser.keySet())
//        {
////            String str =ACkey.trim()+","+ countuser.get(ACkey).size()+","+times.get(ACkey);
////            if(str.contains("?"))
////                        System.out.println(str);
////                writeFile(countAcNameFile,str.replaceAll("\\?",""));
//           writeFile(countAcNameFile,ACkey.trim()+","+ countuser.get(ACkey).size()+","+times.get(ACkey));
//        
//        }
//        根据城市统计
        writeFile(countCityNameFile,"日期,城市,打击用户数,打击次数");
        String datetime ="1970-01-01";
        for(String citykey:cityCountuser.keySet())
        {
//             String str =citykey.trim()+","+ cityCountuser.get(citykey).size()+","+cityTimes.get(citykey);
//             writeFile(countCityNameFile,str.replaceAll("\\?",""));
           writeFile(countCityNameFile,citykey.trim()+","+ cityCountuser.get(citykey).size()+","+cityTimes.get(citykey));
            System.err.println(citykey.trim()+","+ cityCountuser.get(citykey).size()+","+cityTimes.get(citykey));
//             System.err.println(citykey.trim());
            datetime=citykey.trim().split("\\,")[0];
        }
//        增加0.0.0.0和其他ip账户的统计
         writeFile(countCityNameFile,datetime+",其他"+","+ othersip.get("其他").size()+","+othersiptimes);
    }
//生成结果文件result.csv,传入参数地址次对象，和LuceneDB中需要的ip地址信息
     public static void luceneDResultFile(MultiMap testmap,String dbfile,String resultdateFile)
   {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        SimpleLuceneDB db = new SimpleLuceneDB(dbfile);
        try {
//            writeFile(resultdateFile,"用户名,所属AC name,所属AC IP,城市,私网ip类型,私网ip,打击日期,打击时间");
            writeFile(resultdateFile,"用户名,城市,私网ip,打击日期,打击时间,共享台数");
            List<Document> list = db.query("name_s:*",1000000);
            for (int i = 0; i < list.size(); i++) {
                Document doc = list.get(i);
                String name = doc.get("name_s").substring(3);
                String target = doc.get("target_s");
                String data = doc.get("data_s");
                String ip = doc.get("ip_s");
                String total = doc.get("total_i");
                String time1 =sdf1.format(Long.parseLong(doc.get("time_l")));
                String time2 =sdf2.format(Long.parseLong(doc.get("time_l")));
//                System.out.println(resultdateFile);
//                System.out.println(name + "\t" + target + "\t" + data + "\t" + ip + "\t" + total );
                    String key1=ip.split("\\.")[1];
                    String key2=ip.split("\\.")[2];
//                    System.out.println(name+","+testmap.get(key1).get(key2)+","+ip);
                    if(testmap.get(key1).get(key2) !=null)
                    {
//                        String str =name+","+testmap.get(key1).get(key2)+","+ip+","+time1+","+time2;
//                        writeFile(resultdateFile,str.replaceAll("\\?",""));
                        String city = testmap.get(key1).get(key2).split("\\,")[2];
//                        System.out.println(city);
                       writeFile(resultdateFile,name+","+city+","+ip+","+time1+","+time2+","+total);
                    }
                    else
                    {
                     othersip.put("其他",name);
                     othersiptimes++;
                    }
            }
        } catch (ParseException ex) {
            Logger.getLogger(SimpleLuceneDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SimpleLuceneDB.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
     
//    生成结果文件result.csv,传入参数地址次对象，和打击日志文件
   public static void writeResultFile(MultiMap testmap,String detectIp)
   {
//         InputStreamReader  in = null;
         FileReader djfr =null;
         BufferedReader djbufr=null;
//         获取地址池中的所有ip对应关系
//         MultiMap testmap =readFile ("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\新建文件夹\\test.txt");
          try {
             
//                in = new InputStreamReader(new FileInputStream(detectIp),"GB2312");
//                djbufr = new BufferedReader(in);
              djfr = new FileReader(detectIp);
              djbufr = new BufferedReader(djfr);
              String dj;
              String djline;
//               writeFile("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\新建文件夹\\result.csv","城市,所属AC name,所属AC IP,私网ip类型,私网ip");
              writeFile(resultFile,"城市,所属AC name,所属AC IP,私网ip类型,私网ip");
//              生成打击用户如AC对应关系并写入文件中
               while((dj=djbufr.readLine())!=null)
                {
//                    获取打击用户ip，将B类C类地址获取
                    djline=dj.split("\\,")[1];
                    String key1=djline.split("\\.")[1];
                    String key2=djline.split("\\.")[2];
//                    if(key1.equals("0"))
//                        continue;
                    System.out.println(testmap.get(key1).get(key2)+","+djline);
                    if(testmap.get(key1).get(key2) !=null)
                    {
//                        String str = new String((testmap.get(key1).get(key2)+","+djline).getBytes(),"GB2312");
//                       writeFile(resultFile, str);
                     writeFile(resultFile, testmap.get(key1).get(key2)+","+djline);
                    }

                    
                }
          } catch (Exception ex) {
              Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
              try {
//                  in.close();
                  djfr.close();
                  djbufr.close();
              } catch (IOException ex) {
                  Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
   }
    //传入参数ip地址池所在文件，读取ip地址池，并存储在MultiMap中
    public static  MultiMap readFile (String filename)
    {
       MultiMap allmap = new MultiMap();
        try {
              fr= new FileReader(filename);
//             fr = new InputStreamReader(new FileInputStream(filename),"GB2312");
              bufr = new BufferedReader(fr);
              String line = null;
              String[] newline=null;
//              ip上网类型
             String[] ipRange={",CMCC",",i-LiaoNing",",SSID"};
//               writeFile("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\新建文件夹\\test.txt","城市,所属AC name,所属AC IP,CMCC1,CMCC2,i-LiaoNing1,i-LiaoNing2,SSID1,SSID2");
                while((line=bufr.readLine())!=null)
                {
                    String acValue=null;
                    String key1=null;
                    String key2=null;
                    String key3=null;
                    newline = line.split("\\,");
//                    所属AC name,所属AC IP,CMCC1
                    acValue=newline[0]+","+newline[1]+","+newline[2];
                    int arr=0;
                    for(int j=3;j<8;j=j+2)
                    {
//                        System.out.println(j);
//                       将每个地址段的c类地址获取 并存入 MultiMap 
                      key1=newline[j].split("\\.")[1];
                      key2=newline[j].split("\\.")[2];
                      key3=newline[j+1].split("\\.")[2];
                     for(int i= Integer.parseInt(key2);i<=Integer.parseInt(key3);i++)
                     {
                         allmap.put(key1,""+i,acValue+ipRange[arr]);
                     }
                    arr++;
                    }
//                    newline="辽阳,"+line;
//                    writeFile("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\新建文件夹\\test.txt",newline);
                }
                return allmap;
            } catch (IOException ex) {
               ex.printStackTrace();
            }finally
        {
           try {
               fr.close();
               bufr.close();
           } catch (IOException ex) {
               Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
          return null;
    }
//  将ip地址池所在的文件夹传入,读取ip地址池，并存储在MultiMap中
    public static  MultiMap readIpPool (String ipPoolFold)
    {
       MultiMap allmap = new MultiMap();
            File f = new File(ipPoolFold);
             if(!f.isDirectory())
                {
                    System.out.println("你输入的不是一个文件夹，请检查路径是否有误！！");
                }
            else
                {
                    File[] fileList=f.listFiles();
                    for(int i=0;i<fileList.length;i++)
                   {
                       try {
//                        System.out.println(ipPoolFold+"/"+fileList[i].getName());
                       fr= new FileReader(ipPoolFold+"/"+fileList[i].getName());
//                       fr = new InputStreamReader(new FileInputStream(ipPoolFold+"/"+fileList[i].getName()),"GB2312");
                       bufr = new BufferedReader(fr);
                        String line = null;
                        String[] newline=null;
//                      ip上网类型
                        String[] ipRange={",i-LiaoNing",",CMCC",",SSID"};
                        while((line=bufr.readLine())!=null)
                            {
//                                if(line.contains("﻿"))System.out.println(line);
                                String acValue=null;
                                String key1=null;
                                String key2=null;
                                String key3=null;
                                String key4=null;
                                newline = line.split("\\,");
    //                          所属AC name,所属AC IP,CMCC1
                                
                                acValue=newline[1]+","+newline[2]+","+newline[0];
                                int arr=0;
                                for(int j=3;j<8;j=j+2)
                                {
    //                              将每个地址段的c类地址获取 并存入 MultiMap ,若ip为0.0.0.0则不统计
                                    if(newline[j].equals("0.0.0.0"))
                                         continue;
                                     key1=newline[j].split("\\.")[1];
                                     key2=newline[j].split("\\.")[2];
//                                     if(newline[j+1].equals("172.97.16.1"))
//                                     {
//                                         System.out.println(j+1);
//                                      System.err.println(newline[1]+newline[2]+newline[0]+"========="+newline[j+1]);
//                                     }
                                     key4=newline[j+1].split("\\.")[1];
                                     key3=newline[j+1].split("\\.")[2];
                                    
                                  for(int m= Integer.parseInt(key1);m<=Integer.parseInt(key4);m++)
                                   {
                                        for(int k= Integer.parseInt(key2);k<=Integer.parseInt(key3);k++)
                                        {
    //                                        System.out.println(key1+"-----------"+i);
//                                            allmap.put(key1,""+k,acValue+ipRange[arr]);
                                            allmap.put(""+m,""+k,acValue+ipRange[arr]);
    //                                        System.out.println("allmap"+allmap.get(key1));
//                                             System.out.println(k);
                                        }
                                       
                                    }
                                    arr++;
                                }
                            } 
                         } catch (IOException ex)
                         {
                                ex.printStackTrace();
                                return null;
                        }finally
                         {
                            try {
                                fr.close();
                                bufr.close();
                            } catch (IOException ex) {
                                Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
                            }

                         }
                   }
                return allmap;
                }
          return null;
    }
    
//写文件
 public static void writeFile(String filename,String text)
    {
        try {
//            fw = new FileWriter(filename,true);
            fw = new OutputStreamWriter(new FileOutputStream(filename, true),"GB2312");
            bufw = new BufferedWriter(fw);
            bufw.write(text);
            bufw.newLine();
            bufw.flush();
           
        } catch (IOException ex) {
            Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufw.close();
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
//    添加城市
  public static void addCity (String filename)
    {
       
        try {
//            fr = new InputStreamReader(new FileInputStream(filename),"GB2312");
              fr= new FileReader(filename);
              bufr = new BufferedReader(fr);
              String line = null;
              String newline=null;
                while((line=bufr.readLine())!=null)
                {
                    newline="沈阳,"+line;
                    writeFile("G:\\辽宁移动\\打击报表匹配导出\\整理\\ok\\ippool\\shenyang.txt",newline);
                    bufr.readLine();
                }
               
            } catch (IOException ex) {
               ex.printStackTrace();
            }finally
        {
           try {
               fr.close();
               bufr.close();
           } catch (IOException ex) {
               Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
     
    }
  public static String getYestertoday(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date d=new Date();
		d.setDate(d.getDate()-1);
		return sdf.format(d);
	}
   public static String getDay(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date d=new Date();
		d.setDate(d.getDate());
		return sdf.format(d);
	}
  
  public static String filekey(String key)
  {
      InputStream in = null;  
          try {
              String path = System.getProperty("user.dir");
//              System.out.println(path);
              String confPath = path.concat(File.separator).concat("config").concat(File.separator).concat("config.properties");
//              System.out.println(confPath);
              Properties prop = new Properties();
              in = new FileInputStream(confPath);
              prop.load(in);
//              System.out.println(prop.get(key));
              return prop.get(key).toString();
          } catch (Exception ex) {
              Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
              try {
                  in.close();
              } catch (IOException ex) {
                  Logger.getLogger(RWFile.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
            return null;
  }
  
}
