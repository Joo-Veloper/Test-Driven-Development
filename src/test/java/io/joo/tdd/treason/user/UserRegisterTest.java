package io.joo.tdd.treason.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRegisterTest {
    private UserRegister userRegister;
    private UserRepository userRepository;
    private StubWeakPasswordChecker stubWeakPasswordChecker = new StubWeakPasswordChecker();
    private MemoryUserRepository fakeRepository = new MemoryUserRepository();
    private SpyEmailNotifier spyEmailNotifier = new SpyEmailNotifier();

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호 Test")
    void weekPassword() {
        stubWeakPasswordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "12345", "123@gmail.com");
        });
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