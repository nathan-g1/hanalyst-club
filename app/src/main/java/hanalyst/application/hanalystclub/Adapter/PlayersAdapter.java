package hanalyst.application.hanalystclub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.R;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.PlayerHolder> {
    private List<Player> players = new ArrayList<>();

    @NonNull
    @Override
    public PlayerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_item, parent, false);
        return new PlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerHolder holder, int position) {
        Player player = players.get(position);
        holder.playerName.setText(player.getName());
        holder.tNumber.setText(String.valueOf(player.getTNumber()));
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public Player getSelectedPlayer(int position) {
        return this.players.get(position);
    }

    class PlayerHolder extends RecyclerView.ViewHolder {
        private TextView playerName;
        private TextView tNumber;
//        private ImageView playerIcon;

        public PlayerHolder(@NonNull View itemView) {
            super(itemView);
            playerName = itemView.findViewById(R.id.text_view_player_name);
            tNumber = itemView.findViewById(R.id.text_view_t_number);
        }
    }
}
