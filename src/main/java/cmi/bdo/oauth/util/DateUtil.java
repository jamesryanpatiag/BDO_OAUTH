package cmi.bdo.oauth.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jonathan Leijendekker
 *         Date: 01/27/2016
 *         Time: 11:11 PM
 */
public class DateUtil {

    public static String zonedDateTIme(Date date) {
        final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSSX";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_FORMAT);

        String newDate = simpleDateFormat.format(date);

        return newDate;
    }

}
