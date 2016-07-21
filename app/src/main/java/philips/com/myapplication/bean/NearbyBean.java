package philips.com.myapplication.bean;

/**
 * Created by 310231492 on 2016/7/21.
 */
public class NearbyBean {
    private String userIconUrl;
    private String userName;
    private String distance;
    private String musicName;
    private String singer;
    private String date;

    public NearbyBean(String userIconUrl, String userName, String distance, String musicName, String singer, String date) {
        this.userIconUrl = userIconUrl;
        this.userName = userName;
        this.distance = distance;
        this.musicName = musicName;
        this.singer = singer;
        this.date = date;
    }

    public String getUserIconUrl() {
        return userIconUrl;
    }

    public void setUserIconUrl(String userIconUrl) {
        this.userIconUrl = userIconUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
