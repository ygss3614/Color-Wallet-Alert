package com.colorwalletalert.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import java.util.Calendar;

public class Utils {

    private static Utils instance;

    Utils(){}

    public static Utils getInstance(){
        if (instance == null){
            return new Utils();
        }
        return instance;
    }
    /***
     * name: roundTwoDecimals
     * description: format a float value to set precision to two
     * months
     * params:
     *
     * @return Float decimalFormat
     * @param number
     */
    public String roundTwoDecimals(Float number) {
        String decimalFormat = String.format("%.02f", number);
        return decimalFormat;
    }

    /***
     * name: getDaysToMonthEnd
     * description: Calculates how many days left to month end
     * months
     * from: https://stackoverflow.com/questions/47059523/how-to-get-number-of-days-between-todays-date-and-last-date-of-the-current-mont
     * params:
     *
     * @return int daysLeft
     * @param
     */
    public int getDaysToMonthEnd (){
        Calendar calendar = Calendar.getInstance();
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int daysLeft = lastDay - currentDay;
        return daysLeft;
    }

    /***
     * name: calculateNoOfColumns
     * description: Calculates number of columns according to screen size
     * months
     * from: https://stackoverflow.com/questions/33575731/gridlayoutmanager-how-to-auto-fit-columns
     * params:
     *
     * @return int daysLeft
     * @param
     */


    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

}
