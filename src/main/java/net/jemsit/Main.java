package net.jemsit;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.jemsit.DecoratorPattern.*;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        String configFileAddress = "config.json";
        int bettingAmount = 100;
        try {
            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "--config":
                    if (i + 1 < args.length) {
                        configFileAddress = args[i + 1];
                        i++;
                    } else {
                        System.err.println("Error: --config requires a file path");
                        return;
                    }
                    break;
                    case "--betting-amount":
                        if (i + 1 < args.length) {
                            try {
                                bettingAmount = Integer.parseInt(args[i + 1]);
                                i++;
                            } catch (NumberFormatException e) {
                                System.err.println("Error: --betting-amount requires an integer value");
                                return;
                            }
                        } else {
                            System.err.println("Error: --betting-amount requires a value");
                            return;
                        }
                        break;
                    default:
                        System.err.println("Unknown argument: " + args[i]);
                        return;
                }
            }
            Config config = objectMapper.readValue(new File(configFileAddress), Config.class);
            MatrixGenerator matrixGenerator = new MatrixGenerator(config.getColumns(), config.getRows(), config.getProbabilities());
            matrixGenerator.displayMatrix();
            WinAmount amount = new WinningCombination(matrixGenerator.getGeneratedMatrix(), bettingAmount, config);
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