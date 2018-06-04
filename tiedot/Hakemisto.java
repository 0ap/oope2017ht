package oope2017ht.tiedot;
import apulaiset.Komennettava;
import fi.uta.csjola.oope.lista.LinkitettyLista;
import oope2017ht.omalista.OmaLista;

/** Konkreettinen Hakemisto-luokka. Toteuttaa Komennettava rajapinnan.
  * Peritty abstraktista Tieto-luokasta. Hakemisto koostuu ylihakemistosta, alihakemistoista ja tiedostoista.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017.
  * <p>
  * 
  * @author Okko Partanen (Partanen.Okko.A@student.uta.fi).
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class Hakemisto extends Tieto implements Komennettava<Tieto> {
    
        
    /**Hakemiston OmaLista jolle tietoalkiot säilötään.*/
    private final OmaLista LISTA;
    /**Hakemiston ylihakemisto.*/
    private final Hakemisto yliHakemisto;   
    
    /** Konstruktori asettaa listan ja ylihakemiston Hakemistolle.
      * Kutsuu yliluokan konstruktoria.
      * 
      * @param nimi Hakemiston nimi.
      * @param ylihakemisto Hakemiston ylihakemisto.
      */   
    public Hakemisto(StringBuilder nimi, Hakemisto ylihakemisto) {
        super(nimi);
        yliHakemisto = ylihakemisto;
        LISTA = new OmaLista();
    }
    
    /**Palauttaa viitteen Hakemiston listaan.*/
    @Override
    public LinkitettyLista sisalto() {
        return LISTA;
    }
    
     /**@return OmaListan koko. */
    public int koko(){
        return LISTA.koko();
    }   
    
    @Override
    public StringBuilder getName() {        
        return super.getName();
    }   
    
    public void setNimi(StringBuilder nimi){
        super.setName(nimi);
    }    
    
    public Hakemisto getYliHakemisto(){
        return yliHakemisto;
    }  
    
    /** Hakee Hakemiston OmaListalta parametria vastaavan Tieto-alkion.
      * Luo väliaikaisen Hakemiston parametrina saadusta nimestä, ja kutsuu
      * OmaListan haku operaatiota. Palauttaa sen palauttaman arvon. Tietoja vertaillaan
      * nimien perusteella, joten ei ole tarpeellista luoda Hakemisto ja Tiedosto muuttujaa.
      * 
      * @param nimi haettavan Tiedon nimi.
      * @return viite haettavaan Tietoon jos sellainen löytyi, null jos ei.
      */     
    @Override
    public Tieto hae(String nimi) {
        if (nimi==null)
            return null;
        //Luodaan väliaikainen Hakemisto, ja kutsutaan listan haku operaatiota.
        return (Tieto)LISTA.hae(new Hakemisto(new StringBuilder(nimi), null));
    }
    
    /** Lisaa Tieto-alkion Hakemiston OmaListalle. 
      * 
      * @param lisattava Tieto tyyppinen alkio.
      * @return true, jos lisäys onnistui, false jos ei.
      */
    @Override
    public boolean lisaa(Tieto lisattava) {
        //Ei hyväksytä null arvoa, ja tarkastetaan ettei Hakemistossa ole lisättävän nimistä Tietoa.        
        if (lisattava != null && hae(lisattava.getName().toString())==null)
            return LISTA.lisaa(lisattava);
        else
            return false;
    }    
   
    /** Poistaa merkkijonoa vastaavan Tieto-alkion OmaListalta, jos sellainen löytyy.
      * 
      * @param nimi poistettavan Tiedon nimi.
      * @return viite poistettuun Tietoon, null jos Tietoa ei ole OmaListalla, tai jos parametri on null arvoinen.
      */
    @Override
    public Tieto poista(String nimi){
        if (nimi==null)
            return null;
        
        return (Tieto)LISTA.poista(hae(nimi));               
    }    
   
    /** Korvattu toString, kutsuu yliluokan toString-metodia ja lisää palautuvaan
      * merkkijonoon Hakemiston OmaListalla olevien alkioiden määrän.
      *
      * @return Hakemiston merkkijono esitys.
      */    
    @Override
    public String toString(){               
        return super.toString()+"/ "+LISTA.koko();       
    }   
    
    /** Silmukoi Hakemiston OmaListan ja tulostaa kaikki alkiot.*/    
    public void tulosta(){
        //Silmukoidaan ja tulostetaan listan kaikki alkiot.
        for(int i=0; i<LISTA.koko(); i++){            
            System.out.println(LISTA.alkio(i));               
        }
    }
   
    /** Kuormitettu listaa-metodi tulostaa yksittäisen Tiedon merkkijono esityksen
      * jos sellainen löytyy OmaListalta.
      *
      * @param nimi tulostettavan Tiedon nimi.
      * @throws IllegalArgumentException jos parametri oli null arvoinen, tai Tietoa ei löydy Hakemistosta.
      */     
    public void tulosta(String nimi) throws IllegalArgumentException{
        //Heitetään virhe jos haettavaa Tietoa ei ole, tai jos parametri on null.
        if (hae(nimi)==null || nimi==null)
            throw new IllegalArgumentException();
        else
            System.out.println(hae(nimi));
    }
  
   
    /** Rakentaa silmukalla Hakemiston polun ja tulostaa sen.
      *
      * @param hakemisto Hakemisto jonka polku tulostetaan.
      */  
    public void tulostaPolku(Hakemisto hakemisto){      
        StringBuilder tuloste = new StringBuilder();
        while(hakemisto!=null){
            //Lisätään Hakemiston nimi aina polun alkuun,
            //jotta polkuu tulostuu oikeassa järjestyksessä.
            tuloste.insert(0,hakemisto.getName()+"/");           
            hakemisto = hakemisto.getYliHakemisto();            
        }       
        System.out.print(tuloste);
    }  
   
    /** Listaa rekursiivisesti Hakemiston Tiedot ja polun. Aloittaa parametrina
      * saadusta Hakemistosta.
      *
      * @param hakemisto josta rekursiivinen listaaminen aloitetaan.
      */    
    public void tulostaPuu(Hakemisto hakemisto){
        //Silmukoidaan OmaLista.
        for(int i=0; i<LISTA.koko();i++){
            Tieto tieto = (Tieto)LISTA.alkio(i);
            //Tulostetaan Hakemisto polku.
            hakemisto.tulostaPolku(hakemisto);
            //Tulostetaan Tieto.
            System.out.println(tieto);
            //Jos käsiteltävä tieto on Hakemisto, tulostetaan sen polku.
            if (tieto instanceof Hakemisto){
                Hakemisto apuHakemisto = (Hakemisto)tieto;             
                apuHakemisto.tulostaPuu(apuHakemisto);
            }             
        }
    }
}        

