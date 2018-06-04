package oope2017ht.tiedot;

/** Abstrakti Tieto yliluokka, jonka Hakemisto ja Tieto perivät.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017
  * <p>
  *  
  * @author Okko Partanen (Partanen.Okko.A@student.uta.fi).
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public abstract class Tieto implements Comparable<Tieto> {
    /**Tiedon nimi.*/
    private StringBuilder name;
    
    /**Nimessä hyväksytään merkit a-zA-Z0-9_ ja joko yksi piste tai ei yhtäkään.*/
    private final String sallittu = "[a-zA-Z0-9_]*+\\.?[a-zA-Z0-9_]*+";
   
    /** Konstruktori, tarkistaa että anettu nimi on salittua muotoa.
      *  
      * @param nimi Tiedon nimi.
      * @throws IllegalArgumentException jos nimessä oli ei sallittuja merkkejä.
      */    
    public Tieto(StringBuilder nimi) throws IllegalArgumentException{
        //Tarkistetaan että pelkkiä salittuja merkkejä ja, ettei ole pelkkä piste.    
        if(nimi ==null || nimi.toString().equals(".") || !nimi.toString().matches(sallittu))
            throw new IllegalArgumentException();        
        name = nimi;
    }
    
    /** Kopio rakentaja. Luo uuden Tiedon ja asettaa nimeksi parametrina saadun
      * Tiedon nimen.
      * 
      * @param t kopioitava Tieto.
      * @throws IllegalArgumentException jos parametri ei ollut Tieto tyyppinen.
      */
    public Tieto(Tieto t) throws IllegalArgumentException{
        if (!(t instanceof Tieto))            
            throw new IllegalArgumentException();
        //Kopioidaan parametrina saadun Tiedon nimi.
        StringBuilder nimi = new StringBuilder(t.name);         
        name = nimi;
    }
    
   
    public StringBuilder getName(){
        return name;
    }
    
    /** Setteri tarkistaa myös, että nimi on laillista muotoa.
      * 
      * @param nimi asetettava nimi Tiedolle.
      * @throws IllegalArgumentException jos nimi ei laillinen. 
      */
    public void setName(StringBuilder nimi) throws IllegalArgumentException{
        //Tarkistetaan että pelkkiä salittuja merkkejä ja, ettei ole pelkkä piste.
        if(nimi!=null && nimi.toString().matches(sallittu) && !nimi.toString().equals("."))
            name = nimi;
        else
            throw new IllegalArgumentException();
    }
    
    
    /** @return Tiedon merkkijono esitys. */ 
    @Override
    public String toString(){
        return name.toString();
    }   
   
    /** Korvattu compareTo. Tietoja vertaillaan nimien perusteella.
      * Nimet muunnetaan StringBuildereista Stringeiksi, ja vertaillaan niitä.
      * 
      * @param t Tieto johon verrataan.
      * @return 0 jos samat, &lt;0 jos pienempi, &gt;0 jos suurempi.
      * @throws IllegalArgumentException jos parametri null arvoinen.
      */
    @Override
    public int compareTo(Tieto t) throws IllegalArgumentException{
        if (t==null)
            throw new IllegalArgumentException();
        //Muutetaan nimet String tyyppisiksi ja vertaillaan.
        return this.getName().toString().compareTo(t.getName().toString());
    }    
    
    /** Korvattu equals. Tiedot ovat samat jos niiden nimet ovat samat.
      * Muunnetaan nimet Stringeiksi, ja vertaillaan.
      * 
      * @param obj vertailtava Tieto.
      * @return true, jos nimet ovat samat, false jos ei.
      */
    @Override
    public boolean equals(Object obj){        
        if (obj instanceof Tieto){
            Tieto verrattava = (Tieto)obj;
            //Palautetaan olivatko samjoa vai ei.
            return this.getName().toString().equals(verrattava.getName().toString());
        }
        return false;
    }  
}
