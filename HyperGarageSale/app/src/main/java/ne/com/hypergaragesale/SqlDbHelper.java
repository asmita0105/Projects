package ne.com.hypergaragesale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class SqlDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "hyperGarageSale.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + SqlContract.SqlEntry.TABLE_NAME + " (" +
                    SqlContract.SqlEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    SqlContract.SqlEntry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    SqlContract.SqlEntry.COLUMN_NAME_PRICE + TEXT_TYPE + COMMA_SEP +
                    SqlContract.SqlEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE +COMMA_SEP+
                    SqlContract.SqlEntry.COLUMN_NAME_IMAGE +COMMA_SEP+
                    SqlContract.SqlEntry.COLUMN_NAME_LAT +COMMA_SEP+
                    SqlContract.SqlEntry.COLUMN_NAME_LONG +COMMA_SEP+
                    SqlContract.SqlEntry.COLUMN_NAME_ADDRESS  +" )";

    private  final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + SqlContract.SqlEntry.TABLE_NAME;
    public void delete(SQLiteDatabase db){
       // private String SQL_DELETE_COLUMN = "DELETE FROM " + SqlContract.SqlEntry.TABLE_NAME +" WHERE "+ SqlContract.SqlEntry._ID
    }

    public void recreateTable(SQLiteDatabase db) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public SqlDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
