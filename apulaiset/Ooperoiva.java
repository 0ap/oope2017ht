package apulaiset;

/**
  * Pakolliset uudet listaoperaatiot m��ritelev� rajapinta.
  * <p>
  * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017.
  * <p>
  * @author Jorma Laurikkala (jorma.laurikkala@uta.fi), Luonnontieteiden tiedekunta,
  * Tampereen yliopisto.
  *
  */

public interface Ooperoiva {

   /** Tutkii onko listalla haettavaa oliota equals-mieless� vastaava alkio,
     * joita oletetaan olevan korkeintaan yksi kappale.
     *
     * Jos parametri liittyy esimerkiksi merkkijonoon "ab" ja listan tietoalkiot
     * ovat [ "AB, "Ab", "aB", "ab" ], palauttaa operaatio viitteen viimeiseen
     * tietoalkioon, koska "ab".equals("ab") == true.
     *
     * @param haettava listalta haettava alkio, jonka luokan tai luokan esivanhemman
     * oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite l�ydettyyn alkioon. Paluuarvo on null, jos vastaavaa
     * alkiota ei l�ydy, parametri on null-arvoinen tai lista on tyhj�.
     */
   public abstract Object hae(Object haettava);

   /** Listan alkioiden v�lille muodostuu kasvava suuruusj�rjestys, jos lis�ys
     * tehd��n vain t�ll� operaatiolla, koska uusi alkion lis�t��n listalle siten,
     * ett� alkio sijoittuu kaikkien itse��n pienempien tai yht� suurien alkioiden
     * j�lkeen ja ennen kaikkia itse��n suurempia alkioita. Alkioiden suuruusj�rjestys
     * selvitet��n Comparable-rajapinnan compareTo-metodilla.
     * 
     * Jos parametri liittyy esimerkiksi kokonaislukuun 2 ja listan tietoalkiot
     * ovat [ 0, 3 ], on listan sis�lt� lis�yksen j�lkeen [ 0, 2, 3 ],
     * koska 0 < 2 < 3.
     *
     * @param uusi viite olioon, jonka luokan tai luokan esivanhemman oletetaan
     * toteuttaneen Comparable-rajapinnan.
     * @return true, jos lis�ys onnistui ja false, jos uutta alkiota ei voitu
     * vertailla. Vertailu ep�onnistuu, kun parametri on null-arvoinen
     * tai sill� ei ole vastoin oletuksia Comparable-rajapinnan toteutusta.
     */
   public abstract boolean lisaa(Object uusi);
   
   /** Poistaa listalta annettua oliota equals-mieless� vastaavan alkion,
     * joita oletetaan olevan korkeintaan yksi kappale.
     * 
     * Jos parametri liittyy esimerkiksi merkkijonoon "aB" ja listan tietoalkiot
     * ovat [ "AB, "Ab", "aB", "ab" ], on listan sis�lt� poiston j�lkeen
     * [ "AB, "Ab", "ab" ] ja operaatio palauttaa viitteen alkuper�isen listan
     * kolmanteen tietoalkioon, koska "aB".equals("aB") == true.
     *     
     * @param poistettava viite poistettavaan tietoalkioon, jonka luokan
     * tai luokan esivanhemman oletetaan korvanneen Object-luokan equals-metodin.
     * @return viite poistettuun tietoalkioon. Paluuarvo on null, jos poistettavaa
     * alkiota ei l�ydy, parametri on null-arvoinen tai lista on tyhj�.
     */
   public abstract Object poista(Object poistettava);
}
