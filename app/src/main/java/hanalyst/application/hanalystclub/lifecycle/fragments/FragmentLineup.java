package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;

public class FragmentLineup extends Fragment {

    private String gameId;

    public FragmentLineup(String gameId) {
        this.gameId = gameId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_details_lineup, container, false);
        return view;
    }

}
