package philips.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/6/7.
 */
public class SongListBean {
    private String listName;
    private String iconUrl;
    private String creator;
    private String listenedCount;
    private ArrayList<Integer> songsIds;

    public SongListBean(String listName, String iconUrl, String creator) {
        this.listName = listName;
        this.iconUrl = iconUrl;
        this.creator = creator;
    }

    public ArrayList<Integer> getSongsIds() {
        return songsIds;
    }

    public void setSongsIds(ArrayList<Integer> songsIds) {
        this.songsIds = songsIds;
    }

    public String getListenedCount() {
        return listenedCount;
    }

    public void setListenedCount(String listenedCount) {
        this.listenedCount = listenedCount;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
