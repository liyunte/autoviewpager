package com.lyt.auto;

import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;



class OnPageChangeListener <T extends Data> implements ViewPager.OnPageChangeListener {
    private List<T> datas;
    private ViewPager viewPager;
    private int widthPixels;
    private int defaultheight;
    OnPageChangeListener(List<T> datas, ViewPager viewPager) {
        this.datas = datas;
        if (datas==null){
            this.datas = new ArrayList<>();
        }
        this.viewPager = viewPager;
        DisplayMetrics metrics = viewPager.getContext().getResources().getDisplayMetrics();
        widthPixels = metrics.widthPixels;

        if (datas != null && datas.size() > 0) {
            if (datas.get(0) != null) {
                if (datas.get(0).getImg_height() != 0) {
                    ViewGroup.LayoutParams params = viewPager.getLayoutParams();
                    defaultheight = datas.get(0).getImg_height() * widthPixels / datas.get(0).getImg_width();
                    params.height = defaultheight;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    viewPager.setLayoutParams(params);
                }
            }
        }

    }

    @Override
 public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        if (position == datas.size() - 1) {
            return;
        }
        if (datas.get(position).getImg_height()!=0&&datas.get(position+1).getImg_height()!=0){
            int height = (int) ((datas.get(position).getImg_height()*widthPixels/datas.get(position).getImg_width() == 0
                    ? defaultheight : datas.get(position).getImg_height()*widthPixels/datas.get(position).getImg_width())
                    * (1 - positionOffset) +
                    (datas.get(position+1).getImg_height()*widthPixels/datas.get(position+1).getImg_width() == 0
                            ? defaultheight : datas.get(position+1).getImg_height()*widthPixels/datas.get(position+1).getImg_width())
                            * positionOffset);
            ViewGroup.LayoutParams params = viewPager.getLayoutParams();
            params.height = height;
            viewPager.setLayoutParams(params);
        }
        }

      @Override
      public void onPageSelected(int position) {

        }

 @Override
 public void onPageScrollStateChanged(int state) {

        }

}
