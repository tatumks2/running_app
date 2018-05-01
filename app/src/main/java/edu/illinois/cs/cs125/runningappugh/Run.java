package edu.illinois.cs.cs125.runningappugh;
import java.util.Calendar;

public class Run {
    //date goes year, month, day of month
    private int[] date;
    public double distance;
    public long time;
    public int rating;
    public String notes;
    public Run lastRun;
    public static Run lastGlobalRun;

    Run (Run last) {
        time = 0;
        distance = 0;
        lastRun = last;
        rating = -1;
        Calendar cal = Calendar.getInstance();
        date = new int[3];
        date[0] = cal.get(Calendar.YEAR);
        date[1] = cal.get(Calendar.MONTH);
        date[2] = cal.get(Calendar.DAY_OF_MONTH);
    }

    public String getString () {
        String str = "Run on " + date[1] + "/" + date[2]+ "/" + date[0];
        str += System.getProperty("line.separator");
        str += "Time: " + (time / 1000);
        str += System.getProperty("line.separator");
        str += "Distance: " + distance;
        str += System.getProperty("line.separator");
        str += "Average Speed: " + (distance / (time / 1000));
        if (rating != -1) {
            str += System.getProperty("line.separator");
            str += "Rating: " + rating + " stars";
        }
        if (notes != null) {
            str += System.getProperty("line.separator");
            str += "Notes: " + notes;
        }
        str += System.getProperty("line.separator");
        return str;
    }

    public String getAllStrings() {
        if (lastRun != null) {
            return getString() + System.getProperty("line.separator") + lastRun.getAllStrings();
        } else {
            return getString();
        }
    }
}
