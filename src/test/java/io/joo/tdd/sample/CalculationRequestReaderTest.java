package io.joo.tdd.sample;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationRequestReaderTest {
    @Test
    public void System_in_read_data() {
        //given
        CalculationRequestReader calculationRequestReader = new CalculationRequestReader();

        // when
        System.setIn(new ByteArrayInputStream("2 + 3".getBytes()));
        CalculationRequest result = calculationRequestReader.read();

        // then
        assertEquals(2, result.getNum1());
        assertEquals("+", result.getOperator());
        assertEquals(3, result.getNum2());
    }
}
