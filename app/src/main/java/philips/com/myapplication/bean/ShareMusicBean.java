package philips.com.myapplication.bean;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/7/5.
 */
public class ShareMusicBean {
    private String userIconUrl;
    private String userName;
    private String dateStr;
    private String comment;
    private ArrayList<String> imgs;
    private String singer;
    private String musicName;
    private String coverUrl;
    private int commentCount, praiseCount;

    public ShareMusicBean(String userIconUrl, String userName, String dateStr, String comment, ArrayList<String> imgs, String singer, String musicName, String coverUrl, int commentCount, int praiseCount) {
        this.userIconUrl = userIconUrl;
        this.userName = userName;
        this.dateStr = dateStr;
        this.comment = comment;
        this.imgs = imgs;
        this.singer = singer;
        this.musicName = musicName;
        this.coverUrl = coverUrl;
        this.commentCount = commentCount;
        this.praiseCount = praiseCount;
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

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public ArrayList<String> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<String> imgs) {
        this.imgs = imgs;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(int praiseCount) {
        this.praiseCount = praiseCount;
    }
}
