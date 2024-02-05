package Pistekirjaus;
import Nopat.Nopat;

public class Pelaaja {

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
        // pistekirjaus.pisteet.put("Ykkoset", 1);
        // pistekirjaus.pisteet.put("Kakkoset", 2);
        // pistekirjaus.pisteet.put("Kolmoset", 3);

        // pistekirjaus.tallennaPisteetTiedostoon(nimi);

        // pistekirjaus.luePisteetTiedostosta(nimi);

        // try {
        //     Thread.sleep(5000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // if(pistekirjaus.onkoTiedostoja()) {
        //     pistekirjaus.poistaPisteTiedostot();
        // }
    }

    public int[] heitaNopat() {
        return new int[]{1, 2, 5, 1, 2};
    }

    
}
