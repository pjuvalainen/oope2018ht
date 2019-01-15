package oope2018ht.viestit;

import oope2018ht.apulaiset.*;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.*;

/**
 * Viesti -luokka
 * Yksittäisten viestiin liittyvien tietojen hallinnointi.
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public class Viesti implements Comparable<Viesti>, Komennettava<Viesti> {
   
   /*
    * 
    * Atribuutit
    */
   
   /** Tulostamista varten aiheen tunnisteen merkki */
   public final String HASTAG = "#";
   /** Erotin hyödynnettäväski tulosteissa. */
   public final String VALI = " ";
   
   /** Viestin tunniste. */
   private int tunniste;
   
   /** Viestin teksti. */
   private String teksti;
   
   /** Viestin viite toiseen viestiin. */
   private Viesti vastattu;
   
   /** Vastaukset viestiin. */
   private OmaLista vastaukset;
   
   /** Viestin liitetyt tiedostot.  */
   private Tiedosto liite;
   
   /*
    * Rakentajat
    *
    */
   
   /**  
    * Luodaan uusi viesti olio.
    * @param n viestin tunnistenumero.
    * @param t viestin teksi.
    * @param v viitattu viesti.
    * @param l liitetty tiedosto.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public Viesti(int n, String t, Viesti v, Tiedosto l)
   throws IllegalArgumentException {
      // Tarkistetaan parametrit.
      if (n < 0 && t == null) {
         throw new IllegalArgumentException();
      }
      // Hyödynnetään aksessoreita.
      else {
         asetaTunniste(n);
         asetaTeksti(t);
         asetaVastattu(v);
         asetaLiite(l);
         /** Luodaan vastauksille lista. */
         vastaukset = new OmaLista();
         // Listään viestille vastaus.
         if (v != null) {
            v.lisaaVastaus(this);
         }
      }
   }   
   
   /*
    * Aksessorit
    *
    */
   
   @Getteri
   public int lueTunniste() {
      return tunniste;
   }
   
   @Setteri
   public void asetaTunniste(int t) throws IllegalArgumentException {
      if (t <= 0) {
         throw new IllegalArgumentException();
      }
      else {
         tunniste = t;
      }
   }
   
   @Getteri
   public String lueTeksti() {
      return teksti;
   }

   @Setteri
   public void asetaTeksti(String t) throws IllegalArgumentException {
      if (t == null || t.length() < 1) {
         throw new IllegalArgumentException();
      }
      else {
         teksti = t;
      }
   }

   @Getteri
   public Viesti lueVastattu() {
      return vastattu;
   }

   @Setteri
   public void asetaVastattu (Viesti v) {
      vastattu = v;
   }

   @Getteri
   public OmaLista lueVastaukset() {
      return vastaukset;
   }
   
   @Setteri
   public void asetaVastaukset(OmaLista o) throws IllegalArgumentException{
      if (o == null) {
         throw new IllegalArgumentException();
      }
      else {
         vastaukset = o;
      }
   }
   
   @Getteri
   public Tiedosto lueLiite() {
      return liite;
   }
   
   @Setteri
   public void asetaLiite (Tiedosto t) {
      liite = t;
   }

   /*
    * Metodit.
    *
    */
   
   /**
    * 
    * @param t verrataan viestien tunnisteita keskenään.
    * @return paluuarvona tieto kumpi isompi.
    */
   @Override
   public int compareTo (Viesti t) {
      // Viestin tunniste on pienempi.
      if (tunniste < t.lueTunniste()) {
         return -1;
      }
      // Viestin tunniste samat.
      else if (tunniste == t.lueTunniste()) {
         return 0;
      }
      // Viestin tunniste suurempi.
      else {
         return 1;
      }
   }   
   
   /**
    * 
    * @param t verrataan viestien tunnisteita keskenään.
    * @return paluuarvona viesti, josa samat.
    */
   @Override
   public boolean equals(Object t) {
      // Yritetään verrata tiedostoja ja palautetaan tunniste, jos vastaavuus löyty. 
      try {
         Viesti kopio = (Viesti)t;
         return tunniste == kopio.tunniste;
      }
      // Vastaavuutta ei löytynyt.
      catch (Exception e) {
         return false;
      }
   }
   
   /**
    * Tulostetaan viesti.
    * @return viestin tiedot.
    */
   @Override
   public String toString() {
      // Tarkistetaan löytyykö liitettä, jos löytyy, tulostetaan sen tiedot.
      if (liite != null) {
         return HASTAG + tunniste + VALI + teksti + " ("+ lueLiite()+")";
      }
      else {
         return HASTAG + tunniste + VALI + teksti;
      }
   }
   
   /* 
    *
    * Komennettava rajapinnan metodit. 
    */
   
   /** 
    * Tyhjennetään viestin teksti ja liite. 
    */
   @Override
   public void tyhjenna() {
      asetaTeksti(POISTETTUTEKSTI);
      asetaLiite(null);
      
   }
   
   /** 
    * Etsitään tyhjennettävä viesti. 
    * @param a parametri, jonka mukaan poistetaan.
    */
   void poista(int a) {      
      Viesti poistettava = (Viesti) vastaukset.alkio(a - 1);      
      poistettava.tyhjenna();
   }
   
   /**
    * Lisätään viesti vastauksiin.
    * @param lisattava on viesti listalle.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   @Override
   public void lisaaVastaus(Viesti lisattava) throws IllegalArgumentException {
      // Tehdään kopio-olio ja haetaan sille vastaavuus listalta.
      Object onko = vastaukset.hae(lisattava);
      
      // Tarkistetaan parametri.
      if (lisattava == null || onko != null) {                  
         throw new IllegalArgumentException();
      }
      // Operaatio onnistuu.
      else {         
         vastaukset.lisaaLoppuun(lisattava);
      }
   }
   
   /**
    * Haetaan viestiä
    * @param haettava on viesti, jota haetaan.
    * @return viesti, jota haetaan.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   @Override
   public Viesti hae(Viesti haettava) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (haettava == null) {
         throw new IllegalArgumentException();
      }
      // Operaatio onnistu.
      else {
         // Haetaan listalta viestin vastaavuus.
         Viesti tama = (Viesti)vastaukset.hae(haettava);
         // Palautetaan viite olioon.
         return tama;
      }
   }
}
