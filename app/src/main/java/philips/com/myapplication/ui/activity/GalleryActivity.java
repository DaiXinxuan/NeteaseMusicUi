package philips.com.myapplication.ui.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.GalleryViewPagerAdapter;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by 310231492 on 2016/7/7.
 */
public class GalleryActivity extends Activity {
    private ViewPager viewPager;
    private TextView truePicture,percentage,save;
    private ArrayList<String> imgUrls;
    private ArrayList<View> views;
    private int size;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_layout);
        viewPager = (ViewPager) findViewById(R.id.gallery);
        truePicture = (TextView) findViewById(R.id.truepicture);
        percentage = (TextView) findViewById(R.id.percentage);
        save = (TextView) findViewById(R.id.save);
        imgUrls = getIntent().getStringArrayListExtra("urls");
        position = getIntent().getIntExtra("position", 0);
        views = new ArrayList<>();
        if (imgUrls != null && imgUrls.size() > 0){
            size = imgUrls.size();
            percentage.setText( (position+1) +"/" + size);
            for (String url: imgUrls) {
                PhotoView imageView = new PhotoView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        GalleryActivity.this.finish();
                    }
                });
                Picasso.with(getApplicationContext()).load(url).config(Bitmap.Config.RGB_565).into(imageView);
                imageView.setScaleLevels(0.5f, 1.0f, 1.5f);
                views.add(imageView);
            }
        }
        viewPager.setAdapter(new GalleryViewPagerAdapter(views));
        viewPager.setCurrentItem(position);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                percentage.setText( (position + 1) + "/" + size);
                super.onPageSelected(position);
            }
        });
        truePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "test", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
