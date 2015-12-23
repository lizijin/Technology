
package com.cyou.cma.clocker.theme.sdk.utils;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * 获取日期的帮助类
 * 
 * @author jiangbin
 */
public class DateHelper {

    public DateHelper() {
    }

    public static TimeFormat getTimeFormat(Context context, TimeFormat timeFormat) {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        // TimeFormat timeFormat = new TimeFormat();

        if (DateFormat.is24HourFormat(context)) {

            timeFormat.first = hour / 10;
            timeFormat.second = hour - timeFormat.first * 10;
            timeFormat.third = minute / 10;
            timeFormat.fourth = minute - timeFormat.third * 10;
        } else {
            if (hour < 12) {
                // mBitmapUse = mBitmapAm;
                if (hour == 0) {
                    hour = 12;
                }
                timeFormat.first = hour / 10;
                timeFormat.second = hour - timeFormat.first * 10;
                timeFormat.third = minute / 10;
                timeFormat.fourth = minute - timeFormat.third * 10;
                timeFormat.amorpm = 1;
            } else {

                if (hour != 12) {
                    hour -= 12;
                }
                timeFormat.first = hour / 10;
                timeFormat.second = hour - timeFormat.first * 10;
                timeFormat.third = minute / 10;
                timeFormat.fourth = minute - timeFormat.third * 10;
                timeFormat.amorpm = 2;
            }
        }
        return timeFormat;
    }

    public static class TimeFormat {
        int first;
        int second;
        int third;
        int fourth;
        int amorpm;// 0 none 1 am 2 pm

        @Override
        public String toString() {
            return "" + first + "" + second + ":" + third + "" + fourth;
        }

        public String getAmOrPm() {
            switch (amorpm) {
                case 0:
                    return "";
                case 1:
                    return "AM";
                case 2:
                    return "PM";
                default:
                    return "";
            }
        }
    }

}
