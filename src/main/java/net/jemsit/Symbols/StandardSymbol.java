package net.jemsit.Symbols;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;
@Getter
@Setter
@ToString
public class StandardSymbol {
    private int column;
    private int row;
    private Map<String, Integer> symbols;

}

