package oope2018ht.tiedostot;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;
  
/**
 * Tiedosto -luokka
 * Abstrakti tiedosto luokka tiedoston tietoja varten.
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public abstract class Tiedosto implements Comparable<Tiedosto> {
   
   /*
    * Atribuutit
    *
    */
    
   /** Erotin hyödynnettäväski tulosteissa. */
   protected final String VALI = " ";
   /** Merkki kuvan tulosteisiin. */
   protected final String MERKKI = "x";
   /** Merkki tulosteisiin, videonpituus. */
   protected final String VIDEONPITUUS = "s";
   /** Merkki tulosteisiin, tiedoston koko. */
   protected final String TIEDOSTONKOKO = "B";
   
   /** Kätketty atribuutti tiedoston nimelle. */
   private String nimi;
   /** Kätketty atribuutti tiedoston koolle. */
   private int koko;
   
   /*
    * Luokan rakentajat.
    *
    */
 
   /**
    * Tiedoston muodostaminen.
    * @param n tiedoston nimi.
    * @param k tiedoston kokok.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public Tiedosto(String n, int k) throws IllegalArgumentException {
      if (n == null || n.length() < 1 || k < 1) {
         throw new IllegalArgumentException();
      }
      else {
         asetaNimi(n);
         asetaKoko(k);
      }
   }
   
   /*
    * Aksessorit
    *
    */
    
   @Getteri
   public String lueNimi() {
      return nimi;
   }
   
   @Setteri 
   public void asetaNimi(String n) throws IllegalArgumentException {
      if (n == null || n.length() < 1 ) {
         throw new IllegalArgumentException();
      }
      else {
         nimi = n;
      }
   }
   
   @Getteri 
   public int lueKoko() {
      return koko;
   }
   
   @Setteri 
   public void asetaKoko(int k) throws IllegalArgumentException {
      if (k < 1) {
         throw new IllegalArgumentException();
      }
      else {
         koko = k;
      }
   }
   
   String luokanNimi = getClass().getSimpleName();
   
   /*
    * Tiedosto - luokan metodit.
    *
    */
   
   /** 
    * Metodi, jossa verrataan tiedostoja keskeneään.
    * @param t olio, jota verrataan.
    */
   @Override
   public boolean equals(Object t) {
      try {
         Tiedosto k = (Tiedosto)t;
         return (nimi.equals(k.lueNimi())) && (koko == k.lueKoko());
      }
      catch (Exception e) {
         return false;
      }
   }
   
   /** 
    * Verrataan tiedoston kokoja keskenään.
    * @param t tiedosto, jota verrataan.
    */
   public int compareTo (Tiedosto t) {
      // Koko on pienempi.
      if (koko < t.lueKoko()) {
         return -1;
      }
      // Samankokoiset.
      else if (koko == t.lueKoko()) {
         return 0;
      }
      // Koko on suurempi.
      else {
         return 1;
      }
   }
    
   /**
    * Tulostetaan tiedosto.
    * @return tiedoston tiedot.
    */
   @Override
   public String toString() {
      return nimi + VALI + koko + VALI + TIEDOSTONKOKO;
   }
}