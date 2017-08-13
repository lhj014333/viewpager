package com.yulu.viewpager_demo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;
    List<ImageView> imageViews;


    int[] iamgeId = new int[]{
            R.drawable.one,
            R.drawable.two,
            R.drawable.sss
    };
    @BindView(R.id.btn_1)
    Button btn1;
    @BindView(R.id.btn_2)
    Button btn2;
    private ViewAdapter adapter;
    private int item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        imageViews = new ArrayList<>();
        for (int i = 0; i < iamgeId.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(iamgeId[i]);
            imageViews.add(imageView);

        }
viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position<iamgeId.length-1){
           btn2.setVisibility(View.VISIBLE);
        }else {
            btn2.setVisibility(View.GONE);
        }
        if (position==0){
            btn1.setVisibility(View.GONE);
        }else {
            btn1.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
        adapter = new ViewAdapter();
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(iamgeId.length - 1);


    }

    @OnClick({R.id.btn_1, R.id.btn_2})
    public void onViewClicked(View view) {
        item = viewpager.getCurrentItem();
        switch (view.getId()) {
            case R.id.btn_1:
                viewpager.setCurrentItem(item - 1);
                break;
            case R.id.btn_2:
                viewpager.setCurrentItem(item + 1);
                break;
        }
        adapter.notifyDataSetChanged();
    }


    class ViewAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return iamgeId.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }
    }
}
