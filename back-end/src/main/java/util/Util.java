package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    
    public static Long generateUniqueLong() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        String dateTime = ft.format(dNow);
        return Long.parseLong(dateTime);
    }
}
