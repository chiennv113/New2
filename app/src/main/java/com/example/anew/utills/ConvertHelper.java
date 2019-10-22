package com.example.anew.utills;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertHelper {
    public static long convertStringToTimestampMilisecond(String string) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = date.getTime() / 1000;
        return time;
    }
}
