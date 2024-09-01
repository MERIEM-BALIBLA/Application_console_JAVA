import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private int age;
    private List<CarbonConsumption> CarbonConsumption;

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.CarbonConsumption = new ArrayList<>();  // Initialize the list of consumptions
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CarbonConsumption> getCarbonConsumption() {
        return CarbonConsumption;
    }

    public void addConsumption(CarbonConsumption consumption) {
        CarbonConsumption.add(consumption);
    }

    public String toString() {
        return
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age ;
    }
}
