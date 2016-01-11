package jayxu.com.carassist.UI;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.ParseUser;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.tencent.tencentmap.mapsdk.map.UiSettings;

import org.json.JSONException;

import jayxu.com.carassist.R;

public class MapActivity extends AppCompatActivity {
    private static final String TAG=MapActivity.class.getSimpleName();
    MapView mapview=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapview=(MapView)findViewById(R.id.mapview);
        mapview.onCreate(savedInstanceState);

        //获取TencentMap实例
        TencentMap tencentMap = mapview.getMap();
    //设置卫星底图
     //   tencentMap.setSatelliteEnabled(true);
    //设置实时路况开启
        tencentMap.setTrafficEnabled(true);
    //设置地图中心点
     //   tencentMap.setCenter(new LatLng(39, 116));
    //设置缩放级别
        tencentMap.setZoom(11);
        //获取UiSettings实例
        UiSettings uiSettings = mapview.getUiSettings();
//设置logo到屏幕底部中心
 //       uiSettings.setLogoPosition(UiSettings.LOGO_POSITION_CENTER_BOTTOM);
//设置比例尺到屏幕右下角
 //       uiSettings.setScaleViewPosition(UiSettings.SCALEVIEW_POSITION_RIGHT_BOTTOM);
//启用缩放手势(更多的手势控制请参考开发手册)
        uiSettings.setZoomGesturesEnabled(true);

        //Generate the longitute and latitue for both the car and user:
        ParseUser user=ParseUser.getCurrentUser();
        double user_x=0.0;
        double user_y=0.0;
        double car_x=0.0;
        double car_y=0.0;
        try{
            user_x=Double.parseDouble(user.getJSONObject(getString(R.string.JSON_KEY))
                    .getJSONObject(getString(R.string.Home_JSON_KEY))
                    .getString(getString(R.string.UserGPS_X)));
            user_y=Double.parseDouble(user.getJSONObject(getString(R.string.JSON_KEY))
                    .getJSONObject(getString(R.string.Home_JSON_KEY))
                    .getString(getString(R.string.UserGPS_Y)));
            car_x=Double.parseDouble(user.getJSONObject(getString(R.string.JSON_KEY))
                    .getJSONObject(getString(R.string.Home_JSON_KEY))
                    .getString(getString(R.string.CarGPS_X)));
            car_y=Double.parseDouble(user.getJSONObject(getString(R.string.JSON_KEY))
                    .getJSONObject(getString(R.string.Home_JSON_KEY))
                    .getString(getString(R.string.CarGPS_Y)));
            Log.d(TAG, "user x is "+user_x+"user y is "+user_y+"car x is "+car_x+" car y is "+car_y);
        }catch (JSONException e){
            Log.d(TAG, e.getMessage());
        }catch (NumberFormatException e){
            Log.d(TAG, e.getMessage());
        }
        //check to see if valid result was returned
        if(user_x!=0.0 && user_y!=0.0) {
            LatLng user_latlng = new LatLng(user_x, user_y);
            Marker marker = tencentMap.addMarker(new MarkerOptions()
                    .position(user_latlng)
                    .title(getString(R.string.UserLocation))
                    .anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker())
                    .draggable(true));
            marker.showInfoWindow();// 设置默认显示一个infowinfow
            //Set the user location to be centered
            tencentMap.setCenter(user_latlng);
        }
        if(car_x !=0.0 && car_y!=0.0) {
            LatLng car_latlng = new LatLng(car_x, car_y);
            Marker marker = tencentMap.addMarker(new MarkerOptions()
                    .position(car_latlng)
                    .title(getString(R.string.CarLocation))
                    .anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker())
                    .draggable(true));
            marker.showInfoWindow();// 设置默认显示一个infowinfow
        }





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case R.id.action_logout:
                ParseUser.logOut();
                intent = new Intent(MapActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mapview.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mapview.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mapview.onResume();
        super.onResume();
    }

    @Override
    protected void onStop() {
        mapview.onStop();
        super.onStop();
    }
}
