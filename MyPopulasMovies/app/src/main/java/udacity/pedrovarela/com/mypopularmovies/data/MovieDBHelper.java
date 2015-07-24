package udacity.pedrovarela.com.mypopularmovies.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import udacity.pedrovarela.com.mypopularmovies.data.MovieContract.GenereIdEntry;
import udacity.pedrovarela.com.mypopularmovies.data.MovieContract.MovieEntry;

/**
 * Created by pedro on 7/23/15.
 * This class manages a local database for favorite movies data.
 */
public class MovieDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "com_pedro_my_favorite_movies.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * On database create
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_GENRE_ID_TABLE = "CREATE TABLE " +
                GenereIdEntry.TABLE_NAME +
                "(" +
                GenereIdEntry._ID + " INTEGER PRIMARY KEY," +
                GenereIdEntry.COLUMN_ID + " TEXT NOT NULL" +
                ")";

        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
                MovieEntry.TABLE_NAME +
                "(" +
                MovieEntry._ID + " INTEGER PRIMARY KEY, " +
                MovieEntry.COLUMN_ADULT + " INTEGER NOT NULL CHECK (" + MovieEntry.COLUMN_ADULT + " IN (0,1)), " +
                MovieEntry.COLUMN_BACKDROP_PATH + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_GENRE_ID + " INTEGER NOT NULL, " +
                MovieEntry.COLUMN_ORIGINAL_LANGUAGE + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_ORIGINAL_TITLE + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_OVERVIEW + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_RELEASE_DATE + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POSTER_PATH + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_POSTER_IMAGE + " BLOB, " +
                MovieEntry.COLUMN_POPULARITY + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                MovieEntry.COLUMN_VIDEO + " INTEGER NOT NULL CHECK (" + MovieEntry.COLUMN_ADULT + " IN (0,1)), " +
                MovieEntry.COLUMN_VOTE_AVERAGE + " REAL NOT NULL, " +
                MovieEntry.COLUMN_VOTE_COUNT + " INTEGER NOT NULL," +

                " FOREIGN KEY (" + MovieEntry.COLUMN_GENRE_ID +
                ") REFERENCES " +
                GenereIdEntry.TABLE_NAME +
                " (" + GenereIdEntry._ID +
                ")" +
                ")";

        db.execSQL(SQL_CREATE_GENRE_ID_TABLE);
        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    /**
     * On database update
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + GenereIdEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieEntry.TABLE_NAME);
        onCreate(db);

    }
}
