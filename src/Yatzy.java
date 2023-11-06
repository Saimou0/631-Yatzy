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


// @TODO
// PELIN SÄÄNNÖT
// Peliä voi pelata yksin tai useamman kanssa.
// Yksin pelatessa pelataan omaa high scoree vastaan.
// Muiden kanssa pelatessa pelataan muita vastaan.
// Pelaajien hallinta
// 