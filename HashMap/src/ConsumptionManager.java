import Util.DateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.time.temporal.*;
import java.time.*;


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

    /*public LocalDate getStartOfWeek(LocalDate date) {
        return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    }

    // Function to get the end of the week for a given date
    public LocalDate getEndOfWeek(LocalDate startOfWeek) {
        return startOfWeek.plusDays(6);
    }

    // Function to find the week containing a specific date
    public void printWeeklyConsumptionForDateRange(List<CarbonConsumption> consumptionList, LocalDate startDate, LocalDate endDate) {
        LocalDate currentStartOfWeek = getStartOfWeek(startDate);

        // Loop through each week from startDate to endDate
        while (!currentStartOfWeek.isAfter(endDate)) {
            LocalDate currentEndOfWeek = getEndOfWeek(currentStartOfWeek);

            // Ensure the end of the week doesn't go beyond the given endDate
            if (currentEndOfWeek.isAfter(endDate)) {
                currentEndOfWeek = endDate;
            }

            System.out.println("\nWeek from " + currentStartOfWeek + " to " + currentEndOfWeek + ":");

            double weeklyQuantitySum = 0.0;  // Variable to accumulate quantity for the week

            // Check and accumulate the consumption data that falls within the current week
            for (CarbonConsumption consumption : consumptionList) {
                // Adjust the start and end dates to fit within the week boundaries
                LocalDate consumptionStart = consumption.getStart().isBefore(currentStartOfWeek) ? currentStartOfWeek : consumption.getStart();
                LocalDate consumptionEnd = consumption.getEnd().isAfter(currentEndOfWeek) ? currentEndOfWeek : consumption.getEnd();

                // Calculate the number of days for this part of the week
                long daysInWeek = ChronoUnit.DAYS.between(consumptionStart, consumptionEnd) + 1;

                // Calculate quantity for these days and add to the weekly sum
                double quantityForWeek = (consumption.getQuantity() / ChronoUnit.DAYS.between(consumption.getStart(), consumption.getEnd() ) + 1 ) * daysInWeek;
                weeklyQuantitySum += quantityForWeek;

                System.out.println("  " + consumptionStart + " to " + consumptionEnd + ": " + quantityForWeek);
            }

            // Print out the total quantity for the week
            System.out.println("Total quantity for the week: " + weeklyQuantitySum);

            // Move to the next week
            currentStartOfWeek = currentStartOfWeek.plusWeeks(1);
        }
    }
*/

    public void reportWeek(List<CarbonConsumption> carbonConsumptionList){

    }
    public void report(List<CarbonConsumption> carbonConsumptionList){
        double daily = 0;
        double weekly = 0;
        double monthly = 0;

        for (CarbonConsumption consomation : carbonConsumptionList) {
            long days = ChronoUnit.DAYS.between(consomation.getStart(), consomation.getEnd()) + 1;
            double dailyValue = consomation.getQuantity() / days;
            //daily += dailyValue;
            weekly += dailyValue * 7;
            monthly += dailyValue * 30;
            System.out.println("Weeks report : " + weekly );
            System.out.println("Months report : " + monthly );
        }
    }





}
