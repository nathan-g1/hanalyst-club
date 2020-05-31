package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.Game;
import hanalyst.application.hanalystclub.repository.GameRepository;

public class GameViewModel extends AndroidViewModel {
    private GameRepository gameRepository;
    private LiveData<List<Game>> allGames;

    public GameViewModel(@NonNull Application application) {
        super(application);
        gameRepository = new GameRepository(application);
        allGames = gameRepository.getAllGames();
    }

    public void insertGame(Game game) {
        gameRepository.insert(game);
    }

    public void updateGame(Game game) {
        gameRepository.update(game);
    }

    public LiveData<List<Game>> getAllGames() {
        return allGames;
    }

    public void deleteAllGames() {
        gameRepository.deleteAllGames();
    }
}
