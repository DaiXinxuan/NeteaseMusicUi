package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.SongListRecyclerAdapter;
import philips.com.myapplication.bean.SongListBean;

/**
 * Created by 310231492 on 2016/3/21.
 */
public class SongListFragment extends Fragment {
    private RecyclerView recyclerView;
    private SongListRecyclerAdapter songListRecyclerAdapter;
    private ArrayList<SongListBean> songListBeans = new ArrayList<>();
    private StaggeredGridLayoutManager mLayoutManager;
    private int lastVisibleItem;
    private Handler handler = new Handler();

    /**
     * 标记是否正在加载更多，防止再次调用加载更多接口
     */
    private boolean mIsLoadingMore = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_song_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.song_list_recycler);
        mLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        songListBeans.add(new SongListBean("[造语者] 穿透灵魂 如唱诗般空灵唯美", "http://o6quza64p.bkt.clouddn.com/sample11.jpg", "精品歌单"));
        songListBeans.add(new SongListBean("助力高考", "http://o6quza64p.bkt.clouddn.com/sample2.jpg", "佐仓小小千代"));
        songListBeans.add(new SongListBean("节奏控", "http://o6quza64p.bkt.clouddn.com/sample5.jpg", "浔杳"));
        songListBeans.add(new SongListBean("隐藏在巷尾的天籁", "http://o6quza64p.bkt.clouddn.com/sample6.jpg", "JEOHYUN-V"));
        songListBeans.add(new SongListBean("假如我有一间咖啡屋", "http://o6quza64p.bkt.clouddn.com/sample8.jpg", "银色潜水艇"));
        songListBeans.add(new SongListBean("将来你会感激现在努力的自己", "http://o6quza64p.bkt.clouddn.com/sample9.jpg", "艾琳"));
        songListBeans.add(new SongListBean("那些好听却低调的歌曲", "http://o6quza64p.bkt.clouddn.com/hor5.jpg", "螺丝起子"));
        songListBeans.add(new SongListBean("让你的孤独成为一场好时光", "http://o6quza64p.bkt.clouddn.com/sample10.jpg", "Double Seven"));
        songListBeans.add(new SongListBean("泱泱华夏，千古风华","http://o6quza64p.bkt.clouddn.com/sample12.jpg","予笙予词"));
        songListRecyclerAdapter = new SongListRecyclerAdapter(getContext(), songListBeans);
        recyclerView.setAdapter(songListRecyclerAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int[] visibleItems = mLayoutManager.findLastVisibleItemPositions(null);
                lastVisibleItem = Math.max(visibleItems[0], visibleItems[1]);
                if (lastVisibleItem + 1 == songListRecyclerAdapter.getItemCount()) {
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
        recyclerView.setNestedScrollingEnabled(false);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.songlist_recycler_header, recyclerView, false);
        songListRecyclerAdapter.setHeaderView(header);
    }

    private void loadMoreData() {
        songListBeans.add(new SongListBean("将来你会感激现在努力的自己", "http://o6quza64p.bkt.clouddn.com/sample4.jpg", "艾琳"));
        songListBeans.add(new SongListBean("那些好听却低调的歌曲", "http://o6quza64p.bkt.clouddn.com/hor4.jpg", "螺丝起子"));
        songListBeans.add(new SongListBean("让你的孤独成为一场好时光", "http://o6quza64p.bkt.clouddn.com/sample7.jpg", "Double Seven"));
        songListBeans.add(new SongListBean("泱泱华夏，千古风华", "http://o6quza64p.bkt.clouddn.com/kano.jpg", "予笙予词"));
        songListRecyclerAdapter.notifyDataSetChanged();
        songListRecyclerAdapter.notifyItemRemoved(songListRecyclerAdapter.getItemCount());
        mIsLoadingMore = false;
    }
}
