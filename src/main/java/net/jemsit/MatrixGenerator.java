package net.jemsit;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class MatrixGenerator {
    private int columns;
    private int rows;
    private String[][] generatedMatrix;
    private Probability probabilities;
    private boolean randomSymbolIsNotGiven = true;

    public MatrixGenerator(int columns, int rows, Probability probabilities) {
        this.columns = columns;
        this.rows = rows;
        this.probabilities = probabilities;
        generatedMatrix = new String[columns][rows];
        generateRandomMatrix();
    }

    public MatrixGenerator() {
    }

    public void generateRandomMatrix() {
        for (int i = 0; i <columns ; i++) {
            for (int j = 0; j < rows; j++) {
                generatedMatrix[i][j] = getRandomSymbolForCell(i,j);
            }
        }
    }

    public void displayMatrix() {
        System.out.println("---------------");
        for (int i = 0; i < columns; i++) {
            System.out.print("|");
            for (int j = 0; j < rows; j++) {
                System.out.print(" " + generatedMatrix[i][j] + " | ");
            }
            System.out.println("\n---------------");
        }
    }

    public String getRandomStandardSymbolForCell(int col, int row) {
        String result = "";
        var data = probabilities
                .getStandardSymbols()
                .stream()
                .filter(probability -> probability.getColumn() == col && probability.getRow() == row)
                .findFirst();
        if (data.isEmpty()) throw new RuntimeException("Exception in Matrix Generator class, no Symbol like this"+col + row);
        int sumOfProbabilities = findSumOfProbabilities(data.get().getSymbols());
        int random = 0;
        while (random == 0) {
            random = (int) (Math.random() * sumOfProbabilities);
        }
        var probabilities = createMapOfProbabilities(data.get().getSymbols(), sumOfProbabilities);
        for (Map.Entry<String, List<Integer>> currentState : probabilities.entrySet()) {
            if (currentState.getValue().contains(random)) {
                result = currentState.getKey();
                break;
            }
        }
        return result;
    }

    public String getRandomBonusSymbolForCell() {
        var data = probabilities
                .getBonusSymbols()
                .get("symbols");
        int sum = findSumOfProbabilities(data);
        int random = 0;
        while (random == 0) {
            random = (int) (Math.random() * sum);
        }
        var probabilities = createMapOfProbabilities(data, sum);
        String result = "";
        for (Map.Entry<String, List<Integer>> currentState : probabilities.entrySet()) {
            if (currentState.getValue().contains(random)) {
                result = currentState.getKey();
                break;
            }
        }
        return result;
    }
    public String getRandomSymbolForCell(int col, int row) {
        int random = (int) (Math.random() * 10);
        if (random == 1 && randomSymbolIsNotGiven) {
            randomSymbolIsNotGiven = false;
            return getRandomBonusSymbolForCell();
        }
        return getRandomStandardSymbolForCell(col,row);
    }

    public int findSumOfProbabilities(Map<String,Integer> symbols) {
        int sum = 0;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            sum += entry.getValue();
        }
        return sum;
    }

    public HashMap<String, List<Integer>> createMapOfProbabilities(Map<String, Integer> symbols, int sum) {
        HashMap<String, List<Integer>> probabilities = new HashMap<>();
        int value = 1;
        for (Map.Entry<String, Integer> entry : symbols.entrySet()) {
            List<Integer> nums = new ArrayList<>();
            int counter = 1;
            while (counter <= entry.getValue() && value <= sum) {
                nums.add(value);
                value++;
                counter++;
            }
            probabilities.put(entry.getKey(), nums);
        }
        return probabilities;
    }
}
