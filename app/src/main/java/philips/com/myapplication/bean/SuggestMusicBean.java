package philips.com.myapplication.bean;

/**
 * Created by 310231492 on 2016/4/20.
 */
public class SuggestMusicBean {
    private String totalListenedCount;
    private int resId;
    private String imgUrl;
    private String title;

    public SuggestMusicBean(int resId, String title) {
        this.resId = resId;
        this.title = title;
    }

    public SuggestMusicBean(String url, String title) {
        this.imgUrl = url;
        this.title = title;
    }

    public SuggestMusicBean(String imgUrl, String title, String totalListenedCount) {
        this(imgUrl, title);
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
