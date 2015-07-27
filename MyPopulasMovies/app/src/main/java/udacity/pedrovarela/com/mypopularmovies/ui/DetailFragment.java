package udacity.pedrovarela.com.mypopularmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import udacity.pedrovarela.com.mypopularmovies.R;
import udacity.pedrovarela.com.mypopularmovies.core.Movie;
import udacity.pedrovarela.com.mypopularmovies.listener.MovieItemListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "movie_info";
    RatingBar ratingBarMovieRating;
    TextView textViewTitle;
    TextView textViewReleaseDate;
    TextView textViewSynopsis;
    ImageView imageViewMoviePoster;
    TextView textViewRating;
    private Movie mMovie;

    /**
     * Default empty constructor
     */
    public DetailFragment() {
    }

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            mMovie = getArguments().getParcelable(ARG_ITEM_ID);
        }
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    /**
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {



        ratingBarMovieRating = (RatingBar) view.findViewById(R.id.ratingBarMovieRating);
        textViewTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        textViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        textViewSynopsis = (TextView) view.findViewById(R.id.textViewMovieSynopsis);
        imageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        textViewRating = (TextView) view.findViewById(R.id.textViewRating);
        setMovieInformation(mMovie);
    }

    /**
     * Set up movie information
     *
     * @param movie
     */

    private void setMovieInformation(Movie movie) {

        if (movie != null) {
            ratingBarMovieRating.setRating(Math.round(movie.vote_average));
            textViewTitle.setText(movie.title);
            textViewSynopsis.setText(movie.overview);
            textViewReleaseDate.setText(movie.release_date);
            textViewRating.setText((int) movie.vote_average + "/10");
            Picasso.with(getActivity()).load(movie.getPoster_path()).into(imageViewMoviePoster);

            getActivity().setTitle(movie.title);
        }

    }


}
