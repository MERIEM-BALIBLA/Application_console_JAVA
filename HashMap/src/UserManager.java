import java.time.LocalDate;
import java.util.*;
import java.time.format.DateTimeParseException;
public class UserManager {
    private Map<Integer, User> userList;
    private int currentKey;
    //private ConsumptionManager carbonConsumptionManager;
    private ConsumptionManager carbonConsumptionManager;

    public UserManager(Map<Integer, User> userList) {
        this.userList = userList;
        this.currentKey = 1;
        this.carbonConsumptionManager = new ConsumptionManager(); // Initialiser ici
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

    public void SingleSection(int id ){

        User user = foundUser(id);

        if (user != null) {
            System.out.println("Utilisateur trouvé : " + user);
            List<CarbonConsumption> consumptions = user.getCarbonConsumption();

            //loop pour afficher les elements du consommation
            if (consumptions.isEmpty()) {
                System.out.println("  No carbon consumption data available.");
            } else {
                System.out.println("  Carbon Consumption:");
                for (CarbonConsumption carbon : consumptions) {
                    System.out.println("    - Quantity: " + carbon.getQuantity() +
                            ", Start Date: " + carbon.getStart() +
                            ", End Date: " + carbon.getEnd()
                    );
                }
            }

            double totalConsumption = carbonConsumptionManager.calculateTotalConsumption(consumptions);

            System.out.println("Total Carbon Consumption for user: " + totalConsumption);

        } else {
            System.out.println("Aucun utilisateur trouvé avec l'ID : " + id);
        }
    }

    public void updateUser(){
        Scanner scanner = new Scanner(System.in);
        int Id = scanner.nextInt();
        scanner.nextLine();

        User user =foundUser(Id);
        if (user != null) {
            System.out.println("1 -> Update first name");
            System.out.println("2 -> Update last name");
            System.out.println("3 -> Update age");

            //Scanner scanner = new Scanner(System.in);
        int Choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        switch (Choice) {
            //first name
            case 1:
                System.out.println("Current first name: " + user.getFirstName());
                System.out.println("Enter new first name: ");
                String newFirstName = scanner.nextLine();

                if (!newFirstName.trim().isEmpty()  && newFirstName.matches("[a-zA-Z]+")) {
                    user.setFirstName(newFirstName);
                }else{
                    System.out.println("the name should be more than 4 leters ");
                }
                break;
            //last name
            case 2:
                System.out.println("Current last name: " + user.getLastName());
                System.out.println("Enter new last name: ");
                String newLastName = scanner.nextLine();

                if (!newLastName.trim().isEmpty() && newLastName.matches("[a-zA-Z]+")) {
                    user.setLastName(newLastName);
                }else {
                    System.out.println("the name should be more than 4 leters ");
                }
                break;
            //age
            case 3:
                System.out.println("Current age: " + user.getAge());
                System.out.println("Enter new age: ");
                try {
                    int newAge = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character left by nextInt()
                    user.setAge(newAge);
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input for age. Please enter a valid integer.");
                    scanner.nextLine(); // Clear the invalid input
                }
                break;
            default:
                System.out.println("Invalid choice.");
        }}
    }
}
