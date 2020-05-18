package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.Analysis;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.repository.PlayerRepository;

public class FragmentHome extends Fragment {

    public FragmentHome() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((HomeActivity) getActivity()).setTitle("Home");
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button startGameButton = view.findViewById(R.id.game_start_button);
        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Call a game form here before going to analysis
                final Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.custom_game_form);
                dialog.show();

//                PlayerRepository playerRepository = new PlayerRepository(getActivity());
//                final ArrayList<String> arrayList = new ArrayList<>();
//                playerRepository.getPlayers().observe(getActivity(), new Observer<List<Player>>() {
//                    @Override
//                    public void onChanged(@Nullable List<Player> players) {
//                        for (Player player: players) {
//                            arrayList.add(player.getName());
//                        }
//                    }
//                });
                ImageButton saveImgBtn = dialog.findViewById(R.id.button_save_game_form);
                saveImgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: Do validation here if:
                        // TODO: right number of players are selected, Analyst is not missing
                        startActivity(new Intent(getActivity(), Analysis.class));
                    }
                });
                ListView playersList = dialog.findViewById(R.id.list_of_players);
                Spinner opponentTeam = dialog.findViewById(R.id.spinner);
//                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
//                        android.R.layout.simple_spinner_item, arrayList);
//                opponentTeam.setAdapter(dataAdapter);
                EditText analystName = dialog.findViewById(R.id.analyst_name);

                ImageButton cancelImgBtn = dialog.findViewById(R.id.button_cancel);
                cancelImgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });
    }

}
