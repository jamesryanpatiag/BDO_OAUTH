package cmi.bdo.oauth;

import cmi.bdo.oauth.util.DateUtil;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/24/2016
 *         Time: 7:33 PM
 */
public class TimeTest {

    public static void main(String[] args) {

        final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSX";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);

        Date date = new Date();

        String newDate = simpleDateFormat.format(date);

        System.out.println(newDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        Date tomorrow = calendar.getTime();

        System.out.println(DateUtil.zonedDateTIme(tomorrow));

        Timestamp timestamp = new Timestamp(new Date(DateUtil.zonedDateTIme(tomorrow)).getMinutes());

        System.out.println(timestamp);

//        ZonedDateTime time = ZonedDateTime.parse(newDate);
      //  System.out.println(time);
    }
}
