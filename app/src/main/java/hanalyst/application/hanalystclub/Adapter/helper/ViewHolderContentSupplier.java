package hanalyst.application.hanalystclub.Adapter.helper;

import android.content.Context;

import hanalyst.application.hanalystclub.lifecycle.viewmodels.GameViewModel;

public class ViewHolderContentSupplier {
    private GameViewModel gameViewModel;
    private Context context;

    public ViewHolderContentSupplier(GameViewModel gameViewModel, Context context) {
        this.gameViewModel = gameViewModel;
        this.context = context;
    }

    public String getScores() {

    }

}
