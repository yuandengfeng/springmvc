/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class AcObj {
    public static void main(String[] args)
    {
         
//         filekey("detectIp");
//         filekey("resultFile");
//         filekey("ipPoolFold");
//        
        for(int i=0;i<10;i++)
        {
             System.out.println(i);
        }
    
    }
    
     public static String filekey(String key)
  {
      InputStream in = null;  
          try {
              String path = System.getProperty("user.dir");
//              System.out.println(path);
              String confPath = path.concat(File.separator).concat("config").concat(File.separator).concat("config.properties");
              System.out.println(confPath);
              Properties prop = new Properties();
              in = new FileInputStream(confPath);
              prop.load(in);
              System.out.println(prop.get(key));
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
  
    
    
//   acValue=ac名称+ac公网ip+城市
    String acValue;
// acIpSection1-3表示三段ip地址  
    String acIpSection1;
    String acIpSection2;
    String acIpSection3;
    public AcObj(String value,String section1,String section2,String section3)
    {
      this.acValue=value;
      this.acIpSection1=section1;
      this.acIpSection2=section2;
      this.acIpSection3=section3;
    }
    
    public boolean ipExistsInRange(String ip)
    {
      if(IpRangeIn.ipExistsInRange(ip, this.acIpSection1))
          return true;
      else if(IpRangeIn.ipExistsInRange(ip, this.acIpSection2))
           return true;
      else if(IpRangeIn.ipExistsInRange(ip, this.acIpSection3))
           return true;
      return false;
    }
    
    
    
}
