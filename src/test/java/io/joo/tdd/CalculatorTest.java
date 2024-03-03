package io.joo.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {
//    @Test
//    void plus(){
//        int result = Calculator.plus(1,2);
//        assertEquals(3,result);
//    }
    @Test
    void plus() {
        int sum = Calculator.plus(1,2);
        assertEquals(3, sum);
        assertEquals(5,Calculator.plus(4,1));
    }


}