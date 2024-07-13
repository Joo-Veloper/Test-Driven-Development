package io.joo.tdd.treason.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private UserRepository userRepository;
    private WeakPasswordChecker mockPasswordChecker = Mockito.mock(WeakPasswordChecker.class);
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private EmailNotifier mockEmailNotifier = Mockito.mock(EmailNotifier.class);
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(mockPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호 Test")
    void weekPassword() {
        BDDMockito.given(mockPasswordChecker.checkPasswordWeak("password")).willReturn(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "password", "123@gmail.com");
        });
    }

    @Test
    @DisplayName("암호 검사")
    void passwordTest() {
        userRegister.register("id", "password", "email");

        BDDMockito.then(mockPasswordChecker)
                .should()
                .checkPasswordWeak(BDDMockito.anyString());
    }

    @Test
    @DisplayName("이미 같은 ID 존재시 가입 실패")
    void existsId() {
        fakeRepository.save(new User("id", "pw1", "email@emali.com"));
        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }

    @Test
    @DisplayName("같은 ID가 없을시 가입 성공")
    void No_ExistsId() {
        userRegister.register("id", "password", "email");

        User savedUser = fakeRepository.findById("id");
        assertEquals("id", savedUser.getId());
        assertEquals("email", savedUser.getEmail());
    }

    @Test
    @DisplayName("가입하면 이메일 전송")
    void mail_Subscription() {
        userRegister.register("id", "password", "email@gmail.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("email@gmail.com", spyEmailNotifier.getEmail());
    }

}