package net.jemsit;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MatrixGeneratorTest  {
    MatrixGenerator matrixGenerator;
    @Before
    public void setUp() throws Exception {

        matrixGenerator = new MatrixGenerator();
    }

    @Test
    public void testFindSumOfProbabilities() {
        //given
        var given = Map.of(
                "symbol1", 5,
                "symbol2", 8,
                "symbol3", 15,
                "symbol4", 10,
                "symbol5", 11,
                "symbol6", 19
                );

        // when
        var result = matrixGenerator.findSumOfProbabilities(given);
        //then
        assertThat(result).isEqualTo(68);
    }

    @Test
    public void testCreateMapOfProbabilities() {
        //given
        var given = Map.of(
                "symbol1", 1,
                "symbol2", 3,
                "symbol3", 5
        );
        int sum = 9;
        //when
        var result = matrixGenerator.createMapOfProbabilities(given, sum);
        System.out.println(result);
        //then
        assertThat(result.get("symbol1").size()).isEqualTo(1);
        assertThat(result.get("symbol2").size()).isEqualTo(3);
        assertThat(result.get("symbol3").size()).isEqualTo(5);
    }
}