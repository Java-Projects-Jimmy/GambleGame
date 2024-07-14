package net.jemsit.DecoratorPattern;

import java.util.HashMap;
import java.util.Map;

public class CheckForNTimes extends WinningCombination {
    WinAmount winAmount;
    public Map<String, Integer> symbolsCount;

    public CheckForNTimes(WinAmount winAmount) {
        super(winAmount.givenMatrix, winAmount.betAMount, winAmount.config);
        this.winAmount = winAmount;
        getDatas();
    }
    @Override
    public void getDatas() {
        symbolsCount = new HashMap<>();
        String[][] givenMatrix = winAmount.givenMatrix;
        for (int i = 0; i < givenMatrix.length; i++) {
            for (int j = 0; j < givenMatrix[i].length; j++) {
                symbolsCount.put(givenMatrix[i][j], symbolsCount.getOrDefault(givenMatrix[i][j], 0)+1);
            }
        }
    }

    @Override
    public Map<String, Map<String, Double>> winAmount() {
        Map<String, Map<String, Double>> updated = winAmount.winAmount();
        symbolsCount.forEach((key, value)->{
            if (key.length()==1 && value >= 3) {
                var newValue = updated.getOrDefault(
                        key,
                        new HashMap<>(Map.of("symbol_" + key, config.getStandardSymbols().get(key))
                        ));
                newValue.put("same_symbol_"+value+"_times", config.getSameSymbolWinningCombination().get(value));
                updated.put(
                        key,
                        newValue
                );
            }
        });
        return updated;
    }
}
