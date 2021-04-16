package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.CryptoCurrencyConditionDefinition;
import com.rbkmoney.damsel.domain.LegacyCryptoCurrency;
import com.rbkmoney.damsel.domain.CryptoWallet;
import com.rbkmoney.damsel.domain.CryptoCurrencyRef;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentTool;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.CryptoCurrencyUtil.getCryptoCurrencyName;
import static com.rbkmoney.mamsel.CryptoCurrencyUtil.isSetCryptoCurrency;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CryptoCurrencyUtilTest {

    @Test
    void getCryptoCurrencyNameTest_PaymentMethod() {
        PaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullMethod));

        PaymentMethod method = new PaymentMethod();
        assertNull(getCryptoCurrencyName(method));

        method.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(method));

        method.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(method));
    }

    @Test
    void getCryptoCurrencyNameTest_PaymentTool() {
        PaymentTool nullMethod = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullMethod));

        PaymentTool tool = new PaymentTool();
        assertNull(getCryptoCurrencyName(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(tool));

        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(tool));
    }

    @Test
    void getCryptoCurrencyNameTest_CryptoWallet() {
        CryptoWallet nullWallet = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullWallet));

        CryptoWallet commerce = new CryptoWallet();
        assertNull(getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(null);
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(commerce));

        commerce.setCryptoCurrency(new CryptoCurrencyRef(REF));
        commerce.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(REF, getCryptoCurrencyName(commerce));
    }

    @Test
    void getCryptoCurrencyNameTest_CryptoCurrencyConditionDefinition() {
        CryptoCurrencyConditionDefinition nullDefinition = null;
        assertThrows(NullPointerException.class, () -> getCryptoCurrencyName(nullDefinition));

        CryptoCurrencyConditionDefinition definition = new CryptoCurrencyConditionDefinition();
        assertNull(getCryptoCurrencyName(definition));

        definition.setCryptoCurrencyIs(new CryptoCurrencyRef(REF));
        assertEquals(REF, getCryptoCurrencyName(definition));

        definition.setCryptoCurrencyIsDeprecated(LegacyCryptoCurrency.bitcoin);
        assertEquals(LegacyCryptoCurrency.bitcoin.name(), getCryptoCurrencyName(definition));
    }

    @Test
    void getCryptoCurrencyNameTest() {
        assertNull(getCryptoCurrencyName(null, null));
        assertNull(getCryptoCurrencyName(new CryptoCurrencyRef(), null));
        assertNull(getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), null));
        assertEquals(
                REF,
                getCryptoCurrencyName(new CryptoCurrencyRef(REF), null)
        );
        assertEquals(
                REF,
                getCryptoCurrencyName(new CryptoCurrencyRef(REF), LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(null, LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(new CryptoCurrencyRef(), LegacyCryptoCurrency.bitcoin)
        );
        assertEquals(
                LegacyCryptoCurrency.bitcoin.name(),
                getCryptoCurrencyName(new CryptoCurrencyRef(EMPTY), LegacyCryptoCurrency.bitcoin)
        );
    }

    @Test
    void isSetCryptoCurrencyTest_PaymentMethod() {
        PaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCryptoCurrency(nullObj));

        PaymentMethod method = new PaymentMethod();
        assertFalse(isSetCryptoCurrency(method));

        method.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertTrue(isSetCryptoCurrency(method));

        method = new PaymentMethod();
        method.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertTrue(isSetCryptoCurrency(method));
    }

    @Test
    void isSetCryptoCurrencyTest_PaymentTool() {
        PaymentTool nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCryptoCurrency(nullObj));

        PaymentTool tool = new PaymentTool();
        assertFalse(isSetCryptoCurrency(tool));

        tool.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertTrue(isSetCryptoCurrency(tool));

        tool = new PaymentTool();
        tool.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertTrue(isSetCryptoCurrency(tool));
    }

    @Test
    void isSetCryptoCurrencyTest_CryptoWallet() {
        CryptoWallet nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCryptoCurrency(nullObj));

        CryptoWallet wallet = new CryptoWallet();
        assertFalse(isSetCryptoCurrency(wallet));

        wallet.setCryptoCurrency(new CryptoCurrencyRef(REF));
        assertTrue(isSetCryptoCurrency(wallet));

        wallet = new CryptoWallet();
        wallet.setCryptoCurrencyDeprecated(LegacyCryptoCurrency.bitcoin);
        assertTrue(isSetCryptoCurrency(wallet));
    }

    @Test
    void isSetCryptoCurrencyTest_CryptoCurrencyConditionDefinition() {
        CryptoCurrencyConditionDefinition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCryptoCurrency(nullObj));

        CryptoCurrencyConditionDefinition definition = new CryptoCurrencyConditionDefinition();
        assertFalse(isSetCryptoCurrency(definition));

        definition.setCryptoCurrencyIs(new CryptoCurrencyRef(REF));
        assertTrue(isSetCryptoCurrency(definition));

        definition = new CryptoCurrencyConditionDefinition();
        definition.setCryptoCurrencyIsDeprecated(LegacyCryptoCurrency.bitcoin);
        assertTrue(isSetCryptoCurrency(definition));
    }
}
