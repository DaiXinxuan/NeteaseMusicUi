package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.RankingRecyclerAdapter;
import philips.com.myapplication.bean.BillBoardBean;

/**
 * Created by 310231492 on 2016/3/21.
 */
public class RankingListFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<BillBoardBean> billBoardBeans;
    private ArrayList<String> firstThreeSongs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.ranking_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        billBoardBeans = new ArrayList<>();
        firstThreeSongs = new ArrayList<>();
        firstThreeSongs.add("1.竜神の剣を喰らえ");
        firstThreeSongs.add("2.竜が我が敵を喰らえ");
        firstThreeSongs.add("3.Justice rains");
        billBoardBeans.add(new BillBoardBean(R.mipmap.sample4,firstThreeSongs));
        billBoardBeans.add(new BillBoardBean(R.mipmap.sample1,firstThreeSongs));
        billBoardBeans.add(new BillBoardBean(R.mipmap.sample2,firstThreeSongs));
        billBoardBeans.add(new BillBoardBean(R.mipmap.sample3,firstThreeSongs));
        billBoardBeans.add(new BillBoardBean(R.mipmap.sample5,firstThreeSongs));

        billBoardBeans.add(new BillBoardBean("云音乐ACG音乐榜", R.mipmap.sample6, "每周四更新"));
        billBoardBeans.add(new BillBoardBean("云音乐电音榜", R.mipmap.sample7, "每周五更新"));
        billBoardBeans.add(new BillBoardBean("日本Oricon周榜", R.mipmap.sample8, "每周三更新"));
        billBoardBeans.add(new BillBoardBean("韩国Melon排行榜周榜", R.mipmap.sample9, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("韩国Melon原生周榜", R.mipmap.sample10, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("韩国Mnet排行榜周榜", R.mipmap.sample11, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("Beatport全球电子舞曲榜", R.mipmap.sample12, "每周四更新"));
        billBoardBeans.add(new BillBoardBean("云音乐古典音乐榜", R.mipmap.sample13, "每周四更新"));
        billBoardBeans.add(new BillBoardBean("UK排行榜周榜", R.mipmap.hor1, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("美国BillBoard周榜", R.mipmap.sample6, "每周三更新"));
        billBoardBeans.add(new BillBoardBean("法国 NRJ Vos Hits周榜", R.mipmap.sample3, "每周五更新"));
        billBoardBeans.add(new BillBoardBean("iTunes榜", R.mipmap.sample1, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("Hit FM Top榜", R.mipmap.sample2, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("KTV唛榜", R.mipmap.sample4, "每周五更新"));
        billBoardBeans.add(new BillBoardBean("台湾Hito排行榜", R.mipmap.sample7, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("中国Top排行榜（港台榜）", R.mipmap.sample8, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("中国Top排行榜（内地榜）", R.mipmap.sample10, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("香港电台中文歌曲龙虎榜", R.mipmap.sample11, "每周五更新"));
        billBoardBeans.add(new BillBoardBean("华语金曲榜", R.mipmap.sample13, "每周一更新"));
        billBoardBeans.add(new BillBoardBean("中国嘻哈榜", R.mipmap.sample12, "每周五更新"));

        billBoardBeans.add(new BillBoardBean("音乐达人榜", R.mipmap.sample4,"每周一更新"));
        billBoardBeans.add(new BillBoardBean("音乐新人榜", R.mipmap.sample5,"每周一更新"));
        recyclerView.setAdapter(new RankingRecyclerAdapter(getContext(), billBoardBeans));

        return view;
    }
}
