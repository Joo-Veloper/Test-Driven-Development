package io.joo.tdd.pay.memory;

import io.joo.tdd.pay.payinfo.PayInfo;
import io.joo.tdd.pay.payinfo.PayInfoDao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryPayInfoDao extends PayInfoDao {
    private LinkedHashMap<String, PayInfo> infos = new LinkedHashMap<>();
    @Override
    public void insert(PayInfo pi) {
        infos.put(pi.getTrNum(), pi);
    }

    public List<PayInfo> getAll() {
        return new ArrayList<>(infos.values());
    }
}

