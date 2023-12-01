package io.joo.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthMeterTest {

    @DisplayName("비밀번호 8자리 통과한 case")
    @Test
    void meetsAllCriteria_Then_Strong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@AB"); // 비밀 번호 8자리 규칙 통과! 8자리 미만시 에러 출력!
        assertEquals(PasswordStrength.STRONG,result);
        PasswordStrength result2 = meter.meter("abc1!Add");
        assertEquals(PasswordStrength.STRONG, result2);
    }
    @DisplayName("비밀번호 8자리 통과 못한 case")
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@A");
        assertEquals(PasswordStrength.NORMAL, result);
        PasswordStrength result2 = meter.meter("Ab12!c");
        assertEquals(PasswordStrength.NORMAL, result2);
    }

    @DisplayName("숫자 포함 x")
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab!@ABqwer");
        assertEquals(PasswordStrength.NORMAL,result);
    }

}