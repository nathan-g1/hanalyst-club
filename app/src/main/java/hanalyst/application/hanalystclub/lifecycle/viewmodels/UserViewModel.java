package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import hanalyst.application.hanalystclub.Entity.User;
import hanalyst.application.hanalystclub.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepository;
    private LiveData<User> userLiveData;

    public UserViewModel(@NonNull Application application) {
        userRepository = new UserRepository(application);
        userLiveData = userRepository.getUser();
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void updateUser(User user) {
        userRepository.updateUser(user);
    }

    public User getLoggedInUser() {
        return userLiveData.getValue();
    }

    public void deleteUser(User user) {
        userRepository.deleteUser(user);
    }
}
