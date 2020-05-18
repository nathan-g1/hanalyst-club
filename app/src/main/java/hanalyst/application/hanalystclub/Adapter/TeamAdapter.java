package hanalyst.application.hanalystclub.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.R;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamHolder> {

    private List<Team> teams = new ArrayList<>();

    @NonNull
    @Override
    public TeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_item, parent, false);
        return new TeamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamHolder holder, int position) {
        Team team = teams.get(position);
        holder.teamName.setText(team.getName());
        holder.coachName.setText(team.getCoach());
        holder.captainName.setText(team.getCaptain());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
        notifyDataSetChanged();
    }

    class TeamHolder extends RecyclerView.ViewHolder{
        private TextView teamName;
        private TextView coachName;
        private TextView captainName;

        public TeamHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.text_view_team_name);
            coachName = itemView.findViewById(R.id.text_view_coach_name);
            captainName = itemView.findViewById(R.id.text_view_captain_name);
        }
    }
}
