package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.repository.TeamRepository;

public class TeamViewModel extends AndroidViewModel {
    private TeamRepository repository;
    private LiveData<List<Team>> allTeams;

    public TeamViewModel(@NonNull Application application) {
        super(application);
        repository = new TeamRepository(application);
        allTeams = repository.getAllTeams();
    }

    public void updateTeam(Team team) {
        repository.update(team);
    }

    public void insertTeam(Team team) {
        repository.insert(team);
    }

    public Team getATeam(String teamId) {
        return repository.getTeam(teamId);
    }

    public LiveData<List<Team>> getAllTeams() {
        return allTeams;
    }
}
