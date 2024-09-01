import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserManage {
    private List<User> userList;  // Use the provided list
    private List<Carbon> cosList;

    public UserManage(List<User> userList) {
        this.userList = userList;  // Initialize with the passed list
    }

    // Display the user list
    public void ReadList() {
        for (User user : userList) {
            System.out.println(user.getId() + "-" + user.getFirstName() + "-" + user.getLastName() + "-" + user.getAge());
        }
    }

    // Add a new user
    public void AddUser(Scanner scanner) {
        System.out.println("First name here: ");
        String firstName = scanner.nextLine();

        System.out.println("Last name here: ");
        String lastName = scanner.nextLine();

        System.out.println("Your age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        User user = new User(firstName, lastName, age);
        userList.add(user);
        System.out.println("User added successfully!");
    }

    // Find a user by ID
    public User foundUser(int id) {
        for (User user : userList) {
            if (user.getId() == id) {
                return user;
            }
        }
        System.out.println("There is no user with this ID!");
        return null;
    }

    public void userInfo(int id){
        for(User user : userList){
            if(user.getId() == id){
                System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getAge());
                // Display carbon consumption list
                List<Carbon> consumptions = user.getCarbons();
                if (consumptions.isEmpty()) {
                    System.out.println("  No carbon consumption data available.");
                } else {
                    System.out.println("  Carbon Consumption:");
                    for (Carbon carbon : consumptions) {
                        System.out.println("    - Quantity: " + carbon.getQuantity() +
                                ", Start Date: " + carbon.getStart() +
                                ", End Date: " + carbon.getEnd());
                    }
                }
            }
        }
    }

    // Delete a user by ID
    public void deleteUser(Scanner scanner) {
        System.out.println("Enter the user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        User user = foundUser(id);
        if (user != null) {
            userList.remove(user);
            System.out.println("User deleted successfully!");
        }
    }

    // Update user information
    public void updateUser(Scanner scanner) {
        System.out.println("Enter the user ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        User user = foundUser(id);

        if (user != null) {
            System.out.println("1 -> Update first name");
            System.out.println("2 -> Update last name");
            System.out.println("3 -> Update age");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Current first name: " + user.getFirstName());
                    System.out.println("Enter new first name: ");
                    String newFirstName = scanner.nextLine();

                    if (!newFirstName.trim().isEmpty()) {
                        user.setFirstName(newFirstName);
                    }
                    break;
                case 2:
                    System.out.println("Current last name: " + user.getLastName());
                    System.out.println("Enter new last name: ");
                    String newLastName = scanner.nextLine();

                    if (!newLastName.trim().isEmpty()) {
                        user.setLastName(newLastName);
                    }
                    break;
                case 3:
                    System.out.println("Current age: " + user.getAge());
                    System.out.println("Enter new age: ");
                    String ageString = scanner.nextLine();
                    if (!ageString.trim().isEmpty()) {
                        user.setLastName(ageString);
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.println("User updated successfully!");
        }
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

            Carbon carbon = new Carbon(quantity, startDate, endDate);
            user.addConsumption(carbon);  // Add the carbon consumption to the user
            System.out.println("Carbon consumption added successfully!");
        } else {
            System.out.println("User not found!");
        }
    }



}
