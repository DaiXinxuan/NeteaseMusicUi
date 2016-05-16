package philips.com.myapplication.util;

import java.util.ArrayList;

/**
 * Created by 310231492 on 2016/4/21.
 */
public class RecycleUtil {
    public final static int TITLE1 = 1;
    public final static int TITLE2 = 8;
    public final static int TITLE3 = 11;
    public final static int TITLE4 = 18;
    public final static int TITLE5 = 21;
    public final static int TWO1 = 9;
    public final static int TWO2 = 19;
    public final static int TWO3 = 20;
    public final static int FULLSPAN = 10;
    public final static ArrayList<Integer> titles = new ArrayList<Integer>(){
        {add(RecycleUtil.TITLE1); add(RecycleUtil.TITLE2); add(RecycleUtil.TITLE3); add(RecycleUtil.TITLE4); add(RecycleUtil.TITLE5);}
    };
    public final static ArrayList<Integer> twos = new ArrayList<Integer>(){
        {add(RecycleUtil.TWO1); add(RecycleUtil.TWO2); add(RecycleUtil.TWO3);}
    };
}
