import Util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.DayOfWeek;
import java.time.YearMonth;


public class ConsumptionManager {

    public ConsumptionManager() {
    }

    public void addConsumption(User user, Scanner scanner) {
        if (user != null) {

            //saisie de la quantité
            System.out.println("Please enter the quantity used: ");
            double quantity = scanner.nextDouble();
            scanner.nextLine();  // Consume newline after double

            LocalDate today = LocalDate.now();
            boolean validateStartDate = false;
            boolean validateEndDate = false;
            LocalDate startDate = null;
            LocalDate endDate = null;

            // Validation de la date de début
            while (!validateStartDate) {
                System.out.println("Please enter start date (YYYY-MM-DD): ");
                String startDateInput = scanner.nextLine();
                try {
                    startDate = LocalDate.parse(startDateInput);
                    if (!startDate.isBefore(today)) {
                        validateStartDate = true;
                    } else {
                        System.out.println("Start date must be today or in the future. Please try again.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }

            // Validation de la date de fin
            while (!validateEndDate) {
                System.out.println("Please enter end date (YYYY-MM-DD): ");
                String endDateInput = scanner.nextLine();
                try {
                    endDate = LocalDate.parse(endDateInput);
                    if (!endDate.isBefore(today)) {
                        validateEndDate = true;
                    } else {
                        System.out.println("End date must be today or in the future. Please try again.");
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
                }
            }



            boolean status = DateUtil.isAvailableInRange(startDate, endDate, listConsumptionDates(user.getCarbonConsumption()));
            if(!status){
                System.out.println("Is not available");
            }else{
                // Création d'objet consommation
                CarbonConsumption carbonConsumption = new CarbonConsumption(quantity, startDate, endDate);
                user.addConsumption(carbonConsumption);
                System.out.println("Carbon consumption added successfully!");
            }
        } else {
            System.out.println("User not found!");
        }
    }

    // Somme des consommation
    public List<LocalDate> listConsumptionDates(List<CarbonConsumption> carbonConsumptionList){
        List<LocalDate> listDate=new ArrayList<>();
        for (CarbonConsumption c : carbonConsumptionList){
            for (LocalDate currentDate=c.getStart();!currentDate.isAfter(c.getEnd());currentDate = currentDate.plusDays(1)){
                listDate.add(currentDate);
            }
        }
        return listDate;
    }
    public double calculateTotalConsumption(List<CarbonConsumption> carbonConsumptionList) {
        double totalConsumption = 0;
        for (CarbonConsumption carbonConsumption : carbonConsumptionList) {
            totalConsumption += carbonConsumption.getQuantity();
        }
        return totalConsumption;
    }

    public void rapportDay(List<CarbonConsumption> carbonConsumptionList) {
        for (CarbonConsumption carbonConsumption : carbonConsumptionList) {
            LocalDate startDate = carbonConsumption.getStart();
            LocalDate endDate = carbonConsumption.getEnd();
            long daysBetween = ChronoUnit.DAYS.between(startDate, endDate) + 1;  // +1 to include both start and end dates

            double quantityPerDay;
            quantityPerDay = carbonConsumption.getQuantity() / daysBetween;
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                System.out.println(currentDate + " quantity: " + quantityPerDay);
                currentDate = currentDate.plusDays(1);  // Move to the next day
            }
        }
    }

    public void reportWeek(List<CarbonConsumption> carbonConsumptionList) {
        Map<LocalDate, Double> weeklyConsumptionMap = new HashMap<>();

        for (CarbonConsumption consumption : carbonConsumptionList) {
            LocalDate startDate = consumption.getStart();
            LocalDate endDate = consumption.getEnd();
            double totalQuantity = consumption.getQuantity();
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double dailyConsumption = totalQuantity / totalDays;
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                LocalDate weekStart = currentDate.with(DayOfWeek.MONDAY);
                LocalDate weekEnd = weekStart.plusDays(6);
                if (currentDate.isAfter(weekStart)) {
                    weekStart = currentDate;
                }
                if (weekEnd.isAfter(endDate)) {
                    weekEnd = endDate;
                }
                long daysInWeek = ChronoUnit.DAYS.between(weekStart, weekEnd) + 1;
                double weeklyConsumption = daysInWeek * dailyConsumption;
                weeklyConsumptionMap.merge(weekStart, weeklyConsumption, Double::sum);
                currentDate = weekEnd.plusDays(1);
            }
        }
        for (Map.Entry<LocalDate, Double> entry : weeklyConsumptionMap.entrySet()) {
            LocalDate weekStart = entry.getKey();
            double totalConsumption = entry.getValue();
            System.out.println("Total consumption for the week starting " + weekStart + ": " + totalConsumption + " units.");
        }
    }

    public void reportMonth(List<CarbonConsumption> carbonConsumptionList) {
        Map<YearMonth, Double> monthlyConsumptionMap = new HashMap<>();

        for (CarbonConsumption consumption : carbonConsumptionList) {
            LocalDate startDate = consumption.getStart();
            LocalDate endDate = consumption.getEnd();
            double totalQuantity = consumption.getQuantity();
            long totalDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
            double dailyConsumption = totalQuantity / totalDays;
            LocalDate currentDate = startDate;
            while (!currentDate.isAfter(endDate)) {
                YearMonth currentMonth = YearMonth.from(currentDate);
                LocalDate monthEnd = currentMonth.atEndOfMonth();
                if (monthEnd.isAfter(endDate)) {
                    monthEnd = endDate;
                }
                long daysInMonth = ChronoUnit.DAYS.between(currentDate, monthEnd) + 1;
                double monthlyConsumption = daysInMonth * dailyConsumption;
                monthlyConsumptionMap.merge(currentMonth, monthlyConsumption, Double::sum);
                currentDate = monthEnd.plusDays(1);
            }
        }
        for (Map.Entry<YearMonth, Double> entry : monthlyConsumptionMap.entrySet()) {
            YearMonth month = entry.getKey();
            double totalConsumption = entry.getValue();
            System.out.println("Total consumption for " + month + ": " + totalConsumption + " units.");
        }
    }
}


