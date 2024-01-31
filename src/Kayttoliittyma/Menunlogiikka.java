package Kayttoliittyma;
import java.util.Scanner;

import Pistekirjaus.Pelaaja;

public class Menunlogiikka {
    // Tekijä: Simo
    private Kayttoliittyma kl;

    Scanner lukija = new Scanner(System.in);

    public void pelinAloitus(Kayttoliittyma kayttoliittyma) {
        this.kl = kayttoliittyma;
        menu();
    }

    // Menu
    private static final int MONINPELI_VAIHTOEHTO = 1;
    private static final int YKSINPELI_VAIHTOEHTO = 2;
    private static final int LOPETA_VAIHTOEHTO = 0;
    private static final int ALOITA_VAIHTOEHTO = 1;

    public void menu() {

        tyhjennaTerminaali();
        kl.piirraMenu();

        boolean jatkaWhile = true;
        while (jatkaWhile) {

            int kayttajanSyotto = kayttajanSyotto();

            if(kayttajanSyotto == ALOITA_VAIHTOEHTO) {
                this.tyhjennaTerminaali();
                kl.piirraPeliMuodot();
                this.peliMuodonValinta(kayttajanSyotto);
                jatkaWhile = false;

            } else if(kayttajanSyotto == LOPETA_VAIHTOEHTO) {
                kl.piirraJaahyvaiset();
                this.peliMuodonValinta(kayttajanSyotto);
                jatkaWhile = false;
            }

        }
    }

    public void peliMuodonValinta(int kayttajanSyotto) {
        boolean sopivaPeliVaihtoehto = false;
        while (sopivaPeliVaihtoehto == false) {
            
            kayttajanSyotto = kayttajanSyotto();

            switch (kayttajanSyotto) {
                case YKSINPELI_VAIHTOEHTO:
                    Pelaaja pelaaja = new Pelaaja();
                    this.yksinpeli(pelaaja);
                    sopivaPeliVaihtoehto = true;
                    break;
            
                case MONINPELI_VAIHTOEHTO:
                    Pelaaja pelaaja1 = new Pelaaja();
                    Pelaaja pelaaja2 = new Pelaaja();
                    this.moninpeli(pelaaja1, pelaaja2);
                    sopivaPeliVaihtoehto = true;
                    break;
                
                default:
                    break;
            }

            
        }
    }


    // Pelimuodot
    public void yksinpeli(Pelaaja pelaaja) {

    }

    public void moninpeli(Pelaaja pelaaja1, Pelaaja pelaaja2) {

    }

    
    // Utility
    // Ottaa ja tarkistaa käyttäjän syötön.
    private int kayttajanSyotto() {
        try {
            String syotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(syotto);
            return syotonNumero;
        } catch (NumberFormatException e) {
            kl.piirraVirheSyotto();
            return -1;
        }
    }

    // Tyhjentää terminaalin.
    // Lisääjä: Simo
    public void tyhjennaTerminaali() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
