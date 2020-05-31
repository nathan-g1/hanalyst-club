package hanalyst.application.hanalystclub.lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import hanalyst.application.hanalystclub.Entity.remote.History;
import hanalyst.application.hanalystclub.Entity.remote.RPlayer;
import hanalyst.application.hanalystclub.Entity.remote.RTeam;
import hanalyst.application.hanalystclub.Network.API;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.PlayerViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.TeamViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.UserViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    Button loginBtn;
    TextView email, password;
    SharedPreferenceHAn sharedPreferenceHAn;
    PlayerViewModel playerViewModel;
    TeamViewModel teamViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferenceHAn = new SharedPreferenceHAn(Login.this);
        if (!sharedPreferenceHAn.getTeamId().equals("null")) {
            startActivity(new Intent(this, HomeActivity.class));
        }

        // userViewModel // playerViewModel // teamViewModel
        final UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        if (password.getText().toString().isEmpty() || password.getText().toString().length() < 4) {
            Toast.makeText(Login.this, "password too short", Toast.LENGTH_SHORT).show();
        }

        // TODO: email validation
        if (true) {
        }

        final String emailVal = email.getText().toString().trim();
        final String passwordVal = password.getText().toString();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanalyst.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        Call<ClubUser> call = api.getUser("{\"where\": {\"email\": \"" + emailVal + "\"}}");
        call.enqueue(new Callback<ClubUser>() {
            @Override
            public void onResponse(Call<ClubUser> call, Response<ClubUser> response) {
                if (response.isSuccessful()) {
                    // check if the password is similar from the user input
                    if (response.body().getPassword().equals(passwordVal)) {
                        Toast.makeText(Login.this, getString(R.string.welcome_user) + response.body().getName() + "!", Toast.LENGTH_SHORT).show();
                        sharedPreferenceHAn.setTeamId(response.body().getTeamId());
                        addTeamAndPlayersFromRemote();
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    } else {
                        Toast.makeText(Login.this, R.string.incorrect_pass, Toast.LENGTH_SHORT).show();
                    }
                } else if (response.code() / 500 == 1) {
                    Toast.makeText(Login.this, R.string.server_not_responding, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, R.string.user_not_found, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClubUser> call, Throwable t) {
                Toast.makeText(Login.this, R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addTeamAndPlayersFromRemote() {
        String teamId = sharedPreferenceHAn.getTeamId();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanalyst.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);
        // fetch all players in a team and save
        Call<List<RPlayer>> call = api.getMembersInATeam(teamId);
        call.enqueue(new Callback<List<RPlayer>>() {
            @Override
            public void onResponse(Call<List<RPlayer>> call, Response<List<RPlayer>> response) {
                if (response.isSuccessful()) {
                    List<RPlayer> listOfPlayersFromRemote = response.body();
                    if (listOfPlayersFromRemote != null) {
                        for (RPlayer rPlayer : listOfPlayersFromRemote) {
                            playerViewModel.insertPlayer(new Player(
                                    rPlayer.getId(),
                                    rPlayer.getTNumber(),
                                    rPlayer.getName(),
                                    rPlayer.getTeamId(),
                                    rPlayer.getHistory() != null ? saveHistory(rPlayer.getHistory()) : ""));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RPlayer>> call, Throwable t) {
                Toast.makeText(Login.this, R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });
        // fetch team data and save
        Call<RTeam> call1 = api.getTeamData(teamId);
        call1.enqueue(new Callback<RTeam>() {
            @Override
            public void onResponse(Call<RTeam> call, Response<RTeam> response) {
                if (response.isSuccessful()) {
                    RTeam rTeam = response.body();
                    teamViewModel.insertTeam(new Team(rTeam.getId(),
                            rTeam.getCoach(),
                            rTeam.getAnalyst(),
                            rTeam.getSince(),
                            rTeam.getName(),
                            rTeam.getCaptain(),
                            rTeam.getPlayers()));
                }
            }

            @Override
            public void onFailure(Call<RTeam> call, Throwable t) {
                Toast.makeText(Login.this, R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String saveHistory(History history) {
        // TODO: May be save history locally as well
        return String.valueOf(history.getId());
    }
}
