package com.example.visitingengineer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.visitingengineer.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        BottomNavigationView navView = findViewById(R.id.nav_view);
//        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId() ){
//                    case R.id.home:
//                        fragment = new HomeFragment();
//                       break;
//                    case R.id.installed:
//                        Toast.makeText(MainActivity.this,"installed apps",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.ServiceProduct:
//                        Toast.makeText(MainActivity.this,"service Products",Toast.LENGTH_SHORT).show();
//                        break;
//                    case R.id.customerServed:
//                        Toast.makeText(MainActivity.this,"customer Service",Toast.LENGTH_SHORT).show();
//                        break;
//                }
//
//                return loadFragment(fragment);
//
//            }
//        });
         AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.installed,R.id.ServiceProduct, R.id.customerServed)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }
    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.profile:
                Toast.makeText(MainActivity.this,"profile",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(MainActivity.this,profile.class);
                 startActivity(intent);
                   break;
            case R.id.notifications:
                  Toast.makeText(MainActivity.this,"notifications",Toast.LENGTH_LONG).show();

                   break;
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
