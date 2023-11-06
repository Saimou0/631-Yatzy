import Kayttoliittyma.Kayttoliittyma;
import Kayttoliittyma.Menunlogiikka;
import Nopat.Nopat;
import Pistekirjaus.Pistekirjaus;

public class Yatzy {
    public static void main(String[] args) throws Exception {

        Kayttoliittyma kayttoliityma = new Kayttoliittyma();
        Nopat nopat = new Nopat();
        Pistekirjaus pisteKirjaus = new Pistekirjaus();
        Menunlogiikka menunLogiikka = new Menunlogiikka();

        menunLogiikka.pelinAloitus(kayttoliityma, pisteKirjaus);
        
        
    }
}
