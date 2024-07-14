package net.jemsit.DecoratorPattern;

import java.util.Map;

public class CheckForBonus extends WinningCombination {
    WinAmount winAmount;

    public CheckForBonus(WinAmount winAmount) {
        super(winAmount.givenMatrix, winAmount.betAMount, winAmount.config);
        this.winAmount = winAmount;
    }


    @Override
    public Map<String, Map<String, Double>> winAmount() {
        Map<String, Map<String, Double>> updated = winAmount.winAmount();
        String[][] givenMatrix = winAmount.givenMatrix;
        for (int i = 0; i < givenMatrix.length; i++) {
            for (int j = 0; j < givenMatrix[i].length; j++) {
                var bonus = givenMatrix[i][j];
                if (bonus == "MISS") updated.put(bonus, Map.of("miss", 0.0));
                if (config.getMultipleRewardSymbols().containsKey(bonus))
                    updated.put(bonus, Map.of("multiply_reward", config.getMultipleRewardSymbols().get(bonus)));
                if (config.getExtraBonusSymbols().containsKey(bonus))
                    updated.put(bonus, Map.of("extra_bonus", config.getExtraBonusSymbols().get(bonus)));
            }
        }
        return updated;
    }
}


