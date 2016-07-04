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
public class RadioThirdFragment extends Fragment{
    private LinearLayout firstRow,secondRow;
    private ImageView img1,img2;
    private TextView text1,text2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.radio_second, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        firstRow = (LinearLayout) view.findViewById(R.id.first_row);
        img1 = (ImageView) firstRow.findViewById(R.id.first_img);
        img2 = (ImageView) firstRow.findViewById(R.id.second_img);
        text1 = (TextView) firstRow.findViewById(R.id.first_txt);
        text2 = (TextView) firstRow.findViewById(R.id.second_txt);
        img1.setImageResource(R.mipmap.fan);
        img2.setImageResource(R.mipmap.question);
        text1.setText(R.string.xiangsheng);
        text2.setText(R.string.iwannabe);
    }
}
