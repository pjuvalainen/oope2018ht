package oope2018ht.kayttoliittyma;

import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2018ht.apulaiset.In;
import oope2018ht.viestit.*;
import oope2018ht.tiedostot.*;
import oope2018ht.omalista.OmaLista;

/**
 * Kayttoliittymä -luokka
 * joka vastaa ihmisen ja ohjelman vuorovaikutuksesta.
 * <p>
 * Olio-ohjelmoinnin perusteet 2018 - harjoitustyö
 * <p>
 * @author Petteri Juvalainen  - 427130
 * juvalainen.j.petteri@student.uta.fi
 */

public class Kayttoliittyma extends LinkitettyLista {
   
   /* Ohjelman käyttöä ohjaavat käskyt vakioina, jotka antaa käyttäjä */
   
   /** Ketjun lisäys */
   public static final String ADD = "add";
   /** Ketjuhjen aiheiden tulostus */
   public static final String CATALOG = "catalog";
   /** Ketjun valinta */
   public static final String SELECT = "select";
   /** Uuden viestin lisäys */
   public static final String NEW = "new";
   /** Viestiin vastaaminen */
   public static final String REPLY = "reply";
   /** Ketjun tulostus puuna */
   public static final String TREE = "tree";
   /** Ketjun tulostus listana */
   public static final String LIST = "list";
   /** Alueen vanhimpien viestien tulostus */
   public static final String HEAD = "head";
   /** Alueen tuoreimpien viestien tulostus */
   public static final String TAIL = "tail";
   /** Viestin tekstin ja tiedostojen tyhjentäminen */
   public static final String EMPTY = "empty";
   /** Tekstin hakeminen */
   public static final String FIND = "find";
   /** Ohjelman päättäminen */
   public static final String EXIT = "exit";
      
   /** Luokan kätketty atribuutti keskustelualueelle */
   private final Alue keskusteluAlue;
   
   /** Luodaan uusi keskustealue, johon aiheet ja viestit liitetään */
   public Kayttoliittyma() {
      keskusteluAlue = new Alue();
   }
   
   /** Metodi joka suorittaa ohjelman pääsilmukkaa.  */
   public void suorita() {
      /** Atribuutti Ohjelman lopetukselle */
      boolean lopetetaan = false;
      
      /** Kutsutaan aliohjelmaa, joka tervehtii käyttäjää */
      Toiminnot.moro();
      
      /* Ohjelman pääsilmukka. */
      do {
         /** Kutsutaan aliohjelmaa, joka tulostaa määritellyn merkin rivin alkuun. */
         Toiminnot.aloitus();
         
         /* Luetaan käyttäjältä syöte. */
         String syote = In.readString();
         
         /** Kutsutaan aliohjelmaa, joka muuttaa käyttäjän syötteen komennoksi. */
         String kasky = Toiminnot.lueKasky(syote);
         
         /** Kutsutaan aliohjelmaa, joka tarkistaa löytyykö syötteestä tiedostoa. */
         Tiedosto liite = Toiminnot.lueLiite(syote);
         
         // Yritetään suorittaa ohjelmaa.
         try {
            /* Jos käsky ei ole virheellinen, suoritetaan komennon mukainen metodi. */
            if (kasky != null) {                  
               /** luodaan uusi viestiketju. */
               if (kasky.equals(ADD)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.lisaaAihe(syote);
               }

               /** Listataan ketjut ja viestimäärä luomisjärjestyksessä. */
               else if (kasky.equals(CATALOG)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tulostaAiheet();
               }

               /** Valitaan viestiketju. */
               else if (kasky.equals(SELECT)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.valitse(syote);
               }

               /** Luodaan uusi viesti */
               else if (kasky.equals(NEW)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.lisaaViesti(syote, liite);
               }

               /** Vastataan aiemmin lähettyyn viestiin. */
               else if (kasky.equals(REPLY)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.lisaaVastaus(syote, liite);
               }

               /** Tulostetaan ketju puumuodossa. */
               else if (kasky.equals(TREE)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tulostaPuu();
               }

               /** Tulostetaan ketju listana. */
               else if (kasky.equals(LIST)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tulostaLista();
               }

               /** Vanhimpien viestien tulostus. */
               else if (kasky.equals(HEAD)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tulostaPaa(syote);
               }

               /** Tuoreimpien viestien tulostus. */
               else if (kasky.equals(TAIL)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tulostaHanta(syote);
               }

               /** Viestin tekstin ja tiedostojen tyhjentäminen. */
               else if (kasky.equals(EMPTY)){
                  /** Kutsutaan aliohjelmaa. */
                  keskusteluAlue.tyhjenna(syote);
               }

               /** Tekstin hakeminen. */
               else if (kasky.equals(FIND)){
                  /** Kutsutaan aliohjelmaa. */
                   keskusteluAlue.etsi(syote);
               }             

               /** Ohjelman lopettaminen. */
               else if (kasky.equals(EXIT)){
                  lopetetaan = true;
                  /** Kutsutaan aliohjelmaa, joka tulostaa käyttäjelle lopetusviestin. */
                  Toiminnot.lopetus();
               }

               /** Ohjelma kohtaa virheen. */
               else {
                  /** Kutsutaan aliohjelmaa, joka tulostaa käyttäjälle virheen. */
                  Toiminnot.virhe();
               }
            }
         }
         // Napataan poikkeus ja tulostetaan virheilmoitus.
         catch(IllegalArgumentException e) {
            Toiminnot.virhe();
         }
      } 
      /* Ohjelman lopetus. */
      while (!lopetetaan);
   }
}
