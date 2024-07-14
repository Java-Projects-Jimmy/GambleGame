package net.jemsit;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jemsit.DecoratorPattern.CheckForNTimes;
import net.jemsit.DecoratorPattern.CheckForStraightLines;
import net.jemsit.DecoratorPattern.WinAmount;
import net.jemsit.DecoratorPattern.WinningCombination;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Config config = objectMapper.readValue(new File("/home/jemsit/Desktop/testForDubai/GambleGameVersion2/src/config.json"), Config.class);
            System.out.println(config.getSameSymbolWinningCombination());
            System.out.println(config.getStandardSymbols());
            System.out.println(config.getMultipleRewardSymbols());
            System.out.println(config.getExtraBonusSymbols());
            MatrixGenerator matrixGenerator = new MatrixGenerator(config.getColumns(), config.getRows(), config.getProbabilities());
            matrixGenerator.displayMatrix();
            WinAmount amount = new WinningCombination(matrixGenerator.getGeneratedMatrix(), 200, config);
            amount = new CheckForNTimes(amount);
            amount = new CheckForStraightLines(amount);
            System.out.println(amount.winAmount());
            System.out.println(amount.calculateWinningAmount());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}