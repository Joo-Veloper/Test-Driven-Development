package io.joo.tdd.pay.nontest;


import io.joo.tdd.pay.auth.*;

public class LoginService {
    private AuthService authService = new AuthService();
    private CustomerRepository customerRepo;

    public LoginService(CustomerRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);
        if (resp == -1) return LoginResult.badAuthKey();

        if (resp == 1) {
            Customer c = customerRepo.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }

}
