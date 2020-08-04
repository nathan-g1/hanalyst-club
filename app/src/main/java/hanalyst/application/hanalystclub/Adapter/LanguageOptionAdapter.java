package hanalyst.application.hanalystclub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;

import java.util.ArrayList;

import androidx.constraintlayout.widget.ConstraintLayout;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.SharedPreferenceHAn;

public class LanguageOptionAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> languages;
    private String selectedLanguage;
    private int selectedPos;

    public LanguageOptionAdapter(Context context, ArrayList<String> languages, String selectedLanguage) {
        this.context = context;
        this.languages = languages;
        this.selectedLanguage = selectedLanguage;
        selectedPos = languages.indexOf(selectedLanguage);
    }

    public String getSelectedLanguage() {
        return selectedLanguage;
    }

    @Override
    public int getCount() {
        return languages.size();
    }

    @Override
    public Object getItem(int position) {
        return languages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.language_item, parent, false);
        }
        ConstraintLayout constraintLayout = view.findViewById(R.id.list_item_parent);
        CheckedTextView radioButton = view.findViewById(R.id.radio_button_item);
        radioButton.setText(languages.get(position));
        radioButton.setChecked(position == selectedPos);
        radioButton.setTag(position);
        constraintLayout.setOnClickListener(v -> {
            selectedPos = ((int) v.findViewById(R.id.radio_button_item).getTag());
            SharedPreferenceHAn hAn = new SharedPreferenceHAn(context);
            hAn.setCurrentLanguage(languages.get(selectedPos));
            notifyDataSetChanged();
        });
        return view;
    }
}
