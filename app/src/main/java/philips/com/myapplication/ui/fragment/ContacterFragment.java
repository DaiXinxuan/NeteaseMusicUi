package philips.com.myapplication.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ContactRecyclerAdapter;
import philips.com.myapplication.bean.ContactBean;

/**
 * Created by 310231492 on 2016/7/25.
 */
public class ContacterFragment extends Fragment {
    private ArrayList<ContactBean> contactBeans;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler);

        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        contactBeans = new ArrayList<>();
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample12.jpg", "AJ-Soprano", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample10.jpg", "Falchion_L", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample6.jpg", "Mitsumizu", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample11.jpg", "Sirius、、", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample9.jpg", "玩坏少年", true));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample8.jpg", "乌啦啦lylyling", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample7.jpg", "小鹅鸽", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample3.jpg", "星光光光光光", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/sample2.jpg", "小康同学你好", false));
        contactBeans.add(new ContactBean("http://o6quza64p.bkt.clouddn.com/hor1.jpg", "ZYHAzwraith", true));

        recyclerView.setAdapter(new ContactRecyclerAdapter(getContext(), contactBeans));

        return view;
    }
}
