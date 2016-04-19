package philips.com.myapplication.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ViewPagerPictureAdapter;

/**
 * Created by 310231492 on 2016/3/21.
 */
public class SuggestionFragment extends Fragment {
    private ImageView[] imageViews;
    private ViewPager viewPager;
    private ArrayList<View> viewArrayList;

    private boolean isContinue = true;
    private int WHAT_AUTO_PLAY = 1000;
    private int autoPlayDuration = 5000;
    private int scrollDuration = 900;

    private Handler viewPagerHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == WHAT_AUTO_PLAY) {
                if (viewPager != null) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    viewPagerHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
                }
            }
            return false;
        }
    });

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);
        initView(view);
        startAutoPlay();
        return view;
    }

    public void setSliderTransformDuration(int duration) {
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(viewPager.getContext(), null, duration);
            mScroller.set(viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAutoPlay() {
        //避免出现重复消息传递
        stopAutoPlay();
        if (isContinue) {
            viewPagerHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
        }
    }

    private void stopAutoPlay() {
        if (isContinue) {
            viewPagerHandler.removeMessages(WHAT_AUTO_PLAY);
        }
    }

    private void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.suggestion_viewpager);
        viewArrayList = new ArrayList<>();
        ImageView imageView1 = new ImageView(getContext());
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView1.setImageResource(R.mipmap.hor2);
        viewArrayList.add(imageView1);
        ImageView imageView2 = new ImageView(getContext());
        imageView2.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView2.setImageResource(R.mipmap.hor1);
        viewArrayList.add(imageView2);
        ImageView imageView3 = new ImageView(getContext());
        imageView3.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView3.setImageResource(R.mipmap.hor3);
        viewArrayList.add(imageView3);
        ImageView imageView4 = new ImageView(getContext());
        imageView4.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView4.setImageResource(R.mipmap.hor4);
        viewArrayList.add(imageView4);
        ImageView imageView5 = new ImageView(getContext());
        imageView5.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView5.setImageResource(R.mipmap.hor5);
        viewArrayList.add(imageView5);

        // group是R.layou.mainview中的负责包裹小圆点的LinearLayout.
        ViewGroup group = (ViewGroup) view.findViewById(R.id.viewGroup);
        imageViews = new ImageView[viewArrayList.size()];

        for (int i = 0; i < viewArrayList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(50, 50));
            imageView.setPadding(15, 0, 15, 0);
            imageViews[i] = imageView;
            if (i == 0) {
                // 默认选中第一张图片
                imageViews[i].setImageResource(R.mipmap.banner_dian_focus);
            } else {
                imageViews[i].setImageResource(R.mipmap.banner_dian_blur);
            }
            group.addView(imageViews[i]);
        }

        viewPager.setAdapter(new ViewPagerPictureAdapter(viewArrayList));
        //设置当前item到Integer.MAX_VALUE中间的一个值，看起来像无论是往前滑还是往后滑都是ok的
        //如果不设置，用户往左边滑动的时候已经划不动了
        int targetItemPosition = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % viewArrayList.size();
        viewPager.setCurrentItem(targetItemPosition);
        switchIndicator(targetItemPosition % viewArrayList.size());
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                switchIndicator(position % viewArrayList.size());
                super.onPageSelected(position);
            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopAutoPlay();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startAutoPlay();
                        break;
                }
                return false;
            }
        });
        setSliderTransformDuration(scrollDuration);
    }

    private void switchIndicator(int position) {
        for (int i=0; i < viewArrayList.size(); i++) {
            if (position == i) {
                imageViews[i].setImageResource(R.mipmap.banner_dian_focus);
            } else imageViews[i].setImageResource(R.mipmap.banner_dian_blur);
        }
    }

    //调整viewpager中的滑动速率(默认为250毫秒，速度太快)，增加美感
    private class FixedSpeedScroller extends Scroller{
        private int mDuration = 1000;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator, int mDuration) {
            this(context, interpolator);
            this.mDuration = mDuration;
        }
    }
}
