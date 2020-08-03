package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.PlayersAdapter;
import hanalyst.application.hanalystclub.Adapter.TeamAdapter;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.Entity.remote.History;
import hanalyst.application.hanalystclub.Entity.remote.RPlayer;
import hanalyst.application.hanalystclub.Network.API;
import hanalyst.application.hanalystclub.Network.RetrofitBuilder;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.PlayerViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.TeamViewModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTeam extends Fragment {

    public FragmentTeam() {
        // Required empty public constructor
    }


    FloatingActionButton fb;
    String teamId;
    TeamViewModel teamViewModel;
    PlayerViewModel playerViewModel;
    RetrofitBuilder retrofitBuilder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Team");

        SharedPreferenceHAn sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        teamId = sharedPreferenceHAn.getTeamId();


        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        retrofitBuilder = new RetrofitBuilder();

        View view = inflater.inflate(R.layout.fragment_team, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.players_in_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final TeamAdapter teamAdapter = new TeamAdapter();
        final PlayersAdapter playersAdapter = new PlayersAdapter();

        recyclerView.setAdapter(playersAdapter);
        playerViewModel.getAllPlayers().observe(getViewLifecycleOwner(), playersAdapter::setPlayers);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(getString(R.string.are_you_sure_you_want_to_delete_this_player));
                builder.setPositiveButton(getString(R.string.remove), (dialog, which) -> {
                    retrofitBuilder.getApi().deletePlayer(playersAdapter.getSelectedPlayer(position).getId()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                playerViewModel.deletePlayer(playersAdapter.getSelectedPlayer(position).getId());
                                playersAdapter.notifyItemRemoved(position);
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
                        }
                    });
                }).setNegativeButton(getString(R.string.cancel), ((dialog, which) -> {
                    playersAdapter.notifyDataSetChanged();
                })).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        TextView textViewTeamName = view.findViewById(R.id.team_name_display);
        TextView textViewCoachName = view.findViewById(R.id.coach_name_display);
        TextView textViewCaptainName = view.findViewById(R.id.captain_name_display);
        TextView textViewAnalystName = view.findViewById(R.id.analyst_name_display);
        TextView textViewNumberOfPlayers = view.findViewById(R.id.number_of_players_display);
        fb = view.findViewById(R.id.fab_add_players);

        teamViewModel.getAllTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                for (Team team : teams) {
                    if (team.getId().equals(teamId)) {
                        textViewTeamName.setText(team.getName());
                        textViewCoachName.setText(team.getCoach());
                        textViewCaptainName.setText(team.getCaptain());
                        textViewAnalystName.setText(team.getAnalyst());
                        textViewNumberOfPlayers.setText(String.valueOf(team.getPlayers()));
                        return;
                    }
                }
            }
        });


        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Light);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_team_and_player);
                dialog.show();
                // TODO: Cut the four fields for updating the team on settings later
//                final TextView teamName = dialog.findViewById(R.id.input_field_team_name);
//                final TextView captainName = dialog.findViewById(R.id.input_field_captain_name);
//                final TextView coachName = dialog.findViewById(R.id.input_field_coach_name);
//                final TextView analystName = dialog.findViewById(R.id.input_field_analyst_name);
                final TextView playerName = dialog.findViewById(R.id.input_field_player_name);
                final TextView tShirtNumber = dialog.findViewById(R.id.input_field_t_shirt_number);
                Toolbar toolbar = dialog.findViewById(R.id.toolbar);
//                TODO: Back icon for the dialog and dismisses the dialog
//                toolbar.

                Button save = dialog.findViewById(R.id.save_new_player);
                Button cancel = dialog.findViewById(R.id.cancel_adding_player);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addPlayer(Integer.parseInt(tShirtNumber.getText().toString()), playerName.getText().toString(), teamId);
                        dialog.dismiss();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        return view;
    }

    private void addPlayer(int number, String name, String teamId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hanalyst.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<RPlayer> call = api.addAPlayerToATeam(name, number, teamId);
        call.enqueue(new Callback<RPlayer>() {
            @Override
            public void onResponse(Call<RPlayer> call, Response<RPlayer> response) {
                if (response.isSuccessful()) {
                    RPlayer res = response.body();
                    playerViewModel.insertPlayer(new Player(
                            res.getId(),
                            res.getTNumber(),
                            res.getName(),
                            res.getTeamId(),
                            res.getHistory() != null ? saveHistory(res.getHistory()) : ""));
                }
            }

            @Override
            public void onFailure(Call<RPlayer> call, Throwable t) {
                Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String saveHistory(History history) {
        return String.valueOf(history.getId());
    }

}
