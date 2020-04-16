package hanalyst.application.hanalystclub.lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import hanalyst.application.hanalystclub.R;

public class Login extends Activity {
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Goto the next game form before analysis
                startActivity(new Intent(getApplicationContext(), Analysis.class));
            }
        });
    }
}
