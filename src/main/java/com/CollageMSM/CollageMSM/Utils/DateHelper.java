package com.CollageMSM.CollageMSM.Utils;

import java.time.format.DateTimeFormatter;

public class DateHelper {
    public String format(java.time.temporal.TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(temporal);
    }
}
