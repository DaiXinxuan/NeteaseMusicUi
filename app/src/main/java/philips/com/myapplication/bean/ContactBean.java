package philips.com.myapplication.bean;

/**
 * Created by 310231492 on 2016/7/25.
 */
public class ContactBean {
    private String contactIconUrl, contactName;
    private boolean contactRecently;

    public ContactBean(String contactIconUrl, String contactName, boolean contactRecently) {
        this.contactIconUrl = contactIconUrl;
        this.contactName = contactName;
        this.contactRecently = contactRecently;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactIconUrl() {
        return contactIconUrl;
    }

    public void setContactIconUrl(String contactIconUrl) {
        this.contactIconUrl = contactIconUrl;
    }

    public boolean isContactRecently() {
        return contactRecently;
    }

    public void setContactRecently(boolean contactRecently) {
        this.contactRecently = contactRecently;
    }
}
