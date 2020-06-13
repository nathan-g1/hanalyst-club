package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.Login;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameTypeViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.PlayerViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.TeamViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.UserViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSettings extends Fragment {

    public FragmentSettings() {
        // Required empty public constructor
    }

    private UserViewModel userViewModel;
    private TeamViewModel teamViewModel;
    private NotationViewModel notationViewModel;
    private GameTypeViewModel gameTypeViewModel;
    private GameViewModel gameViewModel;
    private PlayerViewModel playerViewModel;
    private String teamId;
    private SharedPreferenceHAn sharedPreferenceHAn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Settings");
        sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        teamId = sharedPreferenceHAn.getTeamId();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        teamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        notationViewModel = new ViewModelProvider(this).get(NotationViewModel.class);
        gameTypeViewModel = new ViewModelProvider(this).get(GameTypeViewModel.class);
        playerViewModel = new ViewModelProvider(this).get(PlayerViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);

        final List<User> list = new ArrayList<>();
        final User[] ux = {null};
        final String[] d = {""};
        userViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    d[0] = user.getName();
                    System.out.println(user.getName() + " 234234 " + user.getEmail());
                    list.add(new User(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getBio(), user.getExperience(), user.getTeamId()));
                }
            }
        });
        final Team[] currentTeam = {null};
        teamViewModel.getAllTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                for (Team team : teams) {
                    if (team.getId().equals(teamId)) {
                        currentTeam[0] = team;
                        break;
                    }
                }
            }
        });

        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        TextView analystName = view.findViewById(R.id.analyst_name_settings);
        TextView clubName = view.findViewById(R.id.club_name_settings);
        TextView bio = view.findViewById(R.id.analyst_bio_settings);
//        analystName.setText(list.get(0).getName());
//        clubName.setText(currentTeam[0].getName());
//        bio.setText(list.get(0).getBio());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayout changePin = view.findViewById(R.id.pin_section);
        LinearLayout changeLanguage = view.findViewById(R.id.language_section);
        LinearLayout signOut = view.findViewById(R.id.logout_section);

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle(getString(R.string.logout));
                alertDialog.setMessage(getString(R.string.are_you_sure));
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                gameTypeViewModel.deleteAllGameType();
                                userViewModel.deleteAllUser();
                                teamViewModel.deleteAllTeams();
                                notationViewModel.deleteAllNotations();
                                playerViewModel.deleteAllPlayers();
                                gameViewModel.deleteAllGames();
                                sharedPreferenceHAn.signOut();
                                startActivity(new Intent(getActivity(), Login.class));
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, getString(R.string.no),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alertDialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        });

    }

    private void clearPreferences() {
        try {
            // clearing app data
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear " + getContext().getPackageName());
            startActivity(new Intent(getActivity(), Login.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
