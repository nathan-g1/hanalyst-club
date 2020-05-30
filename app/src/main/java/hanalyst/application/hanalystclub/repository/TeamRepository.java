package hanalyst.application.hanalystclub.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.Team;
import hanalyst.application.hanalystclub.dao.TeamDao;

public class TeamRepository {
    private TeamDao teamDao;
    private LiveData<List<Team>> allTeams;

    public TeamRepository(Application application) {
        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
        teamDao = hAnalystDb.teamDao();
        allTeams = teamDao.getAllTeams();
    }

    public void insert(Team team) {
        new InsertTeamAsyncTask(teamDao).execute(team);
    }

    public LiveData<Team> getTeam(String teamId) {
        return teamDao.getATeam(teamId);
    }

    public LiveData<List<Team>> getAllTeams() {
        return allTeams;
    }

    public void update(Team team) {
        new UpdateTeamAsyncTask(teamDao).execute(team);
    }

    public void deleteAllTeams() {
        new DeleteAllTeamAsyncTask(teamDao).execute();
    }

    private class InsertTeamAsyncTask extends AsyncTask<Team, Void, Void> {

        private TeamDao teamDao;

        public InsertTeamAsyncTask(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        @Override
        protected Void doInBackground(Team... teams) {
            teamDao.insertTeam(teams[0]);
            return null;
        }
    }

    private class UpdateTeamAsyncTask extends AsyncTask<Team, Void, Void>{

        private TeamDao teamDao;

        public UpdateTeamAsyncTask(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        @Override
        protected Void doInBackground(Team... teams) {
            teamDao.updateTeam(teams[0]);
            return null;
        }
    }

    private class DeleteAllTeamAsyncTask extends AsyncTask<Void, Void, Void> {
        private TeamDao teamDao;

        public DeleteAllTeamAsyncTask(TeamDao teamDao) {
            this.teamDao = teamDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            teamDao.deleteAllTeams();
            return null;
        }
    }
}
