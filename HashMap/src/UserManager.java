import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    private Map<Integer, User> userList;
    private int currentKey;

    //public UserManager() {
      //  this.userList = new HashMap<>();
       // this.currentKey = 1; // Démarrer les clés à 1
   // }

    public UserManager(Map<Integer, User> userList) {
        this.userList = userList;
        this.currentKey = 1;
    }

    public void UserList(){
        for (Map.Entry<Integer, User> entry : userList.entrySet()) {
            Integer key = entry.getKey();
            User user = entry.getValue();
            System.out.println("ID: " + key + "\n" + user);
        }
    }

    // Ajouter un utilisateur
    public int addUser(String firstName, String lastName, int age) {
        User user = new User(firstName, lastName, age);
        userList.put(currentKey, user);

        return currentKey++; // Retourner la clé et incrémenter pour le prochain ajout
    }

    public User foundUser(int id){
        User user = userList.get(id);
        if (user != null) {
          //  System.out.println("Utilisateur trouvé : " + user);
            return user;
        } else {
          //  System.out.println("Aucun utilisateur trouvé avec l'ID : " + id);
            return null;
        }
    }

    public void deleteUser(int id){
        userList.remove(id);
    }


    public void Addconsumption(Scanner scanner, int userId) {
        User user = foundUser(userId);  // Find user by the ID that was passed in

        if (user != null) {
            System.out.println("Please enter the quantity used: ");
            double quantity = scanner.nextDouble();
            scanner.nextLine();  // Consume newline after double

            System.out.println("Please enter start date (YYYY-MM-DD): ");
            String startDateInput = scanner.nextLine();
            LocalDate startDate = LocalDate.parse(startDateInput);

            System.out.println("Please enter end date (YYYY-MM-DD): ");
            String endDateInput = scanner.nextLine();
            LocalDate endDate = LocalDate.parse(endDateInput);

            CarbonConsumption CarbonConsumption = new CarbonConsumption(quantity, startDate, endDate);
            user.addConsumption(CarbonConsumption);  // Add the carbon consumption to the user
            System.out.println("Carbon consumption added successfully!");
        } else {
            System.out.println("User not found!");
        }
    }

    public double consumptionSumm(List<CarbonConsumption> carbonConsumptionList) {
        double totalConsumption = 0;
        for (CarbonConsumption carbonConsumption : carbonConsumptionList) {
            totalConsumption += carbonConsumption.getQuantity();
        }
        return totalConsumption;
    }

    public void SingleSection(int id ){
        User user = foundUser(id);
        if (user != null) {
            System.out.println("Utilisateur trouvé : " + user);
            List<CarbonConsumption> consumptions = user.getCarbonConsumption();
            if (consumptions.isEmpty()) {
                System.out.println("  No carbon consumption data available.");
            } else {
                System.out.println("  Carbon Consumption:");
                for (CarbonConsumption carbon : consumptions) {
                    System.out.println("    - Quantity: " + carbon.getQuantity() +
                            ", Start Date: " + carbon.getStart() +
                            ", End Date: " + carbon.getEnd()
                    );
                    double totalConsumption = consumptionSumm(consumptions);
                    System.out.println("Total Carbon Consumption for user: " + totalConsumption);
                }
            }
        } else {
            System.out.println("Aucun utilisateur trouvé avec l'ID : " + id);
        }
    }

}
