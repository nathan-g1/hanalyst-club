package hanalyst.application.hanalystclub.lifecycle;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.AttackAdapter;
import hanalyst.application.hanalystclub.Adapter.DefenseAdapter;
import hanalyst.application.hanalystclub.Adapter.PlayersAdapter;
import hanalyst.application.hanalystclub.Entity.Attack;
import hanalyst.application.hanalystclub.Entity.Defense;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.AnalysisFactory;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.PlayerViewModel;
import hanalyst.application.hanalystclub.repository.PlayerRepository;

public class Analysis extends AppCompatActivity {

    GridView gridAttack, gridDefence;
    String currentTime = "";
    Timer timerObj = new Timer();
    int minute = 0, second = 0;
    TextView timerTextView, pauseText;
    boolean pause = false;
    PlayerViewModel playerViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gridAttack = findViewById(R.id.grid_attack);
        gridDefence = findViewById(R.id.grid_defence);
        timerTextView = findViewById(R.id.timer);
        pauseText = findViewById(R.id.pause);
        // Attack
        final ArrayList<Attack> attacks = new AnalysisFactory().getAttackList();
        final AttackAdapter attackAdapter = new AttackAdapter(getApplicationContext(), attacks);
        gridAttack.setAdapter(attackAdapter);
        gridAttack.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int prev = attacks.get(position).getValue();
                attacks.get(position).setValue(prev + 1);
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
                defence.get(position).setValue(prev + 1);
                final Dialog dialog = new Dialog(Analysis.this, R.style.FullHeightDialog);

                dialog.setContentView(R.layout.player_list_who);

                if (dialog.getWindow() != null){
                    dialog.getWindow().setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                }

                RecyclerView recyclerView = dialog.findViewById(R.id.players_list);

                final PlayersAdapter playersAdapter = new PlayersAdapter();
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(playersAdapter);




                recyclerView.setAdapter(playersAdapter);
                playerViewModel.getAllPlayers().observe(Analysis.this, new Observer<List<Player>>() {
                    @Override
                    public void onChanged(List<Player> players) {
                        playersAdapter.setPlayers(players);
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
    }

    public void saveNotation() {

    }

}
