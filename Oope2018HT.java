import oope2018ht.kayttoliittyma.*;

/* 
 * Harjoitustyön aiheena on simuloida olioperustaista keskustelualuetta (Simple Oope Board, S.O.B.).  
 * Keskustelualueelle on keskustelulankoja, joissa on viestejä.
 * Viesteihin voi liittää tiedostoja: kuvia tai videoita.  
 * Viestit voivat olla keskustelun avauksia, tai vastauksia edellisiin viesteihin.
 */

/**
 * Ajoluokka joka suorittaa ohjelman.
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitustyö
 * <p>
 * @author Petteri Juvalainen  - 427130
 * <p>
 * juvalainen.j.petteri@student.uta.fi
 */

public class Oope2018HT{

   /** 
    * @param args ohjelman ajoluokka.
    */
   public static void main(String[] args) {
      /* Luodaan käyttöliittymä-olio, joka vastaa ihmisen ja ohjelman vuorovaikutuksesta. */
      Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
      
      /** Kutsutaan käyttöliittmäolion metodia, joka suoritaa ohjelman pääsilmukkan.*/
      kayttoliittyma.suorita();
   }
}