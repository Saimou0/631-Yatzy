package Peli;

import Kayttoliittyma.Kayttoliittyma;
import Kayttoliittyma.Menunlogiikka;

public class Yatzy {


    public static void main(String[] args) throws Exception {

        Kayttoliittyma kayttoliityma = new Kayttoliittyma();
        Menunlogiikka menunLogiikka = new Menunlogiikka();

        menunLogiikka.pelinAloitus(kayttoliityma);
    }

}
