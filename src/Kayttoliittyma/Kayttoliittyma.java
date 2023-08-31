// Tekijä: Simo
package Kayttoliittyma;

import Pistekirjaus.Pistekirjaus;

public class Kayttoliittyma {
    private Pistekirjaus pk;

    // Constructori private pistekirjaukselle
    public void piirtamisenAloitus(Pistekirjaus pistekirjaus) {
        this.pk = pistekirjaus;
    }

    public void piirraVirheSyotto() {
        System.out.println("----Virheellinen syöttö yritä uudestaan.----");
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
            |     0 -> Yksinpeli      |
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

    public void piirraKysyPelaajanNimi() {
        System.out.println("----Pelaajan " + "(lisää muuttuja pelaajan indeksiin)" + " nimi.----");
    }

    public void piirraPelaajanPisteKortti() {
        // Tee Pelaaja lista 
        // Listataan pelaajan nimi, ja pelaajan nimen alle heidän piste korttinsa. Tämä tehdään jokaiselle pelaajalle.
        for(int i = 0; i < pl.getLength; i++) {
            // System.out.println("Pelaaja: " + pl.getPelaaja(i));
            
            // for(Pistekirjaus pistekirjaus : pk.getPisteet) {
    
            // }
        }

    }



}