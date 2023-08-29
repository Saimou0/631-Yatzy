import Kayttoliittyma.Kayttoliittyma;

public class Pelinlogiikka {
    private Kayttoliittyma kl;

    public void logiikkaAloitus(Kayttoliittyma kayttoliittyma) {
        this.kl = kayttoliittyma;

        kl.piirraMenu();
    }
}