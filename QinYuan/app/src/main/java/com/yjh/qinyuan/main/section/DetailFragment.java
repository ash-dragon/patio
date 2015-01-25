package com.yjh.qinyuan.main.section;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.TownBranch;
import com.yjh.qinyuan.main.MainActivity;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.Utils;
import com.yjh.qinyuan.widget.HelveticaTextView;

public class DetailFragment extends BaseFragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_detail, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(mBranch.getnName());
        getActionBar().hideRightButton();

        return mRootView;
    }


    @Override
    public void init() {
        mBranch = (TownBranch) getArguments().getSerializable(Constants.MODEL_TOWN_BRANCH);
        mTitleTextView = (HelveticaTextView) mRootView.findViewById(R.id.n_name);
        mPhone1TextView = (HelveticaTextView) mRootView.findViewById(R.id.phone1);
        mPhone2TextView = (HelveticaTextView) mRootView.findViewById(R.id.phone2);
        mNameTextView = (HelveticaTextView) mRootView.findViewById(R.id.contact);
        mAddressTextView = (HelveticaTextView) mRootView.findViewById(R.id.address);
        mCategory1TextView = (HelveticaTextView) mRootView.findViewById(R.id.category1);
        mCategory2TextView = (HelveticaTextView) mRootView.findViewById(R.id.category2);

        mTitleTextView.setText(mBranch.getnName());
        mPhone1TextView.setText(mBranch.getPhone1());
        mPhone2TextView.setText(mBranch.getPhone2());
        mNameTextView.setText(mBranch.getUsername());
        mAddressTextView.setText(mBranch.getAddress());
        mCategory1TextView.setText(mBranch.getPtName());
        mCategory2TextView.setText(mBranch.getCtName());

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        mImageLoader.displayImage(mBranch.getImage1(),
                (ImageView) mRootView.findViewById(R.id.image), Utils.getImageOptions());

        mMapView = (MapView) mRootView.findViewById(R.id.map);
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
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
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
