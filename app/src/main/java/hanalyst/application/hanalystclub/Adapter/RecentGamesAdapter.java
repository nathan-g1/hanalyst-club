package hanalyst.application.hanalystclub.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Adapter.helper.ViewHolderContentSupplier;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.R;
import hanalyst.application.hanalystclub.Util.TimeManager;
import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;

public class RecentGamesAdapter extends RecyclerView.Adapter<RecentGamesAdapter.GameHolder>  {

    private List<Game> games = new ArrayList<>();
    private Context context;
    private static ClickListener clickListener;
    private GameViewModel gameViewModel;

    public RecentGamesAdapter(GameViewModel gameViewModel, Context context) {
        this.gameViewModel = gameViewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public GameHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        return new GameHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GameHolder holder, int position) {
        TimeManager timeManager = new TimeManager(context);
        ViewHolderContentSupplier vcs = new ViewHolderContentSupplier(gameViewModel, context);
        Game game = games.get(position);
        holder.gameType.setText(game.getGameType());
        holder.playingTeams.setText(game.getPlayingTeams()[0] + " vs " + game.getPlayingTeams()[1]);
        holder.minutesPlayed.setText(timeManager.getMinutesPlayed(game.getStartTime()) + "'");
        holder.liveOrEnded.setText(timeManager.isGameInProgress(game.getEndTime()) ? "In Progress" : "Played");
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
            minutesPlayed = itemView.findViewById(R.id.minutes_played);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v, games.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecentGamesAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v, Game game);
    }
}
