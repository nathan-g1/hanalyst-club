package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;


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
                FragmentGameForm nextFrag = new FragmentGameForm();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame_fragment_switcher, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

    }
}
