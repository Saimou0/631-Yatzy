package Peli;

import java.util.LinkedHashMap;
import java.util.Scanner;

import Kayttoliittyma.Kayttoliittyma;

public class PelaajaSyotto {

    private Kayttoliittyma kayttoliittyma;
    private Scanner lukija = new Scanner(System.in);

    public PelaajaSyotto(Kayttoliittyma kayttoliittyma) {
        this.kayttoliittyma = kayttoliittyma;
    }

    public int pelaajanSyottoInt() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);
            return syotonNumero;
        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
            return -2;
        }
    }

    private static final int HEITA_TILA = 1;
    private static final int LUKITSE_TILA = 2;
    private static final int VALITSE_PISTEET_TILA = 3;

    public int prosessoiPelaajanSyotto(Yksinpeli yksinpeli, LinkedHashMap<String, Integer> tilaLista) {
        int pelaajanSyotto = pelaajanSyottoInt();
        switch (pelaajanSyotto) {
            // Jos pelaajan syötto on 1 niin heitetään nopat
            case HEITA_TILA:
                // Ensin tarkistetaan onko heittäminen tila listassa.
                if (tilaLista.get("Heitä") == 1) {
                    yksinpeli.heitaNopat();
                }
                break;

            // Jos pelaajan syötto on 2 niin mennään noppien lukitsemis tilaan.
            case LUKITSE_TILA:
                if (tilaLista.get("Lukitse") == 1) {
                    yksinpeli.noppienLukitseminen();
                }
                break;

            // Jos pelaajan syötto on 3 niin mennään valitsemaan pisteet
            case VALITSE_PISTEET_TILA:
                if (tilaLista.get("Valitse Pisteet") == 1) {
                    yksinpeli.pelaajanPisteValinta();
                }
                break;

            // Jos pelaajan syöttö on 0 niin lopetetaan peli
            case 0:
                return 0;
            default:
                break;
        }
        return-1;
    }

}
