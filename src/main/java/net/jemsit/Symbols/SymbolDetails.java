package net.jemsit.Symbols;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SymbolDetails {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private int extra;
    private String type;
    private String impact;
}