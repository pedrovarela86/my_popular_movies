package udacity.pedrovarela.com.mypopularmovies.ui;

import android.app.Fragment;
import android.content.Intent;
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

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        RatingBar ratingBarMovieRating = (RatingBar) view.findViewById(R.id.ratingBarMovieRating);
        TextView textViewTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        TextView textViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        TextView textViewSynopsis = (TextView) view.findViewById(R.id.textViewMovieSynopsis);
        ImageView imageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        TextView textViewRating = (TextView) view.findViewById(R.id.textViewRating);

        Intent intent = getActivity().getIntent();
        if(intent!=null && intent.hasExtra("movieInfo")){
            Movie movie = (Movie) intent.getSerializableExtra("movieInfo");

            ratingBarMovieRating.setRating(movie.vote_average);
            textViewTitle.setText(movie.title);
            textViewSynopsis.setText(movie.overview);
            textViewReleaseDate.setText(movie.release_date);
            textViewRating.setText((int) movie.vote_average + "/10");
            Picasso.with(getActivity()).load(movie.getPoster_path()).into(imageViewMoviePoster);


        }

    }
}
