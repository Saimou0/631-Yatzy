import java.util.Scanner;
import Kayttoliittyma.Kayttoliittyma;
import Pistekirjaus.Pistekirjaus;

public class Menunlogiikka {
    private Kayttoliittyma kl;
    
    Scanner lukija = new Scanner(System.in);
    
    
    public void pelinAloitus(Kayttoliittyma kayttoliittyma, Pistekirjaus pistekirjaus) {
        this.kl = kayttoliittyma;
        kl.piirtamisenAloitus(pistekirjaus);
        menu();
    }

    public void menu() {
        clearScreen();

        kl.piirraMenu();

        String syotto = lukija.nextLine();

        if(Integer.valueOf(syotto) != 0) {
            kl.piirraPeliMuodot();
            
            if(Integer.valueOf(syotto) == 0) {
                // Mene yksinpeliin
                kl.piirraUusiPeli();
            } else {
                // Mene moninpeliin
                kl.piirraUusiPeli();
            }

        } else {
            System.out.println("----Nähdään pian!----");
        }
    }
    
    // Tyhjentää terminaalin.
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }  
}
