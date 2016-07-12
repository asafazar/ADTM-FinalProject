package com.model.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by asaf on 02/02/2016.
 */
public class TimeCalculator {
    static Calendar calendar = Calendar.getInstance();

    public static Date getMonthlyExpirationDate(Date actionDate)
    {
        calendar.set( actionDate.getYear(), actionDate.getMonth() + 1, 1 );
        calendar.add( Calendar.DAY_OF_MONTH, -( calendar.get( Calendar.DAY_OF_WEEK ) % 7 + 1 ) );

        if (calendar.getTime().before(actionDate))
        {
            calendar.set( actionDate.getYear(), actionDate.getMonth() + 2, 1 );
            calendar.add( Calendar.DAY_OF_MONTH, -( calendar.get( Calendar.DAY_OF_WEEK ) % 7 + 1 ) );

        }

        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getWeeklyExpirationDate(Date actionDate)
    {
        Calendar actionCal = Calendar.getInstance();
        actionCal.setTime(actionDate);

        if (actionCal.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY)
        {
            int dayOfWeek = actionCal.get(Calendar.DAY_OF_WEEK);
            int daysUntilNextThursday = Calendar.THURSDAY - dayOfWeek;
            actionCal.add(Calendar.DATE, daysUntilNextThursday);
        }

        return actionDate;
    }

    public int getDaysNumUntillExpiration(Date actionDate, boolean isWeekly)
    {
        int days;
        calendar = Calendar.getInstance();
        Date expirationDate;

        if (isWeekly)
        {
            expirationDate = getWeeklyExpirationDate(actionDate);
        }
        else
        {
            expirationDate = getMonthlyExpirationDate(actionDate);
        }

        days = expirationDate.getDay() - calendar.getTime().getDay();
        
        if (expirationDate.getDay() < calendar.getTime().getDay())
        {
            days += 7;
        }

        return days;
    }
    public int getWeeksNumUntillExpiration(Date actionDate)
    {
        int weeks = 0;
        Date expirationDate = getMonthlyExpirationDate(actionDate);
        calendar = Calendar.getInstance();

        if (expirationDate.before(calendar.getTime())) {
            return weeks;
        }
        else
        {
            if (calendar.getTime().getDay() != expirationDate.getDay())
            {
                weeks--;
            }
            while (calendar.getTime().before(expirationDate)) {
                // add another week
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                weeks++;
            }
        }

        return weeks;
    }
}
