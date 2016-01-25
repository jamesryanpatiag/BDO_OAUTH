package cmi.bdo.oauth;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
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

        ZonedDateTime time = ZonedDateTime.parse(newDate);
        System.out.println(time);
    }
}
