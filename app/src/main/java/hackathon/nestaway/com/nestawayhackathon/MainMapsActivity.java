package hackathon.nestaway.com.nestawayhackathon;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.Vector;

public class MainMapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    NestAwayHousesResponse mData;
    Projection projection;
    public double latitude;
    public double longitude;
    Vector<data> tuchPoints;
    FrameLayout fram_map;
    Button btn_draw_State;
    Boolean Is_MAP_Moveable = false; // to detect map is movable
    Vector<LatLng> val;
    ArrayList<Polygon> drawings;
    ArrayList<Marker> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);

        setUpMapIfNeeded();
        fram_map = (FrameLayout) findViewById(R.id.fram_map);
        btn_draw_State = (Button) findViewById(R.id.btn_draw_State);
        val = new Vector<LatLng>();
        getDataFromVolley();
        markers = new ArrayList<Marker>();
        drawings = new ArrayList<Polygon>();
        btn_draw_State = (Button) findViewById(R.id.btn_draw_State);
        btn_draw_State.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (int i = 0; i < drawings.size(); i++) {
                    drawings.get(i).remove();
                }
                // TODO Auto-generated method stub
                if (Is_MAP_Moveable != true) {
                    Is_MAP_Moveable = true;
                } else {

                    clearmarkers();
                    filteringTheHOuses(val);
                    val.clear();
                    Is_MAP_Moveable = false;
                }
            }
        });

        fram_map.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!Is_MAP_Moveable) {
                    return false;
                }
                float x = event.getX();
                float y = event.getY();

                int x_co = Math.round(x);
                int y_co = Math.round(y);

                projection = mMap.getProjection();
                Point x_y_points = new Point(x_co, y_co);

                LatLng latLng = mMap.getProjection().fromScreenLocation(x_y_points);
                latitude = latLng.latitude;

                longitude = latLng.longitude;

                int eventaction = event.getAction();
                switch (eventaction) {
                    case MotionEvent.ACTION_DOWN:
                        // finger touches the screen
                        val.add(new LatLng(latitude, longitude));

                    case MotionEvent.ACTION_MOVE:
                        // finger moves on the screen
                        val.add(new LatLng(latitude, longitude));

                    case MotionEvent.ACTION_UP:
                        // finger leaves the screen

                        Draw_Map();
                        break;
                }
                if (Is_MAP_Moveable == true) {
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();


            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {


    }


    private void getDataFromVolley() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://a88a4240.ngrok.io/";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // Log.d("Renu", "data got " + mData.toString());
                            mData = new ObjectMapper().readValue(response, NestAwayHousesResponse.class);
                            addHousesToMap();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.d("renu", "mapping exception");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Renu", "error " + error);
            }
        });
// Add the request to the RequestQueue.
        RetryPolicy retryPolicy = new DefaultRetryPolicy(60000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        queue.add(stringRequest);
    }


    private void addHousesToMap() {

        for (int i = 0; i < mData.getHouses().size(); i++) {
            addMarker(mData.getHouses().get(i).getLat_double(), mData.getHouses().get(i).getLong_double(), houseInfo(i));

        }
        CameraUpdate update = CameraUpdateFactory.newLatLng(new LatLng(mData.houses.get(mData.getHouses().size() - 1).getLat_double(),
                mData.houses.get(mData.getHouses().size() - 1).getLat_double()));

        mMap.moveCamera(update);

        mMap.animateCamera(update);

    }


    private void addMarker(double lat, double lng, String info) {
        Log.d("renu", "map null or nut " + (mMap == null
        ));
        MarkerOptions marker = new MarkerOptions()
                .position(new LatLng(lat, lng))
                .snippet(info)
                .title("House Details")
                .icon(BitmapDescriptorFactory.defaultMarker());
        markers.add(mMap.addMarker(marker));


    }

    private GoogleMap.OnMarkerClickListener markerClickListener = new GoogleMap.OnMarkerClickListener() {
        @Override
        public boolean onMarkerClick(Marker marker) {

            return true;
        }
    };


    private String houseInfo(int i) {
        /**
         * "bhk_details":"3 BHK",
         * "house_type":"Independent",
         * "id":730,
         * "status_code":4,
         * "title":"303 - SV Paradise",
         * "lat_double":12.989512,
         * "long_double":77.711685,
         * "bed_available_count":6,
         * "min_rent":8000,
         * "slug":"730-fully-furnished-nest-for-boys-in-a-3-bhk-independent-in-303-sv-paradise-hoodi-bengaluru-karnataka-india-bangalore-560048",
         * "nestaway_id":"N730",
         * "shared":6,
         * "private":0,
         * "image_url":"http://d397nwo9hbvzsm.cloudfront.net/uploads/images/thumb_large_6babdf320e.jpg",
         * "gender":"boys",
         * "locality":"Hoodi"
         */
        String info = "";
        info = "bhk: " + mData.getHouses().get(i).getBhk_details() + ","
                + " house type: " + mData.getHouses().get(i).getHouse_type() + ","
                + " Bed Available: " + mData.getHouses().get(i).getBed_available_count() + ","
                + " Gender: " + mData.getHouses().get(i).getGender() + ","
                + "Locality: " + mData.getHouses().get(i).getLocality() + " ,";
        return info;
    }


    public class data {
        double lat;
        double lng;

        public data(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLng() {
            return lng;
        }

        public void setLng(double lng) {
            this.lng = lng;
        }
    }

    public void Draw_Map() {
        PolygonOptions rectOptions = new PolygonOptions();
        rectOptions.addAll(val);
        rectOptions.strokeColor(Color.BLUE);
        rectOptions.strokeWidth(7);
        rectOptions.fillColor(Color.CYAN);
        Polygon polygon = mMap.addPolygon(rectOptions);
        drawings.add(polygon);


    }

    private void filteringTheHOuses(Vector<LatLng> val) {
        for (int i = 0; i < mData.getHouses().size(); i++) {
            //   LatLng l = val.get(i);
            double lat = mData.getHouses().get(i).getLat_double();
            double lng = mData.getHouses().get(i).getLong_double();
            if (checkIfinRange(lat, lng))
                addMarker(lat, lng, "selected");

        }
    }


    private boolean checkIfinRange(double lat, double lang) {

        for (int i = 0; i < val.size(); i++) {
            if (lat <= val.get(i).latitude && lang <= val.get(i).longitude)
                return true;
        }


        return false;
    }


    private void clearmarkers() {
        for (Marker marker : markers)
            marker.remove();
        markers.clear();
    }
}
