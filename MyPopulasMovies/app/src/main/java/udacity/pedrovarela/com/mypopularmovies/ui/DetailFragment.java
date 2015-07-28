package udacity.pedrovarela.com.mypopularmovies.ui;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;

import udacity.pedrovarela.com.mypopularmovies.R;
import udacity.pedrovarela.com.mypopularmovies.core.Movie;
import udacity.pedrovarela.com.mypopularmovies.core.Trailer;
import udacity.pedrovarela.com.mypopularmovies.core.TrailerSearchResult;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "movie_info";
    private final Type typeTrailerResult = new TypeToken<TrailerSearchResult>() {
    }.getType();
    RatingBar ratingBarMovieRating;
    TextView textViewReleaseDate;
    TextView textViewSynopsis;
    ImageView imageViewMoviePoster;
    TextView textViewRating;
    ListView listViewMovieTrailers;
    Movie mMovie;
    private String TAG = DetailFragment.class.getName();
    private ArrayAdapter<Trailer> trailerResultArrayAdapter;

    /**
     * Default empty constructor
     */
    public DetailFragment() {
    }

    /**
     * @param pm
     * @param url
     * @return
     */

    public static Intent getYouTubeIntent(PackageManager pm, String url) {
        Intent intent;
        if (url.length() == 11) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube://" + url));
        } else {
            // url to video
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + url));
        }

        return intent;
    }

    /**
     * @param savedInstanceState saved instance
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    /**
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState saved instance
     * @return Inflated view
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
     * @param view               View
     * @param savedInstanceState Saved instance
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ratingBarMovieRating = (RatingBar) view.findViewById(R.id.ratingBarMovieRating);
        textViewReleaseDate = (TextView) view.findViewById(R.id.textViewReleaseDate);
        textViewSynopsis = (TextView) view.findViewById(R.id.textViewMovieSynopsis);
        textViewSynopsis.setMovementMethod(new ScrollingMovementMethod());
        imageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
        textViewRating = (TextView) view.findViewById(R.id.textViewRating);

        listViewMovieTrailers = (ListView) view.findViewById(R.id.listViewMovieTrailer);
        trailerResultArrayAdapter = new ArrayAdapter<>(getActivity(), R.layout.item_trailer, R.id.textViewTrailer);
        listViewMovieTrailers.setAdapter(trailerResultArrayAdapter);
        listViewMovieTrailers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                playVideo((Trailer) parent.getItemAtPosition(position));
            }
        });

        if (mMovie != null) {
            new FetchMovieTrailers().execute();
            setMovieInformation(mMovie);
        }

    }

    private void playVideo(Trailer itemAtPosition) {

        Uri uri = Uri.parse("http://www.youtube.com/watch?v=" + itemAtPosition.key);

        Intent videoIntent = new Intent(Intent.ACTION_VIEW, uri);
        //force chooser
//        Intent chooser = Intent.createChooser(videoIntent, getString(R.string.trailer_chooser));
        startActivity(videoIntent);

//        final PackageManager pm = getActivity().getPackageManager();
//        List<ResolveInfo> activityList = pm.queryIntentActivities(videoIntent, 0);
//        for (int i = 0; i < activityList.size(); i++) {
//            ResolveInfo app = activityList.get(i);
//            if (app.activityInfo.name.contains("youtube")) {
//                videoIntent.setClassName(app.activityInfo.packageName, app.activityInfo.name);
//                startActivity(videoIntent);
//            }else{
//            Intent chooser = Intent.createChooser(videoIntent, "Choose from");
//            startActivity(chooser);
//            }
//        }
    }

    /**
     * Set movie information
     *
     * @param movie movie information
     */

    private void setMovieInformation(Movie movie) {

        if (movie != null) {
            ratingBarMovieRating.setRating(movie.vote_average);
            textViewSynopsis.setText(movie.overview);
            textViewReleaseDate.setText(movie.release_date);
            textViewRating.setText(movie.vote_average + "/10");
            Picasso.with(getActivity()).load(movie.getPoster_path()).into(imageViewMoviePoster);

            getActivity().setTitle(movie.title);
        }

    }

    /**
     * AsyncTask class to fetch movies from database
     */
    private class FetchMovieTrailers extends AsyncTask<String, Void, TrailerSearchResult> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected TrailerSearchResult doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String trailerJSONString = null;
            TrailerSearchResult trailerSearchResult;

            String base_url = MessageFormat.format("http://api.themoviedb.org/3/movie/{0}/videos", mMovie.id);

            try {

                Uri builtUri = Uri.parse(base_url).buildUpon().appendQueryParameter("api_key", "9207151e0751c013d2d36ff02d6cab7a").build();

                URL url = new URL(builtUri.toString());
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                // Read the input stream into a String
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }

                trailerJSONString = buffer.toString();

            } catch (IOException e) {
                Log.e(TAG, "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(TAG, "Error closing stream", e);
                    }
                }
            }

            Gson gson = new Gson();
            trailerSearchResult = gson.fromJson(trailerJSONString, typeTrailerResult);
            return trailerSearchResult;
        }

        /**
         * @param trailerSearchResult Trailer search result
         */
        @Override
        protected void onPostExecute(TrailerSearchResult trailerSearchResult) {
            trailerResultArrayAdapter.clear();
            if (trailerSearchResult != null) {
                trailerResultArrayAdapter.addAll(trailerSearchResult.results);
            }
        }
    }

}