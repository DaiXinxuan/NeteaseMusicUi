package philips.com.myapplication.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/7/26.
 */
public class SideBar extends View{
    // 触摸事件
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    // 26个字母
    public static String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#" };
    private int choose = -1;
    private Paint paint = new Paint();
    private CardView cardView;
    private TextView mTextDialog;
    private boolean enable = true;


    public void setView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }


    public SideBar(Context context) {
        super(context);
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setClickable(true);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float y = event.getY();
        int oldChoose = choose;
        OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        int position = (int) (y / getHeight() * letters.length);// 点击y坐标所占总高度的比例*letters数组的长度就等于点击letters中的个数.
        switch (action) {
            case MotionEvent.ACTION_UP:
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(INVISIBLE);
                    cardView.setVisibility(INVISIBLE);
                }
                enable = true;
                break;
            default:
                enable = false;
                setVisibility(VISIBLE);
                if (oldChoose != position) {
                    if (position >= 0&& position < letters.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(letters[position]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(letters[position]);
                            mTextDialog.setVisibility(View.VISIBLE);//这里是指的在屏幕中央显示当前点击的一个A，B，C，D...的一个状态显示。
                            cardView.setVisibility(VISIBLE);
                        }
                        choose = position;
                        invalidate();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 获取焦点改变背景颜色.
        int height = getHeight();// 获取对应高度
        int width = getWidth(); // 获取对应宽度
        int singleHeight = height / letters.length;// 获取每一个字母的高度

        for (int i=0; i<letters.length;i++) {
            paint.setColor(Color.WHITE);
            paint.setTypeface(Typeface.SANS_SERIF);
            paint.setAntiAlias(true);
            paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.side_bar_text_size));
            // x坐标等于中间-字符串宽度的一半.
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();// 重置画笔
        }
    }

    public void setCardView(CardView cardView) {
        this.cardView = cardView;
    }

    public boolean isEnable() {
        return enable;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public void setSelected(String nowChar) {
        if(nowChar!=null){
            for(int i=0;i<letters.length;i++){
                if(letters[i].equals(nowChar)){
                    choose=i;
                    break;
                }
                if(i==letters.length-1){
                    choose=-1;
                }
            }
            invalidate();//刷新整个view
        }
    }
}
