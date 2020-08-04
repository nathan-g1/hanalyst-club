package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.Entity.remote.ClubUser;
import hanalyst.application.hanalystclub.Network.RetrofitBuilder;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.FieldValidation;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.UserViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPassword extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_change, container, false);
        EditText oldPassword = view.findViewById(R.id.old_pin);
        EditText newPassword = view.findViewById(R.id.new_pin);
        EditText confirmPassword = view.findViewById(R.id.confirm_pin);
        Button cancel = view.findViewById(R.id.cancel_pin_change);
        Button setNewPassword = view.findViewById(R.id.set_new_pin);

        setNewPassword.setOnClickListener(v -> {
            changePassword(oldPassword, newPassword, confirmPassword);
        });

        cancel.setOnClickListener(v -> {
            goToSettings();
        });
        return view;
    }

    private void changePassword(EditText oldPassword, EditText newPassword, EditText confirmPassword) {
        UserViewModel userViewModel = new ViewModelProvider(getActivity()).get(UserViewModel.class);
        FieldValidation fv = new FieldValidation(getContext());
        if (fv.isEmpty(oldPassword)) fv.validateNotBlank(oldPassword);
        List<User> clubUsers = new ArrayList<>();
        userViewModel.getLoggedInUser().observe(getViewLifecycleOwner(), users -> {
            clubUsers.addAll(users);
            if (!fv.equalsWith(oldPassword, clubUsers.get(0).getPassword()))
                fv.validateWithCustomMessage(confirmPassword, getString(R.string.password_mismatch));
        });
        if (fv.isEmpty(confirmPassword)) fv.validateNotBlank(confirmPassword);
        if (fv.isEmpty(newPassword)) fv.validateNotBlank(newPassword);
        if (!fv.valueMatches(newPassword, confirmPassword))
            fv.validateWithCustomMessage(confirmPassword, getString(R.string.password_mismatch));
        if (!fv.validateNotBlank(newPassword))
            fv.validateWithCustomMessage(newPassword, getString(R.string.password_length_should_be_greater_than_6));
        RetrofitBuilder retrofitBuilder = new RetrofitBuilder();
        userViewModel.getLoggedInUser().observe(getActivity(), users1 -> {
            User user = users1.get(0);
            retrofitBuilder.getApi().changePassword(user.getName(), user.getBio(), user.getExperience(), user.getTeamId(), user.getId(), user.getEmail(), fv.fieldStringValue(newPassword))
                    .enqueue(new Callback<ClubUser>() {
                        @Override
                        public void onResponse(Call<ClubUser> call, Response<ClubUser> response) {
                            if (response.isSuccessful()) {
                                ClubUser user1 = response.body();
                                user.setPassword(user1.getPassword());
                                userViewModel.updateUser(user);
                                Toast.makeText(getContext(), getString(R.string.new_password_set), Toast.LENGTH_SHORT).show();
                                goToSettings();
                            }
                        }

                        @Override
                        public void onFailure(Call<ClubUser> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.problem_in_network, Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    private void goToSettings() {
        FragmentSettings fragmentSettings = new FragmentSettings();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment_switcher, fragmentSettings, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }

}
