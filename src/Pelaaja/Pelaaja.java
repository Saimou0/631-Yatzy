// Lisääjä/Tekijä: Simo
package Pelaaja;
import java.util.Map;

public class Pelaaja{
    Pistekirjaus pistekirjaus;
    Nopat nopat;
    String nimi;
    public int vuorojenMaara = 0;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.nopat = new Nopat();
        this.pistekirjaus = new Pistekirjaus();
    }

    public String getNimi() {
        return this.nimi;
    }

    public void pisteetTesti() {
        pistekirjaus.pisteet.put("Ykköset", -1);
        pistekirjaus.pisteet.put("Kakkoset", 2);
        pistekirjaus.pisteet.put("Kolmoset", 3);
        pistekirjaus.pisteet.put("Neloset", 4);
        pistekirjaus.pisteet.put("Viitoset", 5);
        pistekirjaus.pisteet.put("Kuutoset", 6);

        pistekirjaus.pisteet.put("Välisumma", 21);

        pistekirjaus.pisteet.put("Pari", 10);
        pistekirjaus.pisteet.put("Kaksi paria", 20);
        pistekirjaus.pisteet.put("Kolme samaa", 30);

        pistekirjaus.pisteet.put("Neljä samaa", 30);
        pistekirjaus.pisteet.put("Pikku suora", 30);
        pistekirjaus.pisteet.put("Iso suora", 30);
        pistekirjaus.pisteet.put("Taysikäsi", 30);
        pistekirjaus.pisteet.put("Sattuma", 30);
        pistekirjaus.pisteet.put("Yatzy", 30);
        
        pistekirjaus.pisteet.put("Summa", 30);


        pistekirjaus.tallennaPisteetTiedostoon(nimi);

        pistekirjaus.luePisteetTiedostosta(nimi);
    }

    // Poistaa KAIKKI piste tiedostot
    public void poistaPisteet() {
        if(pistekirjaus.onkoTiedostoja()) {
            pistekirjaus.poistaPisteTiedostot();
        }
    }

    // Nopat
    public int[] heitaNopat() {
        return new int[]{1, 2, 5, 1, 2};
    }

    // Pisteiden hakeminen
    public Map<String, Integer> getPisteet() {
        return pistekirjaus.pisteet;
    }

    public Map<String, Integer> getMahdollisetPisteet() {
        return pistekirjaus.mahdollisetPisteet;
    }

    // Pisteiden lisääminen
    // Tarvitsee pisteiden laskennan ja 0
    public void lisaaPisteet(String nimi) {
        pistekirjaus.pisteet.put(nimi, 10);
    }

    public void lisaaMahdollisetPisteet(String nimi, int pisteet) {
        pistekirjaus.pisteet.put(nimi, pisteet);
    }

    
}
