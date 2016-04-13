package philips.com.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.ImageButton;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/3/16.
 */
public class CheckableImageButton extends ImageButton implements Checkable{

    private boolean isChecked;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private static final int[] CHECKED_STATE_SET = { R.attr.isChecked };
    public CheckableImageButton(Context context) {
        super(context);
    }

    public CheckableImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckableImageButton);
        isChecked = a.getBoolean(R.styleable.CheckableImageButton_isChecked, false);
        setChecked(isChecked);
        a.recycle();
    }

    public CheckableImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CheckableImageButton);
        isChecked = a.getBoolean(R.styleable.CheckableImageButton_isChecked, false);
        setChecked(isChecked);
        a.recycle();
    }

    @Override
    public boolean performClick() {
        setChecked(true);
        return super.performClick();
    }

    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            refreshDrawableState();
            if (null != mOnCheckedChangeListener) {
                mOnCheckedChangeListener.onCheckedChanged(this, isChecked);
                refreshDrawableState();
            }
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

    @Override
    public int[] onCreateDrawableState(int extraSpace) {
        int[] states = super.onCreateDrawableState(extraSpace + 1);
        if (isChecked()) {
            mergeDrawableStates(states, CHECKED_STATE_SET);
        }
        return states;
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        // invalidate();
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Parcelable superParcelable = super.onSaveInstanceState();
        SaveState ss = new SaveState(superParcelable);
        ss.checked = isChecked();
        return ss;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        SaveState ss = (SaveState) state;
        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
    }

    public OnCheckedChangeListener getmOnCheckedChangeListener() {
        return mOnCheckedChangeListener;
    }

    public void setmOnCheckedChangeListener(
            OnCheckedChangeListener mOnCheckedChangeListener) {
        this.mOnCheckedChangeListener = mOnCheckedChangeListener;
    }

    public static interface OnCheckedChangeListener {
        public void onCheckedChanged(CheckableImageButton button, boolean isChecked);
    }

    static class SaveState extends BaseSavedState {
        boolean checked;

        public SaveState(Parcel in) {
            super(in);
            checked = (Boolean) in.readValue(null);
        }

        public SaveState(Parcelable superState) {
            super(superState);
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeValue(checked);
        }

        public static final Parcelable.Creator<SaveState> CREATOR = new Creator<CheckableImageButton.SaveState>() {

            @Override
            public SaveState[] newArray(int size) {
                return new SaveState[size];
            }

            @Override
            public SaveState createFromParcel(Parcel source) {
                return createFromParcel(source);
            }
        };
    }
}
