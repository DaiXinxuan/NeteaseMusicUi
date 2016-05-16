package philips.com.myapplication.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * Created by 310231492 on 2016/4/18.
 * 本地图片缓存类(估计用不到,嘻嘻)
 * ps.这个类之所以存在是因为我本地用的图片分辨率太高，造成了oom，换成加载网络图片的话这个类基本没有存在价值了
 */
public class LocalBitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
    private Context context;
    private int resId;
    private ImageView imageView;
    private ImageLoader imageLoader;
    /**
     * 图片压缩的宽高
     */
    private int reqWidth, reqHeight;

    public LocalBitmapWorkerTask(Context context, ImageView imageView, int reqWidth, int reqHeight) {
        this.context = context;
        this.imageView = imageView;
        this.reqWidth = reqWidth;
        this.reqHeight = reqHeight;
        imageLoader = ImageLoader.getInstance();
    }

    @Override
    protected Bitmap doInBackground(Integer... params) {
        resId = params[0];
        Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(resId + "");
        if (bitmap == null) {
            bitmap = loadImage(resId);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        imageView.setImageBitmap(bitmap);
    }

    public Bitmap loadImage(int resId){
        Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(context, resId, 300, 300);
        imageLoader.addBitmapToMemoryCache(resId+"", bitmap);
        return bitmap;
    }
}
