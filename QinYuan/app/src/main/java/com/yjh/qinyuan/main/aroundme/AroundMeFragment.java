package com.yjh.qinyuan.main.aroundme;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.common.BaseFragment;
import com.yjh.qinyuan.gson.ShopMarker;
import com.yjh.qinyuan.gson.ShopMarkerModel;
import com.yjh.qinyuan.main.section.DetailFragment;
import com.yjh.qinyuan.task.GetMarkerListTask;
import com.yjh.qinyuan.task.RequestCallBack;
import com.yjh.qinyuan.util.Constants;
import com.yjh.qinyuan.util.HttpUtils;

import java.util.ArrayList;

public class AroundMeFragment extends BaseFragment {

    private LocationClient mLocClient;
    private MapView mMapView;
    private BaiduMap mMap;
    public MyLocationListener mListener = new MyLocationListener();
    private boolean mIsFirstLoc = true;
    private ArrayList<Marker> mMarkers = new ArrayList<>();
    private ArrayList<ShopMarker> mShopMarkers = new ArrayList<>();
    private ShopMarker mSelectedShopMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_around_me, container, false);
        view.setClickable(true);
        init(view);

        return view;
    }


    @Override
    public void init(View rootView) {
        mMapView = (MapView) rootView.findViewById(R.id.bmapView);
        mMapView.showZoomControls(false);
        mMap = mMapView.getMap();
        mMap.setMyLocationEnabled(true);
        mMap.setTrafficEnabled(true);
        mLocClient = new LocationClient(getActivity().getApplicationContext());
        mLocClient.registerLocationListener(mListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();

        setMapInfoWindow();
        setMapClickListener();
    }

    private void setMapInfoWindow() {
        mMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                Button button = new Button(getActivity());
                for (int i = 0; i < mMarkers.size(); i++) {
                    if (marker == mMarkers.get(i)) {
                        mSelectedShopMarker = mShopMarkers.get(i);
                        button.setText(mSelectedShopMarker.getnName() + "\n" + mSelectedShopMarker.getPhone1());
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        break;
                    }
                }
                InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick() {

//                        LatLng ll = marker.getPosition();
//                        LatLng llNew = new LatLng(ll.latitude + 0.005, ll.longitude + 0.005);
//                        marker.setPosition(llNew);
//                        mMap.hideInfoWindow();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constants.MODEL_TOWN_BRANCH, mSelectedShopMarker);
                        DetailFragment fragment = new DetailFragment();
                        fragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.add(R.id.content, fragment);
                        fragmentTransaction.commit();
                    }
                };
                LatLng ll = marker.getPosition();
                InfoWindow infoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(button), ll, 0, listener);
                mMap.showInfoWindow(infoWindow);

                return true;
            }
        });
    }

    private void setMapClickListener() {
        mMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.hideInfoWindow();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
    }

    private void getMarkerListTask(double longitude, double latitude) {
        GetMarkerListTask task = new GetMarkerListTask(getActivity(), String.valueOf(longitude),
                String.valueOf(latitude), new RequestCallBack(getActivity(), mProgressBar) {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                mShopMarkers = HttpUtils.getJsonData(s, ShopMarkerModel.class).getShopMarkers();
                for (ShopMarker marker : mShopMarkers) {
                    addMarker(marker.getLatitude(), marker.getLongitude());
                }
            }
        });
        task.executeGet();
    }

    private void addMarker(double latitude, double longitude) {
        LatLng point = new LatLng(latitude, longitude);
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.icon_marka);
        OverlayOptions option = new MarkerOptions().position(point).icon(bitmap);
        mMarkers.add((Marker) mMap.addOverlay(option));
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || mMapView == null)
                return;
            MyLocationData locData =
                    new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(100).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mMap.setMyLocationData(locData);
            if (mIsFirstLoc) {
                mIsFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
                mMap.animateMapStatus(u);
                getMarkerListTask(ll.longitude, ll.latitude);
            }

        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    @Override
    public void onDestroy() {
        mLocClient.stop();
        mMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }
}
