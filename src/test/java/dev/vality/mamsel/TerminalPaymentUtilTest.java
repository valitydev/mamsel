package dev.vality.mamsel;

import dev.vality.damsel.domain.LegacyTerminalPaymentProvider;
import dev.vality.damsel.domain.PaymentServiceRef;
import dev.vality.damsel.domain.PaymentTerminal;
import org.junit.jupiter.api.Test;

import static dev.vality.mamsel.TerminalPaymentUtil.getTerminalPaymentProviderName;
import static dev.vality.mamsel.util.TestConstants.EMPTY;
import static dev.vality.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class TerminalPaymentUtilTest {

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
}
