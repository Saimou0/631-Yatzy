// Lisääjä/Tekijä: Simo
package Kayttoliittyma;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import Pelaaja.Pelaaja;

public class Kayttoliittyma {
    static final String PUNAINEN = "\033[0;31m";
    static final String VIHREA = "\033[0;32m";
    static final String RESETOI_VARI = "\033[0m";

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

    public void kysyPelaajanNimi() {
        System.out.println("----Pelaajan nimi (Tyhjä nimi luo nimen automaattisesti)----");
    }

    // Tarkempia virhe syöttöjä
    public void pelaajaJoValinnutPisteet() {
        System.out.println(PUNAINEN + "----Olet jo valinnut tämän pisteen----" + RESETOI_VARI);
    }

    public void eiHeittoja() {
        System.out.println(PUNAINEN + "----Sinulla ei ole enää heittoja jäljellä----" + RESETOI_VARI);
    }
    
    public void piirraValitseVastustaja(LinkedHashMap<String, Integer> tiedostot) {

        System.out.println("-----------------------------------------------------");
        System.out.println("|    Valitse pisteet joita vastaan haluat pelata    |");
        System.out.println("|                                                   |");

        for(Map.Entry<String, Integer> entry : tiedostot.entrySet()) {
            System.out.printf("| %-49s |\n", entry.getValue() + " -> " + entry.getKey());
        }

        System.out.println("|                                                   |");
        System.out.println("| 0 -> Tee uusi pistetiedosto                       |");
        System.out.println("-----------------------------------------------------");

    }

    // Pelaajan vaihtoehdot pelissä
    public void piirraPelaajanVaihtoehdot(LinkedHashMap<String, Integer> vaihtoehdot) {
        ArrayList<String> piirrettavatVaihtoehdot = new ArrayList<>();

        // Käydään vaihtoehdot läpi ja lisätään ne piirrettävään listaan.
        // Käydään hashmap läpi jos vaihtoehdolla on arvo 1, niin lisätään se piirrettävään listaan, jos arvo on 0, niin ei lisätä sitä listaan.
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

        }

        // Piirretään vaihtoehdot
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
    public void piirraPisteKortti(Map<String, Integer> pisteet, String nimi, Map<String, Integer> mahdollisetPisteet) {
        System.out.println(VIHREA + "Vihreä = valittu piste" + RESETOI_VARI);
        System.out.println(PUNAINEN + "Punainen = Mahdollinen piste" + RESETOI_VARI);

        // -1 arvo tarkoittaa tyhjää arvoa. Arvoa jota pelaaja ei ole vielä valinnut
        for(Map.Entry<String, Integer> sisalto : pisteet.entrySet())  {
            int sisaltoArvo = sisalto.getValue();
            String sisaltoAvain = sisalto.getKey();

            // Jos kyseessä on ykköset niin tulostetaan piste kortin otsikko.
            if(sisaltoAvain.equals("Ykkoset")) {
                System.out.println("-----------------------------");
                System.out.println("|       PISTE KORTTI        |");
                System.out.printf("| %-25s |\n", nimi);
                System.out.printf("| %-25s |\n", " ");
            }

            // Jos kyseessä on välisumma, pari, summa tai neljä samaa niin tulostetaan tyhjä rivi ennen numeroa.
            if(sisaltoAvain.equals("Valisumma") || 
                sisaltoAvain.equals("Summa") || 
                sisaltoAvain.equals("Pieni suora") ||
                sisaltoAvain.equals("Pari")) 
            {
                System.out.printf("| %-25s |\n", " ");
            }
            
            // Tulostetaan piste kortin arvot.
            // Jos arvo on -1 niin tulostetaan tyhjä arvo.
            if(sisaltoArvo == -1) {
                if (mahdollisetPisteet.containsKey(sisaltoAvain)) {
                    int mahdollinenPisteArvo = mahdollisetPisteet.get(sisaltoAvain);
                    System.out.printf(PUNAINEN + "| %-19s %5d |\n" + RESETOI_VARI, sisaltoAvain, mahdollinenPisteArvo);
                } else {
                    System.out.printf("| %-25s |\n", sisaltoAvain);
                }
            } else {
                System.out.printf(VIHREA + "| %-19s %5d |\n" + RESETOI_VARI, sisaltoAvain, sisaltoArvo);
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

    // TODO: Korjaa välisummma ja summa.        
    // TODO: Vastustajan summassa jotain ongelmaa.
    public void piirraMontaPistekorttia(Pelaaja pelaaja, Pelaaja vastustaja, Map<String, Integer> mahdollisetPisteet) {
        Map<String, Integer> pelaajanPisteet = pelaaja.getPisteet();
        Map<String, Integer> vastustajanPisteet = vastustaja.getPisteet();

        for (String avain : pelaajanPisteet.keySet()) {

            if(avain.equals("Ykkoset")) {
                System.out.println("-----------------------------  -----------------------------");
                System.out.println("|       PISTE KORTTI        |  |       PISTE KORTTI        |");
                System.out.printf("| %-25s |  | %-25s |\n", pelaaja.getNimi(), vastustaja.getNimi());
                System.out.printf("| %-25s |  | %-25s |\n", " ", " ");
            }
            
            if(avain.equals("Valisumma") || avain.equals("Summa")) {
                System.out.printf("| %-25s |  | %-25s |\n"," ", " ");
            }
            
            if(avain.equals("Pieni suora") || avain.equals("Pari")) {
                System.out.printf("| %-25s |  | %-25s |\n", " ", " ");
            }

            if(pelaajanPisteet.get(avain) == -1) {
                if (mahdollisetPisteet.containsKey(avain)) {
                    int mahdollinenPisteArvo = mahdollisetPisteet.get(avain);
                    System.out.printf(PUNAINEN + "| %-19s %5d | " + VIHREA + " | %-19s %5d | %n" + RESETOI_VARI, 
                    avain, mahdollinenPisteArvo, avain, (vastustajanPisteet.get(avain) == -1 ? 0 : vastustajanPisteet.get(avain)));
                } else {
                    System.out.printf("| %-25s |  | %-25s | %n", avain, avain);
                }
            } else {
                System.out.printf(VIHREA + "| %-19s %5d |  | %-19s %5d | %n" + RESETOI_VARI, 
                avain, pelaajanPisteet.get(avain), avain, (vastustajanPisteet.get(avain) == -1 ? 0 : vastustajanPisteet.get(avain)));
            }
            // System.out.printf("| %-19s %5d |  | %-19s %5d | %n", avain, pelaajanPisteet.get(avain), avain, vastustajanPisteet.get(avain));
        }

        System.out.println(
            """
            |                           |  |                           |
            -----------------------------  -----------------------------
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

        // Käydään läpi hashmappi lukituista nopista.
        for(Map.Entry<Integer, Boolean> entry : lukitutNopat.entrySet()) {
            String nopanTila;
            if(entry.getValue() == false) {
                nopanTila = "Lukitsematon";
            } else {
                nopanTila = "Lukittu";
            }
            System.out.println(entry.getKey() + " -> " + nopanTila);
        }

        System.out.println("------------------------------------------------");
        System.out.println("|                                              |");
        System.out.println("| Lukitse noppia kirjoittamalla niiden numero. |");
        System.out.println("|                -1 -> Takaisin                |");
        System.out.println("|                                              |");
        System.out.println("------------------------------------------------");

    }

    // Piirrä piste valinta
    public void piirraPisteValinta() {
        System.out.println("--------------------------------------------------");
        System.out.println("|                                                |");
        System.out.println("|   Valitse piste kirjoittamalla pisteen nimi.   |");
        System.out.println("|                 -1 -> Takaisin                 |");
        System.out.println("|                                                |");
        System.out.println("--------------------------------------------------");
    }

    // Tyhjentää terminaalin
    public void tyhjennaTerminaali() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}