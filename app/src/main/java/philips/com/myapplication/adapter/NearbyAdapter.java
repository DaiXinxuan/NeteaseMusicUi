package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.NearbyBean;
import philips.com.myapplication.customview.CircleImageView;
import philips.com.myapplication.viewholder.Holder;

/**
 * Created by 310231492 on 2016/7/21.
 */
public class NearbyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<NearbyBean> nearbyBeans;
    private Context context;

    private final static int TYPE_BEAN = 0;
    private final static int TYPE_FOOTER = 1;

    public NearbyAdapter(Context context, ArrayList<NearbyBean> nearbyBeans) {
        this.context = context;
        this.nearbyBeans = nearbyBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new Holder(view);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.nearby_item, parent, false);
            return new NearbyViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FOOTER) return;
        NearbyViewHolder nearbyViewHolder = (NearbyViewHolder) holder;
        NearbyBean nearbyBean = nearbyBeans.get(position);
        Picasso.with(context).load(nearbyBean.getUserIconUrl()).fit().config(Bitmap.Config.RGB_565).
                placeholder(R.mipmap.loading).into(nearbyViewHolder.userIcon);
        nearbyViewHolder.date.setText(nearbyBean.getDate());
        nearbyViewHolder.singer.setText(nearbyBean.getSinger());
        nearbyViewHolder.distance.setText(nearbyBean.getDistance());
        nearbyViewHolder.musicName.setText(nearbyBean.getMusicName());
        nearbyViewHolder.userName.setText(nearbyBean.getUserName());
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else return TYPE_BEAN;
    }

    @Override
    public int getItemCount() {
        return nearbyBeans.size() + 1;
    }

    class NearbyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userIcon;
        TextView userName, distance, musicName, singer, date;

        public NearbyViewHolder(View itemView) {
            super(itemView);
            userIcon = (CircleImageView) itemView.findViewById(R.id.user_icon);
            userName = (TextView) itemView.findViewById(R.id.user_name);
            musicName = (TextView) itemView.findViewById(R.id.music_name);
            distance = (TextView) itemView.findViewById(R.id.distance);
            singer = (TextView) itemView.findViewById(R.id.singername);
            date = (TextView) itemView.findViewById(R.id.time);
        }
    }
}
