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
public class RadioSecondFragment extends Fragment {
    private LinearLayout firstRow,secondRow;
    private ImageView img1,img2,img3,img4,img5,img6,img7,img8;
    private TextView text1,text2,text3,text4,text5,text6,text7,text8;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_second, container, false);
        initView(view);
        return view;
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
        img1.setImageResource(R.mipmap.abc);
        img2.setImageResource(R.mipmap.jigan);
        img3.setImageResource(R.mipmap.plane);
        img4.setImageResource(R.mipmap.star);
        text1.setText(R.string.englishworld);
        text2.setText(R.string.nigigan);
        text3.setText(R.string.trip);
        text4.setText(R.string.entertainment);
        img5.setImageResource(R.mipmap.earphone);
        img6.setImageResource(R.mipmap.hats);
        img7.setImageResource(R.mipmap.feeder);
        img8.setImageResource(R.mipmap.radio);
        text5.setText(R.string.threed);
        text6.setText(R.string.education);
        text7.setText(R.string.baby);
        text8.setText(R.string.radiocomic);
    }
}
