import java.util.ArrayList;
import java.util.List;
public class User {
    private static int idCounter = 1;
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    private List<Carbon> Carbonconsumption;

    public User(String firstName, String lastName, int age) {
        this.id = idCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.Carbonconsumption = new ArrayList<>();  // Initialize the list of consumptions
    }

    public User() {
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    //consommation
    public void addConsumption(Carbon consumption) {
        Carbonconsumption.add(consumption);
    }

    public List<Carbon> getCarbons() {
        return Carbonconsumption;
    }


}
