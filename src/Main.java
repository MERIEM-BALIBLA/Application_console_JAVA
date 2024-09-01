import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<User> userList;
    private UserManage userManage;


    public Main() {
        userList = new ArrayList<>();
        userManage = new UserManage(userList);
    }

    //list of user
    public void ReadList() {
        userManage.ReadList();
    }

    //add a user
    public void AddUser(Scanner scanner) {
        userManage.AddUser(scanner);
    }

    //found a user
    public void singleSection(Scanner scanner) {
        System.out.println("Enter the user ID to find: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        userManage.userInfo(id);
    }

    //delete user
    public void deleteUser(Scanner scanner) {
        userManage.deleteUser(scanner);
    }

    //modify users informations
    public void updateUser(Scanner scanner) {
        userManage.updateUser(scanner);
    }

    //for consumption of carbon
    public void addconsumption(Scanner scanner, int userId) {
        userManage.Addconsumption(scanner, userId);  // Call the method from userManage to add consumption
    }

    //view
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        User user1 = new User("Merry", "Ball", 24);
        User user2 = new User("Chyppo", "Ball", 3);
        main.userList.add(user1);
        main.userList.add(user2);

        boolean continueLoop = true;  // To keep the program running for multiple operations
        while (continueLoop) {

        System.out.println("------------------ USER LIST ------------------");
        System.out.println("Chose an operation you want to use today: ");
        System.out.println("1 ----------> to add a new user");
        System.out.println("2 ----------> to delete an existing user");
        System.out.println("3 ----------> to update a specifyed user informations");
        System.out.println("4 ----------> to add a new carbon consumption");
        System.out.println("5 ----------> to take a look at the list of users ");
        System.out.println("6 ----------> to take a look at a user informations ");

        System.out.println("------------------ Tap the number here :");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1 :
                    main.AddUser(scanner);
                    System.out.println("The new User List:");
                    main.ReadList();
                break;
            case 2 :
                    main.deleteUser(scanner);
                    System.out.println("The new User List:");
                    main.ReadList();
                break;
            case 3 :
                    main.updateUser(scanner);
                    System.out.println("The new User informations :");
                    main.ReadList();
                break;
            case 4:
                System.out.println("Do you want to add new carbon consumption? YES / NO");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("YES")) {  // Check user's decision
                    System.out.println("Please enter the user ID for whom to add consumption:");
                    int userId = scanner.nextInt();
                    scanner.nextLine();  // Consume the newline

                    main.addconsumption(scanner, userId);  // Pass userId for consumption addition
                } else {
                    System.out.println("No consumption added.");
                }
                break;
            case 5 :
                    main.ReadList();
                break;
            case 6:
                    main.singleSection(scanner);
                break;
            default :
                System.out.println("Invalid choice.");
        }
        }
    }
}
