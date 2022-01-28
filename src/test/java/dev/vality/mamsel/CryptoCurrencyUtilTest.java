package dev.vality.mamsel;

import dev.vality.damsel.domain.CryptoCurrencyRef;
import dev.vality.damsel.domain.CryptoWallet;
import dev.vality.damsel.domain.LegacyCryptoCurrency;
import dev.vality.damsel.domain.PaymentTool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static dev.vality.mamsel.util.TestConstants.EMPTY;
import static dev.vality.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.*;

class CryptoCurrencyUtilTest {

    @Test
    void getCryptoCurrencyNameTest_PaymentTool() {
        PaymentTool nullMethod = null;
        assertThrows(NullPointerException.class, () -> CryptoCurrencyUtil.getCryptoCurrencyName(nullMethod));

        PaymentTool tool = new PaymentTool();
        assertNull(CryptoCurrencyUtil.getCryptoCurrencyName(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, CryptoCurrencyUtil.getCryptoCurrencyName(tool));

        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        Assertions.assertEquals(LegacyCryptoCurrency.bitcoin.name(), CryptoCurrencyUtil.getCryptoCurrencyName(tool));
    }

    @Test
    void getCryptoCurrencyNameTest_CryptoWallet() {
        CryptoWallet nullWallet = null;
        assertThrows(NullPointerException.class, () -> CryptoCurrencyUtil.getCryptoCurrencyName(nullWallet));

        CryptoWallet commerce = new CryptoWallet();
        assertNull(CryptoCurrencyUtil.getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, CryptoCurrencyUtil.getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(null);
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        Assertions.assertEquals(LegacyCryptoCurrency.bitcoin.name(),
                CryptoCurrencyUtil.getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(REF, CryptoCurrencyUtil.getCryptoCurrencyName(commerce));
    }

    @Test
    void getCryptoCurrencyNameTest() {
        assertNull(CryptoCurrencyUtil.getCryptoCurrencyName(null, null));
        assertNull(CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(), null));
        assertNull(CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), null));
        assertEquals(
                REF,
                CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(REF), null)
        );
        assertEquals(
                REF,
                CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(REF), LegacyCryptoCurrency.bitcoin)
        );
        Assertions.assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                CryptoCurrencyUtil.getCryptoCurrencyName(null, LegacyCryptoCurrency.bitcoin)
        );
        Assertions.assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(), LegacyCryptoCurrency.bitcoin)
        );
        Assertions.assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                CryptoCurrencyUtil.getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), LegacyCryptoCurrency.bitcoin)
        );
    }

    @Test
    void isSetCryptoCurrencyTest_PaymentTool() {
        PaymentTool nullObj = null;
        assertThrows(NullPointerException.class, () -> CryptoCurrencyUtil.isSetCryptoCurrency(nullObj));

        PaymentTool tool = new PaymentTool();
        Assertions.assertFalse(CryptoCurrencyUtil.isSetCryptoCurrency(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        Assertions.assertTrue(CryptoCurrencyUtil.isSetCryptoCurrency(tool));

        tool = new PaymentTool();
        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        Assertions.assertTrue(CryptoCurrencyUtil.isSetCryptoCurrency(tool));
    }
}
