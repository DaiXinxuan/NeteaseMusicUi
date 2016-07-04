package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/7/1.
 */
public class RadioFirstFragment extends Fragment{
    private LinearLayout firstRow,secondRow;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    private TextView text1,text2,text3,text4,text5,text6,text7,text8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_second, container, false);
        initView(view);
        return view ;
    }

    private void initView(View view) {
        firstRow = (LinearLayout) view.findViewById(R.id.first_row);
        secondRow = (LinearLayout) view.findViewById(R.id.second_row);
        img1 = (ImageView) firstRow.findViewById(R.id.first_img);
        img2 = (ImageView) firstRow.findViewById(R.id.second_img);
        img3 = (ImageView) firstRow.findViewById(R.id.third_img);
        img4 = (ImageView) firstRow.findViewById(R.id.fourth_img);
        text1 = (TextView) firstRow.findViewById(R.id.first_txt);
        text2 = (TextView) firstRow.findViewById(R.id.second_txt);
        text3 = (TextView) firstRow.findViewById(R.id.third_img_txt);
        text4 = (TextView) firstRow.findViewById(R.id.fourth_img_txt);
        img5 = (ImageView) secondRow.findViewById(R.id.first_img);
        img6 = (ImageView) secondRow.findViewById(R.id.second_img);
        img7 = (ImageView) secondRow.findViewById(R.id.third_img);
        img8 = (ImageView) secondRow.findViewById(R.id.fourth_img);
        text5 = (TextView) secondRow.findViewById(R.id.first_txt);
        text6 = (TextView) secondRow.findViewById(R.id.second_txt);
        text7 = (TextView) secondRow.findViewById(R.id.third_img_txt);
        text8 = (TextView) secondRow.findViewById(R.id.fourth_img_txt);
        img1.setImageResource(R.mipmap.user_fragmet);
        img2.setImageResource(R.mipmap.microphone);
        img3.setImageResource(R.mipmap.smile);
        img4.setImageResource(R.mipmap.book);
        text1.setText(R.string.staras);
        text2.setText(R.string.create);
        text3.setText(R.string.talkshow);
        text4.setText(R.string.beautifulpoem);
        img5.setImageResource(R.mipmap.music);
        img6.setImageResource(R.mipmap.circle_smile);
        img7.setImageResource(R.mipmap.bookwithsound);
        img8.setImageResource(R.mipmap.history);
        text5.setText(R.string.musicstory);
        text6.setText(R.string.motion);
        text7.setText(R.string.bookwithsound);
        text8.setText(R.string.history);
    }
}
