package Util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateUtil {
    public static boolean isAvailableInRange(LocalDate startDate , LocalDate endDate , List<LocalDate> range){
        List<LocalDate> dateList = new ArrayList<>();
        for(LocalDate sdate = startDate; !sdate.isAfter(endDate); sdate= sdate.plusDays(1)){
            if (range.contains(sdate)){
                return false;
            }
        }


        return true;
    }

}
