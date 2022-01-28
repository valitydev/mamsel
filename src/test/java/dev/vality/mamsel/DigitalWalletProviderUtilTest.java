package dev.vality.mamsel;

import dev.vality.damsel.domain.DigitalWallet;
import dev.vality.damsel.domain.LegacyDigitalWalletProvider;
import dev.vality.damsel.domain.PaymentServiceRef;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.vality.mamsel.DigitalWalletUtil.getDigitalWalletName;
import static dev.vality.mamsel.util.TestConstants.EMPTY;
import static dev.vality.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class DigitalWalletProviderUtilTest {

    @Test
    void getDigitalWalletNameTest_DigitalWallet() {
        DigitalWallet nullWalltet = null;
        assertThrows(NullPointerException.class, () -> DigitalWalletUtil.getDigitalWalletName(nullWalltet));

        DigitalWallet wallet = new DigitalWallet();
        assertNull(DigitalWalletUtil.getDigitalWalletName(wallet));

        wallet.setPaymentService(new PaymentServiceRef(REF));
        assertEquals(REF, DigitalWalletUtil.getDigitalWalletName(wallet));

        wallet.setPaymentService(null);
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        Assertions.assertEquals(LegacyDigitalWalletProvider.rbkmoney.name(), DigitalWalletUtil.getDigitalWalletName(wallet));

        wallet.setPaymentService(new PaymentServiceRef(REF));
        wallet.setProviderDeprecated(LegacyDigitalWalletProvider.rbkmoney);
        assertEquals(REF, DigitalWalletUtil.getDigitalWalletName(wallet));
    }

    @Test
    void getDigitalWalletNameTest() {
        assertNull(DigitalWalletUtil.getDigitalWalletName(null, null));
        assertNull(DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(), null));
        assertNull(DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(EMPTY), null));
        assertEquals(
                REF,
                DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(REF), null)
        );
        assertEquals(
                REF,
                DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(REF), LegacyDigitalWalletProvider.rbkmoney)
        );
        Assertions.assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                DigitalWalletUtil.getDigitalWalletName(null, LegacyDigitalWalletProvider.rbkmoney)
        );
        Assertions.assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(), LegacyDigitalWalletProvider.rbkmoney)
        );
        Assertions.assertEquals(
                LegacyDigitalWalletProvider.rbkmoney.name(),
                DigitalWalletUtil.getDigitalWalletName(new PaymentServiceRef(EMPTY), LegacyDigitalWalletProvider.rbkmoney)
        );
    }
}
