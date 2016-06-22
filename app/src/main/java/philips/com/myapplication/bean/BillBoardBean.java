package philips.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/6/21.
 */
public class BillBoardBean {
    private String name;
    private String imgUrl;
    private int imgId;
    private String updateTime;
    private ArrayList<Integer> musicIds;
    private ArrayList<String> firstThreeSongs;

    public BillBoardBean(int imgId, ArrayList<String> firstThreeSongs) {
        this.imgId = imgId;
        this.firstThreeSongs = firstThreeSongs;
    }

    public BillBoardBean(String name, int imgId, String updateTime) {
        this.name = name;
        this.imgId = imgId;
        this.updateTime = updateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public ArrayList<Integer> getMusicIds() {
        return musicIds;
    }

    public void setMusicIds(ArrayList<Integer> musicIds) {
        this.musicIds = musicIds;
    }

    public ArrayList<String> getFirstThreeSongs() {
        return firstThreeSongs;
    }

    public void setFirstThreeSongs(ArrayList<String> firstThreeSongs) {
        this.firstThreeSongs = firstThreeSongs;
    }
}
