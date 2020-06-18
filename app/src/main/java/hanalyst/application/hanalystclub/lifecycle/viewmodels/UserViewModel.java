package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<List<User>> userLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userLiveData = userRepository.getUser();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public LiveData<List<User>> getLoggedInUser() {
        return userLiveData;
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }

    public void deleteAllUser() {
        userRepository.deleteAllUsers();
    }
}
