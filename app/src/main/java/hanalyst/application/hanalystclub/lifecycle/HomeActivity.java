package hanalyst.application.hanalystclub.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentHome;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
