package imoduru.util;

import java.time.LocalDate;

public class DateUtil {

    public static LocalDate today() {
        return LocalDate.now();
    }

    private DateUtil() {}
}
