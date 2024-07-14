package net.jemsit.DecoratorPattern;

import net.jemsit.Config;

import java.util.HashMap;
import java.util.Map;

public  class WinningCombination extends WinAmount {

    public String[][] getGivenMatrix() {
        return givenMatrix;
    }

    public WinningCombination(String[][] givenMatrix, int betAmount, Config config) {
        this.givenMatrix = givenMatrix;
        this.betAMount = betAmount;
        this.config = config;
    }

    @Override
    public Map<String,Map<String,Double>> winAmount() {
        return new HashMap<>();
    }

    @Override
    public void getDatas() {
        System.out.println("getting datas of Winning Combination!");
    }

}
