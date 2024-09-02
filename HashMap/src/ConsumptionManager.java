import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

            // Création d'objet consommation
            CarbonConsumption carbonConsumption = new CarbonConsumption(quantity, startDate, endDate);
            user.addConsumption(carbonConsumption);
            System.out.println("Carbon consumption added successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    // Somme des consommation
    public double calculateTotalConsumption(List<CarbonConsumption> carbonConsumptionList) {
        double totalConsumption = 0;
        for (CarbonConsumption carbonConsumption : carbonConsumptionList) {
            totalConsumption += carbonConsumption.getQuantity();
        }
        return totalConsumption;
    }

    public void rapport(List<CarbonConsumption> carbonConsumptionList ){
        Map<LocalDate, Double> ListCarbonJournalier = new HashMap<>();

        for(CarbonConsumption carbonConsumption : carbonConsumptionList){

            LocalDate StartDate = carbonConsumption.getStart();
            LocalDate EndDate = carbonConsumption.getEnd();
            double Quantity = carbonConsumption.getQuantity();

            ListCarbonJournalier.put(StartDate, Quantity);
        }
    }

}
