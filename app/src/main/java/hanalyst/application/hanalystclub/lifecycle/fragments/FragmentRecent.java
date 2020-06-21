package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.RecentGamesAdapter;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;

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

        RecentGamesAdapter recentGamesAdapter = new RecentGamesAdapter();
        gameViewModel.getAllGames().observe(getActivity(), recentGamesAdapter::setGames);
        gameList = recentGamesAdapter.getGivenGames();
        recentGamesAdapter.setOnItemClickListener((position, v) -> {

        });
        recyclerView.setAdapter(recentGamesAdapter);
        return view;
    }
}
