package philips.com.myapplication.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import philips.com.myapplication.Bean.MusicListBean;
import philips.com.myapplication.R;


/**
 * Created by 310231492 on 2016/3/25.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private LruCache<String, Bitmap> lruCache;
    private Context context;
    private int type;
    private ArrayList<MusicListBean> arrayList = new ArrayList<>();
    private OnRecyclerViewItemClickListener mListener;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon;
        TextView mainText, introduction;
        public ViewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            mainText = (TextView) itemView.findViewById(R.id.mainText);
            introduction = (TextView) itemView.findViewById(R.id.introduction);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(getPosition());
        }
    }

    public RecyclerAdapter(Context context, int type, OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.context = context;
        this.type = type;
        mListener = onRecyclerViewItemClickListener;
        if (type == 1) {
            arrayList.add(new MusicListBean(R.mipmap.github_g, context.getString(R.string.localmusic), "(85)"));
            arrayList.add(new MusicListBean(R.mipmap.steam2_g, context.getString(R.string.recentplay), "(100)"));
            arrayList.add(new MusicListBean(R.mipmap.facebook2_g, context.getString(R.string.downloadmanage), "(93)"));
            arrayList.add(new MusicListBean(R.mipmap.stackoverflow_g, context.getString(R.string.mysinger), "(4)"));
            arrayList.add(new MusicListBean(R.mipmap.twitter_g, context.getString(R.string.myradio), "(3)"));
        } else if (type == 2){
            arrayList.add(new MusicListBean(R.mipmap.sample1, context.getString(R.string.localmusic), "(85)"));
            arrayList.add(new MusicListBean(R.mipmap.sample2, context.getString(R.string.recentplay), "(100)"));
            arrayList.add(new MusicListBean(R.mipmap.sample3, context.getString(R.string.downloadmanage), "(93)"));
            arrayList.add(new MusicListBean(R.mipmap.sample4, context.getString(R.string.mysinger), "(4)"));
        } else {
            arrayList.add(new MusicListBean(R.mipmap.sample5, context.getString(R.string.localmusic), "(85)"));
            arrayList.add(new MusicListBean(R.mipmap.sample6, context.getString(R.string.recentplay), "(100)"));
            arrayList.add(new MusicListBean(R.mipmap.sample7, context.getString(R.string.downloadmanage), "(93)"));
        }
        // 获取应用程序最大可用内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() /1024);
        int cacheSize = maxMemory / 8;
        // 设置图片缓存大小为程序最大可用内存的1/8
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount() / 1024;
            }
        };
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (type == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_list ,parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_list, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MusicListBean musicListBean = arrayList.get(position);
        loadBitmap(musicListBean.getImgId(), holder.icon);
//        holder.icon.setImageResource(musicListBean.getImgId());
        holder.mainText.setText(musicListBean.getText());
        holder.introduction.setText(musicListBean.getIntroduction());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            lruCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return lruCache.get(key);
    }

    public void loadBitmap(int resId, ImageView imageView) {
        final String imageKey = String.valueOf(resId);
        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            BitmapWorkerTask task = new BitmapWorkerTask(imageView);
            task.execute(resId);
        }
    }

    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
        ImageView imageView;

        public BitmapWorkerTask(ImageView imageView) {
            this.imageView = imageView;
        }

        // 在后台加载图片。
        @Override
        protected Bitmap doInBackground(Integer... params) {
            final Bitmap bitmap = decodeSampleBitmapFromResources(params[0], 100, 100);
            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }

        public Bitmap decodeSampleBitmapFromResources(int resId, int reqWidth, int reqHeight) {
            // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), resId, options);
            // 调用上面定义的方法计算inSampleSize值
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
            // 使用获取到的inSampleSize值再次解析图片
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeResource(context.getResources(), resId, options);
        }

        public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
            // 源图片的高度和宽度
            final int height = options.outHeight;
            final int width = options.outWidth;
            int inSampleSize = 1;
            if (height > reqHeight || width > reqWidth) {
                // 计算出实际宽高和目标宽高的比率
                final int heightRatio = Math.round((float) height / (float) reqHeight);
                final int widthRatio = Math.round((float) width / (float) reqWidth);
                // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
                // 一定都会大于等于目标的宽和高。
                inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            }
            return inSampleSize;
        }
    }
}
