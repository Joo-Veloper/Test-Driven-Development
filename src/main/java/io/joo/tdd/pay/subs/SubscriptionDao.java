package io.joo.tdd.pay.subs;

import io.joo.tdd.pay.subs.Subscription;

public interface SubscriptionDao {
    Subscription selectByUser(String id);

    void insert(Subscription subscription);
}
