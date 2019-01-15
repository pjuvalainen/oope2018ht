package oope2018ht.omalista;

import oope2018ht.apulaiset.*;
import fi.uta.csjola.oope.lista.*;

/**
 * OmaLista -luokka
 * Operaatiot listan läpi käymiseksi.
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitustyö
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
@SuppressWarnings("unchecked")
public class OmaLista extends LinkitettyLista implements Ooperoiva {
   
   /**
    * Käydään lista läpi ja palautetaan viite haettuun tietoalkiion.
    * @param haettava olio jota haetaan.
    * @return viite alkioon.
    */
   public Object hae(Object haettava) {
      // Tarkistetaan että lista ei ole tyhjä,
      //   tai parametrinä saatu arvo ei ole null.
      if ( haettava != null && koko > 0 ) {
         
         // Luodaan olio viitteen säilömista varten.
         Object jemma = null;
         
         // Silmukka jossa käydään arvot läpi.
         for (int ind = 0; ind < koko; ind++) {

            if (alkio(ind).equals(haettava)) {
               // Täytetään, jos ei ole löydetty aikaisemmin.
               if (jemma == null) {
                  // Palautetaan löydetty olio.
                  jemma = alkio(ind);
               }
            }
         }
         // Palautetaan tyhjä olio.
         return jemma;
      }
      // Palautetaan tyhjä olio.
      else 
         return null;
   }
   
   /**
    * Lisätään alkiot listalle suuruusjärjestyksessä.
    * @param haettava alkio jonka kokoa verrataan.
    * @return boolean onnistuiko vaihtaminen.
    */
   public boolean lisaa(Object haettava) {  
      // Tarkistetaan ettei parametri ole null arvoinen.
      //  Muutoin palautetaan false arvo.
      if (haettava != null && haettava instanceof Comparable) {
         // Jos lista on tyhjä, asetetaan saatu parametri ensimmäiseksi.
         // Muulloin käydään lista läpi.
         if (koko == 0) {
            lisaaAlkuun(haettava);
            // Vaihto onnistui.
            return true;
         } 
         else {
            // Verrataan löytyykö alkiosta pienempää arvoa.
            for (int i = 0; i < koko(); i++) {
               if (((Comparable)haettava).compareTo(alkio(i)) < 0) {
                  lisaa(i, haettava);
                  // Vaihto onnistui.
                  return true;
               }
            }
         // Parametri lisätään loppuun, jos ei löytynyt pienempää alkiota.
         lisaaLoppuun(haettava);
         // Vaihto onnistui.
         return true;
         }
      }
      // Palautetaan false arvo.
      else
         return false;
   }
   
   /**
    * Haetaan alkiot listalta annetun parametrin mukaan.
    * @param n parametri jonka mukaan haetaan listan ensimmäiset alkiot.
    * @return lista alkioista.
    */
   public OmaLista annaAlku(int n) {
      // Tarkistetaan että koko suurempi kuin annettu parametri.
      // Muutoin palautetaan Null-arvo.
      if (n != 0 && n <= koko) {
         
         // Alustetaan lista.
         OmaLista lista = new OmaLista();
         
         // Silmukka jossa täytetään lista.         
         for (int i = 0; i < n; i++) {
            lista.lisaaLoppuun(alkio(i));
         }
         // Palautetaan lista.         
         return lista; 
      }
      // Palautetaan null arvo.
      else 
         return null;
   }
   
   /**
    * Haetaan alkiot listalta annetun parametrin mukaan.
    * @param n parametri jonka mukaan haetaan listan viimeiset alkiot.
    * @return lista alkioista.
    */
   public OmaLista annaLoppu(int n) {
      // Tarkistetaan että koko suurempi kuin annettu parametri.
      //   Muutoin palautetaan Null-arvo.
      if (n != 0 && n <= koko) {
         
         // Alustetaan lista.
         OmaLista lista = new OmaLista();
         
         // Atribuutti silmukointia varten.
         int j = koko - n;
         
         // Silmukka jossa täytetään lista.
         for (int i = 0; i < n; i++) {
            lista.lisaaLoppuun(alkio(j));
            j++;
         }
         // Palautetaan lista.
         return lista;
      }
      // Palautetaan null arvo.
      else 
         return null;
   }
}
