package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;

public class FragmentGameForm extends Fragment {
    public FragmentGameForm() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Start Game");
        return inflater.inflate(R.layout.fragment_game_form, container, false);
    }
}
