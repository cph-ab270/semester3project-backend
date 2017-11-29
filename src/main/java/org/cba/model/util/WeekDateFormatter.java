package org.cba.model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by adam on 29/11/2017.
 */
public class WeekDateFormatter {
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-'W'ww");

    public String format(Date date) {
        return sdf.format(date);
    }

    public Date parse(String rawDate) throws ParseException {
        return sdf.parse(rawDate);
    }
}
