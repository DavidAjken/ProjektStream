/*
Er ikke implementeret grundet tidsmangel i forbindelse med manglende medlem
 */
import java.util.ArrayList;

public class Bruger {
    protected String username;
    protected String password;
    protected ArrayList<String> minListe;

    public Bruger(String username, String password) {
        this.username = username;
        this.password = password;
        ArrayList<String> minListe = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getMinListe() {
        return minListe;
    }

}


