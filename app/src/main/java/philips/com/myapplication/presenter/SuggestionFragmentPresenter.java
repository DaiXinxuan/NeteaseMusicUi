package philips.com.myapplication.presenter;

import android.content.Context;

/**
 * Created by 310231492 on 2016/4/19.
 */
public class SuggestionFragmentPresenter {

    private Context context;
    private SuggestionActionView suggestionActionView;

    public SuggestionFragmentPresenter(SuggestionActionView suggestionActionView, Context context) {
        this.suggestionActionView = suggestionActionView;
        this.context = context;
    }

    public void requestViewPagerUrl() {

    }
}
