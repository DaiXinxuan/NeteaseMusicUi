package philips.com.myapplication.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by 310231492 on 2016/4/15.
 */
public class RemoteBitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
    private Context context;
    /**
     * 图片的URL地址
     */
    private String mImageUrl;

    private ImageView mImageView;

    private ImageLoader imageLoader;

    /**
     * 图片压缩的宽高
     */
    private int reqWidth, reqHeight;

    /**
     * 将可重复使用的ImageView传入
     *
     * @param imageView
     */
    public RemoteBitmapWorkerTask(ImageView imageView, int reqWidth, int reqHeight, Context context) {
        mImageView = imageView;
        this.reqWidth = reqWidth;
        this.reqHeight = reqHeight;
        imageLoader = ImageLoader.getInstance();
        this.context = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        mImageUrl = params[0];
        Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(mImageUrl);
        if (bitmap == null) {
            bitmap = loadImage(mImageUrl);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        mImageView.setImageBitmap(bitmap);
    }

    private Bitmap loadImage(String mImageUrl) {
        File imageFile = new File(getImagePath(mImageUrl));
        if (!imageFile.exists()) {
            downloadImage(mImageUrl);
        }
        if (mImageUrl != null) {
            Bitmap bitmap = ImageLoader.decodeSampledBitmapFromResource(imageFile.getPath(),
                    reqWidth, reqHeight);
            if (bitmap != null) {
                imageLoader.addBitmapToMemoryCache(mImageUrl, bitmap);
                return bitmap;
            }
        }
        return null;
    }

    /**
     * 获取图片的本地存储路径。
     *
     * @param mImageUrl
     *            图片的URL地址。
     * @return 图片的本地存储路径。
     */
    private String getImagePath(String mImageUrl) {
        int lastSlashIndex = mImageUrl.lastIndexOf("/");
        String imageName = mImageUrl.substring(lastSlashIndex + 1);
        String imageDir = context.getExternalCacheDir().toString();
        String imagePath = imageDir + File.separator + imageName;
        return imagePath;
    }

    private void downloadImage(String mImageUrl) {
        HttpURLConnection con = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        File imageFile = null;
        try {
            URL url = new URL(mImageUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setConnectTimeout(5 * 1000);
            con.setReadTimeout(15 * 1000);
            con.setDoInput(true);
            con.setDoOutput(true);
            bis = new BufferedInputStream(con.getInputStream());
            imageFile = new File(getImagePath(mImageUrl));
            fos = new FileOutputStream(imageFile);
            bos = new BufferedOutputStream(fos);
            byte[] b = new byte[1024];
            int length;
            while ((length = bis.read(b)) != -1) {
                bos.write(b, 0, length);
                bos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (bos != null) {
                    bos.close();
                }
                if (con != null) {
                    con.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
