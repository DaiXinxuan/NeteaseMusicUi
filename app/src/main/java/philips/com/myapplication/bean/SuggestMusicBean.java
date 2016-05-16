package philips.com.myapplication.bean;

import android.graphics.Bitmap;

/**
 * Created by 310231492 on 2016/4/20.
 */
public class SuggestMusicBean {
    private String totalListenedCount;
    private Bitmap bitmap;
    private int resId;
    private String title;

    public SuggestMusicBean(Bitmap bitmap, String title) {
        this.bitmap = bitmap;
        this.title = title;
    }

    public SuggestMusicBean(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public SuggestMusicBean(Bitmap bitmap, String title, String totalListenedCount) {
        this(bitmap, title);
        this.totalListenedCount = totalListenedCount;
    }

    public SuggestMusicBean(int resId, String title, String totalListenedCount) {
        this(resId, title);
        this.totalListenedCount = totalListenedCount;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalListenedCount() {
        return totalListenedCount;
    }

    public void setTotalListenedCount(String totalListenedCount) {
        this.totalListenedCount = totalListenedCount;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
