package philips.com.myapplication.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import philips.com.myapplication.adapter.RecyclerListAdapter;
import philips.com.myapplication.adapter.ViewPagerPictureAdapter;
import philips.com.myapplication.bean.SuggestMusicBean;

/**
 * Created by 310231492 on 2016/3/21.
 */
public class SuggestionFragment extends Fragment {
    private RecyclerView recyclerView;
    private ImageView[] imageViews;
    private ViewPager viewPager;
    private ArrayList<View> viewArrayList;
    private ArrayList<SuggestMusicBean> arrayList;
    private RecyclerListAdapter recyclerListAdapter;

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
        arrayList = initMusicData();
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
        viewPagerHandler.sendEmptyMessageDelayed(WHAT_AUTO_PLAY, autoPlayDuration);
    }

    private void stopAutoPlay() {
        viewPagerHandler.removeMessages(WHAT_AUTO_PLAY);
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.music_home_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        recyclerListAdapter = new RecyclerListAdapter(getContext(), arrayList);
        recyclerView.setAdapter(recyclerListAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        initRecyclerHeader(recyclerView);
    }

    private void initRecyclerHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.recyclerheader, recyclerView, false);
        recyclerListAdapter.setHeaderView(header);
        viewPager = (ViewPager) header.findViewById(R.id.suggestion_viewpager);
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
        ViewGroup group = (ViewGroup) header.findViewById(R.id.viewGroup);
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
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

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


    public ArrayList<SuggestMusicBean> initMusicData() {
        ArrayList<SuggestMusicBean> arrayList = new ArrayList<>();
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/daze.jpeg", "在黑夜里，寻求心灵的慰藉"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample2.jpg", "史诗配乐，可以让你沸腾的音乐"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample1.jpg", "现实永远装不满梦想的田字格"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample3.jpg", "日系女音中细腻的爱情"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample4.jpg", "黑白键上破茧绽放的羽翼"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/kano.jpg", "在黑夜里，寻求心灵的慰藉"));

        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor1.jpg", "网易音乐人 第39期"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor2.jpeg", "探索频道 第116期"));

        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample5.jpg", "一周热评 第86期 你有什么资格不努力"));

        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample6.jpg", "Losing For You"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg", "Losing you"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg", "恋愛"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample9.jpg", "东方入眠抄"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg", "花束お君に"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample11.jpg", "恋に咲け"));

        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor3.jpg", "在，也不见"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor4.jpg", "Needed Me"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor5.jpg", "One More Time, One more Chance"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample5.jpg", "See you again"));

        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg", "Losing For You"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/sample13.jpg", "Losing you"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/kano.jpg", "恋愛"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor3.jpg", "东方入眠抄"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor4.jpg", "花束お君に"));
        arrayList.add(new SuggestMusicBean("http://o6quza64p.bkt.clouddn.com/hor2.jpeg", "恋に咲け"));
        return arrayList;
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
