package bestest.coderz.job.hunter;

/**
 * Created by tomislav on 23.04.15..
 */
public class Filter {


    String naziv;
    String tagovi;
    int podrucje;
    int lokacija;

    public Filter(String naziv,String tagovi,int podrucje,int lokacija)
    {
        this.tagovi=tagovi;
        this.naziv=naziv;
        this.podrucje=podrucje;
        this.lokacija=lokacija;

    }
}
