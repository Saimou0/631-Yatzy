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

    public void pelinLoop() {
        boolean peliJatkuu = true;
        while (peliJatkuu) {
            if(pelaaja.vuorojenMaara >= 13) {
                // Laske pisteet yhteen ja tallena ne tiedostoon.
                // Jos pelaa jo valmista tiedostoa vastaan, vertaa pisteitä.
            }

            if(heittojenMaraa == 0) {
                // Pakota pelaaja valitsemaan pisteen.
            }

            // Kysy heittääkö
            // Kysy lukitseeko
            // Kysy valitseeko pisteen

            peliJatkuu = false;

        }
    }

    // MAHDOLLISUUS EHKÄ

    // public void kayttoliittymanPiirtaminen(int syotto) {
    //     switch (syotto) {
    //         case VALITSE_PISTEET_TILA:
    //             if(tilaLista.contains(VALITSE_PISTEET_TILA)) {
    //                 tilaLista.remove(VALITSE_PISTEET_TILA);
    //             } else {
    //                 tilaLista.add(VALITSE_PISTEET_TILA);
    //             }
    //             break;
        
    //         case HEITA_TILA:
    //             if(tilaLista.contains(HEITA_TILA)) {
    //                 tilaLista.remove(HEITA_TILA);
    //             } else {
    //                 tilaLista.add(HEITA_TILA);
    //             }
    //             break;
            
    //         case LUKITSE_TILA:
    //             if(tilaLista.contains(LUKITSE_TILA)) {
    //                 tilaLista.remove(LUKITSE_TILA);
    //             } else {
    //                 tilaLista.add(LUKITSE_TILA);
    //             }
    //             break;

    //         case TAKAISIN:
    //             if(tilaLista.contains(TAKAISIN)) {
    //                 tilaLista.remove(TAKAISIN);
    //             } else {
    //                 tilaLista.add(TAKAISIN);
    //             }
    //             break;
            
    //         default:
    //             break;
    //     }
    // }

    // public void paivitaKayttoliittyma() {
    //     kayttoliittyma.piirraPelaajanVaihtoehdot(tilaLista);
    // }


    // MIETI VIELÄ

    public int pelaajanSyotto() {

        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);
            return syotonNumero;
            // switch (syotonNumero) {
            //     case 1:
            //         return 1;
            
            //     case 2:
            //         return 2;
                
            //     case 3:
            //         return 3;
            //     default:
            //         kayttoliittyma.piirraVirheSyotto();
            //         break;
            // }

        } catch (Exception e) {
            return -1;
        }
        
        // kayttoliittyma.piirraVirheSyotto();
        // return -1;

    }

    public int pelaajanNoppienSyotto() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);

            switch (syotonNumero) {
                case 1:
                    return 1;
            
                case 2: 
                    return 2;
                
                case 3:
                    return 3;
                
                case 4:
                    return 4;
                
                case 5:
                    return 5;

                case 6:
                    return 6;
                default:
                    break;
            }

        } catch (Exception e) {
            return -1;
        }

        return -1;
    }

    // VARMAAN TARPEEKS HYVÄ
    public void pelaajanPisteValinta() {
        try {
            String kayttajanSyotto = lukija.nextLine();

            switch (kayttajanSyotto) {
                case "Ykköset":
                    pelaaja.lisaaPisteet("Ykköset");
                    break;
                
                case "Kakkoset":
                    pelaaja.lisaaPisteet("Kakkoset");
                    break;
                
                case "Kolmoset":
                    pelaaja.lisaaPisteet("Kolmoset");
                    break;
                
                case "Neloset":
                    pelaaja.lisaaPisteet("Neloset");
                    break;
                
                case "Viitoset":
                    pelaaja.lisaaPisteet("Viitoset");
                    break;

                case "Kuutoset":
                    pelaaja.lisaaPisteet("Kuutoset");
                    break;

                default:
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
