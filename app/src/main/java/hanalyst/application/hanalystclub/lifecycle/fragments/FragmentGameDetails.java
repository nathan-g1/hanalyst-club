package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.Entity.remote.RGame;
import hanalyst.application.hanalystclub.Network.RetrofitBuilder;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.Util.TimeManager;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.controller.TabPageSwitcher;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGameDetails extends Fragment {

    private static String gameId;
    private NotationViewModel notationViewModel;
    private List<Notation> notationList;
    private TabLayout tabLayout;
    private TabPageSwitcher tabPageSwitcher;
    private ViewPager viewPager;

    public FragmentGameDetails() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ((HomeActivity) getActivity()).setTitle("Recent");
        View view = inflater.inflate(R.layout.fragment_game_details, container, false);
        // TODO: would be great to use binding here and else where than finding evey views
        TextView teamName = view.findViewById(R.id.team_name);
        TextView opponentTeamName = view.findViewById(R.id.opponent_team_name);
        TextView gameDate = view.findViewById(R.id.game_date);
        TextView gameTime = view.findViewById(R.id.game_start_time);
        TextView gameLocation = view.findViewById(R.id.game_venue);
        TimeManager tm = new TimeManager(getContext());
        SharedPreferenceHAn sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        retrofitBuilder.getApi().getAGame(sharedPreferenceHAn.getCurrentDetailsGameId())
                .enqueue(new Callback<RGame>() {
                    @Override
                    public void onResponse(Call<RGame> call, Response<RGame> response) {
                        if (response.isSuccessful()) {
                            RGame game = response.body();
                            teamName.setText(game.getPlayingTeams()[0]);
                            opponentTeamName.setText(game.getPlayingTeams()[1]);
                            gameDate.setText(tm.getDateFormatted(game.getStartTime()));
                            gameTime.setText(tm.getTimeFormatted(game.getStartTime()));
                            gameLocation.setText(game.getVenue());
                        }
                    }

                    @Override
                    public void onFailure(Call<RGame> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
                    }
                });

        tabPageSwitcher = new TabPageSwitcher(getChildFragmentManager());
        viewPager = view.findViewById(R.id.viewPager_container);
        setUpViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        notationViewModel = new ViewModelProvider(getActivity()).get(NotationViewModel.class);

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
        TabPageSwitcher tabPageSwitcher = new TabPageSwitcher(getChildFragmentManager());
        tabPageSwitcher.addFragments(new FragmentInfo(gameId), getString(R.string.info));
        tabPageSwitcher.addFragments(new FragmentLineup(gameId), getString(R.string.line_ups));
        tabPageSwitcher.addFragments(new FragmentComments(gameId), getString(R.string.comments));
        viewPager.setAdapter(tabPageSwitcher);
    }
}
