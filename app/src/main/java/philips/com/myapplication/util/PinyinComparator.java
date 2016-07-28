package philips.com.myapplication.util;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.Comparator;

import philips.com.myapplication.bean.ContactBean;

/**
 * Created by 310231492 on 2016/7/26.
 */
public class PinyinComparator implements Comparator<ContactBean> {
    String ostr1;
    String ostr2;

    @Override
    public int compare(ContactBean lhs, ContactBean rhs) {
        ostr1 = getFirstChar(lhs.getContactName());
        ostr2 = getFirstChar(rhs.getContactName());

        for (int i=0; i < ostr1.length()&& i < ostr2.length(); i++) {
            int codePoint1 = ostr1.charAt(i);
            int codePoint2 = ostr2.charAt(i);
            if (Character.isSupplementaryCodePoint(codePoint1)
                    || Character.isSupplementaryCodePoint(codePoint2)) {
                i++;
            }
            if (codePoint1 != codePoint2) {
                // 拼音字符
                if (Character.isSupplementaryCodePoint(codePoint1)
                        || Character.isSupplementaryCodePoint(codePoint2)) {
                    return codePoint1 - codePoint2;
                }
                String pinyin1 = pinyin((char) codePoint1);
                String pinyin2 = pinyin((char) codePoint2);

                if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
                    if (!pinyin1.equals(pinyin2)) {
                        return pinyin1.compareTo(pinyin2);
                    }
                } else {
                    return codePoint1 - codePoint2;
                }
            }
        }
        return ostr1.length() - ostr2.length();
    }

    // 获得汉字拼音的首字符
    private String pinyin(char c) {
        String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
        if (pinyins == null) {
            return null;
        }
        return pinyins[0];
    }

    // 获得字符串的首字母 首字符 转汉语拼音
    public String getFirstChar(String value) {
        // 首字符
        char firstChar = value.charAt(0);
        // 首字母分类
        String first = null;
        // 是否是非汉字
        String[] print = PinyinHelper.toHanyuPinyinStringArray(firstChar);

        if (print == null) {
            // 将小写字母改成大写
            if ((firstChar >= 97 && firstChar <= 122)) {
                firstChar -= 32;
            }
            if (firstChar >= 65 && firstChar <= 90) {
                first = String.valueOf((char) firstChar);
            } else {
                // 认为首字符为数字或者特殊字符
                first = "#";
            }
        } else {
            // 如果是中文分类大写字母
            first = String.valueOf((char) (print[0].charAt(0) - 32));
        }
        if (first == null) {
            first = "?";
        }
        return first;
    }
}
