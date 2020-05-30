package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.GameType;
import hanalyst.application.hanalystclub.repository.GameTypeRepository;

public class GameTypeViewModel extends AndroidViewModel {

    private GameTypeRepository gameTypeRepository;
    private LiveData<List<GameType>> allGameTypes;

    public GameTypeViewModel(@NonNull Application application) {
        super(application);
        gameTypeRepository = new GameTypeRepository(application);
        allGameTypes = gameTypeRepository.getAllGameTypes();
    }

    public void insertGameType(GameType gameType) {
        gameTypeRepository.insert(gameType);
    }

    public LiveData<List<GameType>> getAllGameTypes() {
        return allGameTypes;
    }

    public void deleteAllGameType() {
        gameTypeRepository.deleteAllGameTypes();
    }
}
