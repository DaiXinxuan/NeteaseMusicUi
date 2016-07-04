package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.BillBoardBean;
import philips.com.myapplication.viewholder.SmallBillBoardViewHolder;

/**
 * Created by 310231492 on 2016/6/21.
 */
public class RankingRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private final static int TYPE_TITLE = 0;
    private final static int TYPE_BIG = 1;
    private final static int TYPE_SMALL = 2;
    private ArrayList<BillBoardBean> billBoardBeans;

    public RankingRecyclerAdapter(Context context, ArrayList<BillBoardBean> arrayList) {
        this.context = context;
        billBoardBeans = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_TITLE:
                view = LayoutInflater.from(context).inflate(R.layout.songlist_recycler_header, parent, false);
                return new TitleBillBoardViewHolder(view);
            case TYPE_BIG:
                view = LayoutInflater.from(context).inflate(R.layout.item_bigbillboard, parent, false);
                return new BigBillBoardViewHolder(view);
            case TYPE_SMALL:
                view = LayoutInflater.from(context).inflate(R.layout.item_smallbillboard, parent, false);
                return new SmallBillBoardViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_TITLE) {
            TitleBillBoardViewHolder titleBillBoardViewHolder = (TitleBillBoardViewHolder) holder;
            if (position == 0)  {
                titleBillBoardViewHolder.billBoardTitle.setText("官方榜");
            } else  if (position == 6) {
                titleBillBoardViewHolder.billBoardTitle.setText("全球榜");
            } else titleBillBoardViewHolder.billBoardTitle.setText("用户榜");
        } else if (viewType == TYPE_BIG) {
            BigBillBoardViewHolder bigBillBoardViewHolder = (BigBillBoardViewHolder) holder;
            Picasso.with(context).load(billBoardBeans.get(position-1).getImgId()).fit().config(Bitmap.Config.RGB_565)
                    .placeholder(R.mipmap.loading).into(bigBillBoardViewHolder.imageView);
            bigBillBoardViewHolder.firstSong.setText(billBoardBeans.get(position-1).getFirstThreeSongs().get(0));
            bigBillBoardViewHolder.secondSong.setText(billBoardBeans.get(position-1).getFirstThreeSongs().get(1));
            bigBillBoardViewHolder.thirdSong.setText(billBoardBeans.get(position-1).getFirstThreeSongs().get(2));
        }else if (viewType == TYPE_SMALL) {
            SmallBillBoardViewHolder smallBillBoardViewHolder = (SmallBillBoardViewHolder) holder;
            if (position > 27) {
                smallBillBoardViewHolder.updateTime.setText(billBoardBeans.get(position-3).getUpdateTime());
                smallBillBoardViewHolder.name.setText(billBoardBeans.get(position-3).getName());
                Picasso.with(context).load(billBoardBeans.get(position-3).getImgId()).fit().config(Bitmap.Config.RGB_565)
                        .placeholder(R.mipmap.loading).into(smallBillBoardViewHolder.imageView);
            } else {
                smallBillBoardViewHolder.updateTime.setText(billBoardBeans.get(position-2).getUpdateTime());
                smallBillBoardViewHolder.name.setText(billBoardBeans.get(position-2).getName());
                Picasso.with(context).load(billBoardBeans.get(position-2).getImgId()).fit().config(Bitmap.Config.RGB_565)
                        .placeholder(R.mipmap.loading).into(smallBillBoardViewHolder.imageView);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 6 || position == 27) {
            return TYPE_TITLE;
        } else if (position >= 1 && position <= 5 ) {
            return TYPE_BIG;
        } else return  TYPE_SMALL;
    }

    @Override
    public int getItemCount() {
        return billBoardBeans.size() + 3;
    }

    class BigBillBoardViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView firstSong,secondSong,thirdSong;

        public BigBillBoardViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.big_billboard_img);
            firstSong = (TextView) itemView.findViewById(R.id.first_song);
            secondSong = (TextView) itemView.findViewById(R.id.second_song);
            thirdSong = (TextView) itemView.findViewById(R.id.third_song);
        }
    }

    class TitleBillBoardViewHolder extends RecyclerView.ViewHolder {
        public TextView billBoardTitle;
        public LinearLayout linearLayout;

        public TitleBillBoardViewHolder(View itemView) {
            super(itemView);
            billBoardTitle = (TextView) itemView.findViewById(R.id.title_text);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.choose_category);
            linearLayout.setVisibility(View.INVISIBLE);
        }
    }

}
