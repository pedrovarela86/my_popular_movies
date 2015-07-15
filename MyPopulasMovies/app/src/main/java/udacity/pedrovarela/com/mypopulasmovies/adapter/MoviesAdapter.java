package udacity.pedrovarela.com.mypopulasmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import udacity.pedrovarela.com.mypopulasmovies.R;
import udacity.pedrovarela.com.mypopulasmovies.core.Movie;

/**
 * Created by pedro on 7/13/15.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>
{

    public MoviesAdapter(Context context,  List<Movie> movieList) {
        super(context, R.layout.item_movie_thumbnail, movieList);
    }

    /**
     * Method getView to render data into the GridView
     * @param position current position of list
     * @param convertView the view
     * @param parent parent object
     * @return the rendered View
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_movie_thumbnail,parent,false);
            holder = new ViewHolder();
            holder.imageViewMoviePoster = (ImageView) convertView.findViewById(R.id.imageViewMoviePoster);
            holder.textViewMovieTitle = (TextView) convertView.findViewById(R.id.textViewMovieTitle);
            holder.ratingBarMovieRating = (RatingBar) convertView.findViewById(R.id.ratingBarMovieRating);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(getItem(position).getPoster_path()).into(holder.imageViewMoviePoster);
        holder.textViewMovieTitle.setText(getItem(position).title);
        holder.textViewMovieTitle.setSelected(true);
        holder.ratingBarMovieRating.setRating(getItem(position).vote_average);


        return convertView;

    }

    /**
     * Class for ViewHolder pattern
     */
    class ViewHolder{
        ImageView imageViewMoviePoster;
        TextView textViewMovieTitle;
        RatingBar ratingBarMovieRating;
    }
}
