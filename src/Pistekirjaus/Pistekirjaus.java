package Pistekirjaus;
import java.util.HashMap;

public class Pistekirjaus {
    HashMap<String, Integer> pisteet = new HashMap<>();

    public void lisaaPisteet(String nimi, int pisteet) {
        this.pisteet.put(nimi, pisteet);
    }

    public int getPisteet(String nimi) {
        return this.pisteet.get(nimi);
    }

}
