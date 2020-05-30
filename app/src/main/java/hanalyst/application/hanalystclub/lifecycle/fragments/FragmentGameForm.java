package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.Entity.GameType;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameTypeViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.TeamViewModel;

public class FragmentGameForm extends Fragment {

    TeamViewModel teamViewModel;
    GameTypeViewModel gameTypeViewModel;
    String currentTeamName;

    public FragmentGameForm() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Start Game");
        View view = inflater.inflate(R.layout.fragment_game_form, container, false);
        EditText venueEditText = view.findViewById(R.id.venue);
        EditText refereeNameEditText = view.findViewById(R.id.referee_name);
        RadioGroup radioGroup = view.findViewById(R.id.home_or_away_radio_group);
        Spinner gameTypeSpinner = view.findViewById(R.id.spinner_game_type);
        Spinner opponentTeamSpinner = view.findViewById(R.id.spinner_opponent_team);
        ListView playersMarchingListView = view.findViewById(R.id.list_of_players);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.game_home) {
                    Toast.makeText(getContext(), "choice: game home",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "choice: game away",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        // get currently signed in Team
        SharedPreferenceHAn sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        currentTeamName = sharedPreferenceHAn.getTeamId();

        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        gameTypeViewModel = new ViewModelProvider(this).get(GameTypeViewModel.class);

        List<String> opponentTeamsName = new ArrayList<>();
        opponentTeamsName.add("Select One");

        teamViewModel.getAllTeams().observe(getViewLifecycleOwner(), treams -> {
            for (Team team : treams) {
                if (!team.getName().equals(currentTeamName)) opponentTeamsName.add(team.getName());
            }
        });

        ArrayAdapter<String> a = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opponentTeamsName);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opponentTeamSpinner.setAdapter(a);

        List<String> gameTypes = new ArrayList<>();
        gameTypes.add("Select One");

        gameTypeViewModel.getAllGameTypes().observe(getViewLifecycleOwner(), gameTypes1 -> {
            for (GameType gameType : gameTypes1) {
                gameTypes.add(gameType.getGameType());
            }
        });

        ArrayAdapter<String> gameTypesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, gameTypes);
        gameTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gameTypeSpinner.setAdapter(gameTypesAdapter);




        return view;
    }
}
