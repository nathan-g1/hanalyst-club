package hanalyst.application.hanalystclub.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AttackAdapter extends BaseAdapter {
    private Context context;

    public AttackAdapter(Context c) {
        context = c;
    }

    @Override
    public int getCount() {
        return attackNotationNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);

        return null;
    }

    public int[] attackNotationValues = new int[13];
    public String[] attackNotationNames = new String[13];

}
