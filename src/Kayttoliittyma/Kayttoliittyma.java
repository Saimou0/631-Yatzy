// Tekijä: Simo
package Kayttoliittyma;


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

    public void piirraPisteKortti() {
        
    }

    public void piirraUusiPeli() {
        System.out.println("----Uusi peli.----");
    }

    public void piirraPelinLoppuminen() {
        System.out.println("---- Peli loppui.----");
    }

    // Muista lisätä indeksi
    public void piirraPelaajanNimi(String pelaaja) {
        System.out.println("----Pelaajan " + "(lisää muuttuja pelaajan indeksiin)" + " nimi.----");
    }

    public void piirraNopat(int[] nopat) {

        String[] linjat = new String[5];
        for(int i = 0; i < 5; i++) {
            linjat[i] = "";
        }

        String[] noppienLinjat = null;
        for(int noppa : nopat) {
            switch (noppa) {
                case 1:
                    noppienLinjat = new String[] {
                        "---------",
                        "|       |",
                        "|   1   |",
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

        }
        
        for(int i = 0; i < 5; i++) {
            linjat[i] += noppienLinjat[i] + " ";
        }

        for(String line : linjat) {
            System.out.println(line);
        }

        System.out.println(noppienLinjat.length);
    }




}