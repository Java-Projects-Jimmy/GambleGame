package net.jemsit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.jemsit.Symbols.StandardSymbol;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Probability {
    @JsonProperty("standard_symbols")
    private List<StandardSymbol> standardSymbols;
    @JsonProperty("bonus_symbols")
    private Map<String, HashMap<String,Integer>> bonusSymbols;
}

