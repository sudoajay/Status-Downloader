package com.sudoajay.statusdownloader;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.sudoajay.statusdownloader.Helper.CustomToast;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // global variable
    private Fragment fragment;
    private boolean doubleBackToExitPressedOnce;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private final String rating_link = "https://play.google.com/store/apps/details?id=com.sudoajay.duplication_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_navigation);

        // local variable
        String value = "Scan";
        // get data from intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("passing");
        }

        // Reference and Create Object
        Reference();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//        if (value == null) {
////             default Home
//            setTitle("Home");
//            navigationView.getMenu().getItem(0).setChecked(true);
//            onNavigationItemSelected(navigationView.getMenu().getItem(0));
//        } else {
//            // scan screen
//            setTitle("Scan");
//            navigationView.getMenu().getItem(1).setChecked(true);
//            onNavigationItemSelected(navigationView.getMenu().getItem(1));
//        }
//
//        if (getIntent().getAction() != null) {
//            if (Objects.requireNonNull(getIntent().getAction()).equalsIgnoreCase("Stop_Foreground(Setting)")) {
//                navigationView.getMenu().getItem(1).setChecked(true);
//                onNavigationItemSelected(navigationView.getMenu().getItem(3));
//            }
//
//        }

    }

    // Reference and Create Object
    private void Reference() {

        //reference
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

    }

    // on click listener
    public void OnClick(View v) {

//        switch (v.getId()) {
//            case R.id.duplicateDataImageView:
//            case R.id.duplicateDataTextView:
//                // default Home
//                setTitle("Home");
//                navigationView.getMenu().getItem(0).setChecked(true);
//                onNavigationItemSelected(navigationView.getMenu().getItem(0));
//                break;
//        }

//        if (getVisibleFragment().equals(home)) {
//            home.OnClick(v);
//        } else if (getVisibleFragment().equals(scan)) {
//            scan.OnClick(v);
//        }


    }

    public Fragment getVisibleFragment() {
        FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if ((fragment != null && fragment.isVisible())) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible())
                    return fragment;
            }
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            onBack();
        }
    }

    public void onBack() {
        if (doubleBackToExitPressedOnce) {
            Finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        CustomToast.ToastIt(getApplicationContext(), "Click Back Again To Exit");

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void Finish() {
        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(homeIntent);

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_WhatsAppStatus) {
            // Handle the Home Action
            setTitle("Whatsapp");
//            fragment = home.createInstance(MainActivity.this);
        } else if (id == R.id.nav_InstagramStatus) {
            // Handle the Scan Action
            setTitle("Instagram");
//            fragment = scan.createInstance(MainActivity.this);
        } else if (id == R.id.nav_TikTokStatus) {
            setTitle("Tik Tok");


        } else if (id == R.id.nav_ComingSoon) {

        } else if (id == R.id.nav_Share) {
            Share();

        } else if (id == R.id.nav_Rate_Us) {
            Rate_Us();

        } else if (id == R.id.nav_Send_Feedback) {
            Open_Email();
        } else if (id == R.id.nav_Help) {
            Toast.makeText(getApplicationContext(), "Coming Soon", Toast.LENGTH_LONG).show();
        }
        Replace_Fragments();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Replace Fragments
    public void Replace_Fragments() {

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_Layout, fragment);
            ft.commit();
        }
    }


    private void Share() {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Link-Share");
        i.putExtra(Intent.EXTRA_TEXT, R.string.share_info + rating_link);
        startActivity(Intent.createChooser(i, "Share via"));
    }

    public void Rate_Us() {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(rating_link));
        startActivity(i);
    }


    public void Open_Email() {

        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "sudoajay@gmail.com"));
            intent.putExtra(Intent.EXTRA_SUBJECT, "");
            intent.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(intent);
        } catch (Exception e) {
            CustomToast.ToastIt(getApplicationContext(), "There is no Email App");
        }

    }


}
