package ne.com.hypergaragesale;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URI;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

public class newPost extends AppCompatActivity {
    public static final int REQUEST_CAPTURE = 1;
    ImageView result_photo;
    ImageView result_photo2;
    int counter = 0;
    String imageUri = "asm";
    GpsTracker gpsTracker = new GpsTracker(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Button capture = (Button) findViewById(R.id.capture);
        result_photo = (ImageView) findViewById(R.id.imageView);
        addLocation();
        // result_photo2=(ImageView)findViewById(R.id.imageView2);
        // result_photo.setImageBitmap(decodeSampledBitmapFromResource(getResources(), R.id.imageView, 100, 100));
        if (!(getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY))) {
            capture.setEnabled(false);
        }

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String Uri, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // BitmapFactory.decodeResource(res, resId, options);
        BitmapFactory.decodeFile(Uri, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        //return BitmapFactory.decodeResource(res, resId, options);
        return BitmapFactory.decodeFile(Uri, options);
    }


    private String createDirectoryAndSaveFile(Bitmap imageToSave, String fileName) {


        //Log.e("diralok " , Environment.getExternalStorageDirectory().toString());
        File direct = new File(Environment.getExternalStorageDirectory() + "/Download");


        if (!direct.exists()) {
            File wallpaperDirectory = new File("/sdcard/Download/");
            wallpaperDirectory.mkdirs();
        }

        File file = new File(new File("/sdcard/Download/"), fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);
            //String uri = file.getAbsolutePath();
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    // check for intent
    /*public static boolean isIntentAvailable(Context context, String action) {

        final PackageManager packageManager = context.getPackageManager();

        final Intent intent = new Intent(action);

        List<ResolveInfo> list =

                packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);

        return list.size() > 0;

    }*/
    //launch camera
    public void takePictureIntent(View view) {

        Intent takepicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //if (isIntentAvailable(getApplicationContext(),MediaStore.ACTION_IMAGE_CAPTURE)){
        // if (counter<=2){
        startActivityForResult(takepicture, REQUEST_CAPTURE);
        // counter+=1;
        // }
    }

    //create thumbnail
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.MILLISECOND);
        if (requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageUri = createDirectoryAndSaveFile(imageBitmap, "newfile" + seconds);
            //String Uri2= "/sdcard/Download/newfile"+seconds;
            // Uri u = data.getData();
            result_photo.setImageBitmap(decodeSampledBitmapFromResource(imageUri, R.id.imageView, 100, 100));

            //result_photo2.setImageBitmap(decodeSampledBitmapFromResource(Uri2, R.id.imageView2, 100, 100));
            //result_photo.setImageDrawable(Drawable.createFromPath("/sdcard/Download/newfile"));

            // loadBitmap(R.id.imageView,result_photo);

        }/*else if(requestCode == REQUEST_CAPTURE && resultCode == RESULT_OK && counter==2){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageUri=createDirectoryAndSaveFile( imageBitmap,"newfile"+seconds);
            Log.e("imagemap : "+imageBitmap," newfile"+seconds);
            //String Uri = "/sdcard/Download/newfile"+seconds;
            String Uri2= "/sdcard/Download/newfile"+seconds;

            //result_photo.setImageBitmap(decodeSampledBitmapFromResource(Uri, R.id.imageView, 100, 100));
            result_photo2.setImageBitmap(decodeSampledBitmapFromResource(Uri2, R.id.imageView2, 100, 100));

        }*/
    }

   /* public void loadBitmap(int resId, ImageView imageView) {

        BitmapWorkerTask task = new BitmapWorkerTask(imageView);

        task.execute(resId);

    }*/

    private void showSnackBar(View v) {
        if (v == null) {
            Snackbar.make(findViewById(R.id.my_toolbar), R.string.new_post_snackbar,
                    Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(v, R.string.new_post_snackbar,
                    Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_post_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_post) {
            showSnackBar(null);
            sendData();
        }
        return super.onOptionsItemSelected(item);
    }

    public long sendData() {
        //startActivity(new Intent(this, BrowserPostActivity.class)); //explicit Intent

        SqlDbHelper sqlDbHelper = new SqlDbHelper(getApplicationContext());

        SQLiteDatabase db = sqlDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        EditText titleEntered = (EditText) findViewById(R.id.Itemtitle);
        String title = titleEntered.getText().toString();
        EditText desEntered = (EditText) findViewById(R.id.editTextDis);
        String desc = desEntered.getText().toString();
        EditText priceEntered = (EditText) findViewById(R.id.editTextPrice);
        String price = priceEntered.getText().toString();
        //int intPrice = Integer.parseInt(price);
        String address = addLocation();
        String[] latLong = getLocation();
        String lat= latLong[0];
        String lon= latLong[1];
        Log.i("Data  lat ", lat+"  lonc " +lon);
        //URI uriImg =
        values.put(SqlContract.SqlEntry.COLUMN_NAME_DESCRIPTION, desc);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_TITLE, title);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_PRICE, price);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_IMAGE, imageUri);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_LAT, lat);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_LONG, lon);
        values.put(SqlContract.SqlEntry.COLUMN_NAME_ADDRESS,address);
        return db.insert(SqlContract.SqlEntry.TABLE_NAME, null, values);
    }

    public String addLocation() {
        gpsTracker.getLocation();
        Double latitude =gpsTracker.getLatitude();
        Double logitide = gpsTracker.getLongitude();
        //String addresline = gpsTracker.getAddressLine(this);
        //String postalCode = gpsTracker.getPostalCode(this);
        //String CountryName= gpsTracker.getCountryName(this);
        String locality = gpsTracker.getLocality(this);
        //Log.i("AAA"," address :"+addresline);
        //Log.i("BBB"," address :"+postalCode);

        //String address = addresline+ addresline+CountryName;
        return locality;
        /*Location location;
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();*/
    }
    public String[] getLocation(){
        gpsTracker.getLocation();
        String[] loc = new String[2];
        Double latitude =gpsTracker.getLatitude();
        Double logitide = gpsTracker.getLongitude();
        loc[0]= latitude.toString();
        loc[1]=logitide.toString();
        Log.i("Double lat ", latitude+"  lonc " +logitide);
        return  loc;
    }



}



