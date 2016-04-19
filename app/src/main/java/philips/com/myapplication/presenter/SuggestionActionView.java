package philips.com.myapplication.presenter;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/4/19.
 */
public interface SuggestionActionView {
    void showError(String errorMsg);

    void loadImages(ArrayList<String> imgUrls);

    void displayData();
}
