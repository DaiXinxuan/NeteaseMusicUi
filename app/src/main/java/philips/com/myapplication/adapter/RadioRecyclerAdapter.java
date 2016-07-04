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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.MusicListBean;
import philips.com.myapplication.viewholder.MusicViewHolder;
import philips.com.myapplication.viewholder.SmallBillBoardViewHolder;

/**
 * Created by 310231492 on 2016/7/4.
 */
public class RadioRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final static int TYPE_HEADER = 0;
    private final static int TYPE_ABNORMAL_LIST = 1;
    private final static int TYPE_ABNORMAL_ANOTHER_LIST = 2;
    private final static int TYPE_NORMAL_LIST = 3;
    private final static int TYPE_TITLE = 4;
    private final static int TYPE_FOOTER = 5;
    private View headerView;
    private ArrayList<MusicListBean> musicListBeans;

    public RadioRecyclerAdapter(Context context, ArrayList<MusicListBean> musicListBeans) {
        this.context = context;
        this.musicListBeans = musicListBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (headerView!=null && viewType == TYPE_HEADER) {
            return new Holder(headerView);
        }
        if (viewType == TYPE_TITLE) {
            view = getViewHolder(R.layout.songlist_recycler_header, parent, true);
            return new RadioTitleViewHolder(view);
        } else if (viewType == TYPE_NORMAL_LIST) {
            view = getViewHolder(R.layout.item_music, parent, false);
            ImageView icon = (ImageView) view.findViewById(R.id.music_img);
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            ViewGroup.LayoutParams layoutParams = icon.getLayoutParams();
            layoutParams.height = (windowManager.getDefaultDisplay().getWidth() / 12) * 5;
            icon.setLayoutParams(layoutParams);
            return new MusicViewHolder(view);
        } else if (viewType == TYPE_ABNORMAL_ANOTHER_LIST) {
            view = getViewHolder(R.layout.item_threemusic, parent, true);
            return new ThreeMusicViewHolder(view);
        } else if (viewType == TYPE_ABNORMAL_LIST) {
            view = getViewHolder(R.layout.item_smallbillboard, parent, true);
            return new SmallBillBoardViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            view = getViewHolder(R.layout.item_foot, parent, true);
            return new Holder(view);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_FOOTER||viewType==TYPE_HEADER) {
            return;
        } else if (viewType == TYPE_TITLE) {
            RadioTitleViewHolder radioTitleViewHolder = (RadioTitleViewHolder) holder;
            if (position == 1) {
                radioTitleViewHolder.textView.setText(context.getString(R.string.greatintroduction));
            } else if (position == 6) {
                radioTitleViewHolder.textView.setText(context.getString(R.string.eve_suggest));
            } else {
                radioTitleViewHolder.textView.setText(context.getString(R.string.popurlarradio));
            }
        } else if (viewType == TYPE_ABNORMAL_LIST) {
            SmallBillBoardViewHolder smallBillBoardViewHolde = (SmallBillBoardViewHolder) holder;
            MusicListBean musicListBean = musicListBeans.get(position - 2);
            smallBillBoardViewHolde.name.setText(musicListBean.getText());
            smallBillBoardViewHolde.updateTime.setText(musicListBean.getIntroduction());
            Picasso.with(context).load(musicListBean.getImgUrl()).fit().config(Bitmap.Config.RGB_565)
                    .placeholder(R.mipmap.loading).into(smallBillBoardViewHolde.imageView);
        } else if (viewType == TYPE_ABNORMAL_ANOTHER_LIST) {
            ThreeMusicViewHolder threeMusicViewHolder = (ThreeMusicViewHolder) holder;
            if (position == 7) {
                threeMusicViewHolder.txt1.setText(musicListBeans.get(position-3).getText());
                threeMusicViewHolder.txt2.setText(musicListBeans.get(position-2).getText());
                threeMusicViewHolder.txt3.setText(musicListBeans.get(position-1).getText());
                Picasso.with(context).load(musicListBeans.get(position-3).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img1);
                Picasso.with(context).load(musicListBeans.get(position-2).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img2);
                Picasso.with(context).load(musicListBeans.get(position-1).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img3);
            } else if (position == 8){
                threeMusicViewHolder.txt1.setText(musicListBeans.get(position-1).getText());
                threeMusicViewHolder.txt2.setText(musicListBeans.get(position).getText());
                threeMusicViewHolder.txt3.setText(musicListBeans.get(position+1).getText());
                Picasso.with(context).load(musicListBeans.get(position-1).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img1);
                Picasso.with(context).load(musicListBeans.get(position).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img2);
                Picasso.with(context).load(musicListBeans.get(position+1).getImgUrl()).fit().
                        config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(threeMusicViewHolder.img3);
            }
        } else if (viewType == TYPE_NORMAL_LIST) {
            MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
            musicViewHolder.ordernum.setVisibility(View.VISIBLE);
            MusicListBean musicListBean = musicListBeans.get(position);
            musicViewHolder.musicDes.setText(musicListBean.getText());
            musicViewHolder.orderTxt.setText(musicListBean.getIntroduction());
            Picasso.with(context).load(musicListBean.getImgUrl()).fit().config(Bitmap.Config.RGB_565)
                    .placeholder(R.mipmap.loading).into(musicViewHolder.musicImg);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if (position + 1 == getItemCount()){
            return TYPE_FOOTER;
        } else if (position == 1||position == 6||position==9) {
            return TYPE_TITLE;
        } else if (position > 1&& position < 6) {
            return TYPE_ABNORMAL_LIST;
        } else if (position==7||position==8) {
            return TYPE_ABNORMAL_ANOTHER_LIST;
        } else return TYPE_NORMAL_LIST;
    }

    @Override
    public int getItemCount() {
        return musicListBeans.size() + 1;
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

    public View getViewHolder(int resource, ViewGroup parent, boolean isFullSpan) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
        layoutParams.setFullSpan(isFullSpan);
        view.setLayoutParams(layoutParams);
        return view;
    }

    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        notifyItemInserted(0);
    }

    class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }

    class RadioTitleViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public LinearLayout linearLayout;

        public RadioTitleViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title_text);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.choose_category);
            linearLayout.setVisibility(View.GONE);
        }
    }

    class ThreeMusicViewHolder extends RecyclerView.ViewHolder {
        public ImageView img1,img2,img3;
        public TextView txt1,txt2,txt3;

        public ThreeMusicViewHolder(View itemView) {
            super(itemView);
            img1 = (ImageView) itemView.findViewById(R.id.music_img1);
            img2 = (ImageView) itemView.findViewById(R.id.music_img2);
            img3 = (ImageView) itemView.findViewById(R.id.music_img3);
            txt1 = (TextView) itemView.findViewById(R.id.music_des1);
            txt2 = (TextView) itemView.findViewById(R.id.music_des2);
            txt3 = (TextView) itemView.findViewById(R.id.music_des3);
        }
    }
}
