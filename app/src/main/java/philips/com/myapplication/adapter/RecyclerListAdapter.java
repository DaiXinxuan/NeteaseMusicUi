package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.SuggestMusicBean;
import philips.com.myapplication.imageloader.LocalBitmapWorkerTask;
import philips.com.myapplication.util.RecycleUtil;
import philips.com.myapplication.viewholder.MusicListViewHolder;
import philips.com.myapplication.viewholder.MusicViewHolder;
import philips.com.myapplication.viewholder.TitleViewHolder;

/**
 * Created by 310231492 on 2016/4/20.
 */
public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final static int TYPE_TITLE = 0;
    private final static int TYPE_NORMAL_LIST = 1;
    private final static int TYPE_ABNORMAL_LIST = 2;
    private final static int TYPE_FULL_SPAN = 3;
    private final static int TYPE_HEADER = 4;
    private final static int TYPE_FOOTER = 5;

    private View headerView;

    private ArrayList<SuggestMusicBean> list;

    public RecyclerListAdapter(Context context,ArrayList<SuggestMusicBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if(headerView != null && viewType == TYPE_HEADER) return new Holder(headerView);
        if (viewType == TYPE_FOOTER) return new Holder(getViewHolder(R.layout.recycler_foot, parent, true));
        if (viewType == TYPE_TITLE) {
            view = getViewHolder(R.layout.item_title, parent, true);
            return new TitleViewHolder(view);
        } else if (viewType == TYPE_NORMAL_LIST) {
            view = getViewHolder(R.layout.item_music, parent, false);
            ImageView icon = (ImageView) view.findViewById(R.id.music_img);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            layoutParams.height = (windowManager.getDefaultDisplay().getWidth() / 24) * 7;
            icon.setLayoutParams(layoutParams);
            return new MusicViewHolder(view);
        } else if (viewType == TYPE_ABNORMAL_LIST) {
            view = getViewHolder(R.layout.item_musiclist, parent, true);
            return new MusicListViewHolder(view);
        } else {
            view = getViewHolder(R.layout.item_music, parent, true);
            view.setPadding(28, 5, 28, 5);
            ImageView icon = (ImageView) view.findViewById(R.id.music_img);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            layoutParams.height = windowManager.getDefaultDisplay().getHeight() / 4;
            icon.setLayoutParams(layoutParams);
            return new MusicViewHolder(view);
        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) return;
        if (viewType == TYPE_FOOTER) return;
        if (viewType == TYPE_TITLE) {
            TitleViewHolder viewHolder = (TitleViewHolder) holder;
            dealTitle(position, viewHolder);
        } else if (viewType == TYPE_NORMAL_LIST) {
            MusicViewHolder viewHolder = (MusicViewHolder) holder;
            View view = viewHolder.musicRela;
            switch (holder.getLayoutPosition()) {
                case 2:
                case 12:
                case 22:
                case 5:
                case 15:
                case 25:
                    viewHolder.musicRela.setPadding(view.getPaddingLeft()*2, view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
                    break;
                case 4:
                case 14:
                case 24:
                case 7:
                case 17:
                case 27:
                    viewHolder.musicRela.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight()*2, view.getPaddingBottom());
                    break;
            }
            dealOneMusic(position, viewHolder);
        } else if (viewType == TYPE_ABNORMAL_LIST) {
            MusicListViewHolder viewHolder = (MusicListViewHolder) holder;
            dealMusicList(position, viewHolder);
        } else {
            MusicViewHolder viewHolder = (MusicViewHolder) holder;
            String url = list.get(position - 2).getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.musicImg);
            viewHolder.musicDes.setText(list.get(position - 2).getTitle());
        }
    }

    public void dealTitle(int position, TitleViewHolder viewHolder) {
        switch (position) {
            case RecycleUtil.TITLE1:
                LocalBitmapWorkerTask task = new LocalBitmapWorkerTask(context,
                        viewHolder.icon, 100, 100);
                task.execute(R.mipmap.star_empty);
                viewHolder.des.setText(context.getString(R.string.suggestion_music));
                break;
            case RecycleUtil.TITLE2:
                LocalBitmapWorkerTask task2 = new LocalBitmapWorkerTask(context,
                        viewHolder.icon, 100, 100);
                task2.execute(R.mipmap.html_five2);
                viewHolder.des.setText(context.getString(R.string.onlyone));
                break;
            case RecycleUtil.TITLE3:
                LocalBitmapWorkerTask task3 = new LocalBitmapWorkerTask(context,
                        viewHolder.icon, 100, 100);
                task3.execute(R.mipmap.bubble2);
                viewHolder.des.setText(context.getString(R.string.latestmusic));
                break;
            case RecycleUtil.TITLE4:
                LocalBitmapWorkerTask task4 = new LocalBitmapWorkerTask(context,
                        viewHolder.icon, 100, 100);
                task4.execute(R.mipmap.play2_g);
                viewHolder.des.setText(context.getString(R.string.suggestion_mv));
                break;
            case RecycleUtil.TITLE5:
                LocalBitmapWorkerTask task5 = new LocalBitmapWorkerTask(context,
                        viewHolder.icon, 100, 100);
                task5.execute(R.mipmap.star_empty);
                viewHolder.des.setText(context.getString(R.string.player_radio));
                break;
        }
    }

    public void dealOneMusic(int position, MusicViewHolder viewHolder) {
        if (position > RecycleUtil.TITLE1 && position <RecycleUtil.TITLE2 ) {
            String url = list.get(position - 2).getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.musicImg);
            viewHolder.musicDes.setText(list.get(position - 2).getTitle());
        } else {
            String url = list.get(position - 3).getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.musicImg);
            viewHolder.musicDes.setText(list.get(position - 3).getTitle());
        }
    }

    public void dealMusicList(int position, MusicListViewHolder viewHolder) {
        if (position == RecycleUtil.TWO2) {
            String url = list.get(position - 4).getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.firstImg);
            viewHolder.firstDes.setText(list.get(position - 4).getTitle());
            String url1 = list.get(position - 3).getImgUrl();
            Picasso.with(context).load(url1).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.secondImg);
            viewHolder.secondDes.setText(list.get(position - 3).getTitle());
        } else {
            String url = list.get(position - 3).getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.firstImg);
            viewHolder.firstDes.setText(list.get(position - 3).getTitle());
            String url1 = list.get(position - 2).getImgUrl();
            Picasso.with(context).load(url1).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(viewHolder.secondImg);
            viewHolder.secondDes.setText(list.get(position - 2).getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (RecycleUtil.titles.contains(position)) {
            return TYPE_TITLE;
        } else if (RecycleUtil.twos.contains(position)) {
            return TYPE_ABNORMAL_LIST;
        } else if (position == RecycleUtil.FULLSPAN) {
            return TYPE_FULL_SPAN;
        } else if (position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else return TYPE_NORMAL_LIST;
    }

    public View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
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
        return list.size() + 4;
    }


    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }
}
