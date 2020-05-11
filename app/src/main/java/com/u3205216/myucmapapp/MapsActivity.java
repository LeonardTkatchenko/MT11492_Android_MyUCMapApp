package com.u3205216.myucmapapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

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
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        //TODO: Make marker variable names more explicit/less confusing. Use Hungarian Notation?
        //The 5 marker locations
        final LatLng library = new LatLng(-35.2379694,149.0835983);
        final LatLng studentCentre = new LatLng(-35.2389136, 149.0847174);
        final LatLng coffeeGrounds = new LatLng(-35.2389448, 149.0822645);
        final LatLng brumbiesRugby = new LatLng(-35.2383813, 149.0884262);
        final LatLng lab6B14 = new LatLng(-35.236254, 149.083987);

        //library marker
        final Marker ucLibrary = mMap.addMarker(new MarkerOptions()
                .title("UC Library")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_library))
                .snippet("24 Hr access for all students and staff")
                .position(library)
                .draggable(false));

        //Student Centre marker
        final Marker ucStdCentre = mMap.addMarker(new MarkerOptions()
                .title("Student Centre")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_std_centre))
                .snippet("Your gateway to access support and advice")
                .position(studentCentre)
                .draggable(false));

        //Coffee Grounds marker
        final Marker ucCoffee = mMap.addMarker(new MarkerOptions()
                .title("UC Cooper Lodge")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_coffee))
                .snippet("The Best Coffee on campus, underneath Cooper Lodge")
                .position(coffeeGrounds)
                .draggable(false));

        //Brumbies Rugby marker
        final Marker ucBrumbies = mMap.addMarker(new MarkerOptions()
                .title("UC Brumbies")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_rumbies))
                .snippet("Open to students, staff, and the general public.")
                .position(brumbiesRugby)
                .draggable(false));

        //Lab6B14 marker
        final Marker ucLab6B14 = mMap.addMarker(new MarkerOptions()
                .title("Innovation Lab")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_lab6b14))
                .snippet("Lab 6B14 in Building 6")
                .position(lab6B14)
                .draggable(false));

        //custom info window layout for different markers on map
        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            //inflate selected marker and fill with related information
            @Override
            public View getInfoContents(Marker marker) {
                //added suppression to remove "avoid passing 'null' as the view root" warning
                @SuppressLint("InflateParams") View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_window, null);

                TextView title = infoWindow.findViewById(R.id.textViewTitle);
                TextView snippet = infoWindow.findViewById(R.id.textViewSnippet);
                ImageView image = infoWindow.findViewById(R.id.imageView);

                //UC Library custom info window
                if (marker.getId().equals(ucLibrary.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable((R.drawable.library), getTheme()));

                }

                //UC Student Centre custom info window
                if (marker.getId().equals(ucStdCentre.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable((R.drawable.src), getTheme()));
                }

                //UC Coffee Grounds custom info window
                if (marker.getId().equals(ucCoffee.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable((R.drawable.coffee_grounds), getTheme()));
                }

                //UC Brumbies custom info window
                if (marker.getId().equals(ucBrumbies.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable((R.drawable.brumbies), getTheme()));
                }

                //UC Lab6B14 custom info window
                if (marker.getId().equals(ucLab6B14.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable((R.drawable.lab6b14), getTheme()));
                }

                return infoWindow;

            }
        });

        //uc center map location
        final LatLng uc = new LatLng(-35.23843, 149.0842616);
        //center google map on uc center
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

                //Paths between marker locations

                //Path from central to library:
                Polyline uc_central_library = googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(uc)
                        .add(new LatLng(-35.238330, 149.084180))
                        .add(new LatLng(-35.238246, 149.084117))
                        .add(new LatLng(-35.238227, 149.083599))
                        .add(library)
                );
                uc_central_library.setColor(R.color.colorBluePath);

                //Path from central to student centre:
                Polyline uc_central_stdCentre = googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(uc)
                        .add(studentCentre)
                );
                uc_central_stdCentre.setColor(R.color.colorBluePath);

                //Path from central to coffee grounds:
                Polyline uc_central_coffeegnds = googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(uc)
                        .add(new LatLng(-35.238636, 149.083690))
                        .add(new LatLng(-35.238625, 149.082324))
                        .add(new LatLng(-35.238933, 149.082319))
                        .add(coffeeGrounds)
                );
                uc_central_coffeegnds.setColor(R.color.colorBluePath);

                //Path from central to brumbies rugby:
                Polyline uc_central_brumbies = googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(uc)
                        .add(new LatLng(-35.238770, 149.085382))
                        .add(new LatLng(-35.238217, 149.085393))
                        .add(new LatLng(-35.238217, 149.088243))
                        .add(brumbiesRugby)

                );
                uc_central_brumbies.setColor(R.color.colorBluePath);

                //Path from central to innovation lab 6b14:
                Polyline uc_central_lab6b14 = googleMap.addPolyline(new PolylineOptions().geodesic(true)
                        .add(uc)
                        .add(new LatLng(-35.236937, 149.084229))
                        .add(new LatLng(-35.236937, 149.083638))
                        .add(new LatLng(-35.236254, 149.083641))
                        .add(lab6B14)

                );
                uc_central_lab6b14.setColor(R.color.colorBluePath);
            }
        });

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                //TODO: Improve Repetitious Code
                if (marker.getId().equals(ucLibrary.getId())) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "https://www.canberra.edu.au/library");
                    startActivity(intent);
                }
                if (marker.getId().equals(ucStdCentre.getId())) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "https://www.canberra.edu.au/current-students");
                    startActivity(intent);
                }
                if (marker.getId().equals(ucCoffee.getId())) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "https://www.unilodge.com.au/unilodge-uc-lodge-cooper-lodge");
                    startActivity(intent);
                }
                if (marker.getId().equals(ucBrumbies.getId())) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "https://www.canberra.edu.au/on-campus/facilities/sporting-facilities");
                    startActivity(intent);
                }
                if (marker.getId().equals(ucLab6B14.getId())) {
                    Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                    intent.putExtra("url", "https://www.canberra.edu.au/maps/buildings-directory/building-6");
                    startActivity(intent);
                }
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
