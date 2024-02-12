package Peli;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

import Kayttoliittyma.Kayttoliittyma;
import Pelaaja.Pelaaja;

public class Yksinpeli {
    
    Kayttoliittyma kayttoliittyma;
    Scanner lukija = new Scanner(System.in);
    private Pelaaja pelaaja;
    private int heittojenMaraa = 3;

    private static final int HEITA_TILA = 1;
    private static final int LUKITSE_TILA = 2;
    private static final int VALITSE_PISTEET_TILA = 3;
    private static final int LOPETA_VAIHTOEHTO = 0;
    public ArrayList<Integer> tilaLista = new ArrayList<>();

    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
    }

    // Pelaajan pitää
    /*
        -Heittää noppia
        -Lukita noppia
        -Valita pisteet
        -Lopettaa peli
        -
    */

    public void pelinLoop() {
        boolean jatka = true;
        while (jatka) {
            if(heittojenMaraa == 0) {
                tilaLista.add(VALITSE_PISTEET_TILA);
            } else {
                tilaLista.add(HEITA_TILA);
                tilaLista.add(LUKITSE_TILA);
            }

            kayttoliittyma.piirraPelaajanVaihtoehdot(tilaLista);

            System.out.println(tilaLista.size());
            System.out.println(tilaLista.get(0));
            System.out.println(tilaLista.get(1));

            jatka = false;
        }
    }

    public void paivitaPelinTila() {
        
    }

    public void heitaNopat() {

    }

    public void lukitseNopat() {

    }

    public void valitseTallennettavatPisteet() {

    }

    public void tallennaPisteet() {

    }

    public boolean peliLoppunut() {
        return true;
    }
}
