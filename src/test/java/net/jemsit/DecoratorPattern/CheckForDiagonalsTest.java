package net.jemsit.DecoratorPattern;

import junit.framework.TestCase;
import net.jemsit.Config;
import org.junit.Test;

public class CheckForDiagonalsTest extends TestCase {

    @Test
    public void testGetDatas() {
        //given
        String[][] givenMatrix = {
                {"E", "D", "E"},
                {"A", "E", "C"},
                {"E", "F", "E"}
        };
        Config config = new Config();
        WinAmount amount = new WinningCombination(givenMatrix, 200, config);
        //when
        amount = new CheckForDiagonals(amount);
        //then
    }

}