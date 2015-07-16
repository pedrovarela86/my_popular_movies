package udacity.pedrovarela.com.mypopularmovies.core;

import java.util.List;

/**
 * Created by pedro on 7/14/15.
 *
 * Class to represent a SearchResult
 */
public class SearchResult {
    public int page;
    public int total_pages;
    public int total_results;
    public List<Movie> results;
}
