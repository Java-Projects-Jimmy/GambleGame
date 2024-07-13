package net.jemsit;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Config config = objectMapper.readValue(new File("/home/jemsit/Desktop/testForDubai/GambleGameVersion2/src/config.json"), Config.class);
//            System.out.println(config.getColumns());
//            System.out.println(config.getRows());
//            System.out.println(config.getSymbols());
//            System.out.println(config.getProbabilities());
//            System.out.println(config.getWinCombinations());
            MatrixGenerator matrixGenerator = new MatrixGenerator(config.getColumns(), config.getRows(), config.getProbabilities());
            matrixGenerator.displayMatrix();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}