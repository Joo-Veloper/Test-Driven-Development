package io.joo.tdd.pay.auth;

public interface CustomerRepository {

    Customer findOne(String id);
}
