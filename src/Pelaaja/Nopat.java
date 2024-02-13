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

    public void piirraNopat() {
        Scanner scanner = new Scanner(System.in);
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();

        int heittojaJaljella = 3;

        while (heittojaJaljella > 0) {
            kayttoliittyma.piirraNopat(nopat);

            System.out.println("Heittoja jäljellä: " + heittojaJaljella);
            System.out.println("Valitse toiminto:");
            System.out.println("1 -> Heitä nopat");
            System.out.println("2 -> Lukitse noppia");
            System.out.println("3 -> Valitse pisteet");
            System.out.println("0 -> Lopeta");

            int valinta = scanner.nextInt();

            switch (valinta) {
                case 1:
                    heitaNopat();
                    heittojaJaljella--;
                    break;

                case 2:
                    kayttoliittyma.piirraNopat(nopat);
                    System.out.println("Anna lukittavien noppien indeksit (esim. 1 3 5):");
                    String lukittavatNopat = scanner.nextLine();
                    String[] indeksit = lukittavatNopat.split(" ");
                    for (String indeksi : indeksit) {
                        lukitseNoppa(Integer.parseInt(indeksi) - 1);
                    }
                    break;

                case 3:
                    break;

                case 0:
                    heittojaJaljella = 0;
                    break;

                default:
                    kayttoliittyma.piirraVirheSyotto();
                    break;
            }
        }
    }
}
