package eu.ase.ro.lab6.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//class used to manipulate conversion of a Date to a String and the other way around
public class DateConverter {
    //it is an utility class used for converting a String to a Date and a Date to a String
    //it receives a date format that will be applied during conversion
    //the constructor contains a second parameter that identifies the region in which the date is to be applied
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    public static Date fromString(String dateString) {
        try {
            //parse() -> used to extract a Date object from a String
            return formatter.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String fromDate(Date value) {
        if(value == null) {
            return null;
        }
        //format() -> used to convert a Date in a String
        return formatter.format(value);
    }
}