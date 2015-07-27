package udacity.pedrovarela.com.mypopulasmovies;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.util.Log;

import java.util.HashSet;

import udacity.pedrovarela.com.mypopularmovies.data.MovieContract;
import udacity.pedrovarela.com.mypopularmovies.data.MovieDBHelper;


public class TestDB extends AndroidTestCase {

    // Since we want each test to start with a clean slate
    void deleteTheDatabase() {
        mContext.deleteDatabase(MovieDBHelper.DATABASE_NAME);
    }

    /*
        This function gets called before each test is executed to delete the database.  This makes
        sure that we always have a clean test.
     */
    public void setUp() {
        deleteTheDatabase();
    }

    /*
        Students: Uncomment this test once you've written the code to create the Location
        table.  Note that you will have to have chosen the same column names that I did in
        my solution for this test to compile, so if you haven't yet done that, this is
        a good time to change your column names to match mine.

        Note that this only tests that the Location table has the correct columns, since we
        give you the code for the weather table.  This test does not look at the
     */
    public void testCreateDb() throws Throwable {
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)

        final HashSet<String> tableNameHashSet = new HashSet<>();
        tableNameHashSet.add(MovieContract.GenreIdEntry.TABLE_NAME);
        tableNameHashSet.add(MovieContract.MovieEntry.TABLE_NAME);

        mContext.deleteDatabase(MovieDBHelper.DATABASE_NAME);
        SQLiteDatabase db = new MovieDBHelper(
                this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error: This means that the database has not been created correctly",
                c.moveToFirst());

        // verify that the tables have been created
        do {
            tableNameHashSet.remove(c.getString(0));
        } while (c.moveToNext());

        // if this fails, it means that your database doesn't contain both the genryId entry
        // and movie entry tables
        assertTrue("Error: Your database was created without both the genreId entry and Movie entry tables",
                tableNameHashSet.isEmpty());

        // now, do our tables contain the correct columns?
        c = db.rawQuery("PRAGMA table_info(" + MovieContract.MovieEntry.TABLE_NAME + ")",
                null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> movieColumnHashSet = new HashSet<>();
        movieColumnHashSet.add(MovieContract.MovieEntry._ID);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ADULT);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_GENRE_ID);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_BACKDROP_PATH);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ORIGINAL_LANGUAGE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_OVERVIEW);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_POPULARITY);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_POSTER_IMAGE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_POSTER_PATH);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_RELEASE_DATE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_TITLE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_VIDEO);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_ORIGINAL_TITLE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_VOTE_AVERAGE);
        movieColumnHashSet.add(MovieContract.MovieEntry.COLUMN_VOTE_COUNT);
//        movieColumnHashSet.add(MovieContract.GenreIdEntry._ID);
        movieColumnHashSet.add(MovieContract.GenreIdEntry.COLUMN_ID);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            assertNotNull(columnName,c);
            movieColumnHashSet.remove(columnName);
        } while (c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required movie entry columns",
                movieColumnHashSet.isEmpty());

        c.close();
        db.close();
    }

}
