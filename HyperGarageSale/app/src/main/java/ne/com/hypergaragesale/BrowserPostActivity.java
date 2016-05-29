package ne.com.hypergaragesale;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserPostActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private LayoutInflater inflater;
    private Map<String, Data> dbData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        List<Data> data = readData(null); //fill_with_data();
        //String title = setData(mRecyclerView.getId());
        mAdapter = new Recycler_View_Adapter(getApplicationContext(),data);
        mRecyclerView.setAdapter(mAdapter);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //EditText editText = (EditText)findViewById(R.id.postTitle2);



       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == (R.id.fab)) {
                    sendMessage();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.new_post_menu, menu);
        /*SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }

    public void sendMessage() {
        startActivity(new Intent(this, newPost.class));

    }

    public List<Data>  readData(Context context){
        if (context == null) {
            context = getApplicationContext();
        }
        SqlDbHelper sqlDbHelper = new SqlDbHelper(context);
        SQLiteDatabase db = sqlDbHelper.getReadableDatabase();
        //sqlDbHelper.recreateTable(db);
        Log.i("Alok Alok ALok", "R");
        String[] projection = {
                SqlContract.SqlEntry.COLUMN_NAME_TITLE,
                SqlContract.SqlEntry.COLUMN_NAME_DESCRIPTION,
                SqlContract.SqlEntry.COLUMN_NAME_PRICE,
                SqlContract.SqlEntry._ID,
                SqlContract.SqlEntry.COLUMN_NAME_IMAGE,
                SqlContract.SqlEntry.COLUMN_NAME_ADDRESS
        };

        final String SELECT = "SELECT * FROM " + SqlContract.SqlEntry.TABLE_NAME;
        Cursor cursor = db.rawQuery(SELECT, null);
        Log.i("Asm","cursor"+cursor.getCount());
        Log.i("Asm",SELECT);
        List<Data> data = new ArrayList<>();
        String columnCount = " " + cursor.getColumnCount();
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_TITLE));
                String price = "$"+cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_PRICE));
                String desc =  cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_DESCRIPTION));
                String id = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry._ID));
                String imgUri = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_IMAGE));
                String address = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_ADDRESS));
                String lat = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_LAT));
                String lon = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_LONG));
                Data D = new Data(title,desc,price, Integer.parseInt(id),imgUri, address,lat,lon);
                data.add(D);
                Log.i("AAA ReadData " + id + " title " + title, " added");
                dbData.put(id, D);
            } while (cursor.moveToNext());
        }
        return  data;

    }
    /*This function delete data from data base and update recycleview on click of  delete Icon */
    public void delete(View imageView) {
        if(imageView.getId() == R.id.imageView) {
            SqlDbHelper sqlDbHelper = new SqlDbHelper(getApplicationContext());
            SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
            imageView.getId();
            int id = Integer.parseInt(imageView.getTag(R.string.id).toString());
            int pos = Integer.parseInt(imageView.getTag(R.string.pos).toString());
            final String DELETE = "DELETE FROM "  + SqlContract.SqlEntry.TABLE_NAME + " WHERE " +
                    SqlContract.SqlEntry._ID + " = " + id;
            db.execSQL(DELETE);
            Recycler_View_Adapter rcv = (Recycler_View_Adapter)mAdapter;
            rcv.removeDataPosition(pos);
            mAdapter.notifyItemRemoved(pos);
            mAdapter.notifyDataSetChanged();
        }
    }
   public Data getData(Context context, int ItemId){
       SqlDbHelper sqlDbHelper = new SqlDbHelper(context);
        SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
       readData(context);
      // int id = Integer.parseInt(itemView.getTag(R.string.id).toString());
       // int pos = Integer.parseInt(itemView.getTag(R.string.pos).toString());
       //ItemId =

       String read = "SELECT * FROM " +SqlContract.SqlEntry.TABLE_NAME+" WHERE " +
                SqlContract.SqlEntry._ID + " = " + ItemId;
       Log.i("AAA query ", read);
       Cursor cursor = db.rawQuery(read, null);
      List<Data> datas = new ArrayList<>();
     // if (cursor.moveToFirst()) {
       //    do {
       Data data = null;
       if (cursor != null) {
           if (cursor.moveToFirst()) {

               String title = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_TITLE));
               String price = "$" + cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_PRICE));
               String desc = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_DESCRIPTION));
               String id = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry._ID));
               String imgUri = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_IMAGE));
               String address = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_ADDRESS));
               String lat = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_LAT));
               String lon = cursor.getString(cursor.getColumnIndex(SqlContract.SqlEntry.COLUMN_NAME_LONG));
               data = new Data(title, desc, price, Integer.parseInt(id), imgUri, address,lat,lon);
           }
       }
         //      datas.add(D);
          // } while (cursor.moveToNext());
      // }
       //for(Data data:datas){
         //  title1 = data.getTitle();
       //}
        return data;

    }


}
