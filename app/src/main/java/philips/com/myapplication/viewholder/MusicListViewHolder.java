package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/4/21.
 */
public class MusicListViewHolder extends RecyclerView.ViewHolder {
    public ImageView firstImg, secondImg;
    public TextView firstDes, secondDes;

    public MusicListViewHolder(View itemView) {
        super(itemView);
        firstImg = (ImageView) itemView.findViewById(R.id.first_iv);
        secondImg = (ImageView) itemView.findViewById(R.id.second_iv);
        firstDes = (TextView) itemView.findViewById(R.id.first_tv);
        secondDes = (TextView) itemView.findViewById(R.id.second_tv);
    }
}
