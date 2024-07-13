package net.jemsit;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import net.jemsit.Symbols.SymbolDetails;

import java.util.Map;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {
    private int columns;
    private int rows;
    private Map<String, SymbolDetails> symbols;
    private Probability probabilities;

    public void setColumns(int columns) {
        this.columns = columns-1;
    }

    public void setRows(int rows) {
        this.rows = rows-1;
    }

    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;
}


