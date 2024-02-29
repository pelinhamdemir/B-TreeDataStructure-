public class Employee<Key> {
    private Key id;
    private String name;
    private boolean gender;

    public Employee(Key id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Key getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getGender() {
        return gender;
    }
}