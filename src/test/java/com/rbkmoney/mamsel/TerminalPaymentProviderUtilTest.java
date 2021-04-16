package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentTerminal;
import com.rbkmoney.damsel.domain.PaymentTerminalConditionDefinition;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.TerminalPaymentProviderUtil.getTerminalPaymentProviderName;
import static com.rbkmoney.mamsel.TerminalPaymentProviderUtil.isSetTerminalPaymentProvider;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TerminalPaymentProviderUtilTest {

    @Test
    void getTerminalPaymentProviderNameTest_PaymentMethod() {
        PaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullMethod));

        PaymentMethod method = new PaymentMethod();
        assertNull(getTerminalPaymentProviderName(method));

        method.setPaymentTerminal(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(method));

        method.setPaymentTerminalDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(method));
    }

    @Test
    void getTerminalPaymentProviderNameTest_PaymentTerminal() {
        PaymentTerminal nullTerminal = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullTerminal));

        PaymentTerminal terminal = new PaymentTerminal();
        assertNull(getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(null);
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(terminal));

        terminal.setPaymentService(new PaymentServiceRef(REF));
        terminal.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(REF, getTerminalPaymentProviderName(terminal));
    }

    @Test
    void getTerminalPaymentProviderNameTest_PaymentTerminalConditionDefinition() {
        PaymentTerminalConditionDefinition nullDefinition = null;
        assertThrows(NullPointerException.class, () -> getTerminalPaymentProviderName(nullDefinition));

        PaymentTerminalConditionDefinition definition = new PaymentTerminalConditionDefinition();
        assertNull(getTerminalPaymentProviderName(definition));

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertEquals(REF, getTerminalPaymentProviderName(definition));

        definition.setProviderIsDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertEquals(LegacyTerminalPaymentProvider.uzcard.name(), getTerminalPaymentProviderName(definition));
    }

    @Test
    void getTerminalPaymentProviderNameTest() {
        assertNull(getTerminalPaymentProviderName(null, null));
        assertNull(getTerminalPaymentProviderName(new PaymentServiceRef(), null));
        assertNull(getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), null));
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), null)
        );
        assertEquals(
                REF,
                getTerminalPaymentProviderName(new PaymentServiceRef(REF), LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(null, LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(), LegacyTerminalPaymentProvider.uzcard)
        );
        assertEquals(
                LegacyTerminalPaymentProvider.uzcard.name(),
                getTerminalPaymentProviderName(new PaymentServiceRef(EMPTY), LegacyTerminalPaymentProvider.uzcard)
        );
    }

    @Test
    void isSetTerminalPaymentProviderTest_PaymentMethod() {
        PaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTerminalPaymentProvider(nullObj));

        PaymentMethod method = new PaymentMethod();
        assertFalse(isSetTerminalPaymentProvider(method));

        method.setPaymentTerminal(new PaymentServiceRef(REF));
        assertTrue(isSetTerminalPaymentProvider(method));

        method = new PaymentMethod();
        method.setPaymentTerminalDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertTrue(isSetTerminalPaymentProvider(method));
    }

    @Test
    void isSetTerminalPaymentProviderTest_PaymentTerminal() {
        PaymentTerminal nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTerminalPaymentProvider(nullObj));

        PaymentTerminal method = new PaymentTerminal();
        assertFalse(isSetTerminalPaymentProvider(method));

        method.setPaymentService(new PaymentServiceRef(REF));
        assertTrue(isSetTerminalPaymentProvider(method));

        method = new PaymentTerminal();
        method.setTerminalTypeDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertTrue(isSetTerminalPaymentProvider(method));
    }

    @Test
    void isSetTerminalPaymentProviderTest_PaymentTerminalConditionDefinition() {
        PaymentTerminalConditionDefinition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTerminalPaymentProvider(nullObj));

        PaymentTerminalConditionDefinition definition = new PaymentTerminalConditionDefinition();
        assertFalse(isSetTerminalPaymentProvider(definition));

        definition.setPaymentServiceIs(new PaymentServiceRef(REF));
        assertTrue(isSetTerminalPaymentProvider(definition));

        definition = new PaymentTerminalConditionDefinition();
        definition.setProviderIsDeprecated(LegacyTerminalPaymentProvider.uzcard);
        assertTrue(isSetTerminalPaymentProvider(definition));
    }

}
