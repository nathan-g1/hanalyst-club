package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.RecentGamesAdapter;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.Entity.remote.RGame;
import hanalyst.application.hanalystclub.Network.RetrofitBuilder;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.Analysis;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecent extends Fragment {

    public FragmentRecent() {
        // Required empty public constructor
    }
    private GameViewModel gameViewModel;
    private List<Game> gameList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Recent");
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);

        RecyclerView recyclerView  = view.findViewById(R.id.recent_games_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecentGamesAdapter recentGamesAdapter = new RecentGamesAdapter(gameViewModel, getContext());
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        retrofitBuilder.getApi().getGames().enqueue(new Callback<List<RGame>>() {
            @Override
            public void onResponse(Call<List<RGame>> call, Response<List<RGame>> response) {
                if (response.isSuccessful()) {
                    List<Game> gameList1 = new ArrayList<>();
                    List<RGame> rGames = response.body();
                    for (RGame rGame : rGames) {
                        gameList1.add(new Game(rGame.getId(),
                                rGame.getStartTime(),
                                rGame.getEndTime(),
                                rGame.getVenue(),
                                rGame.isHa(),
                                rGame.getReferee(),
                                rGame.getTemperature(),
                                rGame.getGameType(),
                                rGame.getPlayingTeams()));
                    }
                    recentGamesAdapter.setGames(gameList1);
                }
            }

            @Override
            public void onFailure(Call<List<RGame>> call, Throwable t) {
                Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
            }
        });
//        gameViewModel.getAllGames().observe(getActivity(), recentGamesAdapter::setGames);
        gameList = recentGamesAdapter.getGivenGames();
        recentGamesAdapter.setOnItemClickListener((position, v) -> {
            FragmentGameDetails fragmentGameDetails = new FragmentGameDetails();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_fragment_switcher, fragmentGameDetails, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        });
        recyclerView.setAdapter(recentGamesAdapter);
        return view;
    }
}
