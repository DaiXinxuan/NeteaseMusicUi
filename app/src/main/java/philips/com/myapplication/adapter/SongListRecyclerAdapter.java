package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.SongListBean;
import philips.com.myapplication.viewholder.MusicViewHolder;
import philips.com.myapplication.viewholder.PreciousSongListViewHolder;

/**
 * Created by 310231492 on 2016/6/7.
 */
public class SongListRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final static int TYPE_HEADER = 0;
    private final static int TYPE_ABNORMAL_LIST = 1;
    private final static int TYPE_NORMAL_LIST = 2;
    private final static int TYPE_FOOTER = 3;

    private View headerView;
    private ArrayList<SongListBean> songListBeans;

    public SongListRecyclerAdapter(Context context, ArrayList<SongListBean> songListBeans) {
        this.context = context;
        this.songListBeans = songListBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (headerView != null && viewType == TYPE_HEADER) return new Holder(headerView);
        if (viewType == TYPE_ABNORMAL_LIST) {
            view = getViewHolder(R.layout.precious_songlist_layout, parent, true);
            return new PreciousSongListViewHolder(view);
        } else if (viewType == TYPE_NORMAL_LIST) {
            view = getViewHolder(R.layout.item_music, parent, false);
            ImageView icon = (ImageView) view.findViewById(R.id.music_img);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            layoutParams.height = (windowManager.getDefaultDisplay().getWidth() / 12) * 5;
            icon.setLayoutParams(layoutParams);
            return new MusicViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = getViewHolder(R.layout.item_foot, parent, true);
            return new Holder(view);
        } else{
            return null;
        }
    }

    public View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            return;
        } else if (viewType == TYPE_ABNORMAL_LIST) {
            PreciousSongListViewHolder preciousSongListViewHolder = (PreciousSongListViewHolder) holder;
            SongListBean songListBean = songListBeans.get(0);
            preciousSongListViewHolder.songListDescription.setText(songListBean.getListName());
            Picasso.with(context).load(songListBean.getIconUrl()).fit().config(Bitmap.Config.RGB_565)
                    .placeholder(R.mipmap.loading).into(preciousSongListViewHolder.songListIcon);
        } else if (viewType == TYPE_NORMAL_LIST) {
            SongListBean songListBean = songListBeans.get(position - 1);
            MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
            musicViewHolder.creator_ll.setVisibility(View.VISIBLE);
            musicViewHolder.creator.setText(songListBean.getCreator());
            musicViewHolder.musicDes.setText(songListBean.getListName());
            Picasso.with(context).load(songListBean.getIconUrl()).fit().config(Bitmap.Config.RGB_565)
                    .placeholder(R.mipmap.loading).into(musicViewHolder.musicImg);
        } else if (viewType == TYPE_FOOTER) {
            return;
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else if (position == 1) {
            return TYPE_ABNORMAL_LIST;
        } else {
            return TYPE_NORMAL_LIST;
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getLayoutPosition() == 0) {
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            p.setFullSpan(true);
        }
    }

    @Override
    public int getItemCount() {
        return songListBeans.size() + 2;
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }
}
