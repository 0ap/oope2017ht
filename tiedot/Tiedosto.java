package oope2017ht.tiedot;

/** Konkreettinen Tiedosto-luokka. Peritty Tieto-luokasta.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017
  * <p>
  * 
  * @author Okko Partanen (Partanen.Okko.A@student.uta.fi).
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
public class Tiedosto extends Tieto {
    /** Tiedoston koko.*/
    private int koko;   
    
    /** Tiedoston rakentaja. Tiedolla on nimi ja koko. Kutsuu yliluokan konstruktoria
      * joka tarkastaa, että nimi on laillinen. Koko ei voi olla 0 pienempi.
      *
      * @param nimi Tiedoston nimi.
      * @param asetus Tiedoston koko.
      * @throws IllegalArgumentException jos koko on pienempi kuin 0.
      */    
    public Tiedosto(StringBuilder nimi, int asetus) throws IllegalArgumentException{
        super(nimi);
        if (asetus < 0)
            throw new IllegalArgumentException();
        else
            koko = asetus;
    }    
       
    /** Syväkopio rakentaja. Kutsuu yliluokan kopio rakentajaa.
      * 
      * @param tiedosto kopiotava Tiedosto. 
      */
    public Tiedosto(Tiedosto tiedosto){     
        super(tiedosto);        
        koko = tiedosto.koko;     
    }    
    
    public int getKoko(){
        return koko;
    }
   
    public void setKoko(int asetus) throws IllegalArgumentException{
        if (asetus < 0)
            throw new IllegalArgumentException();
        else
            koko = asetus;
    }
    
    public StringBuilder getNimi(){
        return super.getName();
    }
    
    public void setNimi(StringBuilder nimi){
       super.setName(nimi);        
    }
    
    /** @return Tiedoston nimi ja koko merkkijonona.*/
    @Override
    public String toString(){
        return super.toString() + " " + getKoko();
    }    
}
