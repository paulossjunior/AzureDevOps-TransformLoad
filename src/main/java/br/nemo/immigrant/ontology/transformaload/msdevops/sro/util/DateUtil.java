package br.nemo.immigrant.ontology.transformaload.msdevops.sro.util;
import java.time.LocalDate;
public class DateUtil {


    public  static LocalDate createLocalDate(String day, String month, String year){

        month = checkMonthAndDay(month);
        day = checkMonthAndDay(day);

        String date = year+"-"+month+"-"+day;
        return  LocalDate.parse(date);


    }

    private static String checkMonthAndDay(String value){
        return value.length() == 2? value: "0"+value;
    }
}
