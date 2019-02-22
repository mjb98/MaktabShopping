package com.example.mjb.maktabshopping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TableLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class ListViewPagerActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment() {
        return ProductListFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbar();



    }



    private void setToolbar() {
        AppBarLayout appBarLayout = findViewById(R.id.toolbar_list);
        Toolbar toolbar = ((Toolbar) appBarLayout.findViewById(R.id.toolbar));
        toolbar.setNavigationIcon(R.drawable.ic_menu_24dp);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fragment_list_menu,menu);
        return true;
    }
}
