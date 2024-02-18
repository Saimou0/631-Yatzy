package Peli;
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
    private static final int TAKAISIN = 4;
    private static final int LOPETA_VAIHTOEHTO = 0;
    public ArrayList<Integer> tilaLista = new ArrayList<>();

    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
    }

    // PelaajanSyotto
    /*
        -Heittää noppia
        -Lukita noppia
        -Valita pisteet
        -Lopettaa peli
    */

    // PelaajanPisteSyotto
    /*
        -Valitsee pisteen jonka tallentaa
        -Pitää näyttää pisteet, jota vastaan pelataan.
        -Pitää näyttää mahdolliset pisteet.
    */

    // Pelaajan noppien lukitseminen ja avaaminen
    /*
        -Pystyy valitsemaan nopan ja se noppa lukitaan, jos valitsee avatun nopan niin se avataan.
    */

    /*
        Pelaaja tekee valinnan ja keskus metodi, kutsuu metodeja, jotka päivittää pelin eri komponentit.
        -Nopat
        -Pisteet
        -Nykyinen pelaaja
        -Pelaajan vuorojen määrä
        -Käyttöliittymän 
    */

    
    public void pelinLoop() {
        boolean peliJatkuu = true;
        while (peliJatkuu) {
            if(pelaaja.vuorojenMaara >= 13) {
                // Laske pisteet yhteen ja tallena ne tiedostoon.
                // Jos pelaa jo valmista tiedostoa vastaan, vertaa pisteitä.
            }

            pelinPaivitys();

            

            peliJatkuu = false;

        }
    }

    public void pelinPaivitys() {
        kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi());
        kayttoliittyma.piirraNopat(pelaaja.heitaJaPalautaNopat());
        this.kayttoliittymanPiirtaminen();

    }

    public void kayttoliittymanPiirtaminen() {
        if(heittojenMaraa == 0) {
            tilaLista.add(VALITSE_PISTEET_TILA);
            tilaLista.remove(HEITA_TILA);
            tilaLista.remove(LUKITSE_TILA);
        } else {
            tilaLista.add(HEITA_TILA);
            tilaLista.add(LUKITSE_TILA);
            tilaLista.add(VALITSE_PISTEET_TILA);
        }

        kayttoliittyma.piirraPelaajanVaihtoehdot(tilaLista);
    }

    // MIETI VIELÄ
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

    public int pelaajanNoppienSyotto() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);
            return syotonNumero;

        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
            return -1;
        }
    }

    // VARMAAN TARPEEKS HYVÄ
    public void pelaajanPisteValinta() {
        try {
            String kayttajanSyotto = lukija.nextLine();


            // Tarkistaa onko pelaaja jo valinnut pisteen, jos eivät niin lisätään pisteet muistiin.
            switch (kayttajanSyotto) {
                case "Ykköset":
                    if(!pelaaja.valitutPisteet.contains("Ykköset")) {
                        pelaaja.lisaaPisteet("Ykköset");
                        pelaaja.valitutPisteet.add("Ykköset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;
                
                case "Kakkoset":
                    if(!pelaaja.valitutPisteet.contains("Kakkoset")) {
                        pelaaja.lisaaPisteet("Kakkoset");
                        pelaaja.valitutPisteet.add("Kakkoset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;
                
                case "Kolmoset":
                    if(!pelaaja.valitutPisteet.contains("Kolmoset")) {
                        pelaaja.lisaaPisteet("Kolmoset");
                        pelaaja.valitutPisteet.add("Kolmoset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;
                
                case "Neloset":
                    if(!pelaaja.valitutPisteet.contains("Neloset")) {
                        pelaaja.lisaaPisteet("Neloset");
                        pelaaja.valitutPisteet.add("Neloset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;
                
                case "Viitoset":
                    if(!pelaaja.valitutPisteet.contains("Viitoset")) {
                        pelaaja.lisaaPisteet("Viitoset");
                        pelaaja.valitutPisteet.add("Viitoset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;

                case "Kuutoset":
                    if(!pelaaja.valitutPisteet.contains("Kuutoset")) {
                        pelaaja.lisaaPisteet("Kuutoset");
                        pelaaja.valitutPisteet.add("Kuutoset");
                    } else {
                        System.out.println("Olet jo valinnut tämän pisteen");
                    }
                    break;

                default:
                    kayttoliittyma.piirraVirheSyotto();
                    break;
            }
        } catch(Exception e) {
            kayttoliittyma.piirraVirheSyotto();
        }

    }

    public boolean peliLoppunut() {
        return true;
    }
}
