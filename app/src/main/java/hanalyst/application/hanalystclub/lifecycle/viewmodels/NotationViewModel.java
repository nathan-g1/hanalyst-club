package hanalyst.application.hanalystclub.lifecycle.viewmodels;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import hanalyst.application.hanalystclub.Entity.Notation;
import hanalyst.application.hanalystclub.repository.NotationRepository;

public class NotationViewModel extends AndroidViewModel {
    private NotationRepository notationRepository;
    private LiveData<List<Notation>> allNotations;

    public NotationViewModel(@NonNull Application application) {
        super(application);
        notationRepository = new NotationRepository(application);
        allNotations = notationRepository.getAllNotations();
    }

    public void insertNotation(Notation notation) {
        notationRepository.insert(notation);
    }

    public LiveData<List<Notation>> getAllNotations() {
        return allNotations;
    }
}
