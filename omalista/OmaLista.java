package oope2017ht.omalista;
import apulaiset.Ooperoiva;
import fi.uta.csjola.oope.lista.LinkitettyLista;

/** OmaLista luokka. Luokka perii LinkitettyLista luokan, ja toteuttaa
  * Ooperoiva rajapinnan. OmaListalle säilötään Hakemiston Tieto-alkiot.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017
  * <p>
  *
  * @author Okko Partanen (Partanen.Okko.A@student.uta.fi).
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class OmaLista extends LinkitettyLista implements Ooperoiva {   
    
    /** Konstruktori. Kutsuu yliluokan rakentajaa.*/    
    public OmaLista() {
        super();
    }    
    
    /** Hakee alkion listalta. Silmukoi listan läpi ja vertaa listan alkiota
      * haettaavaan Objektiin.
      * 
      * @param haettava Objekti
      * @return viite haettavaan Objektiin, jos löytyi. Null jos ei.
      */
    @Override
    public Object hae(Object haettava) {
        //Silmukoidaan lista ja tarkastetaan löytyykö haettavaa Objektia.
        for (int i = 0; i < koko(); i++) {           
            if (alkio(i).equals(haettava)) {
                return alkio(i);
            }
        }
        return null;
    }   
    
    /** Listan lisäys metodi. 
      * Metodi olettaa että parametrina saatu Objekti toteuttaa
      * Comparable-rajapinnan. Lisää alkiot listalle suurusjärjestyksessä.
      * 
      * @param uusi lisättävä Objekti.
      * @return true, jos lisäys onnistui, false jos ei.
      */
    @Override
    public boolean lisaa(Object uusi) {        
        if (uusi==null)
            return false;        
        for (int i = 0; i < koko(); i++) {
            Comparable vertailtava = (Comparable)alkio(i);
            //Jos löytyi suurempi alkio, lisätään sen eteen.
            if (vertailtava.compareTo(uusi) > 0) 
                return super.lisaa(i, uusi);                 
            }
        //Jos parametri oli suurempi kuin mikään listan alkioista, lisätään listan loppuun. 
        super.lisaaLoppuun(uusi);
        return true;
    }  
    
    /** Listan poisto metodi. Silmukoi listan läpi, ja jos löytyi
      * parametria vastaava Objekti, poistetaan se.
      * 
      * @param poistettava poistettava Objekti.
      * @return viite poistettuun Objektiin jos löytyi. Null jos ei.
      */
    @Override
    public Object poista(Object poistettava) {
        for (int i = 0; i < koko(); i++) {           
            if (alkio(i).equals(poistettava)) {
                return super.poista(i);
            }
        }
        return null;
    }
}
