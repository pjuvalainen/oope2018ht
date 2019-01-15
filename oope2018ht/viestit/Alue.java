package oope2018ht.viestit;

import fi.uta.csjola.oope.lista.*;
import oope2018ht.omalista.OmaLista;
import oope2018ht.tiedostot.Tiedosto;

/** 
 * Alue -luokka
 * joka vastaa viestejä hallinnoivasta alueesta.
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */
public class Alue extends LinkitettyLista {
   /*
    * 
    * Luokan atribuutit
    */
      
   /** Erotin tulostamista varten. */
   final String VALI = " ";
   /** Tulostamista varten aiheen tunnisteen merkki. */
   final char AIHETUNNISTE = '#';
   /** Tulostamista varten merkki. */
   final char MERKKI = '=';
   /** Tulostamista varten toinen merkki. */
   final String MERKKI2 = "== ";
   /** Tulostamista varten kolmas merkki. */
   final String MERKKI3 = "===";
   
   /** Minimiluku metodeissta käytettäväksi.  */
   public static final int MIN = 1;
   /** Maksimiluku metodeissta käytettäväksi. */
   public static final int MAX = 2;
   
   /** Kätketty omalista aiheille. */
   OmaLista alueenKetjut;
   
   /** Viestien juokseva numerointi.*/
   int viestinNro = 0;
      
   /** Aktiivinen ketju. */
   Ketju aktiivinen;
      
   /**
    * Luokan rakentaja.
    */    
   public Alue() {
      alueenKetjut = new OmaLista();
   }
         
   /*
    *
    * Metodit
    */
   
   /** 
    * Käyttäjän antama Add-kommennon metodi.
    * 
    * Metodi luo alueelle uuden aiheen.
    * @param syote kayttajan antama syote.
    */
   public void lisaaAihe(String syote) {
      if (!"".equals(syote)) {
         String aihe = Toiminnot.haeViestinTeksti(syote, MIN);

         // Tarkistetaan saatu parametri.
         if (aihe != null) {
            // Luodaan uusi ketju, johon viestejä tallennetaan.
            Ketju uusiKetju = new Ketju(alueenKetjut.koko() + 1, aihe);
            // Lisätään ketjuen listaan.
            alueenKetjut.lisaaLoppuun(uusiKetju);
            // Tehdään uudesta ketjusta aktiivinen ketju.
            aktiivinen = uusiKetju;
         }
         // Aiheen lisääminen ei onnistu.
         else 
            Toiminnot.virhe();
      }
               // Aiheen lisääminen ei onnistu.
      else 
         Toiminnot.virhe();
   }
      
   /** 
    * Käyttäjän antama New-kommennon metodi.
    * 
    * Metodi lisää alueeseen uuden viestin.
    * @param syote kayttajan antama syote.
    * @param liite kayttajan antama liite.
    */
   public void lisaaViesti(String syote, Tiedosto liite) {
      
      // Tarkistetaan saatu parametri.
      if (0 < alueenKetjut.koko()) {
         // Kutsutaan aliohjelmaa.
         String teksti = Toiminnot.haeViestinTeksti(syote, MIN);
        
         // Tarkistetaan saatu parametri.
         if (teksti != null) {
            // Lisätään viestien juoksevaa numerointia.
            viestinNro++;
            // Asetaan vastaukseen null-arvo, koska viesti ei ole vastaus mihinkään viestiin.
            Viesti v = null;
            
            // Luodaan uusi viesti-olio.
            Viesti uusiViesti = new Viesti(viestinNro, teksti, v, liite);
            
            // Lisätään alueen oksaketjun listalle viesti.
            aktiivinen.lisaaKetjuun(uusiViesti);
            // Lisätään viestien kokkonaismäärää.
            aktiivinen.asetaViestiMaara();
            // Lisätään alueen kaikkiin viestien listalle viesti.
            aktiivinen.lisaaVarastoon(uusiViesti);
         }
         // Viestin lisääminen ei onnistu.
         else {
            Toiminnot.virhe();
         }
      }
      // Viestin lisääminen ei onnistu.
      else 
         Toiminnot.virhe();
   }
   
   /** 
    * Käyttäjän antama Reply-kommennon metodi.
    * 
    * Metodi lisää vastauksen viestiin.
    * @param syote kayttajan antama syote.
    * @param liite kayttajan antama liite.
    */
   public void lisaaVastaus(String syote, Tiedosto liite) {
      
      // Operaatiossa tarvittavien atribuuttien alustus.
      String teksti = null;
      int annettuLuku = 0;
      
      // Tarkistetaan saatu parametri.
      if (!"".equals(syote)) {
         // Kutsutaan aliohjelmaa.
         annettuLuku = Toiminnot.haeTunniste(syote);
         
         // Kutsutaan aliohjelmaa.
         teksti = Toiminnot.haeViestinTeksti(syote, MAX);
      }
      // Kohdattiin virhe.
      else 
         Toiminnot.virhe();
      
      // Tarkistetaan saatu parametri.
      if (teksti != null) {
         // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
         if (0 < annettuLuku && annettuLuku <= viestinNro && !"".equals(teksti)) {
            // Luodaan kopio viesti saadulla parametrilla.
            Viesti kopio = new Viesti(annettuLuku, " ", null, null);
            
            // Etsitään löydetäänkö kopioa vastaava viesti kaikkien viestien listalta.
            Viesti vastaus = (Viesti)aktiivinen.lueKaikkiViestit().hae(kopio);
            
            // Tarkistetaan saatu olio.
            if (vastaus != null) {              
               
               // Lisätään viestien juoksevaa numerointia.
               viestinNro++;
               
               // Luodaan uusi viesti-olio.
               Viesti uusiViesti = new Viesti(viestinNro, teksti, vastaus, liite);
               
               // Lisätään viestien kokkonaismäärää.
               aktiivinen.asetaViestiMaara();
               // Lisätään alueen kaikkiin viestien listalle viesti.
               aktiivinen.lisaaVarastoon(uusiViesti);
            }
            
            // Viestiin vastaaminen ei onnistu.
            else 
               Toiminnot.virhe();
         }
         // Viestiin vastaaminen ei onnistu.
         else 
            Toiminnot.virhe();
      }
      // Viestiin vastaaminen ei onnistu.
      else 
         Toiminnot.virhe();
   }
   
   /** 
    * Käyttäjän antama Select-kommennon metodi.
    * 
    * Valitaan alueista aktiivinen alue.
    * @param syote kayttajan antama syote.
    */  
   public void valitse(String syote) {
      
      // Tarkistetaan saatu parametri.
      if (!"".equals(syote)) {
         // Kutsutaan aliohjelmaa.
         int annettuLuku = Toiminnot.haeAnnettuLuku(syote);
         
         // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
         if ( 0 < annettuLuku && annettuLuku <= alueenKetjut.koko()){
            // Asetetaan aktiivinen ketju saadun parametrin mukaan.
            aktiivinen = (Ketju) alueenKetjut.alkio(annettuLuku - 1);
         }
         // Ketjun valinta ei onnistu.
         else 
            Toiminnot.virhe();
      }
      // Ketjun valinta ei onnistu.
      else 
         Toiminnot.virhe();
   }
   
   /** 
    * Käyttäjän antama Catalog-kommennon metodi.
    * 
    * Tulostetaan alueen aiheet ja viestimäärä.
    */
   public void tulostaAiheet() {
      
      // Kutsutaan aliohjelmaa.
      Toiminnot.tulostaLista(alueenKetjut);
   }
            
   /** 
    * Käyttäjän antama List-kommennon metodi.
    * 
    * Tulostetaan alueen viestit listana.
    */
   public void tulostaLista() {
      
      // Tarkistetaan että viestejä löytyy.
      if ( 0 < alueenKetjut.koko() ) {         

         // Kutsutaan aliohjelmaa.
         String aihe = aktiivinen.listaus();
         // Tulostetaan.
         System.out.println(MERKKI);
         System.out.println(MERKKI2 + aihe);
         System.out.println(MERKKI3);
         // Kutsutaan aliohjelmaa.
         aktiivinen.tulostaViestitListana();
      }
   }
   
   /** 
    * Käyttäjän antama Tree-kommennon metodi.
    * 
    * Tulostetaan alueen viestit puuna.
    */
   public void tulostaPuu() {
      
      // Tarkistetaan että viestejä löytyy.
      if ( 0 < alueenKetjut.koko() ) {

         // Kutsutaan aliohjelmaa.
         String aihe = aktiivinen.listaus();
         // Tulostetaan.
         System.out.println(MERKKI);
         System.out.println(MERKKI2 + aihe);
         System.out.println(MERKKI3);
         // Kutsutaan aliohjelmaa.
         aktiivinen.tulostaViestitPuuna();
      }
   }
   
   /** 
    * Käyttäjän antama Head-kommennon metodi.
    * 
    * Tulostetaan alueen vanhimmat viestit.
    * @param syote kayttajan antama syote.
    * 
    */
   public void tulostaPaa(String syote) {
      
      // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
      if ( aktiivinen != null && 0 < aktiivinen.lueViestiMaara()) {
         // Kutsutaan aliohjelmaa.
         int annettuLuku = Toiminnot.haeTunniste(syote);
         
         // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
         if (annettuLuku != 0 && annettuLuku > 0 ) {
            
            // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
            if (annettuLuku <= aktiivinen.lueViestiMaara()){
               // Kutsutaan aliohjelmaa.
               aktiivinen.paanTulostus(annettuLuku);
            }
            // Kohdattiin virhe.
            else 
               Toiminnot.virhe();
         }
         // Kohdattiin virhe.
         else 
            Toiminnot.virhe();
      }
      // Kohdattiin virhe.
      else 
         Toiminnot.virhe();
   }
   
   /**
    * Käyttäjän antama Tail-kommennon metodi.
    * 
    * Tulostetaan alueen tuoreimman viestit.
    * @param syote käyttäjän antama syöte.
    */
   public void tulostaHanta(String syote) {
      
      // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
      if ( aktiivinen != null && 0 < aktiivinen.lueViestiMaara()) {
         
         // Kutsutaan aliohjelmaa.
         int annettuLuku = Toiminnot.haeTunniste(syote);
         
         // Tarkistetaan saatu parametri.
         if (annettuLuku != 0 && annettuLuku > 0 ) {
            
            // Tarkistetaan saatu parametri.
            if (annettuLuku <= aktiivinen.lueViestiMaara()){
               
               // Tulosteaan hanta.
               aktiivinen.hannanTulostus(annettuLuku);
            }
            // Kohdattiin virhe.
            else 
               Toiminnot.virhe();
         }
         // Kohdattiin virhe.
         else 
            Toiminnot.virhe();
      }
      // Kohdattiin virhe.
      else 
         Toiminnot.virhe();
   }
   
   /**
    * Käyttäjän antama Empty-kommennon metodi.
    * 
    * Tyhjennetään viestin aihe ja liite, jos sellainen löytyy.
    * @param syote kayttajan antama syote.
    * 
    */
   public void tyhjenna(String syote) {
      
      // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
      if (aktiivinen != null && 0 < aktiivinen.lueViestiMaara()) {

         // Kutsutaan aliohjelmaa.
         int annettuLuku = Toiminnot.haeAnnettuLuku(syote);
         
         // Tarkistetaan että saadulla parametrilla voidaan suorittaa metodia.
         if (annettuLuku != 0 && annettuLuku <= viestinNro) {
            
            // Kutsutaan aliohjelmaa.
            aktiivinen.poistettava(annettuLuku);
         }
         // Kohdattiin virhe.
         else 
            Toiminnot.virhe();
      }
      // Kohdattiin virhe.
      else 
         Toiminnot.virhe();
   }
   
   /** 
    * Käyttäjän antama Find-kommennon metodi.
    * 
    * Etsitään viesteistä vastaavuutta annetun syötteen mukaan.
    * @param syote kayttajan antama syote.
    * 
    */
   public void etsi(String syote) {
      
      // Tarkistetaan saatu parametri.
      if (!"".equals(syote)) {
         
         // Kutsutaan aliohjelmaa.
         String aihe = Toiminnot.haeViestinTeksti(syote, MIN);
         
         // Kutsutaan etsintä metodia.
         aktiivinen.etsiListasta(aihe);
      }
   }
}