package hanalyst.application.hanalystclub.lifecycle;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.AttackAdapter;
import hanalyst.application.hanalystclub.Adapter.DefenseAdapter;
import hanalyst.application.hanalystclub.Adapter.PlayersAdapterWithOnClick;
import hanalyst.application.hanalystclub.Entity.Attack;
import hanalyst.application.hanalystclub.Entity.Defense;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.remote.RNotation;
import hanalyst.application.hanalystclub.Network.API;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.AnalysisFactory;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.PlayerViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Analysis extends AppCompatActivity {

    GridView gridAttack, gridDefence;
    String currentTime = "";
    Timer timerObj = new Timer();
    int minute = 0, second = 0;
    TextView timerTextView, pauseText, effAttack, effDefence, teamsDisplay;
    boolean pause = false;
    PlayerViewModel playerViewModel;
    int zone = 0;
    double attEffectiveness = 0;
    double defEffectiveness = 0;
    private SharedPreferenceHAn sharedPreferenceHAn;
    private List<Player> allPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        sharedPreferenceHAn = new SharedPreferenceHAn(Analysis.this);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gridAttack = findViewById(R.id.grid_attack);
        gridDefence = findViewById(R.id.grid_defence);
        timerTextView = findViewById(R.id.timer);
        pauseText = findViewById(R.id.pause);
        effAttack = findViewById(R.id.textView_attack_effectiveness);
        effDefence = findViewById(R.id.textView_defence_effectiveness);
        teamsDisplay = findViewById(R.id.textView_playing_teams);
        teamsDisplay.setText(sharedPreferenceHAn.getPlayingTeams());

        // Attack
        final ArrayList<Attack> attacks = new AnalysisFactory().getAttackList();
        final AttackAdapter attackAdapter = new AttackAdapter(getApplicationContext(), attacks);
        gridAttack.setAdapter(attackAdapter);
        gridAttack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int prev = attacks.get(position).getValue();
                final Dialog dialog = new Dialog(Analysis.this, R.style.FullHeightDialog);

                dialog.setContentView(R.layout.player_list_who);

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }

                RecyclerView recyclerView = dialog.findViewById(R.id.players_list);

                final PlayersAdapterWithOnClick playersAdapterWithOnClick = new PlayersAdapterWithOnClick();
                playersAdapterWithOnClick.setOnItemClickListener(new PlayersAdapterWithOnClick.ClickListener() {
                    @Override
                    public void onItemClick(int playerPosition, View v) {
                        final Dialog dialog2 = new Dialog(Analysis.this, R.style.FullHeightDialog);
                        dialog2.setTitle(getString(R.string.select_zone));
                        dialog2.setContentView(R.layout.zone_where);
                        Button button1 = dialog2.findViewById(R.id.btn1);
                        Button button2 = dialog2.findViewById(R.id.btn2);
                        Button button3 = dialog2.findViewById(R.id.btn3);
                        Button button4 = dialog2.findViewById(R.id.btn4);
                        Button button5 = dialog2.findViewById(R.id.btn5);
                        Button button6 = dialog2.findViewById(R.id.btn6);
                        Button button7 = dialog2.findViewById(R.id.btn7);
                        Button button8 = dialog2.findViewById(R.id.btn8);
                        Button button9 = dialog2.findViewById(R.id.btn9);
                        Button button10 = dialog2.findViewById(R.id.btn10);
                        Button button11 = dialog2.findViewById(R.id.btn11);
                        Button button12 = dialog2.findViewById(R.id.btn12);
                        ArrayList<Button> arrayList = new ArrayList<>();

                        dialog2.show();
                        arrayList.add(button1);
                        arrayList.add(button2);
                        arrayList.add(button3);
                        arrayList.add(button4);
                        arrayList.add(button5);
                        arrayList.add(button6);
                        arrayList.add(button7);
                        arrayList.add(button8);
                        arrayList.add(button9);
                        arrayList.add(button10);
                        arrayList.add(button11);
                        arrayList.add(button12);

                        for (Button button : arrayList) {
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    allPlayers = playersAdapterWithOnClick.getGivenPlayers();
                                    zone = arrayList.indexOf(button) + 1;
                                    saveNotation(attacks.get(position).getDesc(), zone, playerPosition, currentTime);
                                    attacks.get(position).setValue(prev + 1);
                                    gridAttack.setAdapter(new AttackAdapter(getApplicationContext(), attacks));
                                    atEffectiveness(attacks, effDefence);
                                    dialog.dismiss();
                                    dialog2.dismiss();
                                }
                            });
                        }
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                recyclerView.setAdapter(playersAdapterWithOnClick);
                playerViewModel.getAllPlayers().observe(Analysis.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        playersAdapterWithOnClick.setPlayers(players);
                    }
                });
                dialog.show();
                gridAttack.setAdapter(new AttackAdapter(getApplicationContext(), attacks));
            }
        });
        // Defence
        final ArrayList<Defense> defence = new AnalysisFactory().getDefenseList();
        DefenseAdapter defenseAdapter = new DefenseAdapter(getApplicationContext(), defence);
        gridDefence.setAdapter(defenseAdapter);
        gridDefence.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int prev = defence.get(position).getValue();
                final Dialog dialog = new Dialog(Analysis.this, R.style.FullHeightDialog);

                dialog.setContentView(R.layout.player_list_who);

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }

                RecyclerView recyclerView = dialog.findViewById(R.id.players_list);

                final PlayersAdapterWithOnClick playersAdapterWithOnClick = new PlayersAdapterWithOnClick();
                playersAdapterWithOnClick.setOnItemClickListener(new PlayersAdapterWithOnClick.ClickListener() {
                    @Override
                    public void onItemClick(int playerPosition, View v) {
                        final Dialog dialog2 = new Dialog(Analysis.this, R.style.FullHeightDialog);
                        dialog2.setTitle(getString(R.string.select_zone));
                        dialog2.setContentView(R.layout.zone_where);
                        Button button1 = dialog2.findViewById(R.id.btn1);
                        Button button2 = dialog2.findViewById(R.id.btn2);
                        Button button3 = dialog2.findViewById(R.id.btn3);
                        Button button4 = dialog2.findViewById(R.id.btn4);
                        Button button5 = dialog2.findViewById(R.id.btn5);
                        Button button6 = dialog2.findViewById(R.id.btn6);
                        Button button7 = dialog2.findViewById(R.id.btn7);
                        Button button8 = dialog2.findViewById(R.id.btn8);
                        Button button9 = dialog2.findViewById(R.id.btn9);
                        Button button10 = dialog2.findViewById(R.id.btn10);
                        Button button11 = dialog2.findViewById(R.id.btn11);
                        Button button12 = dialog2.findViewById(R.id.btn12);
                        ArrayList<Button> arrayList = new ArrayList<>();

                        dialog2.show();
                        arrayList.add(button1);
                        arrayList.add(button2);
                        arrayList.add(button3);
                        arrayList.add(button4);
                        arrayList.add(button5);
                        arrayList.add(button6);
                        arrayList.add(button7);
                        arrayList.add(button8);
                        arrayList.add(button9);
                        arrayList.add(button10);
                        arrayList.add(button11);
                        arrayList.add(button12);

                        for (Button button : arrayList) {
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    allPlayers = playersAdapterWithOnClick.getGivenPlayers();
                                    zone = arrayList.indexOf(button) + 1;
                                    saveNotation(defence.get(position).getDesc(), zone, playerPosition, currentTime);
                                    defence.get(position).setValue(prev + 1);
                                    gridDefence.setAdapter(new DefenseAdapter(getApplicationContext(), defence));
                                    deEffectiveness(defence, effDefence);
                                    dialog.dismiss();
                                    dialog2.dismiss();
                                }
                            });
                        }
                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                recyclerView.setAdapter(playersAdapterWithOnClick);
                playerViewModel.getAllPlayers().observe(Analysis.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        playersAdapterWithOnClick.setPlayers(players);
                    }
                });
                dialog.show();
                gridDefence.setAdapter(new DefenseAdapter(getApplicationContext(), defence));
            }
        });
        timer();
    }

    public void timer() {
        this.timerObj = new Timer();
        this.timerObj.schedule(setTask(), 0, 1000);
    }

    public TimerTask setTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                second++;
                if (second == 60) {
                    second = 0;
                    minute++;
                }
                currentTime = minute + ":" + second;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerTextView.setText(currentTime);
                    }
                });
            }
        };
        return task;
    }

    public void pauseTimer(View v) {
        pause = !pause;
        if (pause) {
            this.timerObj.cancel();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String p = "RESUME";
                    pauseText.setText(p);
                }
            });
        } else {
            timer();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String r = "PAUSE";
                    pauseText.setText(r);
                }
            });
        }
        String message = pause ? "Pause" : "Resume";
        Snackbar.make(v, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void atEffectiveness(ArrayList<Attack> attacks, TextView effAttack) {
        int totalShots = attacks.get(9).getValue() + attacks.get(8).getValue();
        attEffectiveness = ((attacks.get(9).getValue() * 100) * 1.0) / (double) totalShots;
        String s = "ATT:EFF " + attEffectiveness + "%";
        effAttack.setText(s);
    }

    private void deEffectiveness(ArrayList<Defense> defenses, TextView effDefence) {
        int totalShots = defenses.get(9).getValue() + defenses.get(8).getValue();
        defEffectiveness = ((defenses.get(9).getValue() * 100) * 1.0) / (double) totalShots;
        String s = "DFN:EFF " + defEffectiveness + "%";
        effDefence.setText(s);
    }

    private void saveNotation(String what, int zone, int playerPosition, String currentTime) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanalyst.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<RNotation> rNotationCall = api.saveNotation(what, allPlayers.get(playerPosition).getName(), String.valueOf(zone), currentTime, sharedPreferenceHAn.getTeamId());
        rNotationCall.enqueue(new Callback<RNotation>() {
            @Override
            public void onResponse(Call<RNotation> call, Response<RNotation> response) {
                RNotation rNotation = response.body();
                if (response.isSuccessful()) {
//                    if (what.equals())
                    NotationViewModel notationViewModel = new ViewModelProvider(Analysis.this).get(NotationViewModel.class);
                    notationViewModel.insertNotation(new Notation(rNotation.getId(),
                            rNotation.getWhat(),
                            rNotation.getWho(),
                            rNotation.getWhen(),
                            rNotation.getWhere(),
                            rNotation.getGameId()));
                }
            }

            @Override
            public void onFailure(Call<RNotation> call, Throwable t) {
                Toast.makeText(Analysis.this, R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
