package cd.amateurmobiledev.ecodim;

import com.google.gson.annotations.SerializedName;

public class Contact {
    @SerializedName("name")
    private String Name;
    @SerializedName("email")
    private String Email;

    public Contact(String name, String email) {
        Name = name;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
