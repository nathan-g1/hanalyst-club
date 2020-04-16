package hanalyst.application.hanalystclub.lifecycle;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentHome;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentRecent;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentReport;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentSettings;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentTeam;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentHome fragmentHome = new FragmentHome();
        ft.replace(R.id.frame_fragment_switcher, fragmentHome);
        ft.commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_home:
                        FragmentHome fragmentHome = new FragmentHome();
                        final FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
                        ft1.replace(R.id.frame_fragment_switcher, fragmentHome);
                        ft1.commit();
                        return true;
                    case R.id.action_recent:
                        FragmentRecent fragmentRecent = new FragmentRecent();
                        final FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
                        ft2.replace(R.id.frame_fragment_switcher, fragmentRecent);
                        ft2.commit();
                        return true;
                    case R.id.action_team:
                        FragmentTeam fragmentTeam = new FragmentTeam();
                        final FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
                        ft3.replace(R.id.frame_fragment_switcher, fragmentTeam);
                        ft3.commit();
                        return true;
                    case R.id.action_report:
                        FragmentReport fragmentReport = new FragmentReport();
                        final FragmentTransaction ft4 = getSupportFragmentManager().beginTransaction();
                        ft4.replace(R.id.frame_fragment_switcher, fragmentReport);
                        ft4.commit();
                        return true;
                    case R.id.action_settings:
                        FragmentSettings fragmentSettings = new FragmentSettings();
                        final FragmentTransaction ft5 = getSupportFragmentManager().beginTransaction();
                        ft5.replace(R.id.frame_fragment_switcher, fragmentSettings);
                        ft5.commit();
                        return true;
                }
                return false;
            }
        });
    }
}
