package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentRecent extends Fragment {

    public FragmentRecent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((HomeActivity) getActivity()).setTitle("Recent");
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }
}
