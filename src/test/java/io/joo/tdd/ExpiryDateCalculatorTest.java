package io.joo.tdd;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpiryDateCalculatorTest {
    // 구현하기 쉬운것 부터
    // 예외 상황 먼저
//    @Test
//    void lastDay() {
//        LocalDate billingDate = LocalDate.of(2019, 3, 1);
//        int payAmount = 10_000;
//
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//
//        assertEquals(LocalDate.of(2019,4,1),expiryDate);
//
//        LocalDate billingDate2 = LocalDate.of(2019,5,5);
//        int payAmount2 = 10_000;
//
//        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2, payAmount2);
//
//        assertEquals(LocalDate.of(2019,6,5),expiryDate2);
//    }
    // 리펙토링
//    @Test
//    void Expired_Month() {
//        LocalDate billingDate = LocalDate.of(2019, 3, 1);
//        int payAmount = 10_000;
//
//        ExpiryDateCalculator cal = new ExpiryDateCalculator();
//        LocalDate expiryDate = cal.calculateExpiryDate(billingDate, payAmount);
//
//        assertEquals(LocalDate.of(2019, 4, 1),expiryDate);
//
//        LocalDate billingDate2 = LocalDate.of(2019, 5, 5);
//        int payAmount2 = 10_000;
//
//        ExpiryDateCalculator cal2 = new ExpiryDateCalculator();
//        LocalDate expiryDate2 = cal2.calculateExpiryDate(billingDate2,payAmount2);
//
//        assertEquals(LocalDate.of(2019,6,5),expiryDate2);
//    }
    @Test
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1));
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5));
    }


    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2019, 5, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        );
        assertExpiryDate(
                PayData.builder()
                        .billingDate(LocalDate.of(2020, 1, 31))
                        .payAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        );
    }


    private void assertExpiryDate(
            PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();
        LocalDate realExpiryDate = cal.calculateExpiryDate(payData);
        assertEquals(expectedExpiryDate, realExpiryDate);
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부(){
        PayData payData = PayData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .payAmount(10_000)
                .build();
        assertExpiryDate(payData, LocalDate.of(2019 ,3, 31));

    }
}

