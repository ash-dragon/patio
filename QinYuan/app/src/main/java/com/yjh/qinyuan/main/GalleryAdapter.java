package com.yjh.qinyuan.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.yjh.qinyuan.R;
import com.yjh.qinyuan.widget.ScaleImageView;

import java.util.ArrayList;

public class GalleryAdapter extends PagerAdapter {

    private ArrayList<String> mUrls;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private DisplayImageOptions mImageOptions;

    public GalleryAdapter(Context context, ArrayList<String> urls) {
        this.mUrls = urls;
        this.mInflater = LayoutInflater.from(context);
        this.mImageLoader = ImageLoader.getInstance();
        this.mImageLoader.init(ImageLoaderConfiguration.createDefault(context));
        this.mImageOptions = new DisplayImageOptions.Builder()
                .showStubImage(R.drawable.placeholder)
                .showImageForEmptyUri(R.drawable.placeholder)
                .showImageOnFail(R.drawable.placeholder)
                .cacheOnDisc()
                .cacheInMemory(true)
                .build();;
    }

    @Override
    public int getCount() {
        return mUrls != null ? mUrls.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mInflater.inflate(R.layout.gallery_image, container, false);
        ScaleImageView imageView = (ScaleImageView) itemView.findViewById(R.id.img);
//        imageView.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        mImageLoader.displayImage(mUrls.get(position), imageView, mImageOptions);
        container.addView(itemView);

        return itemView;
    }
}
