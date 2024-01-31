// Tekijä: Simo
package Kayttoliittyma;

public class Kayttoliittyma {

    public void piirraVirheSyotto() {
        System.out.println("----Virheellinen syöttö yritä uudestaan.----");
    }

    public void piirraJaahyvaiset() {
        System.out.println("----Nähdään pian!----");
    }

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

    public void piirraUusiPeli() {
        System.out.println("----Uusi peli.----");
    }

    public void piirraPelinLoppuminen() {
        System.out.println("---- Peli loppui.----");
    }

    // Muista lisätä indeksi
    public void piirraPelaajanNimi(String pelaaja) {
        System.out.println("----Pelaajan " + "(lisää muuttuja pelaajan indeksiin)" + " nimi.----");
    }




}