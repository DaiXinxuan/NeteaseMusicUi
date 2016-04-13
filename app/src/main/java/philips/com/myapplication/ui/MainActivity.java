package philips.com.myapplication.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

import philips.com.myapplication.R;
import philips.com.myapplication.adapter.RecyclerAdapter;
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
    private RecyclerView listView;


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
        initView();
        setSupportActionBar(toolbar);
        homeFragment = new HomeFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
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
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        goHomeFragment();
    }

    public void initView() {
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
    }

    public void goHomeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.content, homeFragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onCheckedChanged(CheckableImageButton button, boolean isChecked) {
        if (isChecked) {
            switch (button.getId()) {
                case R.id.firstIb:
                    checkableImageButton2.setChecked(false);
                    checkableImageButton3.setChecked(false);
                    goHomeFragment();
                    break;
                case R.id.secondIb:
                    checkableImageButton1.setChecked(false);
                    checkableImageButton3.setChecked(false);
                    if (musicFragment == null) {
                        Log.e("MusicFragment", "is null, new MusicFragment");
                        musicFragment = new MusicFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, musicFragment).addToBackStack(null).commit();
                    break;
                case R.id.thirdIb:
                    checkableImageButton1.setChecked(false);
                    checkableImageButton2.setChecked(false);
                    if (friendFragment == null) {
                        friendFragment = new FriendFragment();
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.content, friendFragment).addToBackStack(null).commit();
            }
        }
    }
}
