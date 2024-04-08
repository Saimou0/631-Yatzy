// Lisääjä/Tekijä: Simo
package Peli;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import Kayttoliittyma.Kayttoliittyma;
import Pelaaja.Pelaaja;

public class Yksinpeli {

    Kayttoliittyma kayttoliittyma;
    PelaajaSyotto pelaajaSyotto;
    Scanner lukija = new Scanner(System.in);
    
    private Pelaaja vastustaja;
    private Pelaaja pelaaja;

    private int heittojenMaraa = 3;
    private boolean ensimmainenHeitto = true;
    private int pelaajanNykyinenVuoro = 1;

    public LinkedHashMap<String, Integer> tilaLista = new LinkedHashMap<>();

    // Konstruktori
    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma, Pelaaja vastustaja) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
        this.vastustaja = vastustaja;
        this.pelaajaSyotto = new PelaajaSyotto(kayttoliittyma);
    }

    // Pelin jatkumisen looppi
    public void pelinLoop() {
        boolean peliJatkuu = true;
        while (peliJatkuu) {
            if (pelaaja.vuorojenMaara == 16) {
                // Laske pisteet yhteen ja tallena ne tiedostoon.
                // Jos pelaa jo valmista tiedostoa vastaan, vertaa pisteitä.
                peliJatkuu = false;
            }
            
            // Nollataan heitot ja nollataan ensimmäinen heitto kun pelaajalla alkaa uusi vuoro
            if(pelaajanNykyinenVuoro < pelaaja.vuorojenMaara) {
                pelaajanNykyinenVuoro = pelaaja.vuorojenMaara;
                ensimmainenHeitto = true;
                heittojenMaraa = 3;
                this.avaaKaikkiNopat();
            }

            // Lopetetaan peli jos pelin päivitys palauttaa 0
            if (pelinPaivitys() == 0) {
                peliJatkuu = false;
            }


        }
    }

    // Päivittää pelin ja käyttöliittymän
    public int pelinPaivitys() {
        kayttoliittyma.tyhjennaTerminaali();

        // Piirretään pelaajan pistekortti
        kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi());

        if (this.vastustaja != null) {
            kayttoliittyma.piirraPisteKortti(vastustaja.getPisteet(), vastustaja.getNimi());
        }

        // Piirretään pelaajan heittojen määrä
        System.out.println("Heittojen määrä: " + heittojenMaraa);

        // Piirretään nopat
        try {
            kayttoliittyma.piirraNopat(pelaaja.getNopat());
        } catch (Exception e) {
            System.out.println("Heitä nopat");
        }

        // Piirretään käyttöliittymä
        kayttoliittymanPiirtaminen();

        // Prosessoidaan pelaajan syöttö
        return pelaajaSyotto.prosessoiPelaajanSyotto(this, tilaLista);
    }

    // Nopat
    // Noppien heittäminen
    public void heitaNopat() {
        // Katsotaan onko pelaajalla heittoja jäljellä
        if(heittojenMaraa == 3 && ensimmainenHeitto == true) {
            ensimmainenHeitto = false;
        }

        if (heittojenMaraa > 0) {
            // Heitetään nopat ja vähennetään heittojen määrää
            pelaaja.heitaNopat();
            heittojenMaraa--;

        } else if (heittojenMaraa == 0) {
            kayttoliittyma.eiHeittoja();
        }
    }

    // Noppien lukitseminen
    public void noppienLukitseminen() {
        if (tilaLista.get("Lukitse") == 1) {
            boolean lukitseTila = true;
            while (lukitseTila) {
                kayttoliittyma.tyhjennaTerminaali();
                kayttoliittyma.piirraLukitseTila(pelaaja.getLukitutNopat());
                kayttoliittyma.piirraNopat(pelaaja.getNopat());
                pelaaja.getLukitutNopat();

                int pelaajanSyotto = pelaajaSyotto.pelaajanSyottoInt();
                if (pelaajanSyotto >= 1 && pelaajanSyotto <= 5) {
                    pelaaja.lukitseJaVapautaNoppia(pelaajanSyotto);
                } else if (pelaajanSyotto == -1) {
                    break;
                } else {
                    kayttoliittyma.piirraVirheSyotto();
                }
            }

        } else {
            kayttoliittyma.piirraVirheSyotto();
        }
    }

    // Avaa kaikkien noppien lukitukset
    private void avaaKaikkiNopat() {
        for (Map.Entry<Integer, Boolean> entry : pelaaja.getLukitutNopat().entrySet()) {
            if (entry.getValue() == true) {
                pelaaja.lukitseJaVapautaNoppia((entry.getKey()));
            }
        }
    }
    
    //Pisteet
    public void pelaajanPisteValinta() {
        kayttoliittyma.tyhjennaTerminaali();
        kayttoliittyma.piirraPisteKortti(pelaaja.getPisteet(), pelaaja.getNimi());
        kayttoliittyma.piirraPisteValinta();

        boolean pisteValintaTila = true;
        while (pisteValintaTila) {
            String kayttajanSyotto = lukija.nextLine();
            
            // Tarkistaa onko pelaaja jo valinnut pisteen, jos eivät niin lisätään pisteet
            // muistiin.
            switch (kayttajanSyotto) {
                case "-1":
                    pisteValintaTila = false;
                    break;

                case "Ykkoset":
                    if (pelaaja.getPisteet().get("Ykkoset") == -1) {
                        pelaaja.lisaaPisteet("Ykkoset", 1);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kakkoset":
                    if (pelaaja.getPisteet().get("Kakkoset") == -1) {
                        pelaaja.lisaaPisteet("Kakkoset", 2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kolmoset":
                    if (pelaaja.getPisteet().get("Kolmoset") == -1) {
                        pelaaja.lisaaPisteet("Kolmoset", 3);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Neloset":
                    if (pelaaja.getPisteet().get("Neloset") == -1) {
                        pelaaja.lisaaPisteet("Neloset", 4);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Viitoset":
                    if (pelaaja.getPisteet().get("Viitoset") == -1) {
                        pelaaja.lisaaPisteet("Viitoset", 5);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Kuutoset":
                    if (pelaaja.getPisteet().get("Kuutoset") == -1) {
                        pelaaja.lisaaPisteet("Kuutoset", 6);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                case "Pari":
                    if (pelaaja.getPisteet().get("Pari") == -1) {
                        pelaaja.lisaaPisteet("Pari", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Kaksi paria":
                    if (pelaaja.getPisteet().get("Kaksi paria") == -1) {
                        pelaaja.lisaaPisteet("Kaksi paria", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Kolme samaa":
                    if (pelaaja.getPisteet().get("Kolme samaa") == -1) {
                        pelaaja.lisaaPisteet("Kolme samaa", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Nelja samaa":
                    if (pelaaja.getPisteet().get("Nelja samaa") == -1) {
                        pelaaja.lisaaPisteet("Nelja samaa", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Pikku suora":
                    if (pelaaja.getPisteet().get("Pikku suora") == -1) {
                        pelaaja.lisaaPisteet("Pikku suora", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Iso suora":
                    if (pelaaja.getPisteet().get("Iso suora") == -1) {
                        pelaaja.lisaaPisteet("Iso suora", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Taysikasi":
                    if (pelaaja.getPisteet().get("Taysikasi") == -1) {
                        pelaaja.lisaaPisteet("Taysikasi", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Sattuma":
                    if (pelaaja.getPisteet().get("Sattuma") == -1) {
                        pelaaja.lisaaPisteet("Sattuma", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Yatzy":
                    if (pelaaja.getPisteet().get("Yatzy") == -1) {
                        pelaaja.lisaaPisteet("Yatzy", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;

                default:
                    kayttoliittyma.piirraVirheSyotto();
                    break;
            }
        }
        
    }

    // Käyttöliittymä
    // Toimii
    // Päätellään pelajaan vaihtoehdot ja piirretään ne käyttöliittymään.
    public void kayttoliittymanPiirtaminen() {
        // Lisätään pelaajan vaihtoehdot listaan.
        tilaLista.put("Heitä", 1);
        tilaLista.put("Lukitse", 1);
        tilaLista.put("Valitse Pisteet", 1);
        tilaLista.put("Lopeta Peli", 1);
        // tilaLista.put("Lopeta Peli", 1);

        // Jos pelaaja on pelannut 14 kertaa, poistetaan heitä ja lukitse vaihtoehdot
        // listasta.
        if (pelaaja.vuorojenMaara >= 14) {
            tilaLista.put("Heitä", 0);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 1);
        }

        // Jos pelaaja ei ole vielä heittänyt kertaakaan vuorolla, poistetaan lukitse ja valitse pisteet vaihtoehdot listasta.
        if (ensimmainenHeitto == true) {
            tilaLista.put("Heitä", 1);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 0);
        }

        // Jos pelaajalla on 0 heittoa, poistetaan heitä ja lukitse vaihtoehdot listasta.
        if (heittojenMaraa == 0) {
            tilaLista.put("Heitä", 0);
            tilaLista.put("Lukitse", 0);
            tilaLista.put("Valitse Pisteet", 1);
        }

        kayttoliittyma.piirraPelaajanVaihtoehdot(tilaLista);
    }

    public void pelinLopetus() {
        if(this.vastustaja != null) {
            Map<String, Integer> pelaajanPisteet = pelaaja.getPisteet();
            Map<String, Integer> vastustajanPisteet = vastustaja.getPisteet();
            
            Integer pelaajanArvo = pelaajanPisteet.get("Summa");
            Integer vastustajanArvo = vastustajanPisteet.get("Summa");

            if(pelaajanArvo == -1) {
                System.out.println("Pelaajalla ei ole summaa");
                return;
            }

            if(vastustajanArvo == -1) {
                System.out.println("Vastustajalla ei ole summaa");
            }

            if(pelaajanArvo > vastustajanArvo) {
                System.out.println("Voittaja on: " + pelaaja.getNimi());
            } else if (pelaajanArvo < vastustajanArvo) {
                System.out.println("Voittaja on: " + vastustaja.getNimi());
            } else {
                System.out.println("Tasapeli");
            }


        } 
    }

}