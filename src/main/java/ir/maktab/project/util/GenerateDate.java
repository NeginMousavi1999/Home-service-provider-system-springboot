package ir.maktab.project.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Negin Mousavi
 */
public class GenerateDate {
    public static Date generateByPattern(String pattern, String date) {//"yyyy-MM-dd"
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date requestDate = null;
        try {
            requestDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return requestDate;
    }
}
