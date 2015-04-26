package bestest.coderz.job.hunter;

/**
 * Created by tomislav on 26.04.15..
 */

/*
* Sort by key: 

+ Options
naslov
opisPosla
idTvrtke
idPodrucja
trazeniTagovi
pbrMjesta
idIzvor
idZupanija

* */
public class Oglas {
    
    String naslov;
    String opisPosla;
   String tvtrka;
    String podrucje;
    String tagovi;
    String pbrMjesto;
    String izvor;
    String zupanija;
    
    
    public Oglas(
            String naslov,
            String opisPosla,
            String tvtrka,
            String podrucje,
            String tagovi,
            String pbrMjesto,
            String izvor,
            String zupanija
    )
    {
        this. naslov=naslov;
        this. opisPosla=opisPosla;
        this. tvtrka=tvtrka;
        this. podrucje=podrucje;
        this. tagovi=tagovi;
        this. pbrMjesto=pbrMjesto;
        this. izvor=izvor;
        this. zupanija=zupanija;

    }
    
}
