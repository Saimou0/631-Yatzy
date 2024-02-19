// Lisääjä/Tekijä: Jimi

package Pelaaja;

import java.util.Scanner;

public class Nopat {

    private int[] nopat;
    private boolean[] lukitutNopat;
    private final int NOPPAMAARA = 5;

    public Nopat() {
        this.nopat = new int[NOPPAMAARA];
        this.lukitutNopat = new boolean[NOPPAMAARA];
    }

    public void heitaNopat() {
        for (int i = 0; i < NOPPAMAARA; i++) {
            if (!lukitutNopat[i]) {
                nopat[i] = (int) (Math.random() * 6) + 1;
            }
        }
    }

    public void lukitseNoppa(int indeksi) {
        if (indeksi >= 0 && indeksi < NOPPAMAARA) {
            lukitutNopat[indeksi] = true;
        }
    }

    public void vapautaNoppa(int indeksi) {
        if (indeksi >= 0 && indeksi < NOPPAMAARA) {
            lukitutNopat[indeksi] = false;
        }
    }

    public int[] getNopat() {
        return nopat;
    }

    public boolean[] getLukitutNopat() {
        return lukitutNopat;
    }
}
