package io.joo.tdd.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculationRequestTest {
    @Test
    public void 유효한_숫자_파싱() {
        String[] parts = new String[]{"2", "+", "3"};

        CalculationRequest calculationRequest = new CalculationRequest(parts);

        assertEquals(2, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(3, calculationRequest.getNum2());
    }

    @Test
    public void 세자리_숫자가_넘어가는_유요한_숫자를_파싱할_수_있다() {
        String[] parts = new String[]{"232", "+", "312"};

        CalculationRequest calculationRequest = new CalculationRequest(parts);

        assertEquals(232, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(312, calculationRequest.getNum2());
    }

    @Test
    public void 유효한_길이의_숫자가_들어오지_않으면_에러() {
        String[] parts = new String[]{"232", "+"};

        assertThrows(BadRequestException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_연산자가_들어오면_에러를_던진다() {
        String[] parts = new String[]{"232", "x", "2"};

        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_길이의_연산자가_들어오면_에러를_던진다() {
        String[] parts = new String[]{"232", "+-", "2"};

        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }

}