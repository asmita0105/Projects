package ne.com.hypergaragesale;

import android.content.ClipData;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.
import android.support.v4.app.Fragment;


public class review_post extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Data data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_post);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_review);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);
        TextView titleText = (TextView) findViewById(R.id.postTitle2);
        TextView descText = (TextView) findViewById(R.id.postDesc2);
        TextView priceText = (TextView) findViewById(R.id.postprice2);
        TextView addressText = (TextView) findViewById(R.id.postaddress2);
        ImageView imgview = (ImageView)findViewById(R.id.imageView3);
        newPost post = new newPost();
        //Log.i("AAA onCreate " +)
        BrowserPostActivity browserPostActivity = new BrowserPostActivity();
        data = browserPostActivity.getData(this, getIntent().getIntExtra("itemId",0));
       titleText.setText(data.getTitle());
        descText.setText(data.getDescription());
        priceText.setText(data.getPrice());
        addressText.setText(data.getAddress());


        imgview.setImageBitmap(post.decodeSampledBitmapFromResource(data.getImgUri(),R.id.imageView3,500,500));

       //setContentView(R.mapView);
        /*MapView mapView = (MapView) findViewById(R.id.mapView);*/
       //MapView mapView = (MapView) findViewById(R.id.mapView);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //setContentView(R.layout.review_post_maps);
       // mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

        /*MapView mapView = (MapView) findViewById(R.id.mapView);
        mapView.getMap();*/
        //mapView.onCreate(savedInstanceState);
       // mMap = mapView.getMap();*/
        //mMap.getUiSettings().setMyLocationButtonEnabled(false);
       // mMap.setMyLocationEnabled(true);
        //setContentView(R.layout.);
        //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Double lat = Double.parseDouble(data.getLat());
        Double lon = Double.parseDouble(data.getLon());
        Log.i("lat ", lat+"  lonc " +lon);
        // Add a marker in Sydney, Australia, and move the camera.
        LatLng santaclara = new LatLng(lat, lon);
        mMap.addMarker(new MarkerOptions().position(santaclara).title("Marker in santa clara"));

        mMap.getUiSettings().setZoomControlsEnabled(true);
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
