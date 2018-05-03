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
        String str = "Run on " + (date[1]+1) + "/" + date[2]+ "/" + date[0];
        str += System.getProperty("line.separator");
        str += "Time: " + timeAsString() + " seconds";
        str += System.getProperty("line.separator");
        str += "Distance: " + distance + " steps";
        str += System.getProperty("line.separator");
        str += "Average Speed: " + (distance / (time / 1000)) + " steps/second";
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

    public String timeAsString() {
        int sec = (int) (time / 1000);
        int min = sec / 60;
        sec -= min * 60;
        int hour = min / 60;
        min -= hour * 60;
        String t = "";
        if (hour < 10) {
            t += "0";
        }
        t += hour + ":";
        if (min < 10) {
            t += "0";
        }
        t += min + ":";
        if (sec < 10) {
            t += "0";
        }
        t += sec;
        return t;
    }
    public static String formatTime(Long ttt) {
        int sec = (int) (ttt / 1000);
        int min = sec / 60;
        sec -= min * 60;
        int hour = min / 60;
        min -= hour * 60;
        String t = "";
        if (hour < 10) {
            t += "0";
        }
        t += hour + ":";
        if (min < 10) {
            t += "0";
        }
        t += min + ":";
        if (sec < 10) {
            t += "0";
        }
        t += sec;
        return t;
    }
}
