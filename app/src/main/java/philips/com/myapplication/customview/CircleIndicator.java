package philips.com.myapplication.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import philips.com.myapplication.R;

/**
 * Created by 310231492 on 2016/4/19.
 */
public class CircleIndicator extends LinearLayout implements View.OnTouchListener{
    RelativeLayout relativeLayout;
    ImageView imageView;
    TextView textView;

    Integer strokeColor;
    Float strokeWidth;
    String description;
    Integer iconId;
    Integer iconPressedId;

    final Float STROKE_WIDTH = 4f;

    public CircleIndicator(Context context) {
        super(context);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.circleindicator, this);
        relativeLayout = (RelativeLayout) findViewById(R.id.circle);
        imageView = (ImageView) findViewById(R.id.res);
        textView = (TextView) findViewById(R.id.description);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircleIndicator, 0, 0);
        try {
            iconId = a.getResourceId(R.styleable.CircleIndicator_resource, 0);
            iconPressedId = a.getResourceId(R.styleable.CircleIndicator_resourcePressed, 0);
            description = a.getString(R.styleable.CircleIndicator_text);
            strokeColor = a.getColor(R.styleable.CircleIndicator_strokeColor, getResources().getColor(R.color.actionBarColor));
            strokeWidth = a.getDimension(R.styleable.CircleIndicator_strokeWidth, STROKE_WIDTH);
        } finally {
            a.recycle();
        }
        imageView.setImageResource(iconId);
        textView.setText(description);
        setCircleColorWidth(strokeWidth, strokeColor);
        setLongClickable(true);
        setOnTouchListener(this);
    }

    public void setBackground(Drawable drawable) {
        relativeLayout.setBackground(drawable);
    }

    public void setCircleColorWidth(float strokeWidth, int strokeColor) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setShape(GradientDrawable.OVAL);
        drawable.setStroke((int) strokeWidth, strokeColor);
        drawable.setColor(getResources().getColor(R.color.white));
        setBackground(drawable);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ((GradientDrawable) relativeLayout.getBackground()).setColor(getResources().getColor(R.color.actionBarColor));
                imageView.setImageResource(iconPressedId);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                ((GradientDrawable) relativeLayout.getBackground()).setColor(getResources().getColor(R.color.white));
                imageView.setImageResource(iconId);
                break;
            default:
                break;
        }
        return false;
    }
}
