package com.example.contactappkisan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    Toolbar toolbar;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setUpPager() {
        tabLayout =findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),ViewPagerAdapter.POSITION_UNCHANGED);

        viewPagerAdapter.addFragment(new ContactFragment(this), "Contacts");
        viewPagerAdapter.addFragment(new MessageFragment(this), "Sent");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contact);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_sent);

    }

    private void setUpToolbar() {
        toolbar = findViewById(R.id .toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpToolbar();
        setUpPager();
    }
}
