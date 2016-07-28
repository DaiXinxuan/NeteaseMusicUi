package philips.com.myapplication.ui.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ContactRecyclerAdapter;
import philips.com.myapplication.bean.ContactBean;
import philips.com.myapplication.customview.SideBar;
import philips.com.myapplication.util.PinyinComparator;

/**
 * Created by 310231492 on 2016/7/25.
 */
public class ContacterFragment extends Fragment {
    private ArrayList<ContactBean> contactBeans;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private SideBar sideBar;
    private HashMap<String, Integer> hashMap;
    private CardView cardView;
    private TextView textView;
    private int recentCount = 0;
    int lastState = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler);
        sideBar = (SideBar) view.findViewById(R.id.sidebar);
        sideBar.setAlpha(0.5f);
        cardView = (CardView) view.findViewById(R.id.letter_rect);
        textView = (TextView) view.findViewById(R.id.letter_txt);
        sideBar.setCardView(cardView);
        sideBar.setView(textView);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_SETTLING||(newState == RecyclerView.SCROLL_STATE_IDLE&&lastState!=RecyclerView.SCROLL_STATE_SETTLING)) {
                    if (sideBar.isEnable()) {
                        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(sideBar, "alpha", 0.5f, 0f);
                        objectAnimator.setDuration(1500);
                        objectAnimator.start();
                    }
                }
                lastState = newState;
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 5) {
                    sideBar.setVisibility(View.VISIBLE);
                    sideBar.setAlpha(0.5f);
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        contactBeans = new ArrayList<>();

        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/hor1.jpg", "ZYHAzwraith", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg", "AJ-Soprano", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample11.jpg", "Sirius、、", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample3.jpg", "星光光光光光", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg", "乌啦啦lylyling", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample6.jpg", "Mitsumizu", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg", "小鹅鸽", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg", "Falchion_L", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample2.jpg", "小康同学你好", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample9.jpg", "玩坏少年", true));

        PinyinComparator pinyinComparator = new PinyinComparator();
        Collections.sort(contactBeans, pinyinComparator);

        hashMap = new HashMap<>();
        for (int i=0; i<contactBeans.size(); i++) {
            ContactBean contactBean = contactBeans.get(i);
            if (contactBean.isContactRecently()) {
                recentCount++;
            }
            String firstChar = pinyinComparator.getFirstChar(contactBean.getContactName());
            if (hashMap.containsKey(firstChar)) {
                continue;
            } else {
                hashMap.put(firstChar, i);
            }
        }
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = getPositionForSection(s);
                if (position != -1) {
                    linearLayoutManager.scrollToPositionWithOffset(position + recentCount + 3, 0);
                }
            }
        });


        recyclerView.setAdapter(new ContactRecyclerAdapter(getContext(), contactBeans));

        return view;
    }

    private int getPositionForSection(String key) {
        if (key.equals("#")) {
            key="[";
        }
        if (!hashMap.containsKey(key)) {
            return -1;
        }
        return hashMap.get(key);
    }
}
