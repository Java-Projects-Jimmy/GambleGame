package net.jemsit.DecoratorPattern;

import net.jemsit.Config;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckForStraightLinesTest {

    @Test
    public void getDatas1() {
        //given
        String[][] givenMatrix = {
                {"F", "E", "F"},
                {"E", "E", "E"},
                {"D", "E", "E"}
        };
        Config config = new Config();
        WinAmount amount = new WinningCombination(givenMatrix, 200, config);
        //when
        CheckForStraightLines underTest = new CheckForStraightLines(amount);
        var result = underTest.straightLineSameSymbols;
        //then
        assertThat(result.size()).isEqualTo(1);
        result.forEach((key,value)->{
            assertThat(key).isEqualTo("E");
            assertThat(value).isEqualTo(2.0);
        });
    }
    @Test
    public void getDatas2() {
        //given
        String[][] givenMatrix = {
                {"F", "F", "F"},
                {"10x", "A", "F"},
                {"D", "C", "F"}
        };
        Config config = new Config();
        WinAmount amount = new WinningCombination(givenMatrix, 200, config);
        //when
        CheckForStraightLines underTest = new CheckForStraightLines(amount);
        var result = underTest.straightLineSameSymbols;
        //then
        assertThat(result.size()).isEqualTo(1);
        result.forEach((key,value)->{
            assertThat(key).isEqualTo("F");
            assertThat(value).isEqualTo(2.0);
        });
    }
}