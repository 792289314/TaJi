package Entity;

public class User {
    private long Id;
    //private String Password;
    private String Name;
    private String Email;
    public long getId() {
        return Id;
    }

    public String getEmail() {
        return Email;
    }

   /* public String getPassword() {
        return Password;
    }*/

    public String getName() {
        return Name;
    }


    public User(long id, String name, String email) {
        Id = id;
        Name = name;
        Email = email;
    }
}
