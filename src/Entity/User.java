package Entity;

public class User {
    private long Id;
    //private String Password;
    private String Name;
    private String Email;

    public User() {
    }

    public User(String userName) {
        Name = userName;
    }

    public long getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }


    public String getName() {
        return Name;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public User(long id, String name, String email) {
        Id = id;
        Name = name;
        Email = email;
    }
}
