package philips.com.myapplication.adapter;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/3/30.
 */
public class ViewPagerPictureAdapter extends PagerAdapter {
    private ArrayList<View> views;

    //n,0,1,2,....,n-1,n,0
    private ArrayList<View> initViews(ArrayList<View> views) {
        ArrayList<View> views1 = new ArrayList<>();
        views1.addAll(views);
        views1.add(0, views.get(views.size() - 1));
        views1.add(views.get(0));
        return views1;
    }

    public ViewPagerPictureAdapter(ArrayList<View> views){
        this.views = initViews(views);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //对ViewPager页号求模取出View列表中要显示的项
//        position = position % views.size();
//        if (position < 0){
//            position = views.size() + position;
//        }
        View view = views.get(position);
        if (position == 0){
            view = views.get(5);
        } else if (position == views.size() - 1) {
            view = views.get(1);
        } else view = views.get(position);
        //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
        ViewParent vp = view.getParent();
        if (vp!=null){
            ViewGroup parent = (ViewGroup)vp;
            parent.removeView(view);
        }
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public void startUpdate(View container) {
        super.startUpdate(container);
    }


//    @Override
//    public Object instantiateItem(View container, int position) {
//        ((ViewPager)container).addView(views.get(position), 0);
//        return views.get(position);
//    }
//
//    @Override
//    public void destroyItem(View container, int position, Object object) {
//        View view = views.get(position % views.size());
//        ((ViewPager) container).removeView(view);
//    }

    @Override
    public void finishUpdate(View container) {
        super.finishUpdate(container);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
        super.restoreState(state, loader);
    }
}
