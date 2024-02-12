// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.Scanner;

import Pelaaja.Pelaaja;

public class Menunlogiikka {

    private Kayttoliittyma kl;
    Scanner lukija = new Scanner(System.in);

    public void pelinAloitus(Kayttoliittyma kayttoliittyma) {
        this.kl = kayttoliittyma;
        aloitusNaytto();
    }

    // Menu
    private static final int MONINPELI_VAIHTOEHTO = 1;
    private static final int YKSINPELI_VAIHTOEHTO = 2;
    private static final int LOPETA_VAIHTOEHTO = 0;
    private static final int ALOITA_VAIHTOEHTO = 1;

    private void aloitusNaytto() {
        tyhjennaTerminaali();
        kl.piirraMenu();

        boolean sopivaVaihtoehto = false;
        while (sopivaVaihtoehto == false) {

            int kayttajanSyotto = kayttajanSyotto();
            switch (kayttajanSyotto) {
                case ALOITA_VAIHTOEHTO:
                    tyhjennaTerminaali();
                    kl.piirraPeliMuodot();
                    this.peliMuodonValinta(kayttajanSyotto);
                    sopivaVaihtoehto = true;
                    break;

                case LOPETA_VAIHTOEHTO:
                    kl.piirraJaahyvaiset();
                    this.peliMuodonValinta(kayttajanSyotto);
                    sopivaVaihtoehto = true;
                    break;

                default:
                    break;
            }

        }
    }

    private void peliMuodonValinta(int kayttajanSyotto) {

        boolean sopivaPeliVaihtoehto = false;
        while (sopivaPeliVaihtoehto == false) {
            
            kayttajanSyotto = kayttajanSyotto();

            switch (kayttajanSyotto) {
                case YKSINPELI_VAIHTOEHTO:
                    this.initYksinpeli();
                    sopivaPeliVaihtoehto = true;
                    break;
                
                case MONINPELI_VAIHTOEHTO:
                    this.initMoninpeli();
                    sopivaPeliVaihtoehto = true;
                    break;
                
                default:
                    break;
            }
            
            
        }
    }
    
    // Pelimuodot
    // Kysytään pelaajan nimi ja luodaan pelaaja.
    private void initYksinpeli() {
        tyhjennaTerminaali();
        Pelaaja pelaaja = null;
        System.out.println("----Pelaajan nimi.----");


        String nimi = kayttajanNimi();
        if(nimi == "") {
            nimi = "Pelaaja " + (1);
        } else {
            pelaaja = new Pelaaja(nimi);
        }

        pelaaja.pisteetTesti();
        

        kl.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi());

    }

    // Kysytään pelaajien määrä ja pelaajien nimet ja luodaan lista pelaajistas.
    private void initMoninpeli() {
        tyhjennaTerminaali();
        
        int pelaajienMaara = 0;
        while (pelaajienMaara < 2) {
            kl.kysyPelaajienMaara();
            pelaajienMaara = kayttajanSyotto();

            if(pelaajienMaara < 2 && pelaajienMaara != -1) {
                System.out.println("----Pelaajien määrän tulee olla vähintään 2.----");
            }
        }
        
        Pelaaja[] pelaajat = new Pelaaja[pelaajienMaara];
        tyhjennaTerminaali();
        for (int i = 0; i < pelaajat.length; i++) {
            kl.kysyPelaajanNimi(i + 1);

            String nimi = kayttajanNimi();
            if(nimi == "") {
                nimi = "Pelaaja " + (i + 1);
            } else {
                pelaajat[i] = new Pelaaja(nimi);
            }
        }

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

    // Ottaa ja tarkistaa käyttäjän nimen syötön.
    private String kayttajanNimi() {
        try {
            String syotto = lukija.nextLine();
            
            if(syotto.length() > 0) {
                return syotto;
            }

            return "";
        } catch (Exception e) {
            kl.piirraVirheSyotto();
            return "";
        }
    }

    // Tyhjentää terminaalin.
    public void tyhjennaTerminaali() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
