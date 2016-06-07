package philips.com.myapplication.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.bean.MusicListBean;
import philips.com.myapplication.R;
import philips.com.myapplication.imageloader.LocalBitmapWorkerTask;
import philips.com.myapplication.imageloader.RemoteBitmapWorkerTask;


/**
 * Created by 310231492 on 2016/3/25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private Context context;
    private int type;
    private ArrayList<MusicListBean> arrayList = new ArrayList<>();
    private OnRecyclerViewItemClickListener mListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView mainText, introduction;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            introduction = (TextView) itemView.findViewById(R.id.introduction);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getPosition());
        }
    }

    public RecyclerAdapter(Context context, int type, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.context = context;
        this.type = type;
        mListener = onRecyclerViewItemClickListener;
        if (type == 1) {
            arrayList.add(new MusicListBean("http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg", context.getString(R.string.localmusic), "(85)"));
            arrayList.add(new MusicListBean("http://img.my.csdn.net/uploads/201309/01/1378037235_9280.jpg", context.getString(R.string.recentplay), "(100)"));
            arrayList.add(new MusicListBean("http://img.my.csdn.net/uploads/201309/01/1378037177_1254.jpg", context.getString(R.string.downloadmanage), "(93)"));
            arrayList.add(new MusicListBean("http://img.my.csdn.net/uploads/201309/01/1378037152_6352.jpg", context.getString(R.string.mysinger), "(4)"));
        } else if (type == 2){
            arrayList.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample13.jpg", context.getString(R.string.localmusic), "(85)"));
            arrayList.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg", context.getString(R.string.recentplay), "(100)"));
            arrayList.add(new MusicListBean("http://o6quza64p.bkt.clouddn.com/sample11.jpg", context.getString(R.string.downloadmanage), "(93)"));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MusicListBean musicListBean = arrayList.get(position);
        if (musicListBean.getImgId() == 0) {
            String url = musicListBean.getImgUrl();
            Picasso.with(context).load(url).fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(holder.icon);
        } else {
            LocalBitmapWorkerTask localBitmapWorkerTask = new LocalBitmapWorkerTask(context, holder.icon, 100, 100);
            localBitmapWorkerTask.execute(musicListBean.getImgId());
        }
        holder.mainText.setText(musicListBean.getText());
        holder.introduction.setText(musicListBean.getIntroduction());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

}
