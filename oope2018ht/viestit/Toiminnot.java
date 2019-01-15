package oope2018ht.viestit;

import oope2018ht.omalista.OmaLista;
import oope2018ht.*;
import java.io.*;
import java.util.*;
import oope2018ht.tiedostot.*;

/**
 * Toiminnot -luokka
 * joka vastaa yleisistä metodeista.
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitustyö
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public interface Toiminnot<T> {   
   
   /* Luokan julkiset vakiot */
   
   /** Käyttäjän tervehdys teksti. */
   public static final String MOROTUS = "Welcome to S.O.B.";
   /** Erotin tulosteisiin. */
   public static final String VALI = " ";
   /** Virheteksti. */
   public static final String VIRHE = "Error!";
   /** Käyttäjän hyvästeleminen. */
   public static final String LOPETUS = "Bye! See you soon.";
   /** Merkki, joka tulostetaan käyttäjälle syötteen odottamiseksi. */   
   public static final String KOMENTOKEHOTTEENMERKKI = ">";
      
   /** 
    * OmaListan tulostaminen.
    * @param lista parametrinä saatu arvo.
    */
   public static void tulostaLista(OmaLista lista) {
      // Syötteen tarkistus.
      if (lista != null) {
         // Tulostetaan silmukalla.
         for (int ind = 0; ind < lista.koko(); ind++) {
            System.out.println(lista.alkio(ind));
         }
      }
   }
   
   /** 
    * Käyttäjän käskyn lukeminen syötteestä. Palautetaan tyhjä, jos ei onnistu.
    * @param syote parametrina saatu arvo.
    * @return syötteestä saatu käsky.
    */
   public static String lueKasky(String syote) {
      
      // Syötteen tarkistus.
      if (!"".equals(syote)) {
         // Pilkotaan syöte taulukkoon.
         String [] taulu = syote.split(" ", 2);
         // Luetaan käsky, joka palautetaan.
         String kasky = taulu[0];
         return kasky;
      }
      // Kohdattin virhe.
      else
         return "";
   }
   
   /** 
    * Luetaan käyttäjän syötteestä numero. Palautetaan 0, jos ei onnistu.
    * @param k parametrina saatu arvo.
    * @return saatu tunniste numero.
    */
   public static int haeTunniste(String k) {
      
      // Syötteen tarkistus.
      if (!"".equals(k)) {
         // Pilkotaan syöte taulukkoon.
         String[] suodatus = k.split("[ ]");
         
         // Tarkistetaan että aulukkon on riittävän pitkä.
         if (1 < suodatus.length) {
            // Hetaan luku, joka palautetaan.
            int tunniste = Toiminnot.haeLuku(suodatus);
            return tunniste;
         }
         // Kohdattin virhe.
         else 
            return 0;
      }
      // Kohdattin virhe.
      else 
         return 0;
   }
   
   /** 
    * Luetaan käyttäjän syötteestä numero. Palautetaan 0, jos ei onnistu.
    * @param syote parametrina saatu arvo.
    * @return luettu numero.
    */   
   public static int haeAnnettuLuku(String syote) {
      
      // Syötteen tarkistus.
      if (!"".equals(syote)) {
         // Luetaan syöte taulukkoon.
         String[] suodatus = syote.split("[ ]");
         
         // Luetaan taulukon pituus.
         int pituus = suodatus.length;
         
         // Tarkistetaan että aulukon pituus riittävä.
         if (pituus == 2) {
            // haetaan luku, joja palautetaan.
            int tunniste = Toiminnot.haeLuku(suodatus);
            return tunniste;
         }
         // Kohdattin virhe.
         else 
            return 0;
      }
      // Kohdattin virhe.
      else 
         return 0;
   }
   
   /** 
    * Muunnetaan annettu teksti luvuksi. Palautetaan 0, jos ei onnistu.
    * @param a parametrina saatu arvo.
    * @return saatu luku.
    */
   public static int haeLuku(String[] a) {
      
      // Syötteen tarkistus.
      if (a != null ) {
         String lukuSana = a[1];
         // Yritetään luvun lukemista.
         try {
            int luku = Integer.parseInt(lukuSana);
            return luku;
         }
         // Luvun lukeminen ei onnistutnut.
         catch (NumberFormatException e) {
            return 0;
         }
      }
      // Kohdattin virhe.
      else
         return 0;
   }
   
   /** 
    * Muunnetaan käyttään syöte taulukoksi. Palautetaan null, jos ei onnistu.
    * @param t parametrina saatu arvo.
    * @param n parametrina saatu arvo.
    * @return saatu aihe.
    */
   public static String haeViestinTeksti(String t, int n) {
      
      // Syötteen tarkistus.
      if (!"".equals(t) && 0 < n ) {
         // Pilkotaan syöte.
         String[] suodatus = t.split("[ ]");
         
         // Taulukon tarkistus.
         if (1 < suodatus.length) {
            // Haetaan aihe, joka palautetaan.
            String aihe = Toiminnot.haeTeksti(suodatus, n);
            return aihe;
         }
         // Kohdattiin virhe.
         else 
            return null;
      }
      // Kohdattiin virhe.
      else 
         return null;
   }
   
   /** 
    * Muunnetaan taulukko tekstiksi. Palautetaan null, jos ei onnistu.
    * @param t parametrina saatu arvo.
    * @param i parametrina saatu arvo.
    * @return saatu teksti.
    */
   public static String haeTeksti(String[] t, int i) {
      
      // Syötteen tarkistus.
      if (t != null && 0 < i ) {
         String teksti = null;
         // Luetaan silmukassa.
         for (int ind = i; ind < t.length; ind++) {
            if (ind == i) {
               teksti = t[ind];
            }
            
            /* Kömpelö tarkistus, jolla katsotaan että käyttäjän antama tiedosto on luettava.
             * Mikäli tiedostoa ei voi lukea, palauttaan null arvo.
             */
            else if (t[ind].startsWith("&")) {
               try {
                  String tiedostoNimi = t[ind];
                  tiedostoNimi = tiedostoNimi.substring(1);
                  File tiedosto = new File(tiedostoNimi);
                  Scanner lukija = new Scanner(tiedosto);
               }
               catch (FileNotFoundException e){
                   return null;
               } 
            }
            // Muuten lisätään tekstin perään tekstiä.
            else 
               teksti = teksti + VALI + t[ind];
         }
         // Palautetaan teksti.
         return teksti;
      }
      // Kohdattiin virhe ja palutetaan null arvo.
      return null;
   }
   
   /** Käyttäjälle virheen tulostaminen. */
   public static void virhe() {
      System.out.println(VIRHE);
   }
   
   /** Käyttäjän tervehtiminen. */
   public static void moro() {
      System.out.println(MOROTUS);
   }
   
   /** Käyttäjälle merkin tulostaminen syötteen antamista varten. */
   public static void aloitus() {
      System.out.print(KOMENTOKEHOTTEENMERKKI);
   }
   
   /** Käyttäjälle lopetustekstin tulostaminen. */
   public static void lopetus() {
      System.out.println(LOPETUS);
   }
   
   /** 
    * Ohjelman testausta varten, jolla voidaan tarkistaa saatuja syötteitä.
    * @param t parametrinä saatu arvo.
    * @param <T> geneerinen tyyppimuunnos.
    */
   public static <T> void ping(T t) {
      System.out.println(t);
   }
   
   /** 
    * Luetaan käyttäjän syötteestä liite tiedosto.  Palautetaan null, jos ei onnistu. 
    * @param syote, josta luetaan liite.
    * @return palautetaan liite.
    */
   public static Tiedosto lueLiite(String syote) {
      
      // Syötteen tarkistus.
      if (!"".equals(syote)) {
         
         // Operaation atribuutit.
         String tiedostoNimi = "";      
         boolean loytyy = false;      
         String[] suodatus = syote.split("[ ]");

         // Luetaan käyttäjän antama syöte silmukassa.
         for (int ind = 0; ind < suodatus.length; ind++) {
            // Taristetaam löytyykö tiedostoa.
            if (suodatus[ind].startsWith("&")) {
               tiedostoNimi = suodatus[ind];
               tiedostoNimi = tiedostoNimi.substring(1);
               
               // Luettava tiedosto löytyy.
               loytyy = true;
            }
         }

         // Jos luettava tiedosto löytyi, aletaan lukea sitä.
         if (loytyy != false) {
            // Yritetään tiedoston lukua.
            try {
               // Luetaan tiedosto.
               File tiedosto = new File(tiedostoNimi);
               Scanner lukija = new Scanner(tiedosto);
               String rivi = lukija.nextLine();
               // Pilkotaan tiedoston tiedot taulukkoon.
               String[] osat = rivi.split("[ ]");

               // Tarkistetaan onko kuva.
               if (osat[0].equals("Kuva") || osat[0].equals("kuva") ) { 
                  // Haetaan tiedot taulukosta. 
                  int koko = Integer.parseInt(osat[1]);
                  int x = Integer.parseInt(osat[2]);
                  int y = Integer.parseInt(osat[3]);
                  
                  // Luodaan kuva -olio, joka palautetaan.
                  Kuva uusiKuva = new Kuva(tiedostoNimi, koko, x, y);
                  return uusiKuva;
               }
               // Tarkistetaan onko video.
               else if (osat[0].equals("Video") || osat[0].equals("video")){
                  // Haetaan tiedot taulukosta. 
                  int koko = Integer.parseInt(osat[1]);               
                  double pituus = Double.parseDouble(osat[2]);
                  
                  // Luodaan video -olio, joka palautetaan.
                  Video uusiVideo = new Video(tiedostoNimi, koko, pituus);
                  return uusiVideo;
               }
               // Tiedoston luku ei onnistunut.
               else
                  return null;
            }
            // Kohdattiin virhe.
            catch (FileNotFoundException | IllegalArgumentException e) {
               return null;
            }

         }
         else 
            return null;   
      }
      else 
         return null; 
   }
}