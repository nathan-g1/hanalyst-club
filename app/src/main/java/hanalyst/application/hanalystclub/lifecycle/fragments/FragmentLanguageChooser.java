package hanalyst.application.hanalystclub.lifecycle.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

import hanalyst.application.hanalystclub.Adapter.LanguageOptionAdapter;
import hanalyst.application.hanalystclub.Entity.Language;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;
import hanalyst.application.hanalystclub.lifecycle.HomeActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLanguageChooser extends Fragment {
    String shortCode;
    private static final String TAG = "FragmentLanguageChooser";
    public FragmentLanguageChooser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferenceHAn hAn = new SharedPreferenceHAn(getContext());
        View view = inflater.inflate(R.layout.fragment_language_chooser, container, false);
        Button setNewLanguage = view.findViewById(R.id.set_new_language);
        Button cancelLanguage = view.findViewById(R.id.cancel_language_change);

        ListView listView = view.findViewById(R.id.list_view_for_languages);
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < Language.values().length; i++) {
            arrayList.add(Language.values()[i].toString());
        }
        LanguageOptionAdapter languageOptionAdapter = new LanguageOptionAdapter(getContext(), arrayList, hAn.getCurrentLanguage());
        for (Language language : Language.values()) {
            if (language.toString().equals(hAn.getCurrentLanguage())) shortCode = language.getLanguageCode();
        }

        setNewLanguage.setOnClickListener(v -> setANewLanguage(shortCode));
        cancelLanguage.setOnClickListener(v -> goToSettings());
        listView.setAdapter(languageOptionAdapter);
        return view;
    }

    private void setANewLanguage(String languageShortCode) {
        Log.d(TAG, "setANewLanguage: " + languageShortCode);
        Locale locale = new Locale(languageShortCode);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        startActivity(new Intent(getContext(), HomeActivity.class));
    }

    private void goToSettings() {
        FragmentSettings fragmentSettings = new FragmentSettings();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_fragment_switcher, fragmentSettings, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }
}
