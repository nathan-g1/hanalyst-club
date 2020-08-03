package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.NotationsAdapter;
import hanalyst.application.hanalystclub.Entity.remote.RNotation;
import hanalyst.application.hanalystclub.Network.RetrofitBuilder;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.NotationViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentComments extends Fragment {

    private String teamId;

    public FragmentComments(String teamId) {
        this.teamId = teamId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_details_comments, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.comments_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        NotationsAdapter notationsAdapter = new NotationsAdapter();

        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        SharedPreferenceHAn sharedPreferenceHAn = new SharedPreferenceHAn(getContext());
        retrofitBuilder.getApi().getNotations(sharedPreferenceHAn.getCurrentDetailsGameId())
                .enqueue(new Callback<List<RNotation>>() {
                    @Override
                    public void onResponse(Call<List<RNotation>> call, Response<List<RNotation>> response) {
                        if (response.isSuccessful()) {
                            notationsAdapter.setNotations(response.body());
                            recyclerView.setAdapter(notationsAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<RNotation>> call, Throwable t) {
                        Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
                    }
                });

        return view;
    }
}
