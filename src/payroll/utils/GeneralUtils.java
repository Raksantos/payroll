package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class GeneralUtils {
    public static ArrayList<Integer> convertDateToArray(String date){
        List<String> dateData = Stream.of(date.split("-"))
        .map(elem -> new String(elem)).collect(Collectors.toList());

        int year = Integer.parseInt(dateData.get(0));
        int month = Integer.parseInt(dateData.get(1));
        int day = Integer.parseInt(dateData.get(2));

        ArrayList<Integer> data = new ArrayList<Integer>();
        
        data.add(year);
        data.add(month);
        data.add(day);

        return data;
    }

    public static LocalDate getLastJobDay(LocalDate lastDayOfMonth){
        LocalDate lastJobDay;

        if(lastDayOfMonth.getDayOfWeek() == DayOfWeek.SATURDAY){
            lastJobDay = lastDayOfMonth.minusDays(1);
        }else if(lastDayOfMonth.getDayOfWeek() == DayOfWeek.SUNDAY){
            lastJobDay = lastDayOfMonth.minusDays(2);
        }else{
            lastJobDay = lastDayOfMonth;
        }

        return lastJobDay;
    }
}
