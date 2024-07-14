package net.jemsit.DecoratorPattern;

import junit.framework.TestCase;
import net.jemsit.Config;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class CheckForDiagonalsTest extends TestCase {

    @Test
    public void testGetDatas() {
        //given
        String[][] givenMatrix = {
                {"E", "D", "E"},
                {"A", "E", "C"},
                {"E", "F", "E"}
        };
        WinAmount amount = new WinningCombination(givenMatrix, 200, new Config());
        //when
        CheckForDiagonals underTest = new CheckForDiagonals(amount);
        var result = (HashMap)underTest.diagonals;
        //then
        assertThat(result.size()).isEqualTo(1);
        result.forEach((key,value)->{
            assertThat(key).isEqualTo("E");
            assertThat(value).isEqualTo(2.0);
        });
    }

}