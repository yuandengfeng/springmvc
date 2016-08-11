package auth;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/8/4.
 */
public class TimeTest {

    public static void main(String[] args)
    {
        Calendar calendar = Calendar.getInstance();
        Date times = calendar.getTime();
        Timer time = new Timer();

        time.schedule(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar = Calendar.getInstance();
                System.out.println(calendar.getTime());
            }
        },times,6*1000); //每6s中执行一次


    }


}
