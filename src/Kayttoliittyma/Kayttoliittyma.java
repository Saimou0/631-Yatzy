package Kayttoliittyma;
public class Kayttoliittyma {

    // Simo
    public void piirraVirheSyotto() {
        System.out.println("Virheellinen syöttö yritä uudestaan.");
    }

    public void piirraMenu() {
        System.out.println(
            """
            ------------------------
                    MENU
                    
                0 -> Lopeta
                1 -> Aloita Peli

            ------------------------
            """
        );
    }

    public void piirraUusiPeli() {
        System.out.println("Uusi peli");
    }

    



}