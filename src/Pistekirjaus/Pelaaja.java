package Pistekirjaus;
import Nopat.Nopat;

public class Pelaaja {

    Pistekirjaus pistekirjaus;
    Nopat nopat;
    
    public Pelaaja() {
        this.nopat = new Nopat();
        this.pistekirjaus = new Pistekirjaus();
    }

    public int[] heitaNopat() {
        return new int[]{1, 2, 5, 1, 2};
    }

    
}
