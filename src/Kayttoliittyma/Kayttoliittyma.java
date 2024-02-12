// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.ArrayList;
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

    public void piirraPelaajanVuoro(String pelaaja) {
        System.out.println("----Pelaajan " + pelaaja + " vuoro.----");
    }
    // Pelaajan vaihtoehdot pelissä
    public void piirraPelaajanVaihtoehdot(ArrayList<Integer> vaihtoehdot) {
        ArrayList<String> piirrettavatVaihtoehdot = new ArrayList<>();
        for(int vaihtoehto : vaihtoehdot) {
            switch (vaihtoehto) {
                case 1:
                    piirrettavatVaihtoehdot.add("|   1 -> Heitä nopat   |");
                    break;
                case 2:
                    piirrettavatVaihtoehdot.add("| 2 -> Lukitse noppia  |");
                    break;
                case 3:
                    piirrettavatVaihtoehdot.add("| 3 -> Valitse pisteet |");
                    break;

                case 4:
                    piirrettavatVaihtoehdot.add("|    4 -> Takaisin     |");
                    break;

                default:
                    this.piirraVirheSyotto();
                    break;
            }

        }

        System.out.println("------------------------");
        System.out.println("|                      |");
        for(String vaihtoehto :piirrettavatVaihtoehdot) {
            System.out.println(vaihtoehto);
        }
        System.out.println("|     0 -> Lopeta      |");
        System.out.println("|                      |");
        System.out.println("------------------------");
    }

    // Pistekortti
    public void piirraPisteKortti(Map<String, Integer> pisteet, String nimi) {

        // -1 arvo tarkoittaa tyhjää arvoa. Arvoa jota pelaaja ei ole vielä valinnut
        for(Map.Entry<String, Integer> sisalto : pisteet.entrySet())  {
            int sisaltoArvo = sisalto.getValue();
            if(sisaltoArvo == -1) {
                sisaltoArvo = 0;
            }
            // Jos kyseessä on ykköset niin tulostetaan piste kortin otsikko.
            if(sisalto.getKey().equals("Ykköset")) {
                System.out.println("-----------------------------");
                System.out.println("|       PISTE KORTTI        |");
                System.out.printf("| %-25s |\n", nimi);
                System.out.printf("| %-25s |\n", " ");
            }

            // Jos kyseessä on välisumma, pari, summa tai neljä samaa niin tulostetaan tyhjä rivi ennen numeroa.
            if(sisalto.getKey().equals("Välisumma") || 
                sisalto.getKey().equals("Summa") || 
                sisalto.getKey().equals("Neljä samaa") ||
                sisalto.getKey().equals("Pari")) 
            {
                System.out.printf("| %-25s |\n", " ");
            }

            // Tulostetaan piste kortin arvot.
            System.out.printf("| %-19s %5d |\n", sisalto.getKey(), sisaltoArvo);
        }

        // Tulostetaan piste kortin alaosa.
        System.out.println(
            """
            |                           |
            -----------------------------
            """
        );
    }

    // Nopat
    // Alin metodi
    public void piirraNopat(int[] nopat) {
        // Tehdään taulukko johon tallenetaan kaikki nopat linja kerrallaan ja peräkäin.
        // Tällein saadaan nopat piirrettyä vierekkäin samalle linjalle.
        String[] linjat = new String[5];
        for(int i = 0; i < 5; i++) {
            linjat[i] = "";
        }


        // COPILOT
        /*
        One area of potential optimization is in the piirraNopat method in your Kayttoliittyma.java file.
        Currently, you're creating a new noppienLinjat array for each possible dice value inside the loop. This could be optimized by creating a 2D array or a Map outside the loop that maps dice values to their corresponding string arrays. This way, you only need to look up the correct string array for each dice value, rather than recreating it each time.
        Here's a simplified example of how you could do this:

        Map<Integer, String[]> diceFaces = new HashMap<>();
        diceFaces.put(1, new String[] {"---------", "|       |", "|   1   |", "|       |", "---------"});
        // ... add the rest of the dice faces ...

        for(int noppa : nopat) {
            noppienLinjat = diceFaces.get(noppa);
            // ... rest of your code ...
        }

        This change could make your code more efficient by reducing the number of objects created and could also make your code cleaner and easier to read. 
        */

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