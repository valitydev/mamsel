package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.EmptyCvvUtil.getEmptyBankCardCvvName;
import static com.rbkmoney.mamsel.EmptyCvvUtil.isSetCvvEmpty;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmptyCvvUtilTest {

    @Test
    void getEmptyBankCardCvvNameTest_PaymentMethod() {
        PaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getEmptyBankCardCvvName(nullMethod));

        PaymentMethod method = new PaymentMethod();
        BankCardPaymentMethod bankCardPaymentMethod = new BankCardPaymentMethod();
        bankCardPaymentMethod.setPaymentSystem(new PaymentSystemRef(REF));
        method.setBankCard(bankCardPaymentMethod);
        assertNull(getEmptyBankCardCvvName(method));

        bankCardPaymentMethod.setIsCvvEmpty(true);
        assertEquals(REF, getEmptyBankCardCvvName(method));

        method.setEmptyCvvBankCardDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertEquals(LegacyBankCardPaymentSystem.uzcard.name(), getEmptyBankCardCvvName(method));
    }

    @Test
    void getEmptyBankCardCvvNameTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getEmptyBankCardCvvName(nullMethod));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertNull(getEmptyBankCardCvvName(method));

        method.setIsCvvEmpty(true);
        assertEquals(LegacyBankCardPaymentSystem.uzcard.name(), getEmptyBankCardCvvName(method));

        method.setPaymentSystemDeprecated(null);
        method.setPaymentSystem(new PaymentSystemRef(EMPTY));
        assertNull(getEmptyBankCardCvvName(method));

        method.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getEmptyBankCardCvvName(method));

        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertEquals(REF, getEmptyBankCardCvvName(method));
    }

    @Test
    void getEmptyBankCardCvvNameTest() {
        assertNull(getEmptyBankCardCvvName(null, null));
        assertNull(getEmptyBankCardCvvName(new PaymentSystemRef(), null));
        assertNull(getEmptyBankCardCvvName(new PaymentSystemRef(EMPTY), null));
        assertEquals(
                REF,
                getEmptyBankCardCvvName(new PaymentSystemRef(REF), null)
        );
        assertEquals(
                REF,
                getEmptyBankCardCvvName(new PaymentSystemRef(REF), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getEmptyBankCardCvvName(null, LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getEmptyBankCardCvvName(new PaymentSystemRef(), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getEmptyBankCardCvvName(new PaymentSystemRef(EMPTY), LegacyBankCardPaymentSystem.ebt)
        );
    }

    @Test
    void isSetCvvEmptyTest_PaymentMethod() {
        PaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCvvEmpty(nullObj));

        PaymentMethod method = new PaymentMethod();
        assertFalse(isSetCvvEmpty(method));

        BankCardPaymentMethod bankCardPaymentMethod = new BankCardPaymentMethod();
        bankCardPaymentMethod.setPaymentSystem(new PaymentSystemRef(REF));
        bankCardPaymentMethod.setIsCvvEmpty(true);
        method.setBankCard(bankCardPaymentMethod);
        assertTrue(isSetCvvEmpty(method));

        bankCardPaymentMethod.setIsCvvEmpty(false);
        assertFalse(isSetCvvEmpty(method));

        method = new PaymentMethod();
        method.setEmptyCvvBankCardDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertTrue(isSetCvvEmpty(method));
    }

    @Test
    void isSetCvvEmptyTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetCvvEmpty(nullObj));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        assertFalse(isSetCvvEmpty(method));

        method.setPaymentSystem(new PaymentSystemRef(REF));
        method.setIsCvvEmpty(true);
        assertTrue(isSetCvvEmpty(method));

        method.setIsCvvEmpty(false);
        assertFalse(isSetCvvEmpty(method));

        method = new BankCardPaymentMethod();
        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertFalse(isSetCvvEmpty(method));

        method.setIsCvvEmpty(true);
        assertTrue(isSetCvvEmpty(method));
    }
}
