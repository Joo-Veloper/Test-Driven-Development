package io.joo.tdd.pay.nontest;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DailyBatchLoader {
    private Times times = new Times();
    private String basePath = ".";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public void setTimes(Times times) {
        this.times = times;
    }

    public int load() {
        LocalDate date = times.today();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String resourcePath = date.format(formatter) + "/batch.txt";

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            Path batchPath = Path.of(classLoader.getResource(resourcePath).toURI());

            return (int) Files.lines(batchPath).count();
        } catch (IOException | NullPointerException | IllegalArgumentException | URISyntaxException e) {
            throw new RuntimeException("파일을 로드할 수 없습니다: " + resourcePath, e);
        }
    }
}
