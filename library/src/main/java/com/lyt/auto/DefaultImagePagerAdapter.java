package com.lyt.auto;


import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



abstract class DefaultImagePagerAdapter<T extends Data> extends PagerAdapter {
    private List<T> datas;
    private SparseArray<View> views;
    public abstract int getImgLayoutId();
    public abstract int getVideoLayoutId();
    public abstract void show(View view,T date,int postion);
    DefaultImagePagerAdapter(List<T> datas) {
        views = new SparseArray<>();
        this.datas = datas;
        if (datas==null){
            this.datas = new ArrayList<>();
        }
    }

    // 获取要滑动的控件的数量，在这里我们以滑动的广告栏为例，那么这里就应该是展示的广告图片的ImageView数量
    @Override
    public int getCount() {
        return datas.size();
    }

    // 来判断显示的是否是同一张图片，这里我们将两个参数相比较返回即可
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup view, int position, Object object) {
        view.removeView(views.get(position));
    }

    // 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        if (views.get(position)==null){
            View imgOrVideo;
            if (datas.get(position).isVideo()){
              imgOrVideo = LayoutInflater.from(view.getContext()).inflate(getVideoLayoutId(),view,false);
            }else {
               imgOrVideo = LayoutInflater.from(view.getContext()).inflate(getImgLayoutId(),view,false);
            }
            show(imgOrVideo ,datas.get(position), position);
            views.put(position,imgOrVideo);
        }
        view.addView(views.get(position));
        return views.get(position);
    }
}
