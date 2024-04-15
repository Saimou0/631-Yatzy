// Lisääjä/Tekijä: Simo
package Pelaaja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pistekirjaus {
    LinkedHashMap<String, Integer> mahdollisetPisteet = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> pisteet = new LinkedHashMap<String, Integer>();
    File pisteetKansio = new File("pisteet/");

    //TODO: Välisumman ja summan laskemiseen metodit.

    // Metodi pisteiden tallentamiseen tekstitiedostoon.
    public void tallennaPisteetTiedostoon(String pelaajannimi) {
        try {
            // Luodaan PrintWriter-olio tiedostoon kirjottamista varten. Tiedosto nimetään
            // pelaajan mukaan ja se luodaan pisteet kansioon.
            PrintWriter kirjoittaja = new PrintWriter(new File("pisteet/" + pelaajannimi + "_pisteet.txt"));

            // Käydään pisteet map läpi ja kirjoitetaan ne tiedostoon.
            for (Map.Entry<String, Integer> merkinta : pisteet.entrySet()) {
                // Kirjoita pistemäärä tiedostoon muodossa "avain: arvo".
                kirjoittaja.println(merkinta.getKey() + ": " + merkinta.getValue());
            }

            kirjoittaja.close();

        } catch (Exception e) {
            System.out.println("Tiedostoon kirjoittaminen ei onnistunut");
            e.printStackTrace();
        }
    }

    public void luePisteetTiedostosta(String pelaajanNimi) {
        LinkedHashMap<String, Integer> luetutPisteet = new LinkedHashMap<String, Integer>();

        try {
            BufferedReader lukija = new BufferedReader(new FileReader("pisteet/" + pelaajanNimi + "_pisteet.txt"));

            String rivi;
            while ((rivi = lukija.readLine()) != null) {
                String[] osat = rivi.split(": ");
                if (osat.length >= 2) {
                    String avain = osat[0];
                    int arvo = Integer.parseInt(osat[1]);
                    luetutPisteet.put(avain, arvo);
                } else {
                    System.out.println("Virheellinen rivi: " + rivi);
                }
            }

            lukija.close();

        } catch (Exception e) {
            System.out.println("Tiedoston lukeminen ei onnistunut");
            e.printStackTrace();
        }

        this.pisteet = luetutPisteet;
    }

    public LinkedHashMap<String, Integer> getPisteTiedostot() {
        String[] tiedostotLista = pisteetKansio.list();
        LinkedHashMap<String, Integer> tiedostot = new LinkedHashMap<String, Integer>();

        for(int i = 0; i < tiedostotLista.length; i++) {
            tiedostot.put(tiedostotLista[i], i + 1);
        }

        return tiedostot;
    } 

    // Tarkistaa onko Pisteet kansiossa teksti tiedostoja
    public boolean onkoTiedostoja() {
        if (pisteetKansio.isDirectory() && pisteetKansio.list().length > 0) {
            return true;
        } else {
            System.out.println("Tiedostoja ei ole");
            return false;
        }

    }

    // Poistaa kaikki tiedostot Pisteet kansiossa
    public void poistaPisteTiedostot() {
        File[] pisteTiedostot = pisteetKansio.listFiles();
        for (File tiedosto1 : pisteTiedostot) {
            if (tiedosto1.isFile()) {
                boolean isDeleted = tiedosto1.delete();
                if (!isDeleted) {
                    System.out.println("Tiedoston: " + tiedosto1.getName() + " poistaminen ei onnistunut");
                }
            }
        }
    }

    public void lisaaPisteet(String nimi, int pisteet) {
        this.pisteet.put(nimi, pisteet);
    }

     public void laskeMahdollisetPisteet(int[] nopat) {
        mahdollisetPisteet.clear();
        
        int ykkostenSumma = laskeNopanSumma(nopat, 1);
        int kakkostenSumma = laskeNopanSumma(nopat, 2);
        int kolmostenSumma = laskeNopanSumma(nopat, 3);
        int nelostenSumma = laskeNopanSumma(nopat, 4);
        int viitostenSumma = laskeNopanSumma(nopat, 5);
        int kuutostenSumma = laskeNopanSumma(nopat, 6);
        int pieniSuora = laskePieniSuora(nopat);
        int isoSuora = laskeIsoSuora(nopat);
        int taysikasi = laskeTaysikasi(nopat);
        int neljaSamaa = laskeNeljaSamaa(nopat);
        int kolmeSamaa = laskeKolmeSamaa(nopat);
        int pari = laskePari(nopat);
        int kaksiParia = laskeKaksiParia(nopat);
        int sattuma = laskeSattuma(nopat);
        int yatzy = laskeYatzy(nopat);

        
        mahdollisetPisteet.put("Ykköset", ykkostenSumma);
        mahdollisetPisteet.put("Kakkoset", kakkostenSumma);
        mahdollisetPisteet.put("Kolmoset", kolmostenSumma);
        mahdollisetPisteet.put("Neloset", nelostenSumma);
        mahdollisetPisteet.put("Viitoset", viitostenSumma);
        mahdollisetPisteet.put("Kuutoset", kuutostenSumma);
        mahdollisetPisteet.put("Pieni suora", pieniSuora);
        mahdollisetPisteet.put("Iso suora", isoSuora);
        mahdollisetPisteet.put("Täyskäsi", taysikasi);
        mahdollisetPisteet.put("Neljä samaa", neljaSamaa);
        mahdollisetPisteet.put("Kolme samaa", kolmeSamaa);
        mahdollisetPisteet.put("Pari", pari);
        mahdollisetPisteet.put("Kaksi paria", kaksiParia);
        mahdollisetPisteet.put("Sattuma", sattuma);
        mahdollisetPisteet.put("Yatzy", yatzy);
    }

    
    private int laskeNopanSumma(int[] nopat, int numero) {
        int summa = 0;
        for (int noppa : nopat) {
            if (noppa == numero) {
                summa += numero;
            }
        }
        return summa;
    }

    
    private int laskePieniSuora(int[] nopat) {
        int[] counts = new int[6];
        for (int noppa : nopat) {
            counts[noppa - 1]++;
        }
        for (int i = 0; i < 3; i++) {
            if (counts[i] > 0 && counts[i + 1] > 0 && counts[i + 2] > 0 && counts[i + 3] > 0) {
                return 15; 
            }
        }
        return 0; 
    }

   
    private int laskeIsoSuora(int[] nopat) {
        int[] counts = new int[6];
        for (int noppa : nopat) {
            counts[noppa - 1]++;
        }
        for (int i = 0; i < 2; i++) {
            if (counts[i] > 0 && counts[i + 1] > 0 && counts[i + 2] > 0 && counts[i + 3] > 0 && counts[i + 4] > 0) {
                return 20; 
            }
        }
        return 0; 
    }

    
    private int laskeTaysikasi(int[] nopat) {
        int[] counts = new int[6];
        for (int noppa : nopat) {
            counts[noppa - 1]++;
        }
        boolean hasThree = false;
        boolean hasTwo = false;
        for (int count : counts) {
            if (count == 3) {
                hasThree = true;
            }
            if (count == 2) {
                hasTwo = true;
            }
        }
        if (hasThree && hasTwo) {
            return 25; 
        }
        return 0; 
    }

    private int laskeNeljaSamaa(int[] nopat) {
        int[] counts = new int[6];
        for (int noppa : nopat) {
            counts[noppa - 1]++;
        }
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 4) {
                return 30; 
            }
        }
        return 0; 
    }

    private int laskeKolmeSamaa(int[] nopat) {
        int[] counts = new int[6];
        for (int noppa : nopat) {
            counts[noppa - 1]++;
        }
        for (int
