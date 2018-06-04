package oope2017ht;
import apulaiset.In;

/** Konkreettinen Kayttoliittyma luokka.
  * Luokka pyytää ja lukee käyttäjän syötteet (In-luokkaa kutsuen), sekä kutsuu
  * Tulkki-luokan käyttäjän syötettä vastaavaa metodia. Ohjelman pääsilmukka
  * on tässä luokassa.
  * <p>
  * Harjoitustyö, Olio-Ohjelmoinnin perusteet, kevät 2017.
  * <p>
  *
  * @author Okko Partanen (Partanen.Okko.A@student.uta.fi).
  * Informaatiotieteiden yksikkö, Tampereen yliopisto.
  */
class Kayttoliittyma {
    
    /** Luokansio komento. */
    private final String LUOKANSIO = "md";
    /** Hakemiston vaihto komento. */
    private final String VAIHDA = "cd";
    /** Tiedoston luonti komento. */
    private final String LUOTIEDOSTO = "mf";
    /** Tiedon uudelleen nimeäminen komento. */
    private final String NIMEA = "mv";
    /** Hakemiston listaaminen, tai yksittäisen Tiedon listaaminen Hakemistosta (kuormitettu). */
    private final String LISTAA = "ls";
    /** Tiedoston kopiointi komento. */
    private final String KOPIOI = "cp";
    /** Tiedon poisto komento. */
    private final String POISTA = "rm";
    /** Rekursiivinen listaus. */
    private final String REKURSIIVINEN = "find";
    /** Pääsilmukan (ja ohjelma suorituksen) lopettava komento. */
    private final String LOPETA = "exit";
    /** Virhe teksti. */
    private final String VIRHE ="Error!";
    /** Totuusarvo joka määrittää jatketaanko pääsilmukan suorittamista. */
    private boolean jatka = true;
    /** Tulkki, käsittelee Hakemistoa. */    
    private final Tulkki TULKKI = new Tulkki();    
    
    /** Ohjelman pääsilmukka, suoritetaan kunnes käyttäjä antaa
      * lopetus komennon "exit". Kutsuu kutsuTulkkia(käyttäjänsyöte) ja
      * kehota(). Sieppaa mahdollisen IllegalArgumentException virheen.
      */
    void suorita() {   
        System.out.println("Welcome to SOS.");
        //Suoritetaan kunnes käyttäjä lopettaa.
        while (jatka) {            
            kehota();
            String cmd = In.readString();
            if (cmd.equals(LOPETA)) {
                jatka = false;
                System.out.println("Shell terminated.");                
            } else {
                try {
                    //Jos tulkin kutsuminen palauttaa totuusarvon false, tulostetaan virhe.
                    if (!kutsuTulkkia(cmd))
                        System.out.println(VIRHE);
                //Siepataan mahdollinen IllegalArgumentException.
                } catch (IllegalArgumentException e) {                    
                    System.out.println(VIRHE);                    
                }
            }
        }
    }      
    
    /**
      * Tulostaa kehotusmerkin" '>' ja nykyisen hakemistopolun kutsumalla
      * tulkin tulostapolku() metodia. Metodia kutsutaan pääsilmukasta aina kun 
      * syöte on luettu ja tulkkia on kutsuttu.
      */
    private void kehota() {
        TULKKI.tulostaPolku();
        System.out.print(">");
    }   
    
    /** Kutsuu tulkin metodeja, käyttäjän syötteen perusteella.
      * Ei hyväksy laitonta syötettä (mm. liikaa välilyöntejä tai pelkkä piste).
      * Kutsuu boolean syoteOk(argument) tarkistaakseen syötteen.
      * 
      * @param argument käyttäjän antama syöte.
      * @return true jos syöte oli laillinen ja toiminto suoritettavissa, false jos ei.
      */
    private boolean kutsuTulkkia(String argument){
        if (!syoteOk(argument))
            return false;
        //Pilkotaan syöte tyhjämerkkien kohdalta.
        String[] args = argument.split("\\s+");
        //Haluttu toiminto on taulukon ensimmäisessä indeksissä.
        String toiminto = args[0];
        int pituus = args.length;
        
        //Kutsutaan tulkkia, tarkastetaan myös että syötteen pituus on laillinen
        if (toiminto.equals(LUOKANSIO) && pituus == 2){
            return TULKKI.luoKansio(args[1]);
        }
        else if(toiminto.equals(LUOTIEDOSTO) && pituus == 3){
            return TULKKI.luoTiedosto(args[1], Integer.parseInt(args[2]));
        }
        else if (toiminto.equals(POISTA) && pituus == 2){
            return TULKKI.poista(args[1]);
        }
        //Pituus == 1, kutsutaan listaa().
        else if (toiminto.equals(LISTAA) && pituus == 1){
            TULKKI.listaa();
            //Toiminto "onnistuu" aina, joten palautetaan true.
            return true;
        }
        //Jos pituus == 2, kutsutaan kuormitettua listaa metodia.
        else if (toiminto.equals(LISTAA) && pituus == 2){
            //Palauttaa true jos onnistui, jos ei onnistu Hakemisto-luokka heittää virheen.
            TULKKI.listaa(args[1]);
            return true;
        }
        //Jos syöte oli pelkkä "cd".
        else if (toiminto.equals(VAIHDA) && pituus == 1){
            return TULKKI.vaihda(args[0]);            
        }
        //Jos käyttäjän syötteen pituus oli 2, kutsutaan vaihda metodia taulukon 2. parametrilla.
        else if (toiminto.equals(VAIHDA) && pituus == 2){
            return TULKKI.vaihda(args[1]);           
        }        
        else if (toiminto.equals(NIMEA) && pituus == 3){
            return TULKKI.nimea(args[1], args[2]);
        }
        else if (toiminto.equals(REKURSIIVINEN) && pituus == 1){
            TULKKI.listaaRekursiivisesti();
            //rekursiivinen listaaminen ei voi myöskään epäonnistua.
            return true;
        }
        else if (toiminto.equals(KOPIOI) && pituus == 3){
            return TULKKI.kopioi(args[1], args[2]);
        }
        //Jos syöte ei vastannut tuettua toimintoa, palautetaan false.
        else
            return false;
    }
    
    /** Tarkistaa ettei käyttäjän syötteessä ollut ylimääräisiä tyhjämerkkejä,
      * tai ettei se koostunut pelkästä pisteestä.
      * 
      * @param argument käyttäjän syöte.
      * @return boolean true jos syöte oli laillinen, false jos ei.
      */
    private boolean syoteOk(String argument){
        int spacecount = 0;
        //Lasketaan tyhjämerkkien määrä
        for (int i=0;i<argument.length();i++){
            if (argument.charAt(i)==(' '))
                spacecount++;
        } 
        //Pilkotaan syöte, ja määritetään taulukon pituus.
        String[] args = argument.split("\\s+");        
        int pituus = args.length;
        //Jos syöte oli pelkkä piste tai siinä oli tabulatoori merkki palutetaan false.
        if (argument.equals(".") || argument.contains("\t"))
            return false;  
        else
            //Välilyöntejä tulee olla yksi vähemmän kuin komentoja.
            return pituus-1 == spacecount;        
    }
}
