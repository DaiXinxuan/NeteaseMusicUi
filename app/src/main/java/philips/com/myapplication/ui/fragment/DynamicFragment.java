package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ShareRecyclerAdapter;
import philips.com.myapplication.bean.ShareMusicBean;
import philips.com.myapplication.customview.DividerItemDecoration;

/**
 * Created by 310231492 on 2016/7/5.
 */
public class DynamicFragment extends Fragment {
    private RecyclerView recyclerView;
    private ShareRecyclerAdapter shareRecyclerAdapter;
    private ArrayList<ShareMusicBean> shareMusicBeans;
    private ArrayList<String> imgUrls,imgUrls1,imgUrls2,imgUrls3;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private boolean isRefresh = false;
    private boolean mIsLoadingMore;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.share_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.dynamic_srl);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = layoutManager.findLastVisibleItemPosition();
                if (visibleItem + 1 == recyclerView.getAdapter().getItemCount()) {
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
//                int topRowVerticalPosition =
//                        (recyclerView == null || recyclerView.getChildCount() == 0) ? 0 : recyclerView.getChildAt(0).getTop();
                swipeRefreshLayout.setEnabled(layoutManager.findFirstCompletelyVisibleItemPosition() == 0);
            }
        });

        //设置手指在屏幕下拉多少距离后触发刷新
        swipeRefreshLayout.setDistanceToTriggerSync(300);
        swipeRefreshLayout.setColorSchemeResources(R.color.actionBarColor);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isRefresh) {
                    isRefresh = true;
                    swipeRefreshLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                            isRefresh = false;
                        }
                    }, 2000);
                }
            }
        });

        initData();
        shareRecyclerAdapter = new ShareRecyclerAdapter(getContext(), shareMusicBeans);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(shareRecyclerAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        return view;
    }

    private void loadMoreData() {
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg","小丑团",
                "昨天15:50","我所不在的街道，如果我不在了，这个世界又会改变多少呢？",imgUrls2,"梶浦由记",
                "she was here, alone","http://o6quza64p.bkt.clouddn.com/sample11.jpg",2,5));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg","w放晴",
                "6月30日","即使我手中空无一物，但我仍有将盔甲变成武器的勇气，望你仍能勇敢的活着",null,"RAM WIRE",
                "僕らの手には何もないけど、","http://o6quza64p.bkt.clouddn.com/sample6.jpg",52,4));

        shareRecyclerAdapter.notifyDataSetChanged();
        shareRecyclerAdapter.notifyItemRemoved(shareRecyclerAdapter.getItemCount());
        mIsLoadingMore = false;
    }

    private void initData() {
        imgUrls = new ArrayList<>();
        imgUrls1 = new ArrayList<>();
        imgUrls2 = new ArrayList<>();
        imgUrls3 = new ArrayList<>();
        imgUrls.add("http://o6quza64p.bkt.clouddn.com/daze.jpeg");
        imgUrls1.add("http://o6quza64p.bkt.clouddn.com/sample9.jpg");
        imgUrls1.add("http://o6quza64p.bkt.clouddn.com/sample8.jpg");
        imgUrls2.add("http://o6quza64p.bkt.clouddn.com/sample3.jpg");
        imgUrls2.add("http://o6quza64p.bkt.clouddn.com/sample7.jpg");
        imgUrls2.add("http://o6quza64p.bkt.clouddn.com/sample10.jpg");
        imgUrls3.add("http://o6quza64p.bkt.clouddn.com/sample2.jpg");
        imgUrls3.add("http://o6quza64p.bkt.clouddn.com/QQ%E5%9B%BE%E7%89%8720160513112753.png");
        imgUrls3.add("http://o6quza64p.bkt.clouddn.com/hor1.jpg");
        imgUrls3.add("http://o6quza64p.bkt.clouddn.com/tree.png");


        shareMusicBeans = new ArrayList<>();
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/daze.jpeg","次元墙怪物",
                "7月5日","只有我不在的街道，美好的事还在继续！！！仆街超带感神曲。。。",imgUrls1,"ASIAN KUNG-FU GENERATION",
                "Re:Re(Single ver.)(TV动画《只有我不存在的街道OP》)","http://o6quza64p.bkt.clouddn.com/hor1.jpg",17,14));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/hor2.jpeg","The_begining",
                "6月29日","放不下这些思念与疼痛，胸口像压了石头，喘不上气。又是一个无法入眠的夜晚，我该怎么办",imgUrls,"小缘",
                "无心（无心)","http://o6quza64p.bkt.clouddn.com/hor3.jpg",20,2));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/hor4.jpg","慎殇、",
                "6月18日","刚才在车上放这首歌有种在开宇宙战舰的感觉",null,"Two Steps From Hell",
                "Start Sky -Instrumental","http://o6quza64p.bkt.clouddn.com/hor5.jpg",47,2));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/kano.jpg","优纪子",
                "6月28日","卧槽卧槽卧槽卧槽卧槽卧槽卧槽卧槽vv",imgUrls3,"AXIA",
                "僕らの戦場","http://o6quza64p.bkt.clouddn.com/sample1.jpg",22,2));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg","小丑团",
                "昨天15:50","我所不在的街道，如果我不在了，这个世界又会改变多少呢？",imgUrls2,"梶浦由记",
                "she was here, alone","http://o6quza64p.bkt.clouddn.com/sample11.jpg",2,5));
        shareMusicBeans.add(new ShareMusicBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg","w放晴",
                "6月30日","即使我手中空无一物，但我仍有将盔甲变成武器的勇气，望你仍能勇敢的活着",null,"RAM WIRE",
                "僕らの手には何もないけど、","http://o6quza64p.bkt.clouddn.com/sample6.jpg",52,4));
    }
}
