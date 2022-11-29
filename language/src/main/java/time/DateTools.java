package time;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateTools {
    public static long truncate(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTimeInMillis(time);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long t = calendar.getTime().getTime();
        System.out.println(t);
        return t;
    }

    public static void main(String[] args) {
        System.out.println(DateTools.truncate(1669623391000l));
    }
}
