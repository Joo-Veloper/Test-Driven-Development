package io.joo.tdd.basic;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DisplayNameTest {

    @Test
    void assertEqualsMethod() {
        LocalDate dateTime1 = LocalDate.now();
        LocalDate dateTime2 = LocalDate.now();
        assertEquals(dateTime1, dateTime2);
    }

    @Disabled
    @Test
    void failMethod() {
        try {
            AuthService authService = new AuthService();
            authService.authenticate(null, null);
            fail();
        } catch (IllegalArgumentException e) {

        }
    }

    @DisplayName("익셉션 발생 여부 테스트")
    @Test
    void assertThrowsTest() {
        assertThrows(IllegalArgumentException.class,
                ()->{
                    AuthService authService = new AuthService();
                    authService.authenticate(null, null);
                });
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
