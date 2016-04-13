package philips.com.myapplication.actionbutton;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageButton;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/3/7.
 */
public class ActionButton extends ImageButton {
    private static final int SQUARE = 0;
    private static final int CIRCLE = 1;

    public ActionButton(final Context context, final AttributeSet attrs) {
        this(context, attrs, R.attr.actionButtonStyleRef);
    }

    public ActionButton(Context context, AttributeSet attrs,
                        int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context, attrs, defStyle);
    }

    private void initializeView(Context context, AttributeSet attrs, int defStyle) {
        Resources resources = context.getResources();
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ActionButton, defStyle,
                R.style.philips_com_myapplication_actionbutton_ActionButton);

        int resID = getResID(a);
        if (resID > 0) {
        }
    }

    private int getResID(TypedArray a) {
        return 0;
    }
}
