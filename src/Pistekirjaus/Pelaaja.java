// Lisääjä/Tekijä: Simo
package Pistekirjaus;
import java.util.LinkedHashMap;

import Nopat.Nopat;
import java.util.Map;

public class Pelaaja{
    Map<String, Integer> pisteet = new LinkedHashMap<>();

    Pistekirjaus pistekirjaus;
    Nopat nopat;
    String nimi;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.nopat = new Nopat();
        this.pistekirjaus = new Pistekirjaus();
    }

    public String getNimi() {
        return this.nimi;
    }

    public void pisteetTesti() {
        this.pisteet.put("Ykkoset", 1);
        this.pisteet.put("Kakkoset", 2);
        this.pisteet.put("Kolmoset", 3);

        pistekirjaus.tallennaPisteetTiedostoon(nimi);

        pistekirjaus.luePisteetTiedostosta(nimi);

        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // System.out.println(pistekirjaus.onkoTiedostoja());

    }

    public void poistaPisteet() {
        if(pistekirjaus.onkoTiedostoja()) {
            pistekirjaus.poistaPisteTiedostot();
        }
    }

    public int[] heitaNopat() {
        return new int[]{1, 2, 5, 1, 2};
    }

    public Map<String, Integer> getPisteet() {
        return this.pisteet;
    }

    
}
