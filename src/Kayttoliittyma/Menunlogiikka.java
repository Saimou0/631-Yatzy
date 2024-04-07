// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import Pelaaja.Pelaaja;
import Pelaaja.Pistekirjaus;
import Peli.Yksinpeli;
import Peli.PelaajaSyotto;

public class Menunlogiikka {
    
    private Kayttoliittyma kayttoliittyma;
    private Yksinpeli yksinpeli;
    private PelaajaSyotto pelaajaSyotto = new PelaajaSyotto(kayttoliittyma);
    private Pistekirjaus pistekirjaus = new Pistekirjaus();
    Pelaaja pelaaja;
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

            int kayttajanSyotto = pelaajaSyotto.pelaajanSyottoInt();
            switch (kayttajanSyotto) {
                case ALOITA_VAIHTOEHTO:
                    kayttoliittyma.tyhjennaTerminaali();
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

        pelaaja.initPisteet();

        Pelaaja vastustaja = paatteleVastustaja();

        // Luodaan yksinpeli ja annetaan pelaaja ja käyttöliittymä.
        this.yksinpeli = new Yksinpeli(pelaaja, kayttoliittyma, vastustaja);

        // Tyhjetään terminaali ja aloitetaan peli.
        kayttoliittyma.tyhjennaTerminaali();
        yksinpeli.pelinLoop();


    }

    // Pelaajan vastustajan luominen, vanhasta tiedostosta
    public Pelaaja paatteleVastustaja() {
        kayttoliittyma.piirraValitseVastustaja(pistekirjaus.getPisteTiedostot());
        LinkedHashMap<String, Integer> pisteTiedostoLista = pistekirjaus.getPisteTiedostot(); 
        int pelaajanSyotto = -2;

        boolean sopivaVastustaja = true;
        while (sopivaVastustaja) {
            pelaajanSyotto = pelaajaSyotto.pelaajanSyottoInt();

            if (pelaajanSyotto == 0) {
                return null;
            }

            if(pelaajanSyotto > pisteTiedostoLista.size() || pelaajanSyotto < 0) {
                kayttoliittyma.piirraVirheSyotto();
            } else {
                sopivaVastustaja = false;
            }
        }

        Pelaaja vastustaja = new Pelaaja("Vastustaja");

        for (Map.Entry<String, Integer> pisteTiedosto : pisteTiedostoLista.entrySet()) {
            if (pelaajanSyotto == Integer.parseInt(pisteTiedosto.getValue().toString())) {
                String kokoAvain = pisteTiedosto.getKey().toString();
                String avaimenNimi = kokoAvain.split("_")[0];
                vastustaja.luePisteetTiedostosta(avaimenNimi);
            } else {
                System.out.println("Virhe vastustajan valinnassa");
            }
        }

        for (Map.Entry<String, Integer> pisteet : vastustaja.getPisteet().entrySet()) {
            System.out.println(pisteet.getKey() + ": " + pisteet.getValue());
        }

        return vastustaja;
    }

    // Utility
    // Ottaa ja tarkistaa käyttäjän syötön.
    // private int kayttajanSyotto() {
    //     try {
    //         String syotto = lukija.nextLine();
    //         int syotonNumero = Integer.parseInt(syotto);
    //         return syotonNumero;
    //     } catch (NumberFormatException e) {
    //         kayttoliittyma.piirraVirheSyotto();
    //         return -1;
    //     }
    // }

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
