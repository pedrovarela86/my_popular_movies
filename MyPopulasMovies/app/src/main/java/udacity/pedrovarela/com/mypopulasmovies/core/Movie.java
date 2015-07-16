package udacity.pedrovarela.com.mypopulasmovies.core;

import java.io.Serializable;

/**
 * Created by pedro on 7/14/15.
 * <p>
 * Class Movie to map json response from the movie database
 */
public class Movie  implements Serializable{
    public boolean adult;
    public String backdrop_path;
    public int[] genre_ids;
    public int id;
    public String original_language;
    public String original_title;
    public String overview;
    public String release_date;
    public String poster_path;
    public String popularity;
    public String title;
    public boolean video;
    public float vote_average;
    public int vote_count;

    public String getPoster_path() {
        return "http://image.tmdb.org/t/p/w185"+poster_path;
    }
}
