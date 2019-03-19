package apulaiset;

// Otetaan k�ytt��n lista-pakkauksen tyyppi.
import fi.uta.csjola.oope.lista.*;

/**
  * Tulkin komentojen toteuttamiseen ja luokkahierarkian testaamiseen soveltuvia
  * metodeja. Kiinnit� geneerinen tyyppi T tyypiksi Tieto, kun toteutat t�m�n
  * rajapinnan Hakemisto-luokassa.
  * <p>
  * Harjoitusty�, Olio-ohjelmoinnin perusteet, kev�t 2017.
  * <p>
  * @author Jorma Laurikkala (jorma.laurikkala@uta.fi), Luonnontieteiden tiedekunta,
  * Tampereen yliopisto.
  *
  */

public interface Komennettava<T> {

   /** Aksessori, joka antaa viitteen hakemiston sis�ll�n s�il�v��n listaan.
     *
     * @return viite hakemisto-olion osaolioon.
     */   
   abstract public LinkitettyLista sisalto();

   /** Hakee hakemistosta tiedostoa tai alihakemistoa. Hy�dynt�� OmaLista-luokan
     * hae-operaatiota. Huomaa, ett� nime� k�ytt�en haun avuksi voidaan luoda
     * v�liaikainen tiedosto tai hakemisto.
     *
     * @param nimi haettavan tiedon nimi.
     * @return viite l�ydettyyn tietoon. Paluuarvo on null, jos tietoa ei l�ydet�.
     */
   abstract public T hae(String nimi);

   /** Lis�� hakemistoon tiedoston tai alihakemiston. Hy�dynt�� OmaLista-luokan
     * lisaa-operaatiota.
     *
     * @param lisattava viite lis�tt�v��n tietoon.
     * @return true, jos lis��minen onnistui ja false, jos tieto on null-arvoinen
     * tai hakemistossa on jo tieto parametrina annetulla nimell�.
     */
   abstract public boolean lisaa(T lisattava);

   /** Poistaa hakemistosta tiedoston tai alihakemiston. Hy�dynt�� OmaLista-luokan
     * poista-operaatiota. Huomaa, ett� nime� k�ytt�en poiston avuksi voidaan luoda
     * v�liaikainen tiedosto tai hakemisto.
     *
     * @param nimi poistettavan tiedon nimi.
     * @return viite poistettuun tietoon. Paluuarvo on null, jos tietoa ei l�ydet�.
     */
   abstract public T poista(String nimi);
}
