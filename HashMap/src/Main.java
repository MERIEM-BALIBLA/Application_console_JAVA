import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {


    public static void main(String[] args) {

        Map<Integer, User> userList =new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        int minlength = 5;
        User user1 = new User("Merry", "Bal", 24);
        User user2 = new User("CHYPPO", "Bal", 24);

        userList.put(1, user1);
        userList.put(2, user2);

        UserManager userManager = new UserManager(userList);

        boolean status = true;
        while (status){

            //Options
            System.out.println("------------------> Chose an operation");
            System.out.println("1 -------------> Users list");
            System.out.println("2 -------------> Add a new User");
            System.out.println("3 -------------> Found a User");
            System.out.println("4 -------------> Modify Users informations");
            System.out.println("5 -------------> Delete an exesting User");
            System.out.println("6 -------------> Single section of User information");
            System.out.println("7 -------------> Add a new consumption of carbon");

            int choice = scanner.nextInt();
            scanner.nextLine();
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

                //Trouver un user
                case 3:
                    System.out.println("Enter the id :");
                    int id = scanner.nextInt();

                    userManager.foundUser(id);
                    break;
                //--------

                //Modifier les informations d'un user
                case 4:
                    System.out.println("Enter the id :");
                    int Id = scanner.nextInt();
                    scanner.nextLine();

                    User user = userManager.foundUser(Id);
                    if (user != null) {
                        System.out.println("1 -> Update first name");
                        System.out.println("2 -> Update last name");
                        System.out.println("3 -> Update age");
                        int Choice = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        switch (Choice) {
                            //first name
                            case 1:
                                System.out.println("Current first name: " + user.getFirstName());
                                System.out.println("Enter new first name: ");
                                String newFirstName = scanner.nextLine();

                                if (!newFirstName.trim().isEmpty() && newFirstName.length()> minlength && newFirstName.matches("[a-zA-Z]+")) {
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

                                if (!newLastName.trim().isEmpty() && newLastName.length()> minlength && newLastName.matches("[a-zA-Z]+")) {
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
                        }
                    }
                    break;
                //--------

                //Supprimer un user
                case 5:
                    System.out.println("Enter the id :");
                    int ID = scanner.nextInt();
                    scanner.nextLine();

                    User User = userManager.foundUser(ID);
                    if(User != null){
                        userManager.deleteUser(ID);
                        System.out.println("the user has been deleted succesfully");
                    }else{
                        System.out.println("User not found !!");
                    }
                    break;
                //--------

                //Single page d'un user
                case 6:
                        System.out.println("Enter the id :");
                        int iD = scanner.nextInt();
                        scanner.nextLine();
                        userManager.SingleSection(iD);
                    break;
                //--------

                //l'ajout d'une consommation pour un user
                case 7:
                    System.out.println("Do you want to add new carbon consumption? YES / NO");
                    String response = scanner.nextLine();

                    if (response.equalsIgnoreCase("YES")) {  // Check user's decision
                        System.out.println("Please enter the user ID for whom to add consumption:");
                        int userId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline

                        userManager.Addconsumption(scanner, userId);  // Pass userId for consumption addition
                    } else {
                        System.out.println("No consumption added.");
                    }
                    break;
                //--------

                default: System.out.println("ERROR");
            }

        }
    }
}