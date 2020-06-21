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
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.R;

public class RecentGamesAdapter extends RecyclerView.Adapter<RecentGamesAdapter.GameHolder>  {

    private List<Game> games = new ArrayList<>();
    private static ClickListener clickListener;

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        return new GameHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        Game game = games.get(position);
        holder.gameType.setText(game.getGameType());
        holder.playingTeams.setText(game.getPlayingTeams()[0] + " vs " + game.getPlayingTeams()[1]);
    }


    @Override
    public int getItemCount() {
        return games.size();
    }

    public void setGames(List<Game> games) {
        this.games = games;
        notifyDataSetChanged();
    }

    public List<Game> getGivenGames() {
        return this.games;
    }

    class GameHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView playingTeams, liveScore, minutesPlayed, gameType, liveOrEnded;

        public GameHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            playingTeams = itemView.findViewById(R.id.playing_teams);
            liveScore = itemView.findViewById(R.id.live_score);
            minutesPlayed = itemView.findViewById(R.id.minutes_played);
            liveOrEnded = itemView.findViewById(R.id.live_or_ended);
            gameType = itemView.findViewById(R.id.game_type_display);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecentGamesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
    }
}
