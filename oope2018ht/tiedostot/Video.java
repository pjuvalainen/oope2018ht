package oope2018ht.tiedostot;

import oope2018ht.apulaiset.Getteri;
import oope2018ht.apulaiset.Setteri;

/**
 * Video -luokka
 * Luokka videoiden mallintamista varten.
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitusty�
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public class Video extends Tiedosto {   
   /*
    * Atribuutit
    *
    */
    
   /** Videon pituus minuutteina. */
   private double pituus;
    
   /*
    * Luokan rakentajat.
    *
    */
    
   /**
    * Luodaan video olio.
    * @param n viden nimi.
    * @param k videon koko.
    * @param p videon pituus.
    * @throws IllegalArgumentException heitään poikkeus, jos ei onnistu.
    */
   public Video(String n, int k, double p) {
        super(n, k);
        if (p < 1) {
         throw new IllegalArgumentException();
      }
      else {
        asetaPituus(p);
      }
   }
    
   /*
    * Aksessorit
    *
    */
    
   @Getteri
   public double luePituus() {
        return pituus;
   }
    
   @Setteri
   public void asetaPituus(double p) throws IllegalArgumentException {
      if (p < 1) {
         throw new IllegalArgumentException();
      }
      else {
         pituus = p;
      }
    }
    
   /*
    * Video luokan metodit.
    *
    */
   
   /**
    * 
    * @param obj olio jota verrataan.
    * @return paluuarvona tosi, josa samat.
    */
   @Override
   public boolean equals(Object obj) {
      try {
         Video k = (Video)obj;
         return super.equals(obj) && (pituus == k.luePituus());
      }
      catch (Exception e) {
         return false;
      }
   }
   
   /** 
    * Tulostetaan videon tiedot.
    * @return videon tiedot.
    */
   @Override
   public String toString() {
      return super.toString() + VALI + pituus + VALI + VIDEONPITUUS;
   }
}
