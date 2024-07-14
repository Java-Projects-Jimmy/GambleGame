package net.jemsit.DecoratorPattern;

import net.jemsit.Config;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckForStraightLinesTest {

    @Test
    public void winAmount() {
    }

    @Test
    public void getDatas1() {
        //given
        String[][] givenMatrix = {{"F", "E", "F"}, {"E", "E", "E"}, {"D", "E", "E"}};
        Config config = new Config();
        WinAmount amount = new WinningCombination(givenMatrix, 200, config);
        //when
        WinAmount straightLineCheck = new CheckForStraightLines(amount);
        //then
    }
    @Test
    public void getDatas2() {
        //given
        String[][] givenMatrix = {{"F", "F", "F"}, {"10x", "A", "F"}, {"D", "C", "F"}};
        Config config = new Config();
        WinAmount amount = new WinningCombination(givenMatrix, 200, config);
        //when
        WinAmount straightLineCheck = new CheckForStraightLines(amount);
        //then
    }
}