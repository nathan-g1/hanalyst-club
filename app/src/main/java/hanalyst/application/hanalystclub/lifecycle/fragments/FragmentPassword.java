package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import hanalyst.application.hanalystclub.R;

public class FragmentPassword extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_change, container, false);
        TextView oldPassword = view.findViewById(R.id.old_pin_layout);
        TextView newPassword = view.findViewById(R.id.new_pin);
        TextView confirmPassword = view.findViewById(R.id.confirm_pin);
        Button cancel = view.findViewById(R.id.cancel_pin_change);
        Button setNewPassword = view.findViewById(R.id.set_new_pin);

        setNewPassword.setOnClickListener(v -> {
//            changePassword();
        });
        return view;
    }

}
