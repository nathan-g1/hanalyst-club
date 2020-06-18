package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.Player;
import hanalyst.application.hanalystclub.repository.PlayerRepository;

public class PlayerViewModel extends AndroidViewModel {
    private PlayerRepository playerRepository;
    private LiveData<List<Player>> allPlayers;

    public PlayerViewModel(@NonNull Application application) {
        super(application);
        playerRepository = new PlayerRepository(application);
        allPlayers = playerRepository.getAllPlayers();
    }

    public void insertPlayer(Player player) {
        playerRepository.insert(player);
    }

    public void updatePlayer(Player player) {
        playerRepository.updatePlayer(player);
    }

    public Player getAPlayer(String playerId) {
        return  playerRepository.getASinglePlayer(playerId).getValue();
    }

    public LiveData<List<Player>> getAllPlayers() {
        return allPlayers;
    }

    public void deletePlayer(String playerId) {
        playerRepository.deletePlayer(playerId);
    }

    public void deleteAllPlayers() {
        playerRepository.deleteAllPlayers();
    }
}
