package philips.com.myapplication.ui.fragment;

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
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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
    private int currentPage = 0;
    private AtomicInteger what = new AtomicInteger(0);

    private Handler viewPagerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            viewPager.setCurrentItem(msg.what);
        }
    };
    private Runnable runnable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_suggestion, container, false);
        initView(view);
        return view;
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
            imageView.setLayoutParams(new ViewGroup.LayoutParams(60, 60));
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
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position - 1;
                if (position == 0) {
                    currentPage = viewArrayList.size() - 1;
                } else if (position > viewArrayList.size()) {
                    currentPage = 0;
                }
                if (currentPage != position - 1){
                    viewPager.setCurrentItem(currentPage+1, false);
                }
                for (int i = 0; i < viewArrayList.size(); i++) {
                    Log.e("Position"," "+position);
                    Log.e("CurrentPage", " "+ currentPage);
                    imageViews[currentPage].setImageResource(R.mipmap.banner_dian_focus);
                    if (i != currentPage) {
                        imageViews[i%viewArrayList.size()].setImageResource(R.mipmap.banner_dian_blur);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        isContinue = false;
                        break;
                    case MotionEvent.ACTION_UP:
                        isContinue = true;
                        break;
                    default:
                        isContinue = true;
                        break;
                }
                return false;
            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                if (isContinue) {
                    viewPagerHandler.sendEmptyMessage(currentPage);
                    currentPage ++;
                    currentPage = currentPage % viewArrayList.size();
                }
                viewPagerHandler.postDelayed(this,3500);
            }
        };
        viewPagerHandler.postDelayed(runnable, 3500);
    }

    private void whatOption() {
        what.incrementAndGet();
//        if (what.get() > imageViews.length - 1) {
//            what = new AtomicInteger(0);
//        }
    }
}
