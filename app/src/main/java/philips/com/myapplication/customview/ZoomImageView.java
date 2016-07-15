package philips.com.myapplication.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 310231492 on 2016/7/8.
 */
public class ZoomImageView extends ImageView implements ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener{
    private boolean mOnce = false;
    private ScaleGestureDetector gestureDetector;

    private float initScale = 1.0f;
    private float midScale = 0.5f;
    private float maxScale = 2.0f;

    private Matrix scaleMatrix;

    public ZoomImageView(Context context) {
        this(context, null);
    }

    public ZoomImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZoomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scaleMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        gestureDetector = new ScaleGestureDetector(context, this);
        setOnTouchListener(this);
    }

    /**
     * 获取当前已经缩放的比例
     * @return  因为x方向和y方向比例相同，所以只返回x方向的缩放比例即可
     */
    private float getDrawableScale() {
        float[] values = new float[9];
        scaleMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (getDrawable() == null) {
            return true;
        }

        float scale = getDrawableScale();
        float scaleFactor = detector.getScaleFactor();

        if ((scale<maxScale&&scaleFactor>1.0f)||(scaleFactor<1.0f&&scale>initScale)) {
            //如果缩小的范围比允许的最小范围还要小，就重置缩放因子为当前的状态的因子
            if (scale * scaleFactor < initScale&&scaleFactor<1.0f) {
                scaleFactor = initScale/scale;
            }
            //如果扩大的范围比允许的最大范围还要大，就重置缩放因子为当前的状态的因子
            if (scale*scaleFactor>maxScale&&scaleFactor>1.0f){
                scaleFactor = maxScale/scale;
            }
            scaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());
            setImageMatrix(scaleMatrix);
        }
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        //返回为true，则缩放手势事件往下进行，否则到此为止，即不会执行onScale和onScaleEnd方法
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (gestureDetector != null) {
            gestureDetector.onTouchEvent(event);
        }
        return true;
    }
}
