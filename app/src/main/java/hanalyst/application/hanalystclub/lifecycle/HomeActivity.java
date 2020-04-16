package hanalyst.application.hanalystclub.lifecycle;

import android.app.Activity;
import android.os.Bundle;

import hanalyst.application.hanalystclub.R;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
