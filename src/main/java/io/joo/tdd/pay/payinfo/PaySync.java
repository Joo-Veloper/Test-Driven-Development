package io.joo.tdd.pay.payinfo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PaySync {
    private PayInfoDao payInfoDao = new PayInfoDao();
    private String resourceFileName = "cp0001.csv";

    public void setPayInfoDao(PayInfoDao payInfoDao) {
        this.payInfoDao = payInfoDao;
    }

    public void setResourceFileName(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public void sync() throws IOException, URISyntaxException {
        // 클래스 로더를 사용해 resources 디렉토리 파일 경로 얻기
        ClassLoader classLoader = getClass().getClassLoader();
        Path path = Paths.get(Objects.requireNonNull(classLoader.getResource(resourceFileName)).toURI());

        List<PayInfo> payInfos = Files.lines(path)
                .map(line -> {
                    String[] data = line.split(",");
                    return new PayInfo(data[0], data[1], Integer.parseInt(data[2]));
                })
                .collect(Collectors.toList());

        payInfos.forEach(payInfoDao::insert);
    }
}
