package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/6/7.
 */
public class PreciousSongListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView songListIcon;
    public TextView songListDescription;

    public PreciousSongListViewHolder(View itemView) {
        super(itemView);
        songListIcon = (ImageView) itemView.findViewById(R.id.list_icon);
        songListDescription = (TextView) itemView.findViewById(R.id.songList_des);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
