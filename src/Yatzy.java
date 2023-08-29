import Kayttoliittyma.Kayttoliittyma;
import Nopat.Nopat;
import Pistekirjaus.Pistekirjaus;

public class Yatzy {
    public static void main(String[] args) throws Exception {

        Kayttoliittyma kayttoliityma = new Kayttoliittyma();
        Nopat nopat = new Nopat();
        Pistekirjaus pistekirjaus = new Pistekirjaus();
        Pelinlogiikka pelinlogiikka = new Pelinlogiikka();

        pelinlogiikka.logiikkaAloitus(kayttoliityma);
        
        
    }
}