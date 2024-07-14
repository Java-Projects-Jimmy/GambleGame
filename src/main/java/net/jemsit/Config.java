package net.jemsit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.jemsit.Symbols.SymbolDetails;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private int columns;
    private int rows;
    private Map<String, SymbolDetails> symbols;
    private Probability probabilities;

    private Map<Integer, Double> sameSymbolWinningCombination = new HashMap<>();
    private Map<String, Double> standardSymbols = new HashMap<>();
    private Map<String, Double> multipleRewardSymbols = new HashMap<>();
    private Map<String, Double> extraBonusSymbols = new HashMap<>();
    private Map<String, Double> linearSymbols = new HashMap<>();
    private Map<String, Double> diagonalSymbols = new HashMap<>();

    public void setColumns(int columns) {
        this.columns = columns-1;
    }

    public void setRows(int rows) {
        this.rows = rows-1;
    }

    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public void setWinCombinations(Map<String, WinCombination> winCombinations) {
        winCombinations.entrySet().forEach(el->{
            if (el.getValue().getGroup().equals("same_symbols")) {
                sameSymbolWinningCombination.put(el.getValue().getCount(),  el.getValue().getRewardMultiplier());
            }
            if (el.getValue().getGroup().equals("horizontally_linear_symbols") || el.getValue().getGroup().equals("vertically_linear_symbols")){
                linearSymbols.put(el.getValue().getGroup(), el.getValue().getRewardMultiplier());
            }
            if (el.getValue().getGroup().equals("ltr_diagonally_linear_symbols") || el.getValue().getGroup().equals("rtl_diagonally_linear_symbols")){
                diagonalSymbols.put(el.getValue().getGroup(), el.getValue().getRewardMultiplier());
            }
        });
        this.winCombinations = winCombinations;
    }

    public void setSymbols(Map<String, SymbolDetails> symbols) {
        symbols.forEach((key, value) -> {
            if (value.getType().equals("standard")) standardSymbols.put(key, value.getRewardMultiplier());
            if(value.getType().equals("bonus")){
                if (value.getImpact().equals("multiply_reward"))
                    multipleRewardSymbols.put(key, value.getRewardMultiplier());
                if (value.getImpact().equals("extra_bonus"))
                    extraBonusSymbols.put(key, (double) value.getExtra());
            }
        });
        this.symbols = symbols;
    }


}


