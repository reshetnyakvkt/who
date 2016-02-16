package questionnaires.extras;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by IntelliJ IDEA
 * User: reashetnyak_viktor
 * Date: 04.11.2015
 */
public class DateUtilities {
    Date date;
    DateFormat df;

    public DateUtilities() {
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.date = null;
    }

    public DateUtilities(String dateString) {
        this();
        try{
            this.date = this.df.parse(dateString);
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }

    public DateUtilities(Date date) {
        this();
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public Date getDateStartDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public Date getDateEndDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
}
