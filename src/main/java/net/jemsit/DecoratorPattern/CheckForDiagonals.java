package net.jemsit.DecoratorPattern;

import java.util.HashMap;
import java.util.Map;

public class CheckForDiagonals extends WinningCombination {
    WinAmount winAmount;
    public Map<String, Double> diagonals;


    public CheckForDiagonals( WinAmount winAmount) {
        super(winAmount.givenMatrix, winAmount.betAMount, winAmount.config);
        this.winAmount = winAmount;
        getDatas();
    }

    @Override
    public void getDatas() {
        // I assume that matrix is equal sized I mean nxn or mxm or kxk (square)
        diagonals = new HashMap<>();
        String[][] givenMatrix = winAmount.givenMatrix;
        int i = 0, j = 0;
        //main diagonal
        while (i < givenMatrix.length) {
            if (!givenMatrix[0][0].equals( givenMatrix[i][j])) break;
            if (i == givenMatrix.length - 1) {
                diagonals.put(givenMatrix[0][0], diagonals.getOrDefault(givenMatrix[0][0], 0.0) + 1);
            }
            i++;
            j++;
        }
        i = givenMatrix.length - 1;
        j = 0;
        //secondary diagonal
        while (i >= 0) {
            if (!givenMatrix[givenMatrix.length - 1][0].equals( givenMatrix[i][j])) break;
            if (i == 0) {
                diagonals.put(givenMatrix[givenMatrix.length - 1][0], diagonals.getOrDefault(givenMatrix[givenMatrix.length - 1][0], 0.0) + 1);
            }
            i--;
            j++;
        }
    }

    @Override
    public Map<String, Map<String, Double>> winAmount() {
        Map<String, Map<String, Double>> updated = winAmount.winAmount();
        diagonals.forEach((key,value)->{
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
