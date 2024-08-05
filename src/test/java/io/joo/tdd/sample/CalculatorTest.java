package io.joo.tdd.sample;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {
    @Test
    @DisplayName("Plus Test")
    public void plus() {
        // given
        long num1 = 2;
        String operator = "+";
        long num2 = 3;
        Calculator calculator = new Calculator();
        //when
        long result = calculator.calculate(num1, operator, num2);
        //then
        assertEquals(5, result); //junit assertion
        /*assertThat(result).isEqualTo(5);*/ // assertj assertion
    }
    @Test
    @DisplayName("Minus Test")
    public void minus() {
        // given
        long num1 = 3;
        String operator = "-";
        long num2 = 2;
        Calculator calculator = new Calculator();
        //when
        long result = calculator.calculate(num1, operator, num2);
        //then
        assertEquals(1, result); //junit assertion
    }
    @Test
    @DisplayName("multiplication Test")
    public void multiplication() {
        // given
        long num1 = 2;
        String operator = "*";
        long num2 = 3;
        Calculator calculator = new Calculator();
        //when
        long result = calculator.calculate(num1, operator, num2);
        //then
        assertEquals(6, result); //junit assertion
    }
    @Test
    @DisplayName("division Test")
    public void division() {
        // given
        long num1 = 6;
        String operator = "/";
        long num2 = 3;
        Calculator calculator = new Calculator();
        //when
        long result = calculator.calculate(num1, operator, num2);
        //then
        assertEquals(2, result); //junit assertion
    }

    @Test
    @DisplayName("invalid operator")
    public void invalid() {
        // given
        long num1 = 6;
        String operator = "x";
        long num2 = 3;
        Calculator calculator = new Calculator();
        //when & then
        assertThrows(InvalidOperatorException.class, () -> {
            calculator.calculate(num1, operator, num2);
        });
    }
}
