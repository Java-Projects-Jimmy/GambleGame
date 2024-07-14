package net.jemsit.DecoratorPattern;

import net.jemsit.Config;

import java.util.Map;

public class CheckForDiagonals extends WinningCombination {
    WinAmount winAmount;

    public CheckForDiagonals( WinAmount winAmount) {
        super(winAmount.givenMatrix, winAmount.betAMount, winAmount.config);
        this.winAmount = winAmount;
    }

    @Override
    public Map<String, Map<String, Double>> winAmount() {
        return super.winAmount();
    }

    @Override
    public void getDatas() {
        super.getDatas();
    }
}
