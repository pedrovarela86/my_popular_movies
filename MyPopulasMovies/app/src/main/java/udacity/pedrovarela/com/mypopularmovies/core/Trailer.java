package udacity.pedrovarela.com.mypopularmovies.core;

/**
 * Created by pedro on 7/27/15.
 * Class to represent a trailer
 */
public class Trailer {

    public String id;
    public String iso_639_1;
    public String key;
    public String name;
    public String site;
    public String size;
    public String type;

    @Override
    public String toString() {
        return name;
    }
}
