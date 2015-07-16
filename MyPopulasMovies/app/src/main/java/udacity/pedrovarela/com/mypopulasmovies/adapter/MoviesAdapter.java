package udacity.pedrovarela.com.mypopulasmovies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import udacity.pedrovarela.com.mypopulasmovies.R;
import udacity.pedrovarela.com.mypopulasmovies.core.Movie;
import udacity.pedrovarela.com.mypopulasmovies.listener.MovieItemListener;

/**
 * Created by pedro on 7/13/15.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>
{
    public MovieItemListener listener;

    public MoviesAdapter(Context context,  List<Movie> movieList,MovieItemListener callBack) {
        super(context, R.layout.item_movie_thumbnail, movieList);
        this.listener = callBack;
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
            holder = new ViewHolder();
            holder.imageViewMoviePoster = (ImageView) convertView.findViewById(R.id.imageViewMoviePoster);
            holder.textViewMovieTitle = (TextView) convertView.findViewById(R.id.textViewMovieTitle);


            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(getContext()).load(getItem(position).getPoster_path()).into(holder.imageViewMoviePoster);
        holder.imageViewMoviePoster.setEnabled(false);
        holder.textViewMovieTitle.setText(getItem(position).title);
        holder.textViewMovieTitle.setSelected(true);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onMovieSelected(getItem(position));
            }
        });

        return convertView;

    }

    /**
     * Class for ViewHolder pattern
     */
    class ViewHolder{
        ImageView imageViewMoviePoster;
        TextView textViewMovieTitle;
    }
}
