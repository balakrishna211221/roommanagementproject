package com.example.roommanagementproject;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.widget.TextView;

public class ProfilePage extends AppCompatActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.transaction:
                    //mTextMessage.setText(R.string.title_home);
                     FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

                     FrameTransaction frameTransaction=new  FrameTransaction();
                     fragmentTransaction.addToBackStack("FrameTransaction");
                     fragmentTransaction.replace(R.id.frame,frameTransaction);
                     fragmentTransaction.commit();

                    return true;
                case R.id.navigation_home:
                    FragmentTransaction fragmentHomet=getSupportFragmentManager().beginTransaction();
                    FragmentHome  fragmentHome=new FragmentHome();
                    fragmentHomet.addToBackStack("FrameHome");
                    FragmentTransaction replace = fragmentHomet.replace(R.id.frame,fragmentHome );
                    fragmentHomet.commit();
                    return true;
                case R.id.navigation_group:
                    FragmentTransaction fragmentgroupt=getSupportFragmentManager().beginTransaction();
                    FragmentGroup  fragmentGroup=new FragmentGroup();
                    fragmentgroupt.addToBackStack("FragmentGroup");
                    FragmentTransaction replace1 =fragmentgroupt.replace(R.id.frame,fragmentGroup );
                    fragmentgroupt.commit();
                    return true;

                   // mTextMessage.setText(R.string.title_dashboard);

                case R.id.mandetorylist:
                    FragmentTransaction fragmentmandetorylist=getSupportFragmentManager().beginTransaction();
                    FragmentMandetoryList  fragmentMandetoryList=new FragmentMandetoryList();
                    fragmentmandetorylist.addToBackStack("FragmentGroup");
                    FragmentTransaction replace2 =fragmentmandetorylist.replace(R.id.frame,fragmentMandetoryList );
                    fragmentmandetorylist.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
