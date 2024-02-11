// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.Map;

public class Kayttoliittyma {

    public void piirraVirheSyotto() {
        System.out.println("----Virheellinen syöttö yritä uudestaan.----");
    }

    public void piirraJaahyvaiset() {
        System.out.println("----Nähdään pian!----");
    }

    public void piirraMenu() {
        
        System.out.println(
            """
            ------------------------
            |        MENU          |
            |                      |
            |   1 -> Aloita Peli   |
            |    0 -> Lopeta       |
            |                      |
            ------------------------
            """
        );
    }

    public void piirraPeliMuodot() {

        System.out.println(
            """
            ---------------------------
            |       PELI MUODOT       |
            |                         |
            |     1 -> Moninpeli      |
            |     2 -> Yksinpeli      |
            |     0 -> Lopeta         |
            |                         |
            ---------------------------
            """
        );
    }

    public void kysyPelaajienMaara() {
        System.out.println("----Anna pelaajien määrä. (Vähintään 2)----");
    }

    public void kysyPelaajanNimi(int pelaajanIndeksi) {
        System.out.println("----Anna pelaajan: " + pelaajanIndeksi + " nimi.----");
    }

    public void piirraUusiPeli() {
        System.out.println("----Uusi peli.----");
    }

    public void piirraPelaajanVuoro(String pelaaja) {
        System.out.println("----Pelaajan " + pelaaja + " vuoro.----");
    }

    public void piirraPisteKortti(Map<String, Integer> pisteet) {
        
        // | Ykköset                   |
        // | Kakkoset                  |
        // | Kolmoset                  |
        // | Neloset                   |
        // | Viitoset                  |
        // | Kuutoset                  |
        // |                           |
        // | Valisumma                 |
        // |                           |
        // | Pari                      |
        // | Kaksi paria               |
        // | Kolme samaa               |
        // |                           |
        // | Nelja samaa               |
        // | Pikku suora               |
        // | Iso suora                 |
        // | Taysikäsi                 |
        // | Sattuma                   |
        // | Yatzy                     |
        // |                           |
        // | Summa                     |
        System.out.println(
            """
            -----------------------------
            |       PISTE KORTTI        |
            |                           |
            """
        );

        for(Map.Entry<String, Integer> sisalto : pisteet.entrySet())  {
            System.out.printf("| %-19s %5d |\n", sisalto.getKey(), sisalto.getValue());
        }

        System.out.println(
            """
            |                           |
            -----------------------------
            """
        );
    }

    public void piirraPelinLoppuminen() {
        System.out.println("---- Peli loppui.----");
    }

    public void piirraNopat(int[] nopat) {
        // Tehdään taulukko johon tallenetaan kaikki nopat linja kerrallaan ja peräkäin.
        // Tällein saadaan nopat piirrettyä vierekkäin samalle linjalle.
        String[] linjat = new String[5];
        for(int i = 0; i < 5; i++) {
            linjat[i] = "";
        }

        // Tallennetaan yksi noppa linja kerrallaan noppienLinjat-taulukkoon.
        String[] noppienLinjat = null;
        for(int noppa : nopat) {
            switch (noppa) {
                case 1:
                    noppienLinjat = new String[] {
                        "---------", //indeksi 0 noppienLinjat listassa
                        "|       |", // indeksi 1
                        "|   1   |", // jne...
                        "|       |",
                        "---------",
                    };
                    break;
                
                case 2:
                    noppienLinjat = new String[] {
                        "---------",
                        "| 2     |",
                        "|       |",
                        "|     * |",
                        "---------"
                    };
                    break;
                
                case 3:
                    noppienLinjat = new String[] {
                        "---------",
                        "| *     |",
                        "|   3   |",
                        "|     * |",
                        "---------"
                    };
                    break;
                
                case 4:
                    noppienLinjat = new String[] {
                        "---------",
                        "| 4   * |",
                        "|       |",
                        "| *   * |",
                        "---------"
                    };
                    break;

                case 5:
                    noppienLinjat = new String[] {
                        "---------",
                        "| *   * |",
                        "|   5   |",
                        "| *   * |",
                        "---------"
                    };
                    break;
                
                case 6:
                    noppienLinjat = new String[] {
                        "---------",
                        "| 6   * |",
                        "| *   * |",
                        "| *   * |",
                        "---------"
                    };
                    break;
            
                default:
                    this.piirraVirheSyotto();
                    break;
            }

            // lisätään linjat-taulukkoon linjat indeksi i + noppienLinjat indeksi i ja lopuksi välilyönti.
            for(int i = 0; i < 5; i++) {
                linjat[i] += noppienLinjat[i] + " ";
            }
        }

        //Tuodaan linjat-taulukon sisältö näkyviin.
        for(String line : linjat) {
            System.out.println(line);
        }
    }




}