package hanalyst.application.hanalystclub.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import hanalyst.application.hanalystclub.Model.Defense;
import hanalyst.application.hanalystclub.R;

public class DefenseAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Defense> arrayList;


    public DefenseAdapter(Context c, ArrayList<Defense> arrayList) {
        context = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
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
        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.row_item, null);
        TextView value = convertView.findViewById(R.id.value);
        TextView description = convertView.findViewById(R.id.description);

        value.setText(String.valueOf(arrayList.get(position).getValue()));
        description.setText(arrayList.get(position).getDesc());
        return convertView;
    }
}
