package net.jemsit.DecoratorPattern;

import net.jemsit.Config;

import java.util.Map;

public abstract class WinAmount {
    public String[][] givenMatrix;
    public int betAMount;
    Config config;
    private boolean win = false;

    public abstract Map<String,Map<String,Double>> winAmount();
    public abstract void getDatas();


    public  double calculateWinningAmount(){
        double sum = 0;
        var mapIs = winAmount();
        for (var outerEntry : mapIs.entrySet()) {
            String outerKey = outerEntry.getKey();
            var innerMap = outerEntry.getValue();
            double tempSum = 1;
            if (outerKey.length() == 1){
                win = true;
                for (var innerEntry : innerMap.entrySet()) {
                    tempSum  *= innerEntry.getValue();
                }
                sum += betAMount*tempSum;
            }
        }
        if (!win) return 0;

        for (var outerEntry : mapIs.entrySet()) {
            String outerKey = outerEntry.getKey();
            var innerMap = outerEntry.getValue();
            double tempSum = 1;
            if (!outerKey.equals("MISS") && outerKey.length() >1){
                if (innerMap.containsKey("multiply_reward")){
                    sum *= innerMap.get("multiply_reward");
                }
                if (innerMap.containsKey("extra_bonus")){
                    sum += innerMap.get("extra_bonus");
                }
            }
        }
        return sum;
    }
}
