package io.joo.tdd.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    @Test
    @DisplayName("assertEquals(expected, actual) : 실제 값이 기대하는 값과 같은지 검사.")
    void assertEqualsMethod(){
        LocalDate dateTime1 = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now();
        assertEquals(dateTime1, dateTime2);
    }

    @Test
    void failMethod() {
        try {
            AuthService authService = new AuthService();
            authService.authenticate(null, null);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    void assertThrowsTest(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> {
                    AuthService authService = new AuthService();
                    authService.authenticate(null, null);
                });
        assertTrue(thrown.getMessage().contains("id"));
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertEquals(2, 5 / 2),
                () -> assertEquals(4, 2 * 2),
                () -> assertEquals(5, 11 / 2)
        );
    }
}