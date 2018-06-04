package oope2017ht;
import oope2017ht.tiedot.Tieto;
import oope2017ht.tiedot.Tiedosto;
import oope2017ht.tiedot.Hakemisto;

/** Konkreettinen Tulkki luokka. Käsittelee Hakemistoa. Tulkin metodeja kutsutaan
  * Kayttoliittyma-luokasta.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017.
  * <p>
  *
  * @author Okko Partanen Partanen.Okko.A@student.uta.fi.
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
class Tulkki {    
    
    /**Viite nykyiseen Hakemistoon ohjelmassa. */    
    private Hakemisto nykyinenHakemisto;
    /**Viite ohjelman juurihakemistoon. */
    private final Hakemisto JUURI;  
    
    /** Konstruktori alustaa juurihakemiston, ja asettaa sen nykyiseksi hakemistoksi.*/
    Tulkki(){
        //Alustetaan juuri Hakemisto ja asetetaan se nykyiseksi Hakemistoksi.
        JUURI = new Hakemisto(new StringBuilder(""), null); 
        nykyinenHakemisto = JUURI;                      
    }    
    
    /** Tulostaa nykyisen Hakemiston tiedot.*/
    void listaa(){
        nykyinenHakemisto.tulosta();        
    }    
    
    /** Kuormitettu listaa kutsuu Hakemiston kuormitettua listaa metodia
      * joka tulostaa yksittäisen Tiedon merkkijono esityksen, jos sellainen löytyy nykyisestä hakemistosta.
      * 
      * @param nimi tulostettavan Tiedon nimi. 
      */
    void listaa(String nimi){        
            nykyinenHakemisto.tulosta(nimi);              
    }    
  
    /** Saa parametrina uudelleen nimettävän Tiedon nimen, ja sen uuden nimen.
      * Jos Hakemistosta löytyy nimeä vastaava Tieto, asetetaan sen viite apu-olioon,
      * poistetaan Tieto, asetetaan apu-olion nimeksi uusi nimi ja lisätään se Hakemistoon.
      * Näin Hakemiston listan järjestys säilyy. 
      * 
      * @param nimi uudelleen nimettävän Tiedon nimi.
      * @param uusiNimi uusi nimi Tiedolle.
      * @return true jos uudelleen nimeäminen onnistui, false jos ei.
      */
    boolean nimea(String nimi, String uusiNimi){
        //Apu-olio.
        Tieto apu = nykyinenHakemisto.hae(nimi);        
        if(apu!=null && nykyinenHakemisto.hae(uusiNimi)==null && uusiNimi!=null){ 
            nykyinenHakemisto.poista(nimi);
            apu.setName(new StringBuilder(uusiNimi));                        
            return nykyinenHakemisto.lisaa(apu);                
        }        
        return false;
    }  
    
    /** Vaihtaa nykyisen hakemiston, joko juureen (jos parametri on "cd"),
      * nykyisen hakemiston ylihakemistoon (jos parametri on ".."),
      * tai alihakemistoon (parametri hakemiston nimi), jos sellainen hakemistossa on.
      * 
      * @param argumentti määrittää mihin hakemistoon ohjelmassa siirrytään.
      * @return true jos hakemiston vaihtaminen onnistui, false jos ei.
      */
    boolean vaihda(String argumentti){
        if(argumentti==null)
            return false;
        //Jos argumentti on "cd" siirrytään juureen.
        if (argumentti.equals("cd")){                           
            nykyinenHakemisto = JUURI;
            return true;
        }else if (argumentti.equals("..")){
                //Tarkastetaan että ei olla juuressa.
            if (!nykyinenHakemisto.equals(JUURI)){
                nykyinenHakemisto = nykyinenHakemisto.getYliHakemisto();
                return true;
            }            
            return false;
        }else{
            //Etsitään parametria vastaava Hakemisto.
            Tieto apu = nykyinenHakemisto.hae(argumentti);
            //Jos parametria vastaavaa hakemisto ei löydy, palautetaan false
            if (apu==null || apu instanceof Tiedosto)
                return false;
            //Jos löytyi asetetaan nykyiseksi Hakemistoksi.
            nykyinenHakemisto = (Hakemisto)apu;                
            return true;
        }
    }  
    
    /** Poistaa Tiedon, jos sellainen on Hakemistossa.
      *  
      * @param nimi poistettavan Tiedon nimi.
      * @return true jos poisto onnistui, false jos tietoa ei löytynyt hakemistosta.
      */
    boolean poista(String nimi){        
        return nykyinenHakemisto.poista(nimi)!= null;
    }  
    
    /** Luo Hakemiston, jos Hakemistossa ei ole parametrina saatua nimeä vastaavaa
      * tietoa.
      *   
      * @param nimi luotavan Hakemiston nimi
      * @return true jos onnistui, false jos ei.
      */
    boolean luoKansio(String nimi){
        //Jos parametri on null palautetaan false.
        if (nimi==null)
            return false;
        //Luodaan Hakemisto, ja palautetaan onnistuiko.
        return nykyinenHakemisto.lisaa(new Hakemisto(new StringBuilder(nimi), nykyinenHakemisto));
        
    }
    /** Luo Tiedoston Hakemistoon, jos mahdollista.
      * 
      * @param nimi luotavan Tiedoston nimi.
      * @param koko luotavan Tiedoston koko.
      * @return true jos Tiedoston luonti onnistui, false jos ei.
      */
    boolean luoTiedosto(String nimi, int koko){
        //Palautetaan false jos parametri null.
        if (nimi==null)
            return false;
        //Palautetaan onnistuiko lisäys.        
        return nykyinenHakemisto.lisaa(new Tiedosto(new StringBuilder(nimi), koko));
        
    }    
    
    /** Luo kopion Tiedostosta, jos hakemistossa on parametrina saadun merkkijonon niminen
      * Tiedosto, ja jos siellä ei ole vielä kopion nimistä Tiedostoa.
      * 
      * @param kopioitavaNimi kopioitavan tiedoston nimi
      * @param kopio kopion nimi
      * @return true jos kopiointi oli mahdollista, false jos ei.
      */
    boolean kopioi(String kopioitavaNimi, String kopio){
        if (kopio==null)
            return false;
        //Tarkastetaan että Tieto joka kopioidaan löytyy Hakemistosta.
        Tieto kopioitava = nykyinenHakemisto.hae(kopioitavaNimi);
        //Tarkasetaan ettei löydy kopion nimistä Tietoa.
        Tieto apu = nykyinenHakemisto.hae(kopio);
        //Kopioitava Tieto tulee olla Tiedosto tyyppiä.
        if (kopioitava != null && kopioitava instanceof Tiedosto && apu==null){            
            Tiedosto uusi = new Tiedosto((Tiedosto)kopioitava);                     
            uusi.setName(new StringBuilder(kopio));
            return nykyinenHakemisto.lisaa(uusi);            
        }               
        return false;      
    }
    
    /** Listaa nykyisen Hakemiston Tiedot rekursiivisesti.*/
    void listaaRekursiivisesti(){        
        nykyinenHakemisto.tulostaPuu(nykyinenHakemisto);
    }
    
    /** Tulostaa Hakemiston polun. */
    void tulostaPolku(){
        nykyinenHakemisto.tulostaPolku(nykyinenHakemisto);
    }
}
