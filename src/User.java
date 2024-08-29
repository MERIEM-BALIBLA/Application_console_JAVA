//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


public class User {
    private static int idCounter = 1;
    private int id;
    private String FirstName;
    private String LastName;
    private int Age;

    public User(String firstName, String lastName, int age) {
        this.id = idCounter++;
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Age = age;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }

    public int getAge() {
        return this.Age;
    }

    public void setAge(int age) {
        this.Age = age;
    }
}
