package tool.excelpoi;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/7/28.
 */
public class DateUtil {
    public static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long dateStartTime(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = simpleDateFormat.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime() / 1000;
        return ts;
    }

    /*
     * 将时间转换为时间戳(2016-10-19-11)
     */
    public static long dateToStampHour(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime() / 1000;
        return ts;
    }

    /*
    *获取昨天的时间
    * 格式yyyy-MM-dd
    * */
    public static String getDayOfYesterday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday = dateFormat.format(cal.getTime());
        return yesterday;
    }
    /*
    *获取当月
    * 格式yyyy-MM
    * */
    public static String getMonth() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
//        cal.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday = dateFormat.format(cal.getTime());
        return yesterday;
    }

    /*
    *获取昨天的时间
    * 格式yyyy/MM/dd
    * */
    public static String getDayOfYesterdays() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        String yesterday = dateFormat.format(cal.getTime());
        return yesterday;
    }

    /**
     * 时间戳转换成String
     *
     * @param s
     * @return
     * @throws ParseException
     */
    public static String stampTodate(Long s) throws ParseException {
        //时间戳转化为Sting或Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(s * 1000);
        return d;
    }

    /*
     * 将时间转换为时间戳(2016-10-19)
     */
    public static long dateToStampDay(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long ts = date.getTime() / 1000;
        return ts;
    }


    /*
    *获取当天的时间
    * 格式yyyy-MM-dd-hh
    * */
    public static String getDateNowHour() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH");
        String nowTime = dateFormat.format(new Date());
        return nowTime;
    }

    /*
    *获取当天的时间
    * 格式yyyy-MM-dd-hh
    * */
    public static String getDateNowDay() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String nowTime = dateFormat.format(new Date());
        return nowTime;
    }

    /*
  *获取当前的时间
  * 格式yyyy-MM-dd HH:mm:ss
  * */
    public static String getDateNowss() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = dateFormat.format(new Date());
        return nowTime;
    }

    /*
  *获取明天的时间
  * 格式yyyy-MM-dd
  * */
    public static String getDayOfTomorrow() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = new GregorianCalendar();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 1);
        String tomorrow = dateFormat.format(cal.getTime());
        return tomorrow;
    }

    public static void main(String[] args) {
//        System.out.println(getDayOfTomorrow());
//        System.out.println(getDayOfYesterday());
        System.out.println(getMonth());
    }
}
