import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Map<Integer, User> userList =new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        User user1 = new User("Merry", "Bal", 24);
        User user2 = new User("CHYPPO", "Bal", 24);

        userList.put(1, user1);
        userList.put(2, user2);

        UserManager userManager = new UserManager(userList);
        ConsumptionManager consumptionManager = new ConsumptionManager() ;

        boolean status = true;
        while (status){

            //Options menu
            System.out.println("------------------> Chose an operation");
            System.out.println("1 -------------> Users list");
            System.out.println("2 -------------> Add a new User");
            //System.out.println("3 -------------> Found a User");
            System.out.println("3 -------------> Modify Users informations");
            System.out.println("4 -------------> Delete an exesting User");
            System.out.println("5 -------------> Single section of User information");
            System.out.println("6 -------------> Add a new consumption of carbon");
            System.out.println("7 -------------> consumption rapport");

            int choice = scanner.nextInt();
            scanner.nextLine();

            //operations
            switch (choice){

                //affichage
                case 1:
                    System.out.println("Users list :");
                    userManager.UserList();
                    break;
                //--------

                // l'ajout d'un user
                case 2:
                    System.out.println("first name:");
                    String firstName = scanner.nextLine();

                    System.out.println("last name :");
                    String lastName = scanner.nextLine();

                    System.out.println("the age :");
                    int age = scanner.nextInt();

                    int key = userManager.addUser(firstName, lastName, age);
                    System.out.println("Utilisateur ajouté avec la clé : " + key);
                    break;
                //--------

                //Modifier les informations d'un user
                case 3:
                    System.out.println("Enter the id :");
                    userManager.updateUser();
                    break;
                //--------

                //Supprimer un user
                case 4:
                    System.out.println("Enter the id :");
                    int ID = scanner.nextInt();
                    scanner.nextLine();

                    User User = userManager.foundUser(ID);
                    if(User != null){
                        userManager.deleteUser(ID)
                        ;
                        System.out.println("the user has been deleted succesfully");
                    }else{
                        System.out.println("User not found !!");
                    }
                    break;
                //--------

                //Single page d'un user
                case 5:
                        System.out.println("Enter the id :");
                        int iD = scanner.nextInt();
                        scanner.nextLine();
                        userManager.SingleSection(iD);
                    break;
                //--------

                //l'ajout d'une consommation pour un user
                case 6:
                    System.out.println("Do you want to add new carbon consumption? YES / NO");
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("YES")) {  // Check user's decision
                        System.out.println("Please enter the user ID for whom to add consumption:");
                        int userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline
                        User user = userManager.foundUser(userId);  // Find user by the ID that was passed in
                        consumptionManager.addConsumption(user,scanner);  // Pass userId for consumption addition
                    } else {
                        System.out.println("No consumption added.");
                    }
                    break;
                //--------

                //Rapport de consommation
                case 7:
                    System.out.println("Enter the user ID for the report:");
                    int reportID = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    User userForReport = userManager.foundUser(reportID);

                    if (userForReport != null) {
                        System.out.println("Consumption Report:");
                        List<CarbonConsumption> consumptions = userForReport.getCarbonConsumption();

                        if (!consumptions.isEmpty()) {
                            consumptionManager.rapportDay(consumptions);
                            LocalDate sdate = consumptions.get(0).getStart();
                            LocalDate edate = consumptions.get(0).getEnd();

                            consumptionManager.printWeeklyConsumptionForDateRange(consumptions, sdate, edate);

                        } else {
                            System.out.println("No consumption data found for this user.");
                        }
                    } else {
                        System.out.println("User not found.");
                    }
                    break;

                default: System.out.println("ERROR");
            }

        }
    }
}