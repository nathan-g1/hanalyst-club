package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;
import hanalyst.application.hanalystclub.lifecycle.controller.TabPageSwitcher;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;

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
