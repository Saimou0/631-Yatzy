package Peli;
import Kayttoliittyma.Kayttoliittyma;
import Kayttoliittyma.Menunlogiikka;

public class Yatzy {
    public static void main(String[] args) throws Exception {

        Kayttoliittyma kayttoliityma = new Kayttoliittyma();
        Menunlogiikka menunLogiikka = new Menunlogiikka();

        menunLogiikka.pelinAloitus(kayttoliityma);
        
        /*Logiikan kulku 
         1. Yatzy.java avaa menunLogiikan pelin aloituksen.
         2. Menunlogiikka.java hoitaa käyttöliittymän ja pelaajan/pelaajat
         3. Pelaaja.java hoitaa pelaajan nopat ja pistekirjauksen.
         4. Menunlogiikka.java hoitaa pelin käyttöliittymän
        */

    }
    
}
