package io.joo.tdd.baseball.math;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MathUtils {
    public static long sum(File dataFile) {
        try{
            return Files.readAllLines(dataFile.toPath()).stream() // String을 stream 으로 변환
                    .mapToLong(Long::parseLong) // long 타입 매핑
                    .sum(); // 스트림 값 합
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
