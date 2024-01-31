package Pistekirjaus;
import java.util.HashMap;

public class Pistekirjaus {
    HashMap<String, Integer> pisteet = new HashMap<>();

    public void lisaaPisteet(String nimi, int pisteet) {
        this.pisteet.put(nimi, pisteet);
    }

    public void tulostaPisteet() {
        for (String nimi: this.pisteet.keySet()) {
            System.out.println(nimi + " " + this.pisteet.get(nimi));
        }
        System.out.println(this.pisteet.keySet());
    }

    public void runPisteet() {
        this.lisaaPisteet("Simo", 20);
        this.lisaaPisteet("Simo1", 100);

        this.tulostaPisteet();
    }

}
