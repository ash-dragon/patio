package com.yjh.qinyuan.main.aroundme;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

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
import com.yjh.qinyuan.main.DetailActivity;
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
    private PopupWindow mPopupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_around_me, container, false);
            mRootView.setClickable(true);
            init();
        }

        getActionBar().setTitle(R.string.around_me);
        getActionBar().setRightButton(R.drawable.btn_refresh, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                mMarkers = new ArrayList<Marker>();
                mShopMarkers = new ArrayList<ShopMarker>();
                mIsFirstLoc = true;
                mMapView.invalidate();
                init();
            }
        });

        return mRootView;
    }


    @Override
    public void init() {
        mMapView = (MapView) mRootView.findViewById(R.id.bmapView);
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
                for (int i = 0; i < mMarkers.size(); i++) {
                    if (marker == mMarkers.get(i)) {
                        LatLng latLng = marker.getPosition();
                        Point p = mMap.getProjection().toScreenLocation(latLng);
                            //向上平移30 使图标在Marker的上方
                        p.y -= (int) TypedValue.applyDimension(TypedValue
                                .COMPLEX_UNIT_DIP, 24, getActivity().getResources().getDisplayMetrics());
                        mSelectedShopMarker = mShopMarkers.get(i);
                        String text = mSelectedShopMarker.getnName() + "\n" + mSelectedShopMarker.getPhone1();
                        showPopupWindow(p, text);
                    }
                }

                return true;
            }
        });
    }

    private void showPopupWindow( Point p, String text) {
        Resources resources = getActivity().getResources();
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, resources.getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 120, resources.getDisplayMetrics());
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView;
        int windowWidth = getActivity().getResources().getDisplayMetrics().widthPixels;
        if (p.x + width / 2 > windowWidth) {
            popupView  =inflater.inflate(R.layout.info_right_view, null);
        } else if (p.x - width / 2 < 0) {
            popupView  =inflater.inflate(R.layout.info_left_view, null);
        } else {
            popupView  =inflater.inflate(R.layout.info_middle_view, null);
        }
        TextView textView = (TextView) popupView.findViewById(R.id.name);
        textView.setText(text);
        mPopupWindow = new PopupWindow(popupView, width, height, true);
        mPopupWindow.setOutsideTouchable(true);
        if (p.x + width / 2 > windowWidth) {
            mPopupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ico_dialog_mapr_normal));
        } else if (p.x - width / 2 < 0) {
            mPopupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ico_dialog_mapl_normal));
        } else {
            mPopupWindow.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.ico_dialog_map_normal));
        }
        View parent = getActivity().getWindow().getDecorView();
        mPopupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, p.x - width / 2, p.y - height / 2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mMap.hideInfoWindow();
                mPopupWindow.dismiss();
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(Constants.MODEL_TOWN_BRANCH, mSelectedShopMarker);
                startActivity(intent);
            }
        });
    }

    private void setMapClickListener() {
        mMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.hideInfoWindow();
                if (mPopupWindow != null && mPopupWindow.isShowing()) {
                    mPopupWindow.dismiss();
                }
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
                    addMarker(marker);
                }
            }
        });
        task.executeGet();
    }

    private void addMarker(ShopMarker marker) {
        LatLng point = new LatLng(marker.getLatitude(), marker.getLongitude());
        BitmapDescriptor bitmap = null;
        if (marker.getPtId() == 1000) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_green);
        } else if (marker.getPtId() == 1002) {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_purple);
        } else {
            bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ico_pin_red);
        }
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

//    @Override
//    public void onResume() {
//        mMapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        mMapView.onPause();
//        super.onPause();
//    }
}
