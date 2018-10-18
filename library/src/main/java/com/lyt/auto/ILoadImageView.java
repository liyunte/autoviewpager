package com.lyt.auto;

import android.view.View;



public interface ILoadImageView<T extends Data> {
    void show(View rootView, T data, int position);
    void onPageSelected(int position, int size);
}
