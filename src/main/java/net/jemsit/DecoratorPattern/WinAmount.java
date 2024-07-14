package net.jemsit.DecoratorPattern;

import net.jemsit.Config;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

public abstract class WinAmount {
    public String[][] givenMatrix;
    public int betAMount;
    Config config;
    private boolean win = false;

    public abstract Map<String,Map<String,Double>> winAmount();
    public abstract void getDatas();


    public BigDecimal calculateWinningAmount(){
        BigDecimal sum = BigDecimal.ZERO;
        var mapIs = winAmount();
        for (var outerEntry : mapIs.entrySet()) {
            String outerKey = outerEntry.getKey();
            var innerMap = outerEntry.getValue();
            BigDecimal tempSum = BigDecimal.ONE;
            if (outerKey.length() == 1){
                win = true;
                for (var innerEntry : innerMap.entrySet()) {
                    tempSum = tempSum.multiply(BigDecimal.valueOf(innerEntry.getValue()));
                }
                sum = sum.add(tempSum.multiply(BigDecimal.valueOf(betAMount)));
            }
        }
        if (!win) return BigDecimal.ZERO;

        for (var outerEntry : mapIs.entrySet()) {
            String outerKey = outerEntry.getKey();
            var innerMap = outerEntry.getValue();
            BigDecimal tempSum = BigDecimal.ONE;
            if (!outerKey.equals("MISS") && outerKey.length() >1){
                if (innerMap.containsKey("multiply_reward")){
                    sum = sum.multiply(BigDecimal.valueOf(innerMap.get("multiply_reward")));
                }
                if (innerMap.containsKey("extra_bonus")){
                    sum = sum.add(BigDecimal.valueOf(innerMap.get("extra_bonus")));
                }
            }
        }
        return sum.setScale(2, RoundingMode.HALF_UP);
    }
}
