package Singleton;

import java.util.Arrays;

/**
 * 총 3개의 인스턴스가 존재하는 Triple Class
 */
public class Triple {

    private int id;

    private static final Triple[] instances = { new Triple(0), new Triple(1), new Triple(2) };

    private Triple() {}

    private Triple(int id) {
        this.id = id;
    }

    public static Triple getInstance(int id) {
        return Arrays.stream(instances)
                .filter((i) -> i.id == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("해당 ID를 갖는 Triple 인스턴스가 존재하지 않습니다."));
    }

}
