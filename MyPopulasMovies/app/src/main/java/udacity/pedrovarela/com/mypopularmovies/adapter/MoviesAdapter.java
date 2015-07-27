package udacity.pedrovarela.com.mypopularmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import udacity.pedrovarela.com.mypopularmovies.R;
import udacity.pedrovarela.com.mypopularmovies.core.Movie;
import udacity.pedrovarela.com.mypopularmovies.listener.MovieItemListener;

/**
 * Created by pedro on 7/13/15.
 * Movies adapter used in the grid view
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_movie_thumbnail,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(getItem(position).getPoster_path()).into(holder.imageViewMoviePoster);
        holder.imageViewMoviePoster.setEnabled(false);
        holder.textViewMovieTitle.setText(getItem(position).title);
        holder.textViewMovieTitle.setSelected(true);
        return convertView;

    }

    /**
     * Class for ViewHolder pattern
     */
    class ViewHolder{
        public final ImageView imageViewMoviePoster;
        public final TextView textViewMovieTitle;

        public ViewHolder(View view) {
            this.imageViewMoviePoster = (ImageView) view.findViewById(R.id.imageViewMoviePoster);
            this.textViewMovieTitle = (TextView) view.findViewById(R.id.textViewMovieTitle);
        }
    }
}
