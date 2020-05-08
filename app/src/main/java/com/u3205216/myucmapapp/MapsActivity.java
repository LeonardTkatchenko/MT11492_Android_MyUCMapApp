package com.u3205216.myucmapapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //requirement: "The activity title must be UC Map"
        setTitle("UC Map");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng uc = new LatLng(-35.2384551,149.0844455);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(uc, 13));

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                // 15 GPS Coordinates for UC border line
                ArrayList<LatLng> coordListUC = new ArrayList<>();

                //TODO: Improve Repetitious Code
                coordListUC.add(new LatLng(-35.231033, 149.080434));
                coordListUC.add(new LatLng(-35.231333, 149.082150));
                coordListUC.add(new LatLng(-35.231812, 149.083885));
                coordListUC.add(new LatLng(-35.233775, 149.087533));
                coordListUC.add(new LatLng(-35.234359, 149.089354));
                coordListUC.add(new LatLng(-35.234809, 149.091897));
                coordListUC.add(new LatLng(-35.240098, 149.090168));
                coordListUC.add(new LatLng(-35.242076, 149.090168));
                coordListUC.add(new LatLng(-35.242431, 149.088212));
                coordListUC.add(new LatLng(-35.242481, 149.077551));
                coordListUC.add(new LatLng(-35.240925, 149.074851));
                coordListUC.add(new LatLng(-35.237956, 149.077039));
                coordListUC.add(new LatLng(-35.235509, 149.077713));
                coordListUC.add(new LatLng(-35.234003, 149.078477));
                coordListUC.add(new LatLng(-35.232478, 149.079656));

                //add all LatLng coordinates for polygon to outline UC
                Polygon uc_polygon = mMap.addPolygon(new PolygonOptions().geodesic(true)
                        .addAll(coordListUC)
                );

                //fill entire campus area with colour
                uc_polygon.setFillColor(getResources().getColor(R.color.colorFilledArea, getTheme()));
                //set campus area outline colour
                uc_polygon.setStrokeColor(getResources().getColor(R.color.colorLightBorder, getTheme()));

                //event listener for click events when user taps on a location within the UC Map
                uc_polygon.setClickable(true);
                mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
                    @Override
                    // user taps on a location within UC Map
                    public void onPolygonClick(Polygon polygon) {
                        // Change campus border to dark color
                        //NOTE: No requirement to turn border back to light color
                        polygon.setStrokeColor(getResources().getColor(R.color.colorDarkBorder, getTheme()));
                        //display 'University of Canberra' Toast for 2 seconds
                        Toast.makeText(getApplicationContext(),"University of Canberra", Toast.LENGTH_SHORT).show();
                    }
                });

                // Get the bounds of the campus then zoom to it
                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                for (LatLng coordinate : coordListUC){
                    builder.include(coordinate);
                }
                LatLngBounds bounds = builder.build();

                int padding = 20; //padding between nMap and edges
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.map_normal:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_satellite:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
