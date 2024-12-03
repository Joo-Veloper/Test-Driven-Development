package io.joo.tdd.pay.point;

import io.joo.tdd.pay.subs.Product;
import io.joo.tdd.pay.subs.Subscription;

import java.time.LocalDate;

import static io.joo.tdd.pay.subs.Grade.GOLD;

public class PointRule {
    public int calculate(Subscription s, Product p, LocalDate now) {
        int point = 0;
        if (s.isFinished(now)) {
            point += p.getDefaultPoint();
        } else {
            point += p.getDefaultPoint() + 10;
        }
        if (s.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}
