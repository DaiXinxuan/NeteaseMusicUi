package philips.com.myapplication.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.ViewPagerFragmentAdapter;
import philips.com.myapplication.customview.CheckableImageButton;
import philips.com.myapplication.ui.fragment.FriendFragment;
import philips.com.myapplication.ui.fragment.HomeFragment;
import philips.com.myapplication.ui.fragment.MusicFragment;

public class MainActivity extends AppCompatActivity implements CheckableImageButton.OnCheckedChangeListener{
    private ImageView menu;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private CheckableImageButton checkableImageButton1,checkableImageButton2,checkableImageButton3;

    private HomeFragment homeFragment;
    private MusicFragment musicFragment;
    private FriendFragment friendFragment;
    private ArrayList<Fragment> fragments;
    private ViewPager viewPager;

    @Override
    public void onBackPressed() {
        if (navigationView.isShown())
            drawerLayout.closeDrawer(Gravity.LEFT);
        else {
            super.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        musicFragment = new MusicFragment();
        friendFragment = new FriendFragment();
        fragments.add(homeFragment);
        fragments.add(musicFragment);
        fragments.add(friendFragment);
        initView();
        setSupportActionBar(toolbar);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem mPreMenuItem;
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (mPreMenuItem != null) {
                    mPreMenuItem.setChecked(false);
                }
                item.setChecked(true);
                mPreMenuItem = item;
                switch (item.getItemId()) {
                    case R.id.message:
                        break;
                    case R.id.store:
                        break;
                    case R.id.music:
                        break;
                    case R.id.twitter:
                        break;
                    case R.id.window:
                        break;
                }
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
        navigationView.setItemIconTintList(null);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.main_viewpager);
        menu = (ImageView) findViewById(R.id.menu);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        checkableImageButton1 = (CheckableImageButton) findViewById(R.id.firstIb);
        checkableImageButton2 = (CheckableImageButton) findViewById(R.id.secondIb);
        checkableImageButton3 = (CheckableImageButton) findViewById(R.id.thirdIb);
        checkableImageButton1.setmOnCheckedChangeListener(this);
        checkableImageButton2.setmOnCheckedChangeListener(this);
        checkableImageButton3.setmOnCheckedChangeListener(this);

        viewPager.setAdapter(new ViewPagerFragmentAdapter(getSupportFragmentManager(), fragments));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: checkableImageButton1.performClick();
                        break;
                    case 1: checkableImageButton2.performClick();
                        break;
                    case 2: checkableImageButton3.performClick();
                        break;
                }
            }
        });
    }

    @Override
    public void onCheckedChanged(CheckableImageButton button, boolean isChecked) {
        if (isChecked) {
            switch (button.getId()) {
                case R.id.firstIb:
                    checkableImageButton2.setChecked(false);
                    checkableImageButton3.setChecked(false);
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.secondIb:
                    checkableImageButton1.setChecked(false);
                    checkableImageButton3.setChecked(false);
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.thirdIb:
                    checkableImageButton1.setChecked(false);
                    checkableImageButton2.setChecked(false);
                    viewPager.setCurrentItem(2);
            }
        }
    }
}
