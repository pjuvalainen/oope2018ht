package oope2018ht.tiedostot;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;

/**
 * Kuva -luokka
 * Luokka kuvien mallintamista varten. 
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitustyö
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public class Kuva extends Tiedosto {
   /*
    * Atribuutit
    *
    */
    
   /** Kuvan leveys. */
   private int leveys;
   /** Kuvan korkeus. */
   private int korkeus;
   
   /*
    * Luokan rakentajat.
    *
    */
   
    /**
     * Kuvan rakentaja.
     * @param n kuvan nimi.
     * @param k kuvan koko.
     * @param x kuvan korkeus.
     * @param y kuvan leveys.
     * @throws IllegalArgumentException heitään poikkeus, jos ei onnistu.
     */
   public Kuva(String n, int k, int x, int y) throws IllegalArgumentException {
      super(n, k);
      if (x < 1 && y < 1){
         throw new IllegalArgumentException();
      }
      else {
         asetaLeveys(x);
         asetaKorkeus(y);
      }
   }
   
   /*
    * Aksessorit
    *
    */
   
   @Getteri
   public int lueLeveys () {
      return leveys;
   }
   
   @Setteri
   public void asetaLeveys(int l) throws IllegalArgumentException {
      if (l < 1) {
         throw new IllegalArgumentException();
      }
      else {
         leveys = l;
      }
   }
   
   @Getteri
   public int lueKorkeus() {
      return korkeus;
   }
   
   @Setteri
   public void asetaKorkeus(int k) throws IllegalArgumentException {
      if (k < 1) {
         throw new IllegalArgumentException();
      }
      else {
         korkeus = k;
      }
   }
   
   /*
    * 
    * Kuva - luokan metodit.
    */
    
   /** 
    * Kuvan tietojen vertaamista.
    * @param obj saatu olio jota verrataan.
    * 
    */
   public boolean equals(Object obj) {
      try {
         Kuva k = (Kuva)obj;
         return super.equals(obj) && (leveys == k.lueLeveys() && korkeus == k.lueKorkeus());
      }
      catch (Exception e) {
         return false;
      }
   }
   
   /**
    * Kuvan tulostaminen.
    * @return kuvan tiedot.
    */
   @Override
   public String toString() {
      return super.toString() + VALI + leveys + MERKKI + korkeus;
   }
}