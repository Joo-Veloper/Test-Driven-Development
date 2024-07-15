package io.joo.tdd.basic;

public class AuthService {
    public void authenticate(String id, String password) {
        if (id == null){
            throw new IllegalArgumentException("id");
        }
        if (password == null) {
            throw new IllegalArgumentException("password");
        }
    }
}
