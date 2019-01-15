package oope2018ht.viestit;

import fi.uta.csjola.oope.lista.*;
import oope2018ht.apulaiset.*;
import oope2018ht.omalista.OmaLista;

/** 
 * Ketju -luokka
 * joka vastaa alueen viesti ketjuista.
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
  */
public class Ketju extends LinkitettyLista {
   
   /*
    * 
    * Atribuutit
    */
   
   /** Erotin tulostamista varten. */
   final String VALI = " ";
   /** Tulosteissa hyödynneväksi sulkumerkin aloitus. */
   final String SULKU1 = " (";
   /** Tulosteissa hyödynneväksi sulkumerkin lopetus. */
   final String SULKU2 = " messages)";
   /** Tulostamista varten aiheen tunnisteen merkki. */
   final String AIHETUNNISTE = "#";
   /** Tulostamista varten apumuuttuja.  */
   final int TASONSYVYYS = 3;
      
   /** Kätketty lista oksaviesteille. */
   private OmaLista ketjunViestit;
   
   /** Kätketty lista kaikille viesteille.  */
   private OmaLista kaikkiViestit;
   
   /** Ketjun yksilöivän tunnisteen juokseva numerointi. */
   private int ketjunTunniste;
   
   //** Ketjun aihe. */
   private String ketjunAihe;
   
   /** Ketjun kokonaisviestimnäärä. */
   private int viestiMaara;
   
   /** 
    * Hyödynnetään aksesseoreita arvon asettamisessa.
    * Aksessori heittää poikkeuksen, jos arvo ei ole sopiva.
    * @param i ketjun tunniste.
    * @param a ketjun aihe
    */   
   public Ketju(int i, String a) throws IllegalArgumentException{
      if (0 < i && a != null ) {
         asetaKetjunTunniste(i);
         asetaKetjunAihe(a);
         ketjunViestit = new OmaLista();
         kaikkiViestit = new OmaLista();
      }
      else
         throw new IllegalArgumentException();
   }
   
   /*
    * 
    * Aksessorit.
    */
   
   @Getteri
   public int lueKetjunTunniste() {
      return ketjunTunniste;
   }
   
   @Setteri
   public void asetaKetjunTunniste (int i) throws IllegalArgumentException {
      if (0 < i) {
         ketjunTunniste = i;
      }
   }
   
   @Getteri
   public String lueAihe() {
      return ketjunAihe;
   }
   
   @Setteri
   public void asetaKetjunAihe(String a) throws IllegalArgumentException {
      if (a != null && 0 < a.length()) {
         ketjunAihe = a;
      }
   }
   
   @Getteri
   public OmaLista lueKetjunViestit() {
      return ketjunViestit;
   }
   
   
   @Setteri
   public void asetaKetjunViestit(OmaLista o) throws IllegalArgumentException{
      if (o == null) {
         throw new IllegalArgumentException();
      }
      else {
         ketjunViestit = o;
      }
   }
   
   @Getteri
   public OmaLista lueKaikkiViestit() {
      return kaikkiViestit;
   }
   
   
   @Setteri
   public void asetaKaikkiViestit(OmaLista o) throws IllegalArgumentException{
      if (o == null) {
         throw new IllegalArgumentException();
      }
      else {
         kaikkiViestit = o;
      }
   }
   
   @Getteri
   public int lueViestiMaara() {
      return viestiMaara;
   }
   
   @Setteri
   public void asetaViestiMaara() {
      viestiMaara++;
   }
      
      
   /*
    * Metodit
    *
    */
   
   /**
    * Operaatio, joka
    * @param k lisää ketjun oksalistalle viestin.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void lisaaKetjuun(Viesti k) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (k != null) {
         // Listään oksaviestien listalle.
         ketjunViestit.lisaaLoppuun(k);
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();
   }
   
   /**
    * Operaatio, joka 
    * @param k lisää ketjun kaikkien viestien listalle viestin.
    */
   public void lisaaVarastoon(Viesti k) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (k != null) {
         // Lisätään kaikkien viestien listalle.
         kaikkiViestit.lisaa(k);
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();
   }
   
   /**
    * Operaatio, joka tulostaa viestit.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void tulostaViestitListana() throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (ketjunViestit != null) {
         // Kutsutaan aliohjelmaa.
         Toiminnot.tulostaLista(kaikkiViestit);  
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();   
   }
   
   /**
    * Viestien tulostaminen.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void tulostaViestitPuuna() throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (ketjunViestit != null) {
         // Silmukka jossa käydään viesti lista läpi.
         for (int ind = 0; ind < ketjunViestit.koko(); ind++) {
            // Tulostetaan ja kutstuaan apumetodia.
            tulostaPuuna((Viesti)ketjunViestit.alkio(ind), 0);  
         }
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();        
   }
   
   /**
    * Viestien tulostamisen apumetodia, jossa käytetään rekursiota.
    * @param viesti joka tulostetaan.
    * @param syvyys sisennys puun tulostukselle.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void tulostaPuuna(Viesti viesti, int syvyys) throws IllegalArgumentException {
      
      // Tarkistetaan saatu parametri.
      if(viesti != null) {
         // Sisennystä.
         for (int j = 0; j < syvyys; j++) {
            // Tulostetaan tyhjä väli.
            System.out.print(VALI);
         }

         // Tulostetaan viestin.
         System.out.println(viesti);  
         // Luetaan viestin vastaukset.
         OmaLista vastaukset = viesti.lueVastaukset();
         // Käydään viestin vastauksen läpi silmukalla.
         for (int ind = 0; ind < vastaukset.koko(); ind++) {
            // Tulostetaan viesti ja käydään vastaukset läpi uudellee..
            tulostaPuuna((Viesti) vastaukset.alkio(ind), syvyys + TASONSYVYYS);
         }
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();  
   }
   
   /**
    * Operaatio, jolla koitetaan tyhjentää viesti.
    * @param annettuLuku parametrina saatu luku, jonka mukaan poistetaan.
    */
   void poistettava (int annettuLuku) {
      
      // Yritetään viestin tyhjentämistä.
      try {
         // Luodaan kopioviesti.
         Viesti kopio = new Viesti(annettuLuku, " ", null, null);
         // Etsitään viesteistä vastaavuutta.
         Viesti poista = (Viesti)lueKaikkiViestit().hae(kopio);
         // Poistetaan viesti, jos löyty.
         Viesti poistettavaListalta = poista;
         // Tyhjennetään lista.
         poistettavaListalta.tyhjenna();

      }
      // Ei onnistututtu.
      catch (IllegalArgumentException e){
         Toiminnot.virhe();
      }
   }
   
   /**
    * Tulostetaan vanhimmat viesti.
    * @param a parametri joka saadaan käyttäjältä.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void paanTulostus(int a) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (0 < a) {
         OmaLista alku = kaikkiViestit.annaAlku(a);
         // Kutsutaan aliohjelmaa.
         Toiminnot.tulostaLista(alku);
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();  
   }
   
   /**
    * Tulostetaan uusimmat viestiy.
    * @param a parametri joka saadaan käyttäjältä.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void hannanTulostus(int a) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (0 < a) {
         OmaLista loppu = kaikkiViestit.annaLoppu(a);      
         Toiminnot.tulostaLista(loppu);
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();  
   }
   
   /**
    * Etsitään viesteistä käyttäjän antaman syötteen mukaan.
    * @param etsittava parametri, jonka mukaan etsitään.
    * @throws IllegalArgumentException jos parametreissa oli virhe.
    */
   public void etsiListasta(String etsittava) throws IllegalArgumentException {
      // Tarkistetaan saatu parametri.
      if (!"".equals(etsittava)) {
         // Luodaan omalista.
         OmaLista lista = new OmaLista();
         // Käydään kaikkien viestien lista läpi.
         for (int ind = 0; ind < kaikkiViestit.koko(); ind++) {
            // Luodaan kopio-olio.
            Viesti kopio = (Viesti) kaikkiViestit.alkio(ind);        
            // Haetaan kopion teksti.
            String teksti = kopio.toString();
            // Pilkotaan teksti taulukoksi.
            String [] taulu = teksti.split(" ", 2);
            // Ja poimitaan viestin aihe.
            teksti = taulu[1];
            
            // Etsitään löytyy vastaavuutta.
            if (teksti.indexOf(etsittava) >= 0) {
               // Jos löytyy, lisätään viesti listalle.
               lista.lisaaLoppuun(kopio);
            }
         }
         // Jos lista ei ole tyhjä, tulostetaan se.
         if (lista != null) {
            // Kutstutaan apuohjelmaa.
            Toiminnot.tulostaLista(lista);
         }
      }
      // Heitetään poikkeus.
      else
         throw new IllegalArgumentException();  
   }
   
   /**
    * Tulostetaan viesti.
    * @return viestin tulosus.
    */
   @Override
   public String toString() {
      return AIHETUNNISTE + ketjunTunniste + VALI + ketjunAihe + SULKU1 + viestiMaara + SULKU2;
   }
   
   // Jostain kumman syystä ylempi tulostus ei toiminut, tein uuden operaation.
   /**
    * Operaatio viestin tulostamiselle.
    * @return viesti.
    */
   String listaus() {
      return AIHETUNNISTE + ketjunTunniste + VALI + ketjunAihe + SULKU1 + viestiMaara + SULKU2;
   }
}
