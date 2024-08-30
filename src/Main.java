import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<User> userList;
    private UserManage userManage;

    private CarbonManage carbonManage;

    public Main() {
        userList = new ArrayList<>();
        userManage = new UserManage(userList);
    }

    public void ReadList() {
        userManage.ReadList();
    }

    public void AddUser(Scanner scanner) {
        userManage.AddUser(scanner);
    }

    public void foundUser(Scanner scanner) {
        System.out.println("Enter the user ID to find: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        userManage.userInfo(id);
    }

    public void deleteUser(Scanner scanner) {
        userManage.deleteUser(scanner);
    }

    public void updateUser(Scanner scanner) {
        userManage.updateUser(scanner);
    }

    public void addconsumption(User user, Carbon consumption) {
        // Ensure user and consumption are not null
        if (user != null) {
            // Add consumption to the user's list of consumptions
            //user.Addconsumption(consumption);
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        User user1 = new User("Merry", "Ball", 24);
        User user2 = new User("Chyppo", "Ball", 3);
        main.userList.add(user1);
        main.userList.add(user2);
        System.out.println("User List:");
        main.ReadList();
        System.out.println("Wish operation you want to use : ");
        System.out.println("1 to add a new user");
        System.out.println("2 to delete an existing user");
        System.out.println("3 to update a specifyed user informations");
        System.out.println("4 to take a look at users informations ");

        System.out.println("Tap the number here : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            main.AddUser(scanner);
            System.out.println("The new User List:");
            main.ReadList();
        } else if (choice == 2) {
            main.deleteUser(scanner);
            System.out.println("The new User List:");
            main.ReadList();
        } else if (choice == 3) {
            main.updateUser(scanner);
        }else if (choice == 4){
            main.foundUser(scanner);

            System.out.println("You want to add new quantity ?");

            System.out.println("OUI / NON");
            String response = scanner.nextLine();
            if(response.equals("OUI")){
                System.out.print("HELLO");
               // main.addconsumption();
            }else{
                System.out.print("none");
            }

        }
    }
}
