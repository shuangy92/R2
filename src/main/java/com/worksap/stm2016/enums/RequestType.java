package com.worksap.stm2016.enums;

/**
 * Created by Shuang on 4/18/2016.
 */
public enum RequestType {
    STAFFING(Values.STAFFING), CONTRACT_EXTEND(Values.CONTRACT_EXTEND), LEAVE(Values.LEAVE), RESIGNATION(Values.RESIGNATION), OTHER(Values.OTHER);

    RequestType(String val) {
        // force equality between name of enum instance, and value of constant
        if (!this.name().equals(val))
            throw new IllegalArgumentException("Incorrect use of ELanguage");
    }

    public static class Values {
        public static final String STAFFING = "STAFFING";
        public static final String CONTRACT_EXTEND = "CONTRACT_EXTEND";
        public static final String LEAVE = "LEAVE";
        public static final String RESIGNATION = "RESIGNATION";
        public static final String OTHER = "OTHER";
    }
}
