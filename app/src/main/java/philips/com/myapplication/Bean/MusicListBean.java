package philips.com.myapplication.Bean;


/**
 * Created by 310231492 on 2016/3/23.
 */
public class MusicListBean {
    private int imgId;
    private String text;
    private String introduction;

    public  MusicListBean(int imgId, String text, String introduction) {
        this.imgId = imgId;
        this.text = text;
        this.introduction = introduction;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
