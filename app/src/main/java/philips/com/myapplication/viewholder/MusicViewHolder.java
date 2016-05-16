package philips.com.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/4/21.
 */
public class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView musicImg;
    public TextView musicDes;
    public RelativeLayout musicRela;

    public MusicViewHolder(View itemView) {
        super(itemView);
        musicImg = (ImageView) itemView.findViewById(R.id.music_img);
        musicDes = (TextView) itemView.findViewById(R.id.music_des);
        musicRela = (RelativeLayout) itemView.findViewById(R.id.music_rela);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
    }
}
