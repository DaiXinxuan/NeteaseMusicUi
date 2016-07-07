package philips.com.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.bean.ShareMusicBean;
import philips.com.myapplication.customview.CircleImageView;
import philips.com.myapplication.ui.activity.GalleryActivity;

/**
 * Created by 310231492 on 2016/7/5.
 */
public class ShareRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ShareMusicBean> shareMusicBeans;
    private final static int TYPE_NORMAL = 1;
    private final static int TYPE_FOOTER = 2;

    public ShareRecyclerAdapter(Context context, ArrayList<ShareMusicBean> shareMusicBeans) {
        this.context = context;
        this.shareMusicBeans = shareMusicBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_FOOTER) {
            view = LayoutInflater.from(context).inflate(R.layout.item_foot, parent, false);
            return new Holder(view);
        } else if (viewType == TYPE_NORMAL){
            view = LayoutInflater.from(context).inflate(R.layout.share_layout, parent, false);
            ShareViewHolder viewHolder = new ShareViewHolder(view);
            return viewHolder;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_FOOTER) {
            return;
        } else if (viewType == TYPE_NORMAL) {
            ShareViewHolder shareViewHolder = (ShareViewHolder) holder;
            ShareMusicBean shareMusicBean = shareMusicBeans.get(position);
            if (shareMusicBean.getImgs() != null && shareMusicBean.getImgs().size() > 0 &&
                    shareMusicBean.getImgs().size() < 5) {
                ArrayList<String> imgUrls = shareMusicBean.getImgs();

                DisplayMetrics displayMetrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);

                int screenWidth = displayMetrics.widthPixels;
                if (shareMusicBean.getImgs().size() == 1) {
                    shareViewHolder.onrPictureLl.setVisibility(View.VISIBLE);
                    shareViewHolder.twoPicturesLl.setVisibility(View.GONE);
                    shareViewHolder.threePicturesLl.setVisibility(View.GONE);
                    shareViewHolder.fourPicturesLl.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = shareViewHolder.onePicture.getLayoutParams();
                    layoutParams.height = dip2px(px2dip(screenWidth) - 70) / 2;
                    shareViewHolder.onePicture.setLayoutParams(layoutParams);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(0))
                            .fit().config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading)
                            .into(shareViewHolder.onePicture);

                    shareViewHolder.onePicture.setOnClickListener(new ImgOnClickListener(imgUrls,0));
                } else if (shareMusicBean.getImgs().size() == 2) {
                    shareViewHolder.twoPicturesLl.setVisibility(View.VISIBLE);
                    shareViewHolder.onrPictureLl.setVisibility(View.GONE);
                    shareViewHolder.threePicturesLl.setVisibility(View.GONE);
                    shareViewHolder.fourPicturesLl.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = shareViewHolder.twoPicsFirst.getLayoutParams();
                    layoutParams.height = dip2px(px2dip(screenWidth) - 75) / 2;
                    shareViewHolder.twoPicsFirst.setLayoutParams(layoutParams);
                    shareViewHolder.twoPicsSecond.setLayoutParams(layoutParams);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(0)).fit()
                            .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(shareViewHolder.twoPicsFirst);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(1)).fit()
                            .config(Bitmap.Config.RGB_565).placeholder(R.mipmap.loading).into(shareViewHolder.twoPicsSecond);

                    shareViewHolder.twoPicsFirst.setOnClickListener(new ImgOnClickListener(imgUrls,0));
                    shareViewHolder.twoPicsSecond.setOnClickListener(new ImgOnClickListener(imgUrls,1));
                } else if (shareMusicBean.getImgs().size() == 3) {
                    shareViewHolder.threePicturesLl.setVisibility(View.VISIBLE);
                    shareViewHolder.onrPictureLl.setVisibility(View.GONE);
                    shareViewHolder.twoPicturesLl.setVisibility(View.GONE);
                    shareViewHolder.fourPicturesLl.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = shareViewHolder.threePicsFirst.getLayoutParams();
                    layoutParams.height = dip2px(px2dip(screenWidth) - 80) / 3;
                    shareViewHolder.threePicsFirst.setLayoutParams(layoutParams);
                    shareViewHolder.threePicsSecond.setLayoutParams(layoutParams);
                    shareViewHolder.threePicsThird.setLayoutParams(layoutParams);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(0)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.threePicsFirst);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(1)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.threePicsSecond);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(2)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.threePicsThird);

                    shareViewHolder.threePicsFirst.setOnClickListener(new ImgOnClickListener(imgUrls,0));
                    shareViewHolder.threePicsSecond.setOnClickListener(new ImgOnClickListener(imgUrls,1));
                    shareViewHolder.threePicsThird.setOnClickListener(new ImgOnClickListener(imgUrls,2));
                } else if (shareMusicBean.getImgs().size() == 4) {
                    shareViewHolder.fourPicturesLl.setVisibility(View.VISIBLE);
                    shareViewHolder.onrPictureLl.setVisibility(View.GONE);
                    shareViewHolder.threePicturesLl.setVisibility(View.GONE);
                    shareViewHolder.twoPicturesLl.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParams = shareViewHolder.fourPicsFirst.getLayoutParams();
                    layoutParams.height = dip2px(px2dip(screenWidth)-75) / 2;
                    shareViewHolder.fourPicsFirst.setLayoutParams(layoutParams);
                    shareViewHolder.fourPicsSecond.setLayoutParams(layoutParams);
                    shareViewHolder.fourPicsThird.setLayoutParams(layoutParams);
                    shareViewHolder.fourPicsFourth.setLayoutParams(layoutParams);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(0)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.fourPicsFirst);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(1)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.fourPicsSecond);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(2)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.fourPicsThird);
                    Picasso.with(context).load(shareMusicBean.getImgs().get(3)).fit().config(Bitmap.Config.RGB_565).
                            placeholder(R.mipmap.loading).into(shareViewHolder.fourPicsFourth);

                    shareViewHolder.fourPicsFirst.setOnClickListener(new ImgOnClickListener(imgUrls,0));
                    shareViewHolder.fourPicsSecond.setOnClickListener(new ImgOnClickListener(imgUrls,1));
                    shareViewHolder.fourPicsThird.setOnClickListener(new ImgOnClickListener(imgUrls,2));
                    shareViewHolder.fourPicsFourth.setOnClickListener(new ImgOnClickListener(imgUrls,3));
                }
            } else {
                shareViewHolder.onrPictureLl.setVisibility(View.GONE);
                shareViewHolder.twoPicturesLl.setVisibility(View.GONE);
                shareViewHolder.threePicturesLl.setVisibility(View.GONE);
                shareViewHolder.fourPicturesLl.setVisibility(View.GONE);
            }
            shareViewHolder.commentCount.setText(shareMusicBean.getCommentCount()+"");
            shareViewHolder.comment.setText(shareMusicBean.getComment());
            shareViewHolder.praiseCount.setText(shareMusicBean.getPraiseCount()+"");
            shareViewHolder.singer.setText(shareMusicBean.getSinger());
            shareViewHolder.dateText.setText(shareMusicBean.getDateStr());
            shareViewHolder.musicName.setText(shareMusicBean.getMusicName());
            shareViewHolder.userName.setText(shareMusicBean.getUserName());
            Picasso.with(context).load(shareMusicBean.getCoverUrl()).fit().config(Bitmap.Config.RGB_565).
                    placeholder(R.mipmap.loading).into(shareViewHolder.cover);
            Picasso.with(context).load(shareMusicBean.getUserIconUrl()).fit().config(Bitmap.Config.RGB_565).
                    placeholder(R.mipmap.loading).into(shareViewHolder.userIcon);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return shareMusicBeans.size() + 1;
    }

    class ShareViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView userIcon;
        private TextView userName,dateText,comment,musicName,singer,praiseCount,commentCount;
        private ImageView cover,onePicture,twoPicsFirst,twoPicsSecond,threePicsFirst,
                threePicsSecond,threePicsThird,fourPicsFirst,fourPicsSecond,fourPicsThird,fourPicsFourth;
        private RelativeLayout relativeLayout;
        private LinearLayout onrPictureLl,twoPicturesLl,threePicturesLl,fourPicturesLl;

        public ShareViewHolder(View itemView) {
            super(itemView);
            userIcon = (CircleImageView) itemView.findViewById(R.id.head_icon);
            cover = (ImageView) itemView.findViewById(R.id.sharemusic_icon);
            userName = (TextView) itemView.findViewById(R.id.username);
            dateText = (TextView) itemView.findViewById(R.id.date_text);
            comment = (TextView) itemView.findViewById(R.id.comment_text);
            musicName = (TextView) itemView.findViewById(R.id.sharemusic_name);
            singer = (TextView) itemView.findViewById(R.id.sharemusic_creator);
            praiseCount = (TextView) itemView.findViewById(R.id.praise_count);
            commentCount = (TextView) itemView.findViewById(R.id.comment_count);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.comment_part);
            onrPictureLl = (LinearLayout) itemView.findViewById(R.id.one_picture_ll);
            twoPicturesLl = (LinearLayout) itemView.findViewById(R.id.two_pictures_ll);
            threePicturesLl = (LinearLayout) itemView.findViewById(R.id.three_pictures_ll);
            fourPicturesLl = (LinearLayout) itemView.findViewById(R.id.four_pictures_ll);

            onePicture = (ImageView) itemView.findViewById(R.id.one_picture_img);
            twoPicsFirst = (ImageView) itemView.findViewById(R.id.two_pictures_first);
            twoPicsSecond = (ImageView) itemView.findViewById(R.id.two_pictures_second);
            threePicsFirst = (ImageView) itemView.findViewById(R.id.three_pictures_first);
            threePicsSecond = (ImageView) itemView.findViewById(R.id.three_pictures_second);
            threePicsThird = (ImageView) itemView.findViewById(R.id.three_pictures_third);
            fourPicsFirst = (ImageView) itemView.findViewById(R.id.four_pictures_first);
            fourPicsSecond = (ImageView) itemView.findViewById(R.id.four_pictures_second);
            fourPicsThird = (ImageView) itemView.findViewById(R.id.four_pictures_third);
            fourPicsFourth = (ImageView) itemView.findViewById(R.id.four_pictures_fourth);

        }
    }

    class Holder extends RecyclerView.ViewHolder {

        public Holder(View itemView) {
            super(itemView);
        }
    }

    public int dip2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public int px2dip(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    class ImgOnClickListener implements View.OnClickListener{
        private ArrayList<String> urls;
        private int position;

        public ImgOnClickListener(ArrayList<String> urls, int position) {
            this.urls = urls;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, GalleryActivity.class);
            intent.putStringArrayListExtra("urls", urls);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }
    }
}
