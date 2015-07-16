package udacity.pedrovarela.com.mypopulasmovies.ui;

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

import org.w3c.dom.Text;

import udacity.pedrovarela.com.mypopulasmovies.R;
import udacity.pedrovarela.com.mypopulasmovies.core.Movie;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    private RatingBar ratingBarMovieRating;
    private TextView textViewTitle;
    private ImageView imageViewMoviePoster;
    private TextView textViewReleaseDate,textViewRating;
    private TextView textViewSynopsis;

    private Movie movie;
    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ratingBarMovieRating = (RatingBar) view.findViewById(R.id.ratingBarMovieRating);
        textViewTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        textViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        textViewSynopsis = (TextView) view.findViewById(R.id.textViewMovieSynopsis);
        imageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        textViewRating = (TextView) view.findViewById(R.id.textViewRating);

        Intent intent = getActivity().getIntent();
        if(intent!=null && intent.hasExtra("movieInfo")){
            movie = (Movie) intent.getSerializableExtra("movieInfo");

            ratingBarMovieRating.setRating(movie.vote_average);
            textViewTitle.setText(movie.title);
            textViewSynopsis.setText(movie.overview);
            textViewReleaseDate.setText(movie.release_date);
            textViewRating.setText((int) movie.vote_average + "/10");
            Picasso.with(getActivity()).load(movie.getPoster_path()).into(imageViewMoviePoster);


        }

    }
}
