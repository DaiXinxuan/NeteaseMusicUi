package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import philips.com.myapplication.R;


/**
 * Created by 310231492 on 2016/4/21.
 */
public class TitleViewHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView des;

    public TitleViewHolder(View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.title_img);
        des = (TextView) itemView.findViewById(R.id.title_tv);
    }
}
