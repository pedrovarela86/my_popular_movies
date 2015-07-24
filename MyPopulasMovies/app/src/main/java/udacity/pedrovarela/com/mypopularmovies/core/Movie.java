package udacity.pedrovarela.com.mypopularmovies.core;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pedro on 7/14/15.
 *
 * Class Movie to map json response from the movie database
 *
 * Implementing Parcelable, suggested documentation
 * http://www.developerphil.com/parcelable-vs-serializable/
 */
public class Movie implements Parcelable {
    public int id;
    public boolean adult;
    public String backdrop_path;
    public int[] genre_ids;
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

    /**
     * Parcelable creator
     */
    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {

        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public Movie() {
    }

    /**
     * Constructor from Parcel
     *
     * @param in
     */
    public Movie(Parcel in) {
        id = in.readInt();
        adult = in.readInt() == 1;
        backdrop_path = in.readString();
        genre_ids = in.createIntArray();
        original_language = in.readString();
        original_title = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        popularity = in.readString();
        title = in.readString();
        video = in.readInt() == 1;
        vote_average = in.readFloat();
        vote_count = in.readInt();
    }

    /**
     * @return
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Write to parcel
     *
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(adult ? 1 : 0);
        dest.writeString(backdrop_path);
        dest.writeIntArray(genre_ids);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(poster_path);
        dest.writeString(popularity);
        dest.writeString(title);
        dest.writeInt(video ? 1 : 0);
        dest.writeFloat(vote_average);
        dest.writeInt(vote_count);
    }
}
