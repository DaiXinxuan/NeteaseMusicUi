package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/7/4.
 */
public class SmallBillBoardViewHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView name,updateTime;
    public RelativeLayout relativeLayout;

    public SmallBillBoardViewHolder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
        imageView = (ImageView) itemView.findViewById(R.id.small_billboard_img);
        name = (TextView) itemView.findViewById(R.id.billboard_name);
        updateTime = (TextView) itemView.findViewById(R.id.update_time);
        relativeLayout = (RelativeLayout) itemView.findViewById(R.id.main_rela);
    }
}
