// Lisääjä/Tekijä: Jimi
// Hyvin paljon muokannut: Simo
package Pelaaja;

import java.util.LinkedHashMap;

public class Nopat {

    LinkedHashMap<Integer, Boolean> lukitutNopat = new LinkedHashMap<Integer, Boolean>();
    private int[] nopat;
    private final int NOPPAMAARA = 5;

    public Nopat() {
        this.nopat = new int[NOPPAMAARA];
        for(int i = 1; i <= NOPPAMAARA; i++) {
            lukitutNopat.put(i, false);
        }
    }
    
    public void heitaNopat() {
        for (int i = 0; i < NOPPAMAARA; i++) {
            if(lukitutNopat.get(i + 1) == false) {
                nopat[i] = (int) (Math.random() * 6) + 1;
            }
        }
    }

    public void lukitseNoppa(int indeksi) {
        if (indeksi >= 1 && indeksi <= NOPPAMAARA) {
            lukitutNopat.put(indeksi, true);
        }

    }

    public void vapautaNoppa(int indeksi) {
        if (indeksi >= 1 && indeksi <= NOPPAMAARA) {
            lukitutNopat.put(indeksi, false);
        }
    }

    public int[] getNopat() {
        return nopat;
    }

    public LinkedHashMap<Integer, Boolean> getLukitutNopat() {
        return lukitutNopat;
    }
}