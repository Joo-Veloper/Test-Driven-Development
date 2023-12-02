package io.joo.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();
    private void assertStrength(String password, PasswordStrength expStr){
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }
    @DisplayName("비밀번호 8자리 통과한 case")
    @Test
    void meetsAllCriteria_Then_Strong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }
    @DisplayName("비밀번호 8자리 통과 못한 case")
    @Test
    void meetsOtherCriteria_except_for_Length_Then_Normal() {
        assertStrength("ab12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @DisplayName("숫자 포함 x")
    @Test
    void meetsOtherCriteria_except_for_number_Then_Normal(){
        assertStrength("ab!@ABqwer", PasswordStrength.NORMAL);
    }

    @DisplayName("예외 처리")
    @Test
    void nullInput_Then_Invalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @DisplayName(" 빈 문자열 ")
    @Test
    void emptyInput_Then_Invaild(){
        assertStrength("", PasswordStrength.INVALID);
    }

    @DisplayName("대문자 포함 x")
    @Test
    void meetsOtherCriteria_except_for_Uppercase_Then_Normal(){
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @DisplayName("길이가 8글자 이상인 조건만 충족")
    @Test
    void meetsOnlyLengthCritica_Then_Weak() {
        assertStrength("abdefghi", PasswordStrength.WEAK);
    }

    @DisplayName("숫자 포함 조건만 충족")
    @Test
    void meetsOnlyNumCriteria_Then_Weak(){
        assertStrength("12345", PasswordStrength.WEAK);
    }


}