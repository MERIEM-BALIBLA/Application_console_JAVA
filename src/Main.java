//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private List<User> UserList = new ArrayList();

    public Main() {
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        User user1 = new User("Merry", "Ball", 24);
        User user2 = new User("Chyppo", "Ball", 3);
        main.UserList.add(user1);
        main.UserList.add(user2);
        System.out.println("User List:");
        main.ReadList();
        System.out.println("Wish operation you want to use : ");
        System.out.println("1 to add a new user");
        System.out.println("2 to delete an existing user");
        System.out.println("3 to update a specifyed user informations");
        System.out.println("Tap the number here : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        if (choice == 1) {
            main.AddUser(scanner);
        } else if (choice == 2) {
            main.deleteUser(scanner);
            System.out.println("The new User List:");
            main.ReadList();
        } else if (choice == 3) {
            main.updateUser();
        }

    }

    public void ReadList() {
        Iterator var1 = this.UserList.iterator();

        while(var1.hasNext()) {
            User user = (User)var1.next();
            PrintStream var10000 = System.out;
            int var10001 = user.getId();
            var10000.println("" + var10001 + " " + user.getFirstName() + " " + user.getLastName());
        }

    }

    public void AddUser(Scanner scanner) {
        System.out.println("First name here : ");
        String FirstName = scanner.nextLine();
        System.out.println("Last name here : ");
        String LastName = scanner.nextLine();
        System.out.println("Your age : ");
        int Age = scanner.nextInt();
        User user = new User(FirstName, LastName, Age);
        this.UserList.add(user);
    }

    public User foundUser(int id) {
        Iterator var2 = this.UserList.iterator();

        User user;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            user = (User)var2.next();
        } while(user.getId() != id);

        return user;
    }

    public void deleteUser(Scanner scanner) {
        System.out.println("Tap the user id that must be deleted :");
        int Id = scanner.nextInt();
        User user = this.foundUser(Id);
        if (user != null) {
            this.UserList.remove(user);
            System.out.println("Use has deleted succesfuly !");
        }

    }

    public void updateUser(Scanner scanner) {
        System.out.println("Chose one user to change his informations : ");
        int Id = scanner.nextInt();
    }
}
