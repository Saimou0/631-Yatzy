// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.Scanner;

import Pelaaja.Pelaaja;
import Peli.Yksinpeli;

public class Menunlogiikka {
    
    private Kayttoliittyma kayttoliittyma;
    private Yksinpeli yksinpeli;
    Scanner lukija = new Scanner(System.in);

    public void pelinAloitus(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
        aloitusNaytto();
    }

    // Menu
    private static final int LOPETA_VAIHTOEHTO = 0;
    private static final int ALOITA_VAIHTOEHTO = 1;

    private void aloitusNaytto() {
        kayttoliittyma.tyhjennaTerminaali();
        kayttoliittyma.piirraMenu();

        boolean sopivaVaihtoehto = false;
        while (sopivaVaihtoehto == false) {

            int kayttajanSyotto = kayttajanSyotto();
            switch (kayttajanSyotto) {
                case ALOITA_VAIHTOEHTO:
                    kayttoliittyma.tyhjennaTerminaali();
                    kayttoliittyma.piirraPeliMuodot();
                    this.initYksinpeli();
                    sopivaVaihtoehto = true;
                    break;

                case LOPETA_VAIHTOEHTO:
                    System.out.println("----Nähdään pian!----");
                    sopivaVaihtoehto = true;
                    break;

                default:
                    break;
            }

        }
    }
    
    // Kysytään pelaajan nimi ja luodaan pelaaja.
    private void initYksinpeli() {
        kayttoliittyma.tyhjennaTerminaali();
        
        Pelaaja pelaaja = null;
        kayttoliittyma.kysyPelaajanNimi();
        String nimi = tarkistaKayttajanNimi();

        if(nimi == "") {
            nimi = "Pelaaja " + (1);
        } else {
            pelaaja = new Pelaaja(nimi);
        }

        pelaaja.pisteetTesti();

        // Luodaan yksinpeli ja annetaan pelaaja ja käyttöliittymä.
        this.yksinpeli = new Yksinpeli(pelaaja, kayttoliittyma);

        // Tyhjetään terminaali ja aloitetaan peli.
        kayttoliittyma.tyhjennaTerminaali();
        yksinpeli.pelinLoop();


    }

    // Utility
    // Ottaa ja tarkistaa käyttäjän syötön.
    private int kayttajanSyotto() {
        try {
            String syotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(syotto);
            return syotonNumero;
        } catch (NumberFormatException e) {
            kayttoliittyma.piirraVirheSyotto();
            return -1;
        }
    }

    // Ottaa ja tarkistaa käyttäjän nimen syötön.
    private String tarkistaKayttajanNimi() {
        try {
            String syotto = lukija.nextLine();
            
            if(syotto.length() > 0) {
                return syotto;
            }

            return "";
        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
            return "";
        }
    }

}
