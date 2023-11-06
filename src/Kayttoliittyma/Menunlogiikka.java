package Kayttoliittyma;
import java.util.Scanner;

import Pistekirjaus.Pistekirjaus;

public class Menunlogiikka {
    // Tekijä: Simo
    private Kayttoliittyma kl;

    Scanner lukija = new Scanner(System.in);

    public void pelinAloitus(Kayttoliittyma kayttoliittyma, Pistekirjaus pistekirjaus) {
        this.kl = kayttoliittyma;
        kl.piirtamisenAloitus(pistekirjaus);
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
                tyhjennaTerminaali();
                kl.piirraPeliMuodot();
                
                // While loop pitää käyttäjän pelin muoto valinnassa kunnes hän syöttää sopivan vaihtoehdon.
                boolean sopivaPeliVaihtoehto = false;
                while (sopivaPeliVaihtoehto == false) {
                    
                    kayttajanSyotto = kayttajanSyotto();

                    if (kayttajanSyotto == YKSINPELI_VAIHTOEHTO) {
                        kl.piirraUusiPeli();
                        jatkaWhile = false;
                        sopivaPeliVaihtoehto = true;
    
                    } else if(kayttajanSyotto == MONINPELI_VAIHTOEHTO) {
                        kl.piirraUusiPeli();
                        jatkaWhile = false;
                        sopivaPeliVaihtoehto = true;
    
                    } else if(kayttajanSyotto == LOPETA_VAIHTOEHTO) {
                        kl.piirraJaahyvaiset();
                        jatkaWhile = false;
                        sopivaPeliVaihtoehto = true;
    
                    }
                    
                }
                

            } else if(kayttajanSyotto == LOPETA_VAIHTOEHTO) {
                kl.piirraJaahyvaiset();
                jatkaWhile = false;
            }

        }
    }

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
    public static void tyhjennaTerminaali() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
