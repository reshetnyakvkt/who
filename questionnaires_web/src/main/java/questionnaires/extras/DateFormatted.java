package questionnaires.extras;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by D2R2 on 04.11.2015.
 */
public class DateFormatted {
    Date date;
    DateFormat df;

    public DateFormatted() {
        this.df = new SimpleDateFormat("yyyy-MM-dd");
        this.date = null;
    }

    public DateFormatted(String dateString) {
        this();
        try{
            this.date = this.df.parse(dateString);
        }
        catch ( Exception ex ){
            System.out.println(ex);
        }
    }

    public Date getDate() {
        return date;
    }
}
