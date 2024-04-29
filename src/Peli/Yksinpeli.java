// Lisääjä/Tekijä: Simo
package Peli;

import java.util.LinkedHashMap;
import java.util.Scanner;
import Kayttoliittyma.Kayttoliittyma;
import Pelaaja.Pelaaja;

public class Yksinpeli {
    Kayttoliittyma kayttoliittyma;
    PelaajaSyotto pelaajaSyotto;
    Scanner lukija = new Scanner(System.in);

    private Pelaaja vastustaja;
    private Pelaaja pelaaja;

    private int heittojenMaraa = 3;
    private boolean ensimmainenHeitto = true;
    private int pelaajanNykyinenVuoro = 1;

    public LinkedHashMap<String, Integer> tilaLista = new LinkedHashMap<>();

    // Konstruktori
    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma, Pelaaja vastustaja) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
        this.vastustaja = vastustaja;
        this.pelaajaSyotto = new PelaajaSyotto(kayttoliittyma);
    }

    // Pelin jatkumisen looppi
    public void pelinLoop() {
        boolean peliJatkuu = true;
        while (peliJatkuu) {

            if (pelaaja.vuorojenMaara == 16) {
                // Laske pelaajan summa ja tallenna se
                pelaaja.tallennaSumma();
                peliJatkuu = false;
            }

            // Nollataan heitot ja nollataan ensimmäinen heitto kun pelaajalla alkaa uusi
            // vuoro
            if (pelaajanNykyinenVuoro < pelaaja.vuorojenMaara) {
                pelaajanNykyinenVuoro = pelaaja.vuorojenMaara;
                ensimmainenHeitto = true;
                heittojenMaraa = 3;

                pelaaja.avaaKaikkiNopat();
            }

            pelaaja.laskeValisumma();

            // Lopetetaan peli jos pelin päivitys palauttaa 0
            if (pelinPaivitys() == 0) {
                pelaaja.tallennaSumma();
                peliJatkuu = false;
            }

        }
    }

    // Päivittää pelin ja käyttöliittymän
    private int pelinPaivitys() {
        kayttoliittyma.tyhjennaTerminaali();

        // Piirretään pelaajan ja vastustajan pistekortit. Jos ei ole vastustajaa
        // piirretään vain pelaajan pistekortti.
        if (this.vastustaja != null) {
            kayttoliittyma.piirraMontaPistekorttia(pelaaja, vastustaja, pelaaja.getMahdollisetPisteet());
        } else {
            kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi(), pelaaja.getMahdollisetPisteet());
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
        kayttoliittymanPiirtaminen();

        // Prosessoidaan pelaajan syöttö
        return pelaajaSyotto.prosessoiPelaajanSyotto(this, tilaLista);
    }

    // Nopat
    // Noppien heittäminen
    public void heitaNopat() {
        // Katsotaan onko ensimmäinen heitto
        if (heittojenMaraa == 3 && ensimmainenHeitto == true) {
            ensimmainenHeitto = false;
        }

        // Katsotaan onko pelaajalla heittoja jäljellä
        if (heittojenMaraa > 0) {
            // Heitetään nopat ja vähennetään heittojen määrää
            pelaaja.heitaNopat();
            heittojenMaraa--;

        } else if (heittojenMaraa == 0) {
            kayttoliittyma.eiHeittoja();
        }
    }

    // Noppien lukitsemisen tila
    public void noppienLukitseminen() {
        if (tilaLista.get("Lukitse") == 1) {
            boolean lukitseTila = true;
            while (lukitseTila) {
                kayttoliittyma.tyhjennaTerminaali();
                kayttoliittyma.piirraLukitseTila(pelaaja.getLukitutNopat());
                kayttoliittyma.piirraNopat(pelaaja.getNopat());

                int pelaajanSyotto = pelaajaSyotto.pelaajanSyottoInt();
                if (pelaajanSyotto >= 1 && pelaajanSyotto <= 5) {
                    pelaaja.lukitseJaVapautaNoppia(pelaajanSyotto);
                } else if (pelaajanSyotto == -1) {
                    lukitseTila = false;
                } else {
                    kayttoliittyma.piirraVirheSyotto();
                }
            }

        } else {
            kayttoliittyma.piirraVirheSyotto();
        }
    }

    // Pisteet
    public void pelaajanPisteValinta() {
        kayttoliittyma.tyhjennaTerminaali();
        kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi(), pelaaja.getMahdollisetPisteet());
        kayttoliittyma.piirraPisteValinta();

        boolean pisteValintaTila = true;
        while (pisteValintaTila) {
            String kayttajanSyotto = pelaajaSyotto.pelaajanSyottoString();

            // Tarkistetaan haluaako pelaaja lopettaa pisteiden valinnan
            if (kayttajanSyotto.equals("-1")) {
                pisteValintaTila = false;
                continue;
            }

            // Tarkistetaan onko pelaajan syöttö olemassa pisteissä
            if (!pelaaja.getPisteet().containsKey(kayttajanSyotto)) {
                kayttoliittyma.piirraVirheSyotto();
                continue;
            }

            // Estetään pelajaa valitsemasta summaa tai välisummaa.
            if (kayttajanSyotto.equals("Summa") || kayttajanSyotto.equals("Valisumma")) {
                kayttoliittyma.piirraVirheSyotto();
                continue;
            }

            // Tarkistetaan onko pelaaja jo valinnut pisteen
            if (pelaaja.getPisteet().get(kayttajanSyotto) != -1) {
                kayttoliittyma.pelaajaJoValinnutPisteet();
                continue;
            }

            // lisätään pisteet pelaajalle
            pelaaja.lisaaPisteet(kayttajanSyotto);
            pelaaja.vuorojenMaara++;
            pisteValintaTila = false;
        }
    }

    // Käyttöliittymä
    // Päätellään pelajaan vaihtoehdot ja piirretään ne käyttöliittymään.
    private void kayttoliittymanPiirtaminen() {
        // Lisätään pelaajan vaihtoehdot listaan.
        tilaLista.put("Heitä", 1);
        tilaLista.put("Lukitse", 1);
        tilaLista.put("Valitse Pisteet", 1);
        tilaLista.put("Lopeta Peli", 1);
        // tilaLista.put("Lopeta Peli", 1);

        // Jos pelaaja on pelannut 16 kertaa, poistetaan heitä ja lukitse vaihtoehdot
        // listasta.
        if (pelaaja.vuorojenMaara >= 16) {
            tilaLista.put("Heitä", 0);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 1);
        }

        // Jos pelaaja ei ole vielä heittänyt kertaakaan vuorolla, poistetaan lukitse ja
        // valitse pisteet vaihtoehdot listasta.
        if (ensimmainenHeitto == true) {
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
}