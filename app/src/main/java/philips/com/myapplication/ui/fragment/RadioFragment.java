package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.RadioRecyclerAdapter;
import philips.com.myapplication.adapter.ViewPagerFragmentAdapter;
import philips.com.myapplication.bean.MusicListBean;
import philips.com.myapplication.bean.SongListBean;

/**
 * Created by 310231492 on 2016/3/21.
 */
public class RadioFragment extends Fragment {
    private ViewPager viewPager;
    private ViewGroup indicators;
    private ArrayList<Fragment> fragments;
    private ImageView[] imageViews;
    private RecyclerView recyclerView;
    private final static int FRAGMENTS_COUNT = 3;
    StaggeredGridLayoutManager mLayoutManager;
    int lastVisibleItem;
    boolean mIsLoadingMore;
    private Handler handler = new Handler();
    private ArrayList<MusicListBean> musicListBeans;
    private RadioRecyclerAdapter radioRecyclerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_radio, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.radio_recycler);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        musicListBeans = initData();
        radioRecyclerAdapter = new RadioRecyclerAdapter(getContext(), musicListBeans);
        recyclerView.setAdapter(radioRecyclerAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem = Math.max(visibleItems[0], visibleItems[1]);
                if (lastVisibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
                    if (!mIsLoadingMore) {
                        mIsLoadingMore = true;
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadMoreData();
                            }
                        }, 1000);
                    }
                }
            }
        });
        initHeader(recyclerView);
        return view;
    }

    private void loadMoreData() {
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample4.jpg","将来你会感激现在努力的自己",  "1234订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/hor4.jpg", "那些好听却低调的歌曲","8865订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg","让你的孤独成为一场好时光", "233订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/kano.jpg","泱泱华夏，千古风华",  "1876订阅"));
        radioRecyclerAdapter.notifyDataSetChanged();
        radioRecyclerAdapter.notifyItemRemoved(radioRecyclerAdapter.getItemCount());
        mIsLoadingMore = false;
    }

    private ArrayList<MusicListBean> initData() {
        ArrayList<MusicListBean> musicListBeans = new ArrayList<>();
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample2.jpg","笑红尘—陈一发儿","跟陈一发儿去混江湖!"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample3.jpg","Skm破音-离开地球表面","听超勤劳模破破唱首歌"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample1.jpg","别看我，你眼睛会怀孕","用一句话概括你向往的生活"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample4.jpg","听歌学英语08","听歌学英语"));

        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample5.jpg","南征小姐的ASMR",""));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample6.jpg","三国名将的搞笑出场方式",""));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg","纠正日语发音",""));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg","叙述历史的新方式",""));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample9.jpg","听3分钟够吹一年",""));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg","去污听段子",""));

        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample11.jpg","程一电台","28万订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg","围炉夜话","24802订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample13.jpg","李电台","42556订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/daze.jpeg","SHOW桓 HIPHOPLE RADIO","5361订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/hor1.jpg","阿YueYue","8579订阅"));
        musicListBeans.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/hor2.jpeg","雪奕电台","30841订阅"));
        return musicListBeans;
    }

    private void initHeader(RecyclerView recyclerView) {
        View header = LayoutInflater.from(getContext()).inflate(R.layout.radio_header, recyclerView, false);
        viewPager = (ViewPager) header.findViewById(R.id.title_pager);
        indicators = (ViewGroup) header.findViewById(R.id.indicators);
        fragments = new ArrayList<>();
        fragments.add(new RadioFirstFragment());
        fragments.add(new RadioSecondFragment());
        fragments.add(new RadioThirdFragment());
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getChildFragmentManager(), fragments));

        imageViews = new ImageView[FRAGMENTS_COUNT];
        for (int i=0; i<FRAGMENTS_COUNT; i++) {
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
            indicators.addView(imageViews[i]);
        }
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                switchIndicator(position);
                super.onPageSelected(position);
            }
        });
        radioRecyclerAdapter.setHeaderView(header);
    }

    private void switchIndicator(int position) {
        for (int i=0; i < FRAGMENTS_COUNT; i++) {
            if (position == i) {
                imageViews[i].setImageResource(R.mipmap.banner_dian_focus);
            } else imageViews[i].setImageResource(R.mipmap.banner_dian_blur);
        }
    }
}
