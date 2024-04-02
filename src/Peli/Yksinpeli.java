// Lisääjä/Tekijä: Simo
package Peli;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import Kayttoliittyma.Kayttoliittyma;
import Pelaaja.Pelaaja;

public class Yksinpeli {

    Kayttoliittyma kayttoliittyma;
    Scanner lukija = new Scanner(System.in);
    private Pelaaja vastustaja;
    private Pelaaja pelaaja;

    private int heittojenMaraa = 3;
    private boolean ensimmainenHeitto = true;
    private int pelaajanNykyinenVuoro = 1;

    private static final int HEITA_TILA = 1;
    private static final int LUKITSE_TILA = 2;
    private static final int VALITSE_PISTEET_TILA = 3;
    private static final int TAKAISIN = -1;

    public LinkedHashMap<String, Integer> tilaLista = new LinkedHashMap<>();

    public Yksinpeli(Pelaaja pelaaja, Kayttoliittyma kayttoliittyma) {
        this.pelaaja = pelaaja;
        this.kayttoliittyma = kayttoliittyma;
    }

    // TODO: Pelaajan vuoro loppuu kun hän on valinnut pisteen, nollataan heitot ja lisätään vuorojen määrää.
    // TODO: Vertaa pelaajan pisteitä vastustajan pisteisiin ja tallenna voittaja tiedostoon.

    /*
     * TODO: Optimointia.
    */

    public void pelinLoop() {
        // Kysytään haluaaka pelaaja vastustajan.
        this.paatteleVastustaja();

        boolean peliJatkuu = true;
        while (peliJatkuu) {
            if (pelaaja.vuorojenMaara == 15) {
                // Laske pisteet yhteen ja tallena ne tiedostoon.
                // Jos pelaa jo valmista tiedostoa vastaan, vertaa pisteitä.
                peliJatkuu = false;
            }
            
            // Nollataan heitot ja nollataan ensimmäinen heitto kun pelaajalla alkaa uusi vuoro
            if(pelaajanNykyinenVuoro < pelaaja.vuorojenMaara) {
                pelaajanNykyinenVuoro = pelaaja.vuorojenMaara;
                ensimmainenHeitto = true;
                heittojenMaraa = 3;
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

        // Selitetään piirrettyjen pisteiden värit.
        kayttoliittyma.selitaVarit();

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
        this.kayttoliittymanPiirtaminen();

        // Prosessoidaan pelaajan syöttö
        return prosessoiPelaajanSyotto();
    }


    // Vastustaja
    // Toimii
    //TODO Tarkista, että pelaaja ei voi pelata uutta tiedostoa vastaan. Ja ei voi kirjoittaa stringiä
    public int paatteleVastustaja() {
        
        kayttoliittyma.piirraValitseVastustaja(pelaaja.getPisteTiedostot());
        LinkedHashMap<String, Integer> pisteTiedostoLista = pelaaja.getPisteTiedostot(); 
        int pelaajanSyotto = -2;

        boolean sopivaVastustaja = true;
        while (sopivaVastustaja) {
            pelaajanSyotto = pelaajanSyottoInt();
            if(pelaajanSyotto > pisteTiedostoLista.size()) {
                System.out.println("Vastusta ei löytynyt, valitse uudelleen");
            } else {
                sopivaVastustaja = false;
            }
        }

        if (pelaajanSyotto == 0) {
            return 0;
        }

        vastustaja = new Pelaaja("Vastustaja");

        for (Map.Entry<String, Integer> pisteTiedosto : pisteTiedostoLista.entrySet()) {
            if (pelaajanSyotto == Integer.parseInt(pisteTiedosto.getValue().toString())) {
                String kokoAvain = pisteTiedosto.getKey().toString();
                String avaimenNimi = kokoAvain.split("_")[0];
                if(avaimenNimi == pelaaja.getNimi()) {
                    System.out.println("Et voi pelata itseäsi vastaan");
                    return -1;
                }
                vastustaja.luePisteetTiedostosta(avaimenNimi);
            } else {
                System.out.println("Virhe vastustajan valinnassa");
            }
        }

        for (Map.Entry<String, Integer> pisteet : vastustaja.getPisteet().entrySet()) {
            System.out.println(pisteet.getKey() + ": " + pisteet.getValue());
        }

        return -1;
    }


    // Nopat
    // Toimii
    // Noppien heittäminen
    public void heitaNopat() {
        // Katsotaan onko pelaajalla heittoja jäljellä
        if (heittojenMaraa > 0) {
            // Tyhjennetään terminaali
            kayttoliittyma.tyhjennaTerminaali();

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

                int pelaajanSyotto = pelaajanSyottoInt();
                if (pelaajanSyotto >= 1 && pelaajanSyotto <= 5) {
                    pelaaja.lukitseJaVapautaNoppia(pelaajanSyotto);
                } else if (pelaajanSyotto == TAKAISIN) {
                    break;
                } else {
                    kayttoliittyma.piirraVirheSyotto();
                }
            }

        } else {
            kayttoliittyma.piirraVirheSyotto();
        }
    }

    
    //Pisteet
    // Ei toimi
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
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Kaksi paria":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Kolme samaa":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Nelja samaa":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Pikku suora":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                
                case "Iso suora":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Taysikasi":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Sattuma":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
                        pelaaja.vuorojenMaara++;
                        pisteValintaTila = false;
                    } else {
                        kayttoliittyma.pelaajaJoValinnutPisteet();
                    }
                    break;
                    
                case "Yatzy":
                    if (pelaaja.getPisteet().get("") == -1) {
                        pelaaja.lisaaPisteet("", -2);
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

    // Pelaajan syöttö
    // Toimii
    // Pelaajan syöttön tarkistaminen
    public int pelaajanSyottoInt() {
        try {
            String kayttajanSyotto = lukija.nextLine();
            int syotonNumero = Integer.parseInt(kayttajanSyotto);
            return syotonNumero;

        } catch (Exception e) {
            kayttoliittyma.piirraVirheSyotto();
            return -1;
        }
    }

    public int prosessoiPelaajanSyotto() {
        int pelaajanSyotto = pelaajanSyottoInt();
        switch (pelaajanSyotto) {
            // Jos pelaajan syötto on 1 niin heitetään nopat
            case HEITA_TILA:
                // Ensin tarkistetaan onko heittäminen tila listassa.
                if (tilaLista.get("Heitä") == 1) {
                    heitaNopat();
                }
                break;

            // Jos pelaajan syötto on 2 niin mennään noppien lukitsemis tilaan.
            case LUKITSE_TILA:
                if (tilaLista.get("Lukitse") == 1) {
                    noppienLukitseminen();
                }
                break;

            // Jos pelaajan syötto on 3 niin mennään valitsemaan pisteet
            case VALITSE_PISTEET_TILA:
                if (tilaLista.get("Valitse Pisteet") == 1) {
                    pelaajanPisteValinta();
                }
                break;

            // Jos pelaajan syöttö on 0 niin lopetetaan peli
            case 0:
                return 0;
            default:
                break;
        }
        return-1;
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
            ensimmainenHeitto = false;
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