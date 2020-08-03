package hanalyst.application.hanalystclub.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Entity.remote.RNotation;
import hanalyst.application.hanalystclub.R;

public class NotationsAdapter extends RecyclerView.Adapter<NotationsAdapter.NotationHolder> {
    private List<RNotation> notations = new ArrayList<>();


    @NonNull
    @Override
    public NotationsAdapter.NotationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notation_item, parent, false);
        return new NotationHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NotationsAdapter.NotationHolder holder, int position) {
        RNotation notation = notations.get(position);
        holder.minutesTextView.setText(notation.getWhen().split(":")[0] + "'");
        holder.commentsTextView.setText(notation.getWhat());
    }

    public void setNotations(List<RNotation> newNotations) {
        this.notations = newNotations;
    }

    @Override
    public int getItemCount() {
        return notations.size();
    }

    class NotationHolder extends RecyclerView.ViewHolder {
        private TextView minutesTextView;
        private TextView commentsTextView;

        public NotationHolder(@NonNull View itemView) {
            super(itemView);
            minutesTextView = itemView.findViewById(R.id.notation_minutes);
            commentsTextView = itemView.findViewById(R.id.notation_name);
        }
    }
}
