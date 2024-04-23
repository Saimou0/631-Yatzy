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
    Pelaaja pelaaja = null;
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

        kayttoliittyma.kysyPelaajanNimi();
        String nimi = pelaajaSyotto.tarkistaKayttajanNimi();

        if (nimi == "") {
            nimi = "Pelaaja " + (1);
        } else {
            this.pelaaja = new Pelaaja(nimi);
        }

        pelaaja.initPisteet();

        Pelaaja vastustaja = paatteleVastustaja(this.pelaaja);

        // Luodaan yksinpeli ja annetaan pelaaja ja käyttöliittymä.
        this.yksinpeli = new Yksinpeli(this.pelaaja, this.kayttoliittyma, vastustaja);

        // Tyhjetään terminaali ja aloitetaan peli.
        kayttoliittyma.tyhjennaTerminaali();
        yksinpeli.pelinLoop();
    }

    // Pelaajan vastustajan luominen, vanhasta tiedostosta
    private Pelaaja paatteleVastustaja(Pelaaja pelaaja) {
        LinkedHashMap<String, Integer> pisteTiedostoLista = pistekirjaus.getPisteTiedostot();

        // Iterator<Map.Entry<String, Integer>> iteraattori =
        // pisteTiedostoLista.entrySet().iterator();

        // while (iteraattori.hasNext()) {
        // Map.Entry<String, Integer> pisteTiedosto = iteraattori.next();
        // String kokoAvain = pisteTiedosto.getKey().toString();
        // String avaimenNimi = kokoAvain.split("_")[0];
        // if(avaimenNimi.equals(pelaaja.getNimi())) {

        // }
        // }

        kayttoliittyma.piirraValitseVastustaja(pisteTiedostoLista);
        int pelaajanSyotto = -2;

        boolean sopivaVastustaja = true;
        while (sopivaVastustaja) {
            pelaajanSyotto = pelaajaSyotto.pelaajanSyottoInt();

            if (pelaajanSyotto == 0) {
                return null;
            }

            // Mennään koko tiedostot mäppi läpi
            // TODO: koska koko mäppi mennään läpi, niin jos jokin keysetti ei matchaa
            // pelaajan nimee niin sopiva vastustaja meen falseks ja sil ei oo väliä jos
            // jokin keyset sit matchaa, ohjelma joka tapauksessa lopettaa loopin.
            // * Mieti kanttiiko tää kaikki sirtää Yatzy.javaan
            // !TODO: Does not work at all
            // for(Map.Entry<String, Integer> pisteTiedosto : pisteTiedostoLista.entrySet())
            // {
            // String kokoAvain = pisteTiedosto.getKey().toString();
            // String avaimenNimi = kokoAvain.split("_")[0];

            // // Jos pelaajan syötto on suurempi kun tiedostojen määrä tai pienempi kun 0,
            // piirretään virheilmoitus.
            // if(pelaajanSyotto > pisteTiedostoLista.size() || pelaajanSyotto < 0) {
            // kayttoliittyma.piirraVirheSyotto();
            // } else {
            // if(avaimenNimi.equals(pelaaja.getNimi())) {
            // System.out.println("Et voi pelata tätä tiedostoa vastaan.");
            // } else {

            // }
            // }

            // }

        }

        Pelaaja vastustaja = new Pelaaja("Vastustaja");

        for (Map.Entry<String, Integer> pisteTiedosto : pisteTiedostoLista.entrySet()) {
            String kokoAvain = pisteTiedosto.getKey().toString();
            String avaimenNimi = kokoAvain.split("_")[0];

            if (pelaajanSyotto == Integer.parseInt(pisteTiedosto.getValue().toString())) {
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

}
