package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.TeamAdapter;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.TeamViewModel;

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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Team");

        SharedPreferenceHAn sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        teamId = sharedPreferenceHAn.getTeamId();


        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);


        View view = inflater.inflate(R.layout.fragment_team, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.players_in_team);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final TeamAdapter teamAdapter = new TeamAdapter();

        recyclerView.setAdapter(teamAdapter);
        teamViewModel.getAllTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                teamAdapter.setTeams(teams);
                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });
        Team currentTeam = teamViewModel.getATeam("5e3dbcf08b1e76683bd9afd4");
        TextView textViewTeamName = view.findViewById(R.id.team_name_display);
        TextView textViewCoachName = view.findViewById(R.id.coach_name_display);
        TextView textViewCaptainName = view.findViewById(R.id.captain_name_display);
        TextView textViewNumberOfPlayers = view.findViewById(R.id.number_of_players_display);
//        textViewTeamName.setText(currentTeam.getName());
//        textViewCoachName.setText(currentTeam.getCoach());
//        textViewCaptainName.setText(currentTeam.getCaptain());
//        textViewNumberOfPlayers.setText(currentTeam.getPlayers());

        fb = view.findViewById(R.id.fab_add_players);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeam();
            }
        });
//        fb.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Dialog dialog = new Dialog(getContext(), android.R.style.Theme_Light);
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                dialog.setContentView(R.layout.dialog_team_and_player);
//                dialog.show();
//
//                final TextView teamName = dialog.findViewById(R.id.input_field_team_name);
//                final TextView captainName = dialog.findViewById(R.id.input_field_captain_name);
//                final TextView coachName = dialog.findViewById(R.id.input_field_coach_name);
//                final TextView analystName = dialog.findViewById(R.id.input_field_analyst_name);
//                final TextView playerName = dialog.findViewById(R.id.input_field_player_name);
//                final TextView tShirtNumber = dialog.findViewById(R.id.input_field_t_shirt_number);
//
//                Button save = dialog.findViewById(R.id.save_new_player);
//                Button cancel = dialog.findViewById(R.id.cancel_adding_player);
//                save.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        updateTeam(teamName.toString(), captainName.toString(), coachName.toString(), analystName.toString());
//                        addPlayer(Integer.parseInt(tShirtNumber.toString()), playerName.toString(), teamId);
//                        dialog.dismiss();
//                    }
//                });
//
//                cancel.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//            }
//        });
        return view;
    }

    private void addPlayer(int number, String name, String teamId) {
//        PlayerRepository playerRepository = new PlayerRepository(getContext());
//        playerRepository.insertPlayer(number, name, teamId);
    }

    private void addTeam() {
        double i = Math.random();
        Team x = new Team(
                "5e3dbcf08b1e76683bd9afd4" + i,
                "Andinet Mekuria",
                "Shibeshi",
                "1990-01-01T00:00:00.000Z",
                "Mekelle",
                "Rambo",
                17);
        teamViewModel.insertTeam(x);
    }

    private void updateTeam(String teamName, String captainName, String coachName, String analystName) {

    }
}
