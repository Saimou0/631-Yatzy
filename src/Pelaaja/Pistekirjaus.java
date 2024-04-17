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

    // TODO: Välisumman ja summan laskemiseen metodit.
    // TODO: Rikkinäiset pisteiden laskemisen metodit.

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

        for(int i = 0; i < tiedostotLista.length; i++) {
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

    // Poistaa kaikki tiedostot Pisteet kansiossa
    public void poistaPisteTiedostot() {
        File[] pisteTiedostot = PISTEETKANSIO.listFiles();
        for (File tiedosto1 : pisteTiedostot) {
            if (tiedosto1.isFile()) {
                boolean isDeleted = tiedosto1.delete();
                if (!isDeleted) {
                    System.out.println("Tiedoston: " + tiedosto1.getName() + " poistaminen ei onnistunut");
                }
            }
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
        int ykkostenSumma = laskeNopanSumma(nopat, 1);
        int kakkostenSumma = laskeNopanSumma(nopat, 2);
        int kolmostenSumma = laskeNopanSumma(nopat, 3);
        int nelostenSumma = laskeNopanSumma(nopat, 4);
        int viitostenSumma = laskeNopanSumma(nopat, 5);
        int kuutostenSumma = laskeNopanSumma(nopat, 6);

        mahdollisetPisteet.put("Ykkoset", ykkostenSumma);
        mahdollisetPisteet.put("Kakkoset", kakkostenSumma);
        mahdollisetPisteet.put("Kolmoset", kolmostenSumma);
        mahdollisetPisteet.put("Neloset", nelostenSumma);
        mahdollisetPisteet.put("Viitoset", viitostenSumma);
        mahdollisetPisteet.put("Kuutoset", kuutostenSumma);
        
        // Samat
        int pari = laskePari(nopat);
        int kaksiParia = laskeKaksiParia(nopat);
        int kolmeSamaa = laskeKolmeSamaa(nopat);
        int neljaSamaa = laskeNeljaSamaa(nopat);
        
        mahdollisetPisteet.put("Pari", pari);
        mahdollisetPisteet.put("Kaksi paria", kaksiParia);
        mahdollisetPisteet.put("Kolme samaa", kolmeSamaa);
        mahdollisetPisteet.put("Nelja samaa", neljaSamaa);

        // Muut
        int pieniSuora = laskePieniSuora(nopat);
        int isoSuora = laskeIsoSuora(nopat);
        int taysikasi = laskeTaysikasi(nopat);
        int sattuma = laskeSattuma(nopat);
        int yatzy = laskeYatzy(nopat);
        
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

    private int laskePari(int[] nopat) {
        int[] nopatLista = nopat;

        Arrays.sort(nopatLista);

        for(int i = nopatLista.length - 1; i > 0; i--) {
            if (nopatLista[i] == nopatLista[i - 1]) {
                return nopatLista[i] + nopatLista[i - 1];
            }
        }

        return 0;
    }

    // TODO EI TOIMI
    private int laskeKaksiParia(int[] nopat) {
        int[] nopatLista = nopat;
        Arrays.sort(nopatLista);
        int summa = 0;

        for(int i = nopatLista.length - 1; i > 0; i--) {
            if(laskeNeljaSamaa(nopatLista) == 0) {
                if (nopatLista[i] == nopatLista[i - 1]) {
                    summa = nopatLista[i] + nopatLista[i - 1];
                }
                
                if(i > 2) {
                    for(int j = i - 2; j > 0; j--) {
                        if (nopatLista[j] == nopatLista[j - 1]) {
                            return summa + nopatLista[j] + nopatLista[j - 1];
                        }
                    }
                }
            }


        }


        return 0;
    }

    private int laskeKolmeSamaa(int[] nopat) {
        int[] nopatLista = nopat;

        Arrays.sort(nopatLista);

        for(int i = nopatLista.length - 1; i > 1; i--) {
            if(nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2]) {
                return nopatLista[i] + nopatLista[i - 1] + nopatLista[i - 2];
            }
        }

        return 0;
    }

    private int laskeNeljaSamaa(int[] nopat) {
        int[] nopatLista = nopat;

        Arrays.sort(nopatLista);

        for(int i = nopatLista.length - 1; i > 2; i--) {
            if(nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2] && nopatLista[i] == nopatLista[i - 3]) {
                return nopatLista[i] + nopatLista[i - 1] + nopatLista[i - 2] + nopatLista[i - 3];
            }
        }

        return 0;
    }

    private int laskePieniSuora(int[] nopat) {
        int[] nopatLista = nopat;
        Arrays.sort(nopatLista);
        int[] testiLista = {1, 2, 3, 4, 5};
        boolean onkoPieniSuora = true;
        for(int i = 0; i < nopatLista.length; i++) {
            if(nopatLista[i] != testiLista[i]) {
                onkoPieniSuora = false;
            }
        }

        if(onkoPieniSuora) {
            return 15;
        }

        return 0;
    }

    // TODO Ei toimi
    private int laskeIsoSuora(int[] nopat) {
        int[] nopatLista = nopat;
        Arrays.sort(nopatLista);
        int[] testiLista = {2, 3, 4, 5, 6};
        boolean onkoIsoSuora = true;

        for(int i = 0; i > nopatLista.length; i++) {
            if(nopatLista[i] != testiLista[i]) {
                onkoIsoSuora = false;
            }
        }

        if(onkoIsoSuora) {
            return 20;
        }

        return 0; 
    }

    // TODO Ei toimi
    private int laskeTaysikasi(int[] nopat) {
        int[] nopatLista = nopat;
        Arrays.sort(nopatLista);
        int summa = 0;

        for(int i = nopatLista.length - 1; i > 1; i--) {
            if(nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2]) {
                summa = nopatLista[i] + nopatLista[i - 1] + nopatLista[i - 2];
            }

            if(i > 2) {
                for(int j = i - 2; j > 0; j--) {
                    if (nopatLista[j] == nopatLista[j - 1]) {
                        return summa + nopatLista[j] + nopatLista[j - 1];
                    }
                }
            }
        }

        return 0; 
    }

    private int laskeSattuma(int[] nopat) {
        int summa = 0;
        for(int noppa : nopat) {
            summa += noppa;
        }

        return summa;
    }

    private int laskeYatzy(int[] nopat) {
        int[] nopatLista = nopat;
        Arrays.sort(nopatLista);

        for(int i = nopatLista.length - 1; i > 3; i--) {
            if(nopatLista[i] == nopatLista[i - 1] && nopatLista[i] == nopatLista[i - 2] && nopatLista[i] == nopatLista[i - 3] && nopatLista[i] == nopatLista[i - 4]) {
                return 50;
            }
        }

        return 0; 
    }

}
