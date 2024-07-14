package net.jemsit.DecoratorPattern;

import net.jemsit.Config;

import java.util.Map;

public abstract class WinAmount {
    public String[][] givenMatrix;
    public int betAMount;
    Config config;

    public abstract Map<String,Map<String,Double>> winAmount();
    public abstract void getDatas();


    public  double calculateWinningAmount(){
        double sum = 0;
        var mapIs = winAmount();
        for (Map.Entry<String, Map<String, Double>> outerEntry : mapIs.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, Double> innerMap = outerEntry.getValue();
            double tempSum = 1;
            for (Map.Entry<String, Double> innerEntry : innerMap.entrySet()) {
                tempSum  *= innerEntry.getValue();
            }
            sum += betAMount*tempSum;
        }
        return sum;
    }
}
