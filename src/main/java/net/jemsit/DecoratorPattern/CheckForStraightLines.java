package net.jemsit.DecoratorPattern;

import java.util.HashMap;
import java.util.Map;

public class CheckForStraightLines extends WinningCombination {
    WinAmount winAmount;
    public Map<String, Double> straightLineSameSymbols;

    public CheckForStraightLines(WinAmount winAmount) {
        super(winAmount.givenMatrix, winAmount.betAMount, winAmount.config);
        this.winAmount = winAmount;
        getDatas();
    }


    @Override
    public void getDatas() {
        straightLineSameSymbols = new HashMap<>();
        String[][] givenMatrix = winAmount.givenMatrix;
        // check rows for same symbol
        for (int i = 0; i < givenMatrix.length; i++) {
            for (int j = 0; j < givenMatrix[i].length; j++) {
                if (!givenMatrix[i][0].equals(givenMatrix[i][j])) break;
                if (j == givenMatrix[i].length - 1) {
                    straightLineSameSymbols.put(
                            givenMatrix[i][0],
                            straightLineSameSymbols.getOrDefault(givenMatrix[i][0], 0.0) + 1);
                    System.out.println("rows");
                }
            }
        }
        // check columns for same symbol
        int i = 0, j = 0;
        while (i < givenMatrix.length) {
            if(givenMatrix[0][j]
                    .equals(givenMatrix[i][j])) {
                if (i == givenMatrix.length-1) {
                    straightLineSameSymbols.put(
                            givenMatrix[0][j],
                            straightLineSameSymbols.getOrDefault(givenMatrix[0][j], 0.0) + 1);
                    System.out.println("columns");
                    j++;
                    i = 0;
                    if (j > givenMatrix[i].length-1) break;
                } else i++;
            }else {
                j++;
                i = 0;
                if (j > givenMatrix[i].length-1) break;
            }
        }
    }

    @Override
    public Map<String, Map<String, Double>> winAmount() {
        Map<String, Map<String, Double>> updated = winAmount.winAmount();
        straightLineSameSymbols.forEach((key,value)->{
            var newValue = updated.getOrDefault(
                    key,
                    new HashMap<>(Map.of("same_symbols_straight_line", 0.0)
                    ));
            newValue.put("same_symbols_straight_line", value*2);
            updated.put(
                    key,
                    newValue
            );
        });
        return updated;
    }

}
