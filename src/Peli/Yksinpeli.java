// Lisääjä/Tekijä: Simo
package Peli;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import Kayttoliittyma.Kayttoliittyma;
import Pelaaja.Pelaaja;

public class Yksinpeli {

    Kayttoliittyma kayttoliittyma;
    Scanner lukija = new Scanner(System.in);
    private Pelaaja vastustaja;
    private Pelaaja pelaaja;
    private int heittojenMaraa = 3;

    private static final int HEITA_TILA = 1;
    private static final int LUKITSE_TILA = 2;
    private static final int VALITSE_PISTEET_TILA = 3;
    private static final int TAKAISIN = -1;
    private static final int LOPETA_VAIHTOEHTO = 0;

    public LinkedHashMap<String, Integer> tilaLista = new LinkedHashMap<>();

    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
        tilaLista.put("Heitä", 1);
        tilaLista.put("Lukitse", 1);
        tilaLista.put("Valitse Pisteet", 1);
        tilaLista.put("Lopeta Peli", 1);
    }

    // PelaajanPisteSyotto
    /*
     * -Valitsee pisteen jonka tallentaa.
     * -Pitää näyttää pisteet, jota vastaan pelataan.
     * -Pitää näyttää mahdolliset pisteet.
     */

    // TODO: Vertaa pelaajan pisteitä vastustajan pisteisiin ja tallenna voittaja
    // tiedostoon.

    /*
     * TODO: Projektin loppu puolella, mieti jos tätä tiedostoa voisi pilkkoa. Jopa
     * jo sitten kun teet monin peliä.
     * TODO: Tästä tiedostosta voi varmaan kopioida paljon moninpeliin.
     * TODO: Teknisesti vaan loopataan jokaisen pelaajan vuorot päivitäpelissä.
     * TODO: Varmasti pystyy optimoimaan, mut mieti sitä viimesenä.
     */

    /*
     * Pelaaja tekee valinnan ja keskus metodi, kutsuu metodeja, jotka päivittää
     * pelin eri komponentit.
     * -Nopat
     * -Pisteet
     * 
     * Enemmän moninpeliin
     * -Nykyinen pelaaja
     */

    public void pelinLoop() {
        // Kysytään haluaaka pelaaja vastustajan.
        this.paatteleVastustaja();
        boolean peliJatkuu = true;
        while (peliJatkuu) {
            if (pelaaja.vuorojenMaara >= 13) {
                // Laske pisteet yhteen ja tallena ne tiedostoon.
                // Jos pelaa jo valmista tiedostoa vastaan, vertaa pisteitä.
                peliJatkuu = false;
            }

            // Lopetetaan peli jos pelin päivitys palauttaa 0
            if (pelinPaivitys() == 0) {
                peliJatkuu = false;
            }

            // Lisätään pelaajan vuorojen määrää
            if (heittojenMaraa < 3) {
                pelaaja.vuorojenMaara++;
            }
        }
    }

    // Päivittää pelin ja käyttöliittymän
    public int pelinPaivitys() {
        kayttoliittyma.tyhjennaTerminaali();

        // Piirretään tiedot pisteiden väreistä.
        System.out.println("\033[0;32m" + "Vihreä = valittu piste" + "\033[0m");
        System.out.println("\033[0;31m" + "Punainen = Mahdollinen piste" + "\033[0m");

        // Piirretään pelaajan pistekortti
        kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi());

        if (this.vastustaja != null) {
            kayttoliittyma.piirraPisteKortti(vastustaja.getPisteet(), vastustaja.getNimi());
        }

        // Piirretään pelaajan heittojen määrä
        System.out.println("Heittojen määrä: " + heittojenMaraa);

        // Piirretään nopat
        try {
            kayttoliittyma.piirraNopat(pelaaja.getNopat());
        } catch (Exception e) {
            System.out.println("Heitä nopat");
        }

        // Piirretään käyttöliittymä
        this.kayttoliittymanPiirtaminen();

        // Prosessoidaan pelaajan syöttö
        int pelaajanSyotto = pelaajanSyotto();
        switch (pelaajanSyotto) {
            // Jos pelaajan syötto on 1 niin heitetään nopat
            case HEITA_TILA:
                // Ensin tarkistetaan onko heittäminen tila listassa.
                if (tilaLista.get("Heitä") == 1) {
                    heitaNopat();
                }
                // heitaNopat();
                break;

            // Jos pelaajan syötto on 2 niin mennään noppien lukitsemis tilaan.
            case LUKITSE_TILA:
                if (tilaLista.get("Lukitse") == 1) {
                    noppienLukitseminen();
                }
                break;

            // Jos pelaajan syötto on 3 niin mennään valitsemaan pisteet
            case VALITSE_PISTEET_TILA:

                break;

            // Jos pelaajan syöttö on 0 niin lopetetaan peli
            case LOPETA_VAIHTOEHTO:
                return 0;
            default:
                break;
        }
        return -1;
    }

    public void paatteleVastustaja() {
        kayttoliittyma.piirraValitseVastustaja(pelaaja.getPisteTiedostot());

        int pelaajanSyotto = pelaajanSyotto();

        if (pelaajanSyotto == 0) {
            return;
        }

        LinkedHashMap<String, Integer> pisteTiedostoLista = pelaaja.getPisteTiedostot();

        vastustaja = new Pelaaja("Vastustaja");

        for (Map.Entry<String, Integer> pisteTiedosto : pisteTiedostoLista.entrySet()) {
            if (pelaajanSyotto == Integer.parseInt(pisteTiedosto.getValue().toString())) {
                String kokoAvain = pisteTiedosto.getKey().toString();
                String avaimenNimi = kokoAvain.split("_")[0];
                vastustaja.luePisteetTiedostosta(avaimenNimi);
            }
        }

        for (Map.Entry<String, Integer> pisteet : vastustaja.getPisteet().entrySet()) {
            System.out.println(pisteet.getKey() + ": " + pisteet.getValue());
        }
    }

    // Päätellään pelajaan vaihtoehdot ja piirretään ne käyttöliittymään.
    public void kayttoliittymanPiirtaminen() {

        // Lisätään pelaajan vaihtoehdot listaan.
        tilaLista.put("Heitä", 1);
        tilaLista.put("Lukitse", 1);
        tilaLista.put("Valitse Pisteet", 1);
        tilaLista.put("Lopeta Peli", 1);

        // Jos pelaaja on heittänyt 13 kertaa, poistetaan heitä ja lukitse vaihtoehdot
        // listasta.
        if (pelaaja.vuorojenMaara >= 13) {
            tilaLista.put("Heitä", 0);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 1);
        }

        // Jos pelaaja ei ole vielä pelannut kertaakaan, poistetaan lukitse ja valitse
        // pisteet vaihtoehdot listasta.
        if (pelaaja.vuorojenMaara == 1) {
            tilaLista.put("Heitä", 1);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 0);
        }

        // Jos pelaajalla on 0 heittoa, poistetaan heitä ja lukitse vaihtoehdot
        // listasta.
        if (heittojenMaraa == 0) {
            tilaLista.put("Heitä", 0);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 1);
        }

        kayttoliittyma.piirraPelaajanVaihtoehdot(tilaLista);
    }

    // Pelaajan syöttön tarkistaminen
    public int pelaajanSyotto() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);
            return syotonNumero;

        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
            return -1;
        }
    }

    // Noppien heittäminen
    public void heitaNopat() {
        // Katsotaan onko pelaajalla heittoja jäljellä
        if (heittojenMaraa > 0) {
            // Tyhjennetään terminaali
            kayttoliittyma.tyhjennaTerminaali();

            // Heitetään nopat ja vähennetään heittojen määrää
            pelaaja.heitaNopat();
            heittojenMaraa--;

        } else if (heittojenMaraa == 0) {
            kayttoliittyma.eiHeittoja();
        }
    }

    // Noppien lukitseminen
    public void noppienLukitseminen() {
        if (tilaLista.get("Lukitse") == 1) {
            boolean lukitseTila = true;
            while (lukitseTila) {
                kayttoliittyma.tyhjennaTerminaali();
                kayttoliittyma.piirraLukitseTila(pelaaja.getLukitutNopat());
                kayttoliittyma.piirraNopat(pelaaja.getNopat());
                pelaaja.getLukitutNopat();

                int pelaajanSyotto = pelaajanSyotto();
                if (pelaajanSyotto >= 1 && pelaajanSyotto <= 5) {
                    pelaaja.lukitseJaVapautaNoppia(pelaajanSyotto);
                } else if (pelaajanSyotto == TAKAISIN) {
                    break;
                } else {
                    kayttoliittyma.piirraVirheSyotto();
                }
            }

        } else {
            kayttoliittyma.piirraVirheSyotto();
        }
    }

    // EH
    public void pelaajanPisteValinta() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            // Tarkistaa onko pelaaja jo valinnut pisteen, jos eivät niin lisätään pisteet
            // muistiin.
            switch (kayttajanSyotto) {
                case "Ykköset":
                    if (!pelaaja.valitutPisteet.get("Ykköset")) {
                        pelaaja.lisaaPisteet("Ykköset");
                        pelaaja.valitutPisteet.put("Ykköset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kakkoset":
                    if (!pelaaja.valitutPisteet.get("Kakkoset")) {
                        pelaaja.lisaaPisteet("Kakkoset");
                        pelaaja.valitutPisteet.put("Kakkoset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kolmoset":
                    if (!pelaaja.valitutPisteet.get("Kolmoset")) {
                        pelaaja.lisaaPisteet("Kolmoset");
                        pelaaja.valitutPisteet.put("Kolmoset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Neloset":
                    if (!pelaaja.valitutPisteet.get("Neloset")) {
                        pelaaja.lisaaPisteet("Neloset");
                        pelaaja.valitutPisteet.put("Neloset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Viitoset":
                    if (!pelaaja.valitutPisteet.get("Viitoset")) {
                        pelaaja.lisaaPisteet("Viitoset");
                        pelaaja.valitutPisteet.put("Viitoset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kuutoset":
                    if (!pelaaja.valitutPisteet.get("Kuutoset")) {
                        pelaaja.lisaaPisteet("Kuutoset");
                        pelaaja.valitutPisteet.put("Kuutoset", true);
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                default:
                    kayttoliittyma.piirraVirheSyotto();
                    break;
            }
        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
        }
    }

    public void peliLoppunut(int loppumisenSyy) {

    }
}
