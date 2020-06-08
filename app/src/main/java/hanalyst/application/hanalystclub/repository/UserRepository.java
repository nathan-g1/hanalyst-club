package hanalyst.application.hanalystclub.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Database.HAnalystDb;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.dao.UserDao;

public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> userLiveData;

    public UserRepository(Application application) {
        HAnalystDb hAnalystDb = HAnalystDb.getInstance(application);
        userDao = hAnalystDb.userDao();
        userLiveData = userDao.getCurrentUser();
    }

    public void insertUser(User user) {
        new InsertUserAsync(userDao).execute(user);
    }

    public LiveData<List<User>> getUser() {
        return userLiveData;
    }

    public void deleteUser(User user) {
        new DeleteUserAsync(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UpdateUserAsync(userDao).execute(user);
    }

    private class InsertUserAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public InsertUserAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
            return null;
        }
    }

    private class UpdateUserAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public UpdateUserAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.updatePlayer(users[0]);
            return null;
        }
    }

    private class DeleteUserAsync extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        public DeleteUserAsync(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users[0]);
            return null;
        }
    }
}
