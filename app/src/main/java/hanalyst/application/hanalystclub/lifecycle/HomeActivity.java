package hanalyst.application.hanalystclub.lifecycle;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.Entity.GameType;
import hanalyst.application.hanalystclub.Entity.remote.RGameType;
import hanalyst.application.hanalystclub.Network.API;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentHome;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentRecent;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentReport;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentSettings;
import hanalyst.application.hanalystclub.lifecycle.fragments.FragmentTeam;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameTypeViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {

    GameTypeViewModel gameTypeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_AppCompat);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        final int[] gameTypesSize = new int[1];
        gameTypeViewModel = new ViewModelProvider(this).get(GameTypeViewModel.class);
        gameTypeViewModel.getAllGameTypes().observe(this, gameTypes -> {
            gameTypesSize[0] = gameTypes.size();
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanalyst.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<List<RGameType>> call = api.getGameTypes();
        call.enqueue(new Callback<List<RGameType>>() {
            @Override
            public void onResponse(Call<List<RGameType>> call, Response<List<RGameType>> response) {
                if (response.isSuccessful()) {
                    List<RGameType> remoteListOfGameTypes = response.body();
                    if (remoteListOfGameTypes != null && remoteListOfGameTypes.size() > gameTypesSize[0]) {
                        for (RGameType rGameType : remoteListOfGameTypes) {
                            gameTypeViewModel.insertGameType(new GameType(rGameType.getId(), rGameType.getGameType(), rGameType.getShortCode()));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RGameType>> call, Throwable t) {
                Toast.makeText(HomeActivity.this, R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });

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

    @Override
    public void onBackPressed() {

    }
}
