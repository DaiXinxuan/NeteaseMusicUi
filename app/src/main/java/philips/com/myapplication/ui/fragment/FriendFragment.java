package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ViewPagerFragmentAdapter;

/**
 * Created by 310231492 on 2016/3/23.
 */
public class FriendFragment extends Fragment {
    private TextView[] textViews = new TextView[3];
    private View cutOffRule;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;


    int screenWidth;
    int currentIndex;
    private int previousTextColor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fragments = new ArrayList<>();
        fragments.add(new SuggestionFragment());
        fragments.add(new SongListFragment());
        fragments.add(new RadioFragment());
        textViews[0] = (TextView) view.findViewById(R.id.dynamic);
        textViews[1] = (TextView) view.findViewById(R.id.around);
        textViews[2] = (TextView) view.findViewById(R.id.friends);
        previousTextColor = textViews[1].getCurrentTextColor();
        textViews[0].setOnClickListener(new TextViewOnClickListener(0));
        textViews[1].setOnClickListener(new TextViewOnClickListener(1));
        textViews[2].setOnClickListener(new TextViewOnClickListener(2));
        cutOffRule = view.findViewById(R.id.cut_off_rule);
        adjustCutOffRule();
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        initViewPager(view);
    }

    private void initViewPager(final View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerFragmentAdapter(getChildFragmentManager(), fragments));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) cutOffRule.getLayoutParams();
                if (currentIndex - position == 1) {
                    layoutParams.leftMargin = (int)(-(1 - positionOffset) * (screenWidth * 1.0 / 3)) + currentIndex * (screenWidth / 3);
                } else if (position == currentIndex){
                    layoutParams.leftMargin = (int)(positionOffset * (screenWidth * 1.0 / 3)) + currentIndex * (screenWidth / 3);
                }
                cutOffRule.setLayoutParams(layoutParams);
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < textViews.length; i++) {
                    if (i == position) {
                        textViews[i].setTextColor(getResources().getColor(R.color.actionBarColor));
                    } else textViews[i].setTextColor(previousTextColor);
                }
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(0);
    }

    private void adjustCutOffRule() {
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        ViewGroup.LayoutParams layoutParams = cutOffRule.getLayoutParams();
        layoutParams.width = screenWidth / 3;
        cutOffRule.setLayoutParams(layoutParams);
    }

    class TextViewOnClickListener implements View.OnClickListener{
        int index;
        public TextViewOnClickListener(int index) {
            this.index = index;
        }
        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }
}
