// Lisääjä/Tekijä: Simo
package Pelaaja;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pelaaja{
    Pistekirjaus pistekirjaus;
    Nopat nopat;
    String nimi;
    public int vuorojenMaara = 1;

    public Pelaaja(String nimi) {
        this.nimi = nimi;
        this.nopat = new Nopat();
        this.pistekirjaus = new Pistekirjaus();
    }

    public String getNimi() {
        return this.nimi;
    }

    public void pisteetTesti() {
        pistekirjaus.pisteet.put("Ykkoset", -1);
        pistekirjaus.pisteet.put("Kakkoset", -1);
        pistekirjaus.pisteet.put("Kolmoset", -1);
        pistekirjaus.pisteet.put("Neloset", -1);
        pistekirjaus.pisteet.put("Viitoset", -1);
        pistekirjaus.pisteet.put("Kuutoset", -1);

        pistekirjaus.pisteet.put("Valisumma", -1);

        pistekirjaus.pisteet.put("Pari", -1);
        pistekirjaus.pisteet.put("Kaksi paria", -1);
        pistekirjaus.pisteet.put("Kolme samaa", -1);

        pistekirjaus.pisteet.put("Nelja samaa", -1);
        pistekirjaus.pisteet.put("Pikku suora", -1);
        pistekirjaus.pisteet.put("Iso suora", -1);
        pistekirjaus.pisteet.put("Taysikasi", -1);
        pistekirjaus.pisteet.put("Sattuma", -1);
        pistekirjaus.pisteet.put("Yatzy", -1);
        
        pistekirjaus.pisteet.put("Summa", -1);

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
    // Heittää nopat
    public void heitaNopat() {
        nopat.heitaNopat();
    }
    
    // Palauttaa nopat
    public int[] getNopat() {
        return nopat.getNopat();
    }

    // Lukitsee ja avaa nopan
    public void lukitseJaVapautaNoppia(int indeksi) {
        if(nopat.lukitutNopat.get(indeksi) == true) {
            nopat.vapautaNoppa(indeksi);
        } else {
            nopat.lukitseNoppa(indeksi);
        }
    }

    // Palauttaa lukitut nopat
    public LinkedHashMap<Integer, Boolean> getLukitutNopat() {
        return nopat.getLukitutNopat();
    }

    // Palautaa pisteet
    public Map<String, Integer> getPisteet() {
        return pistekirjaus.pisteet;
    }

    // Palautaa mahdolliset pisteet
    public Map<String, Integer> getMahdollisetPisteet() {
        return pistekirjaus.mahdollisetPisteet;
    }

    public LinkedHashMap<String, Integer> getPisteTiedostot() {
        return pistekirjaus.getPisteTiedostot();
    }

    public void luePisteetTiedostosta(String nimi) {
        pistekirjaus.luePisteetTiedostosta(nimi);
    }

    // Pisteiden lisääminen
    // Tarvitsee pisteiden laskennan
    public void lisaaPisteet(String nimi, int pisteet) {
        this.pistekirjaus.lisaaPisteet(nimi, pisteet);

        if(this.pistekirjaus.pisteet.get("Ykkoset") != -1 && 
            this.pistekirjaus.pisteet.get("Kakkoset") != -1 && 
            this.pistekirjaus.pisteet.get("Kolmoset") != -1 &&
            this.pistekirjaus.pisteet.get("Neloset") != -1 &&
            this.pistekirjaus.pisteet.get("Viitoset") != -1 &&
            this.pistekirjaus.pisteet.get("Kuutoset") != -1) 
        {
            // this.pistekirjaus.lisaaPisteet("Valisumma", this.pistekirjaus.laskeValisumma());
        }

        this.pistekirjaus.tallennaPisteetTiedostoon(this.nimi);
    }

    // Lisää mahdolliset pisteet listaan.
    public void lisaaMahdollisetPisteet(String nimi, int pisteet) {
        pistekirjaus.mahdollisetPisteet.put(nimi, pisteet);
    }

    
}
