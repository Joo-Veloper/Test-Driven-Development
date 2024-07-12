package io.joo.tdd.treason.account;

import io.joo.tdd.treason.account.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MemoryAutoDebitInfoRepositoryTest {
    private StubCardNumberValidator cardNumberValidator;
    private AutoDebitRegister register;
    private MemoryAutoDebitInfoRepository repository;


    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }
    @Test
    void alreadyRegistered_InfoUpdate() {
        repository.save(new AutoDebitInfo("userId", "111222333444", LocalDateTime.now()));
        AutoDebitReq req = new AutoDebitReq("user1", "123456789012");
        RegisterResult result = this.register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("123456789012", saved.getCardNumber());
    }

    @Test
    void notYetRegistered_newInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "1234123412341234");
        RegisterResult result = this.register.register(req);
        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("1234123412341234", saved.getCardNumber());
    }
}