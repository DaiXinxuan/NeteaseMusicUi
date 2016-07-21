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
import philips.com.myapplication.adapter.NearbyAdapter;
import philips.com.myapplication.bean.NearbyBean;

/**
 * Created by 310231492 on 2016/7/21.
 */
public class NearbyFragment extends Fragment{
    private ArrayList<NearbyBean> nearbyBeans;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager layoutManager;
    private NearbyAdapter nearbyAdapter;
    private boolean isRefresh = false;
    private boolean mIsLoadingMore;
    private Handler handler = new Handler();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.nearby_layout, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.nearby_recycler);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.nearby_swipe);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        nearbyBeans = new ArrayList<>();
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg","可以-容我想一想吗",
                "0.02km","「青春无处安放」","- 赵雷","昨天17:40"));
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg","Joy慧子",
                "0.03km","「刚刚好」","- 薛之谦","55分钟前"));
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg","甜瓜先生",
                "0.05km","「青春无处安放」","- 赵雷","7月19日"));
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample2.jpg","可以-容我想一想吗",
                "0.04km","「青春无处安放」","- 赵雷","09:11"));
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample9.jpg","可以-容我想一想吗",
                "0.04km","「青春无处安放」","- 赵雷","昨天21:04"));
        nearbyAdapter = new NearbyAdapter(getContext(), nearbyBeans);
        recyclerView.setAdapter(nearbyAdapter);


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

        return view;
    }

    private void loadMoreData() {
        mIsLoadingMore = false;
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg","可以-容我想一想吗",
                "0.02km","「青春无处安放」","- 赵雷","昨天17:40"));
        nearbyBeans.add(new NearbyBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg","Joy慧子",
                "0.03km","「刚刚好」","- 薛之谦","55分钟前"));
        nearbyAdapter.notifyDataSetChanged();
        nearbyAdapter.notifyItemRemoved(nearbyAdapter.getItemCount());
    }
}
