package com.lyt.auto;


import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 */
@SuppressWarnings("ALL")
public class AutoViewPager<T extends Data> {
    private ViewPager viewPager;
    private List<T> datas;
    private DefaultImagePagerAdapter<T> adapter;
    private ILoadImageView callback;
    private int imgLayoutId;
    private int videoLayoutId;
    private AutoViewPager(ViewPager viewPager, List<T> datas, int imgLayoutId, int videoLayoutId, ILoadImageView callback){
        this.viewPager = viewPager;
        this.datas = datas==null?new ArrayList<T>():datas;
       this.callback = callback;
        this.imgLayoutId = imgLayoutId;
        this.videoLayoutId = videoLayoutId;
       initView();
    }

    private void initView() {
        adapter = new DefaultImagePagerAdapter<T>(datas) {
            @Override
            public int getImgLayoutId() {
                return imgLayoutId;
            }

            @Override
            public int getVideoLayoutId() {
                return videoLayoutId==0? imgLayoutId:videoLayoutId;
            }

            @Override
            public void show(View view,T data,int position) {
                if (callback!=null){
                    callback.show(view,data,position);
                }
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new OnPageChangeListener<T>(datas,viewPager){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (callback!=null){
                    callback.onPageSelected(position,datas.size());
                }
            }
        });
    }

    public static class Builder<T extends Data>{
        private ViewPager viewPager;
        private List<T> datas;
        private int imgLayoutId;
        private int videoLayoutId;
        private ILoadImageView callback;
        public Builder setViewPager(ViewPager viewPager){
            this.viewPager = viewPager;
            return this;
        }

        public Builder setDatas(List<T> datas){
            this.datas = datas;
            return this;
        }
        public Builder setImgLayoutId(int imgLayoutId){
            this.imgLayoutId  = imgLayoutId;
            return this;
        }
        public Builder setVideoLayoutId(int videoLayoutId){
            this.videoLayoutId  = videoLayoutId;
            return this;
        }
        public Builder setCallBack(ILoadImageView callback){
            this.callback = callback;
            return this;
        }
        public AutoViewPager build(){
            return new AutoViewPager(viewPager, datas,imgLayoutId,videoLayoutId,callback);
        }
    }
}
