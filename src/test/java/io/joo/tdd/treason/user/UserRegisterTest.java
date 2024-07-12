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

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(stubWeakPasswordChecker, fakeRepository);
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
    void existsId(){
        fakeRepository.save(new User("id", "pw1", "email@emali.com"));
        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw2", "email");
        });
    }
}