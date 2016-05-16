package philips.com.myapplication.ui.fragment;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.RecyclerAdapter;
import philips.com.myapplication.customview.ExpandedPanel;

/**
 * Created by 310231492 on 2016/3/23.
 */
public class MusicFragment extends Fragment {
    private RecyclerView mainList;
    private RecyclerView createSongList;
    private RecyclerView collectSongList;
    private ImageView arrow1;
    private ImageView arrow2;
    private ExpandedPanel expandedPanel1, expandedPanel2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mainList = (RecyclerView) view.findViewById(R.id.mainList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainList.setLayoutManager(linearLayoutManager);
        mainList.setAdapter(new RecyclerAdapter(getContext(), 1, new RecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        }));
        mainList.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        createSongList = (RecyclerView) view.findViewById(R.id.createdSongList);
        createSongList.setLayoutManager(linearLayoutManager1);
        createSongList.setAdapter(new RecyclerAdapter(getContext(), 2, new RecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        }));
        createSongList.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        collectSongList = (RecyclerView) view.findViewById(R.id.collectedSongList);
        collectSongList.setLayoutManager(linearLayoutManager2);
        collectSongList.setAdapter(new RecyclerAdapter(getContext(), 3, new RecyclerAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
        }));
        collectSongList.setNestedScrollingEnabled(false);

        expandedPanel1 = (ExpandedPanel) view.findViewById(R.id.expandedpanel1);
        expandedPanel2 = (ExpandedPanel) view.findViewById(R.id.expandedpanel2);
        arrow1 = (ImageView) view.findViewById(R.id.arrow1);
        arrow2 = (ImageView) view.findViewById(R.id.arrow2);
        expandedPanel1.setOnExpandListener(new ExpandedPanel.OnExpandListener() {
            ObjectAnimator objectAnimator = null;
            @Override
            public void onExpand(View handle, View content) {
                objectAnimator = ObjectAnimator.ofFloat(arrow1, "rotation", 0f, 90f);
                objectAnimator.setDuration(200);
                objectAnimator.start();
            }

            @Override
            public void onCollapse(View handle, View content) {
                objectAnimator = ObjectAnimator.ofFloat(arrow1, "rotation", 90f, 0f);
                objectAnimator.setDuration(200);
                objectAnimator.start();
            }
        });
        expandedPanel2.setOnExpandListener(new ExpandedPanel.OnExpandListener() {
            ObjectAnimator objectAnimator = null;
            @Override
            public void onExpand(View handle, View content) {
                objectAnimator = ObjectAnimator.ofFloat(arrow2, "rotation", 0f, 90f);
                objectAnimator.setDuration(200);
                objectAnimator.start();
            }

            @Override
            public void onCollapse(View handle, View content) {
                objectAnimator = ObjectAnimator.ofFloat(arrow2, "rotation", 90f, 0f);
                objectAnimator.setDuration(200);
                objectAnimator.start();
            }
        });


    }
}
