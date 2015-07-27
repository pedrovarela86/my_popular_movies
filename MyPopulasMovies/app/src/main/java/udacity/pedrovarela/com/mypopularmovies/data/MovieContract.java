package udacity.pedrovarela.com.mypopularmovies.data;

import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by pedro on 7/23/15.
 * <p>
 * The MovieContract defines table and column names for the movies database.
 */
public class MovieContract {
    /**
     * To make it easy to query for the exact date, we normalize all dates that go into
     * the database to the start of the the Julian day at UTC.
     */
    public static long normalizeDate(long startDate) {
        // normalize the start date to the beginning of the (UTC) day
        Time time = new Time();
        time.set(startDate);
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return time.setJulianDay(julianDay);
    }


    /**
     * Inner class that defines the table contents of the movie table
     */
    public static final class MovieEntry implements BaseColumns {
        public static final String TABLE_NAME = "movie";

        public static final String COLUMN_ADULT = "adult";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        public static final String COLUMN_GENRE_ID = "genre_id";
        public static final String COLUMN_ORIGINAL_LANGUAGE = "original_language";
        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_POSTER_IMAGE = "poster_image";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_VIDEO = "video";
        public static final String COLUMN_VOTE_AVERAGE = "average";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
    }

    /**
     * Inner class that defines the table contents of the genreId    table
     */
    public static final class GenreIdEntry implements BaseColumns {
        public static final String TABLE_NAME = "genre_id";

        public static final String COLUMN_ID = "id ";
    }


}
