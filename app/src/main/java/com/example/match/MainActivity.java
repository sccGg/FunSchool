package com.example.match;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.match.Entity.Article;
import com.example.match.Entity.User;
import com.example.match.Fragment.ArticleListFragment;
import com.example.match.Fragment.IndexFragment;
import com.example.match.Fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FragmentContainerView fragmentContainerView;
    Fragment[] fragments;

    int lastfragment=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDataBase.getInstance(getApplicationContext());
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        fragmentContainerView = findViewById(R.id.fragment);
        fragments=new Fragment[]{new IndexFragment(),new ArticleListFragment(),new UserFragment()};
        switchFragment(0,0);
        RegisterBottomNavigationListener();
    }
    //注册底部导航按钮监听
    public void RegisterBottomNavigationListener(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.index_bottom_navigation:
                        if(lastfragment!=0){
                            switchFragment(lastfragment,0);
                            lastfragment=0;
                        }
                        break;
                    case R.id.bst_bottom_navigation:
                        if(lastfragment!=1){
                            switchFragment(lastfragment,1);
                            lastfragment=1;
                        }
                        break;
                    case R.id.person_bottom_navigation:
                        if(lastfragment!=2){
                            switchFragment(lastfragment,2);
                            lastfragment=2;
                        }
                        break;

                }
                return true;
            }
        });
    }

    /**
     *切换fragment
     */
    private void switchFragment(int lastfragment, int index) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        //隐藏上个Fragment
       transaction.hide(fragments[lastfragment]);
        if (fragments[index].isAdded() == false) {
            transaction.add(R.id.fragment, fragments[index]);
        }
        transaction.show(fragments[index]).commit();
    }

}
