package net.jemsit;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jemsit.DecoratorPattern.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Config config = objectMapper.readValue(new File("/home/jemsit/Desktop/testForDubai/GambleGameVersion2/src/config.json"), Config.class);
            MatrixGenerator matrixGenerator = new MatrixGenerator(config.getColumns(), config.getRows(), config.getProbabilities());
            matrixGenerator.displayMatrix();
            WinAmount amount = new WinningCombination(matrixGenerator.getGeneratedMatrix(), 200, config);
            amount = new CheckForNTimes(amount);
            amount = new CheckForStraightLines(amount);
            amount = new CheckForDiagonals(amount);
            amount = new CheckForBonus(amount);
            System.out.println(amount.winAmount());
            System.out.println(amount.calculateWinningAmount());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}