// Lisääjä/Tekijä: Jimi
package Nopat;

import java.util.Random;
import java.util.Scanner;

public class Nopat {

    public static void main(String[] args) {
        int[] nopat = new int[5];
        boolean[] lukitut = new boolean[5];
        int heittoja = 3;

        Scanner lukija = new Scanner(System.in);

        while (heittoja > 0) {
            
            heitaNopat(nopat, lukitut);
            
            tulostaNopat(nopat, lukitut);

            heittoja--;

            if (heittoja > 0) {
                System.out.println("Heittoja jäljellä: " + heittoja);
                System.out.print("Lukitse nopat (esim. 1 3 5): ");

                
                lukitseNopat(nopat, lukitut, lukija.nextLine());
            }
        }

        int pisteet = laskePisteet(nopat);
        System.out.println("Pisteet: " + pisteet);

        lukija.close();
    }

    private static int laskePisteet(int[] nopat) {
        return 0;
    }

    
    public static void heitaNopat(int[] nopat, boolean[] lukitut) {
        Random random = new Random();

        for (int i = 0; i < nopat.length; i++) {
            if (!lukitut[i]) {
                nopat[i] = random.nextInt(6) + 1; 
            }
        }
    }

   
    public static void tulostaNopat(int[] nopat, boolean[] lukitut) {
        System.out.print("Nopat: ");
        for (int i = 0; i < nopat.length; i++) {
            if (lukitut[i]) {
                System.out.print("[" + nopat[i] + "] ");
            } else {
                System.out.print(nopat[i] + " ");
            }
        }
        System.out.println();
    }

   
    public static void lukitseNopat(int[] nopat, boolean[] lukitut, String lukitse) {
        String[] lukitseLista = lukitse.split(" ");
        for (String s : lukitseLista) {
            int indeksi = Integer.parseInt(s) - 1;
            lukitut[indeksi] = true;
        }
    }
}
