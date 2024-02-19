// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Kayttoliittyma {
    String punainen = "\033[0;31m";
    String vihrea = "\033[0;32m";
    String resetoiVari = "\033[0m";

    // Yleinen virhe syöttö
    public void piirraVirheSyotto() {
        System.out.println("----Virheellinen syöttö yritä uudestaan.----");
    }

    // Menu
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
        System.out.println("----Anna pelaajan: " + pelaajanIndeksi + " nimi----");
    }

    // Tarkempia virhe syöttöjä
    public void pelaajaJoValinnutPisteet() {
        System.out.println(punainen + "----Olet jo valinnut tämän pisteen----" + resetoiVari);
    }

    public void eiHeittoja() {
        System.out.println(punainen + "----Sinulla ei ole enää heittoja jäljellä----" + resetoiVari);
    }

    // Moninpeli
    public void piirraPelaajanVuoro(String pelaaja) {
        System.out.println("----Pelaajan " + pelaaja + " vuoro----");
    }

    // TODO: Tee metodi joka piirtää valitse pisteet käyttöliittymän.

    // Pelaajan vaihtoehdot pelissä
    public void piirraPelaajanVaihtoehdot(LinkedHashMap<String, Integer> vaihtoehdot) {
        ArrayList<String> piirrettavatVaihtoehdot = new ArrayList<>();

        for(Map.Entry<String, Integer> entry : vaihtoehdot.entrySet()) {
            if (entry.getKey() == "Heitä" && entry.getValue() == 1){
                piirrettavatVaihtoehdot.add("|   1 -> Heitä nopat   |");
            }

            if(entry.getKey() == "Lukitse" && entry.getValue() == 1){
                piirrettavatVaihtoehdot.add("| 2 -> Lukitse noppia  |");
            }

            if(entry.getKey() == "Valitse Pisteet" && entry.getValue() == 1){
                piirrettavatVaihtoehdot.add("| 3 -> Valitse pisteet |");
            }

            if(entry.getKey() == "Lopeta" && entry.getValue() == 1) {
                piirrettavatVaihtoehdot.add("|   0 -> Lopeta peli   |");
            }
        }

        // for(LinkedHashMap<String, Integer> vaihtoehto : vaihtoehdot.values()) {
        //     switch (vaihtoehto) {
        //         case 1:
  

        //     }
        // }

        System.out.println("------------------------");
        System.out.println("|                      |");
        for(String vaihtoehto :piirrettavatVaihtoehdot) {
            System.out.println(vaihtoehto);
        }
        System.out.println("|   0 -> Lopeta peli   |");
        System.out.println("|                      |");
        System.out.println("------------------------");
    }

    // Pistekortti
    public void piirraPisteKortti(Map<String, Integer> pisteet, String nimi) {

        // -1 arvo tarkoittaa tyhjää arvoa. Arvoa jota pelaaja ei ole vielä valinnut
        for(Map.Entry<String, Integer> sisalto : pisteet.entrySet())  {
            int sisaltoArvo = sisalto.getValue();

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
            // Jos arvo on -1 niin tulostetaan tyhjä arvo.
            if(sisaltoArvo == -1) {
                System.out.printf("| %-25s |\n", sisalto.getKey());
            } else {
                System.out.printf("| %-19s %5d |\n", sisalto.getKey(), sisaltoArvo);
            }

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
    public void piirraNopat(int[] nopat) {
        // Hashmap jossa on kaikkien noppien sivut taulukkoina.
        Map<Integer, String[]> noppienSivut = new HashMap<>();
        noppienSivut.put(1, new String[] {"---------", "|       |", "|   1   |", "|       |", "---------"});
        noppienSivut.put(2, new String[] {"---------", "| 2     |", "|       |", "|     * |", "---------"});
        noppienSivut.put(3, new String[] {"---------", "| *     |", "|   3   |", "|     * |", "---------"});
        noppienSivut.put(4, new String[] {"---------", "| 4   * |", "|       |", "| *   * |", "---------"});
        noppienSivut.put(5, new String[] {"---------", "| *   * |", "|   5   |", "| *   * |", "---------"});
        noppienSivut.put(6, new String[] {"---------", "| 6   * |", "| *   * |", "| *   * |", "---------"});

        // Tehdään taulukko johon tallenetaan kaikki nopat linja kerrallaan ja peräkäin.
        // Tällein saadaan nopat piirrettyä vierekkäin samalle linjalle.
        String[] linjat = new String[5];
        for(int i = 0; i < 5; i++) {
            linjat[i] = "";
        }
        
        // Tallennetaan yksi noppa linja kerrallaan noppienLinjat-taulukkoon.
        String[] noppienLinjat = new String[5];
        for(int noppa : nopat) {
            noppienLinjat = noppienSivut.get(noppa);
            for(int i = 0; i < 5; i++) {
                linjat[i] += noppienLinjat[i] + " ";
            }
        }
        
        // Tulostetaan nopat. 
        for(String linja : linjat) {
            System.out.println(linja);
        }

        // Numeroidaan nopat lukitsemista varten
        System.out.println("----1---- " + "----2---- " + "----3---- " + "----4---- " + "----5----");

    }

    // Noppien lukitseminen
    public void piirraLukitseTila(LinkedHashMap<Integer, Boolean> lukitutNopat) {
        System.out.println("----Valitse noppa jonka haluat lukita----");

        for(Map.Entry<Integer, Boolean> entry : lukitutNopat.entrySet()) {
            String nopanTila;
            if(entry.getValue() == false) {
                nopanTila = "Lukitsematon";
            } else {
                nopanTila = "Lukittu";
            }
            System.out.println(entry.getKey() + " -> " + nopanTila);
        }
            

    }

    // Tyhjentää terminaalin
    public void tyhjennaTerminaali() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


}