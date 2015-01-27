package com.yjh.qinyuan.main;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseActivity;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.Utils;
import com.yjh.qinyuan.widget.HelveticaTextView;
import com.yjh.qinyuan.widget.MyActionBar;

public class DetailActivity extends BaseActivity {

    private HelveticaTextView mTitleTextView;
    private HelveticaTextView mPhone1TextView;
    private HelveticaTextView mPhone2TextView;
    private HelveticaTextView mNameTextView;
    private HelveticaTextView mAddressTextView;
    private HelveticaTextView mCategory1TextView;
    private HelveticaTextView mCategory2TextView;

    private MapView mMapView;
    private BaiduMap mMap;
    private ImageLoader mImageLoader;
    private TownBranch mBranch;
    private boolean mIsFirstLoc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }


    @Override
    public void init() {
        mBranch = (TownBranch) getIntent().getSerializableExtra(Constants.MODEL_TOWN_BRANCH);
        ((MyActionBar) findViewById(R.id.action_bar)).setTitle(mBranch.getnName());
        mTitleTextView = (HelveticaTextView) findViewById(R.id.n_name);
        mPhone1TextView = (HelveticaTextView) findViewById(R.id.phone1);
        mPhone2TextView = (HelveticaTextView) findViewById(R.id.phone2);
        mNameTextView = (HelveticaTextView) findViewById(R.id.contact);
        mAddressTextView = (HelveticaTextView) findViewById(R.id.address);
        mCategory1TextView = (HelveticaTextView) findViewById(R.id.category1);
        mCategory2TextView = (HelveticaTextView) findViewById(R.id.category2);

        mTitleTextView.setText(mBranch.getnName());
        mPhone1TextView.setText(mBranch.getPhone1());
        mPhone2TextView.setText(mBranch.getPhone2());
        mNameTextView.setText(mBranch.getUsername());
        mAddressTextView.setText(mBranch.getAddress());
        mCategory1TextView.setText(mBranch.getPtName());
        mCategory2TextView.setText(mBranch.getCtName());

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(this));
        mImageLoader.displayImage(mBranch.getImage1(),
                (ImageView) findViewById(R.id.image), Utils.getImageOptions());

        mMapView = (MapView) findViewById(R.id.map);
        mMapView.setClickable(false);
        mMapView.setEnabled(false);
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);
        mMap = mMapView.getMap();
        mMap.setTrafficEnabled(true);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);

        LatLng ll = new LatLng(mBranch.getLatitude(), mBranch.getLongitude());
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mMap.animateMapStatus(u);

        BitmapDescriptor bitmap = null;
        if (mBranch.getPtId() == 1000) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_green);
        } else if (mBranch.getPtId() == 1002) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_purple);
        } else {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_red);
        }

        OverlayOptions overlayOptions = new MarkerOptions().position(ll).icon(bitmap);
        mMap.addOverlay(overlayOptions);
    }

    @Override
    public void onDestroy() {
        mMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
//        mMapView.setVisibility(View.VISIBLE);
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
//        mMapView.setVisibility(View.GONE);
        super.onPause();
    }
}
