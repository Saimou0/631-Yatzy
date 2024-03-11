// Lisääjä/Tekijä: Simo
package Pelaaja;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

// Muuta hashmapit linkedhashmapeiks

public class Pistekirjaus{
    LinkedHashMap<String, Integer> mahdollisetPisteet = new LinkedHashMap<String, Integer>();
    LinkedHashMap<String, Integer> pisteet = new LinkedHashMap<String, Integer>();
    File pisteetKansio = new File("src/pisteet/");

    public void tallennaPisteetTiedostoon(String pelaajannimi) {
        try {
            PrintWriter kirjoittaja = new PrintWriter(new File("src/pisteet/" + pelaajannimi + "_pisteet.txt"));

            for (Map.Entry<String, Integer> merkinta : pisteet.entrySet()) {
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
            BufferedReader lukija = new BufferedReader(new FileReader("src/pisteet/" + pelaajanNimi + "_pisteet.txt"));

            String rivi;
            while((rivi = lukija.readLine()) != null) {
                String[] osat = rivi.split(": ");
                if(osat.length >= 2) {
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

    // Tarkistaa onko Pisteet kansiossa teksti tiedostoja
    public boolean onkoTiedostoja() {
        if(pisteetKansio.isDirectory() && pisteetKansio.list().length > 0) {
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
                if(!isDeleted) {
                    System.out.println("Tiedoston: " + tiedosto1.getName() + " poistaminen ei onnistunut");
                }
            }
        }
    }

    public void lisaaPisteet(String nimi, int pisteet) {
        this.pisteet.put(nimi, pisteet);
    }

    // Possibly obsolete

    // public LinkedHashMap<String, Integer> getPisteet() {
    //     return this.pisteet;
    // }

    // public LinkedHashMap<String, Integer> getMahdollisetPisteet() {
    //     return this.mahdollisetPisteet;
    

    public void laskeMahdollisetPisteet(int[] nopat) {
        mahdollisetPisteet.clear();
        int ykkostenSumma = laskeNopanSumma(nopat, 1);
        mahdollisetPisteet.put("Ykköset", ykkostenSumma);
        
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

    public void tallennaPisteetTiedostoon(String pelaajannimi) {
        
    }

    public void luePisteetTiedostosta(String pelaajanNimi) {
       
    }

    
}
    }



}
