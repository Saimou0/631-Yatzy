// Lisääjä/Tekijä: Simo
package Pelaaja;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pistekirjaus {
    LinkedHashMap<String, Integer> mahdollisetPisteet = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> pisteet = new LinkedHashMap<String, Integer>();
    private final File PISTEETKANSIO = new File("pisteet/");

    private final String[] numerot = { "Ykkoset", "Kakkoset", "Kolmoset", "Neloset", "Viitoset", "Kuutoset"};

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
        String[] tiedostotLista = PISTEETKANSIO.list();
        LinkedHashMap<String, Integer> tiedostot = new LinkedHashMap<String, Integer>();

        for (int i = 0; i < tiedostotLista.length; i++) {
            tiedostot.put(tiedostotLista[i], i + 1);
        }

        return tiedostot;
    }

    // Tarkistaa onko Pisteet kansiossa teksti tiedostoja
    public boolean onkoTiedostoja() {
        if (PISTEETKANSIO.isDirectory() && PISTEETKANSIO.list().length > 0) {
            return true;
        } else {
            System.out.println("Tiedostoja ei ole");
            return false;
        }
    }

    public void lisaaPisteet(String nimi) {
        this.pisteet.put(nimi, mahdollisetPisteet.get(nimi));
    }

    public LinkedHashMap<String, Integer> getMahdollisetPisteet(int[] nopat) {
        laskeMahdollisetPisteet(nopat);
        return mahdollisetPisteet;
    }

    public void laskeMahdollisetPisteet(int[] nopat) {
        mahdollisetPisteet.clear();

        // 1 - 6
        for (int i = 1; i <= 6; i++) {
            int summa = laskeNopanSumma(nopat, i);
            mahdollisetPisteet.put(numerot[i - 1], summa);
        }

        // Samat
        int pari = laskeSamat(nopat, 2);
        int kaksiParia = laskeKaksiParia(nopat);
        int kolmeSamaa = laskeSamat(nopat, 3);
        int neljaSamaa = laskeSamat(nopat, 4);

        mahdollisetPisteet.put("Pari", pari);
        mahdollisetPisteet.put("Kaksi paria", kaksiParia);
        mahdollisetPisteet.put("Kolme samaa", kolmeSamaa);
        mahdollisetPisteet.put("Nelja samaa", neljaSamaa);

        // Muut
        int pieniSuora = laskePieniSuora(nopat);
        int isoSuora = laskeIsoSuora(nopat);
        int taysikasi = laskeTaysikasi(nopat);
        int sattuma = laskeSattuma(nopat);
        int yatzy = laskeSamat(nopat, 5);

        mahdollisetPisteet.put("Pieni suora", pieniSuora);
        mahdollisetPisteet.put("Iso suora", isoSuora);
        mahdollisetPisteet.put("Taysikasi", taysikasi);
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

    private int laskeSamat(int[] nopat, int maara) {
        int[] nopatLista = Arrays.copyOf(nopat, nopat.length);
        Arrays.sort(nopatLista);

        // Pari
        if (maara == 2) {
            for (int i = nopatLista.length - 1; i > 0; i--) {
                if (nopatLista[i] == nopatLista[i - 1]) {
                    return nopatLista[i] + nopatLista[i - 1];
                }
            }
        }
        // Kolme samaa
        if (maara == 3) {
            for (int i = nopatLista.length - 1; i > 1; i--) {
                if (nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2]) {
                    return nopatLista[i] + nopatLista[i - 1] + nopatLista[i - 2];
                }
            }
        }
        // Neljä samaa
        if (maara == 4) {
            for (int i = nopatLista.length - 1; i > 2; i--) {
                if (nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2]
                        && nopatLista[i] == nopatLista[i - 3]) {
                    return nopatLista[i] + nopatLista[i - 1] + nopatLista[i - 2] + nopatLista[i - 3];
                }
            }
        }
        // Yatzy
        if (maara == 5) {
            for (int i = nopatLista.length - 1; i > 3; i--) {
                if (nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2]
                        && nopatLista[i] == nopatLista[i - 3] && nopatLista[i] == nopatLista[i - 4]) {
                    return 50;
                }
            }
        }

        return 0;
    }

    private int laskeKaksiParia(int[] nopat) {
        int[] nopatLista = Arrays.copyOf(nopat, nopat.length);
        Arrays.sort(nopatLista);

        if(laskeSamat(nopatLista, 2) != 0 && laskeSamat(nopatLista, 3) == 0) {
            int poistettavaNumero = laskeSamat(nopatLista, 2) / 2;
            
            nopatLista = poistaNoppia(nopatLista, poistettavaNumero);

            if(laskeSamat(nopatLista, 2) != 0) {
                return laskeSamat(nopatLista, 2) + poistettavaNumero * 2;
            }
        }

        return 0;
    }

    private int laskePieniSuora(int[] nopat) {
        int[] nopatLista = Arrays.copyOf(nopat, nopat.length);
        Arrays.sort(nopatLista);
        int[] testiLista = { 1, 2, 3, 4, 5 };
        boolean onkoPieniSuora = true;

        for (int i = 0; i < nopatLista.length; i++) {
            if (nopatLista[i] != testiLista[i]) {
                onkoPieniSuora = false;
            }
        }

        if (onkoPieniSuora) {
            return 15;
        }

        return 0;
    }

    private int laskeIsoSuora(int[] nopat) {
        int[] nopatLista = Arrays.copyOf(nopat, nopat.length);
        Arrays.sort(nopatLista);
        int[] testiLista = { 2, 3, 4, 5, 6 };
        boolean onkoIsoSuora = true;

        for (int i = 0; i < nopatLista.length; i++) {
            if (nopatLista[i] != testiLista[i]) {
                onkoIsoSuora = false;
            }
        }

        if (onkoIsoSuora) {
            return 20;
        }

        return 0;
    }

    private int laskeTaysikasi(int[] nopat) {
        int[] nopatLista = Arrays.copyOf(nopat, nopat.length);
        Arrays.sort(nopatLista);
        int summa = 0;

        if(laskeSamat(nopatLista, 3) != 0 && laskeSamat(nopatLista, 4) == 0 && laskeSamat(nopatLista, 5) == 0) 
        {
            int poistettavaNumero = laskeSamat(nopatLista, 3) / 3;

            nopatLista = poistaNoppia(nopatLista, poistettavaNumero);

            if(laskeSamat(nopatLista, 2) != 0) {
                summa = (nopatLista[0] + nopatLista[1]) + poistettavaNumero * 3;
            }

            return summa;
        }

        return 0;
    }

    private int laskeSattuma(int[] nopat) {
        int summa = 0;
        for (int noppa : nopat) {
            summa += noppa;
        }

        return summa;
    }

    private int[] poistaNoppia(int[] nopat, int numero) {
        int laskuri = 0;
        for (int i = 0; i < nopat.length; i++) {
            if (nopat[i] == numero) {
                laskuri++;
            }
        }

        int[] uusiNopat = new int[nopat.length - laskuri];
        int j = 0;
        for(int i = 0; i < nopat.length; i++) {
            if(nopat[i] != numero) {
                uusiNopat[j] = nopat[i];
                j++;
            }
        }

        return uusiNopat;
    }

    public void laskeValisumma() {
        int summa = 0;

        for(String nimi : numerot) {
            Integer pisteet = this.pisteet.get(nimi);
            if(pisteet == null || pisteet == -1) {
                return;
            }

            summa += pisteet;
        }
        
        this.pisteet.put("Valisumma", summa);
    }

    public void laskeSumma() {
        int summa = 0;

        for (Map.Entry<String, Integer> merkinta : pisteet.entrySet()) {
            if(merkinta.getValue() == -1 || merkinta.getValue() == null && 
               merkinta.getKey().equals("Summa") || merkinta.getKey().equals("Valisumma")) 
            {
                continue;
            }
            
            summa += merkinta.getValue();
            
        }

        this.pisteet.put("Summa", summa);
    }

}
