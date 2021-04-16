package com.rbkmoney.mamsel.internal;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UtilTest {

    @Test
    void isNotNullOrEmptyTest() {
        assertFalse(Util.isNotNullOrEmpty(null));
        assertFalse(Util.isNotNullOrEmpty(""));
        assertTrue(Util.isNotNullOrEmpty("123"));
    }
}
