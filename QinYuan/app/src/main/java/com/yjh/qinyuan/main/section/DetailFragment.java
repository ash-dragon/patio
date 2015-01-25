package com.yjh.qinyuan.main.section;


import android.os.Bundle;
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
    private ScrollView mScrollView;

    private LocationClient mLocClient;
    private MapView mMapView;
    private BaiduMap mMap;
    private ImageLoader mImageLoader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        view.setClickable(true);
        init(view);

        return view;
    }


    @Override
    public void init(View rootView) {
        mScrollView = (ScrollView) rootView.findViewById(R.id.scroll_view);
        mTitleTextView = (HelveticaTextView) rootView.findViewById(R.id.n_name);
        mPhone1TextView = (HelveticaTextView) rootView.findViewById(R.id.phone1);
        mPhone2TextView = (HelveticaTextView) rootView.findViewById(R.id.phone2);
        mNameTextView = (HelveticaTextView) rootView.findViewById(R.id.contact);
        mAddressTextView = (HelveticaTextView) rootView.findViewById(R.id.address);
        mCategory1TextView = (HelveticaTextView) rootView.findViewById(R.id.category1);
        mCategory2TextView = (HelveticaTextView) rootView.findViewById(R.id.category2);

        TownBranch branch = (TownBranch) getArguments().getSerializable(Constants.MODEL_TOWN_BRANCH);
        mTitleTextView.setText(branch.getnName());
        mPhone1TextView.setText(branch.getPhone1());
        mPhone2TextView.setText(branch.getPhone2());
        mNameTextView.setText(branch.getUsername());
        mAddressTextView.setText(branch.getAddress());
        mCategory1TextView.setText(branch.getPtName());
        mCategory2TextView.setText(branch.getCtName());

        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        mImageLoader.displayImage(branch.getImage1(),
                (ImageView) rootView.findViewById(R.id.image), Utils.getImageOptions());

        mMapView = (MapView) rootView.findViewById(R.id.map);
        mMapView.showZoomControls(false);
        mMapView.showScaleControl(false);
        mMap = mMapView.getMap();
        mMap.setTrafficEnabled(true);
//        mLocClient = new LocationClient(getActivity().getApplicationContext());
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);
//        option.setCoorType("bd09ll");
//        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);
//        mLocClient.start();

        LatLng ll = new LatLng(branch.getLatitude(), branch.getLongitude());
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mMap.animateMapStatus(u);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        OverlayOptions overlayOptions = new MarkerOptions().position(ll).icon(bitmap);
        mMap.addOverlay(overlayOptions);
//
        mMapView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    mScrollView.requestDisallowInterceptTouchEvent(false);
                }else{
                    mScrollView.requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
    }
}
