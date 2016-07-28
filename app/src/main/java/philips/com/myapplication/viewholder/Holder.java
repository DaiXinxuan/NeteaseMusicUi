package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 310231492 on 2016/7/21.
 */
public class Holder extends RecyclerView.ViewHolder {

    public Holder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
    }
}
