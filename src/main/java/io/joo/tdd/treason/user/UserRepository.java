package io.joo.tdd.treason.user;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
