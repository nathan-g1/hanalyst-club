package hanalyst.application.hanalystclub.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentHome;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FragmentHome fragmentHome = new FragmentHome();
        ft.replace(R.id.frame_fragment_switcher, fragmentHome);
        ft.commit();
    }
}
