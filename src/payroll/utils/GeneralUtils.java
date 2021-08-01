package utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Stream;

import models.Company;

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

    public static String saveState(Company company){
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(company);
            oos.close();
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't save the state.\n\n");
            return "";
        }
    }

    public static Company restoreState(String state){
        try{
            byte[] data = Base64.getDecoder().decode(state);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data));
            return (Company) ois.readObject();
        }catch(Exception err){
            err.printStackTrace();
            System.out.println("\n\nCouldn't restore the state.\n\n");
            return null;
        }
    }
}
