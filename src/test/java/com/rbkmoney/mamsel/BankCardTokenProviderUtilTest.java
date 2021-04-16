package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.BankCardTokenServiceRef;
import com.rbkmoney.damsel.domain.LegacyBankCardTokenProvider;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.TokenizedBankCard;
import org.junit.jupiter.api.Test;

import static com.rbkmoney.mamsel.BankCardTokenProviderUtil.getTokenProviderName;
import static com.rbkmoney.mamsel.BankCardTokenProviderUtil.isSetTokenProvider;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BankCardTokenProviderUtilTest {

    @Test
    void getTokenProviderNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> BankCardTokenProviderUtil.getTokenProviderName(nullCard));

        BankCard card = new BankCard();
        assertNull(BankCardTokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(null);
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                BankCardTokenProviderUtil.getTokenProviderName(card)
        );

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(card));
    }

    @Test
    void getTokenProviderNameTest_TokenizedBankCard() {
        TokenizedBankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> BankCardTokenProviderUtil.getTokenProviderName(nullCard));

        TokenizedBankCard card = new TokenizedBankCard();
        assertNull(BankCardTokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(card));

        card.setPaymentToken(null);
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                BankCardTokenProviderUtil.getTokenProviderName(card)
        );

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(card));
    }

    @Test
    void getTokenProviderNameTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> BankCardTokenProviderUtil.getTokenProviderName(nullMethod));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        assertNull(BankCardTokenProviderUtil.getTokenProviderName(method));

        method.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(method));

        method.setPaymentToken(null);
        method.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                BankCardTokenProviderUtil.getTokenProviderName(method)
        );

        method.setPaymentToken(new BankCardTokenServiceRef(REF));
        method.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(method));
    }

    @Test
    void getTokenProviderNameTest_PaymentSystemCondition() {
        PaymentSystemCondition nullCondition = null;
        assertThrows(NullPointerException.class, () -> BankCardTokenProviderUtil.getTokenProviderName(nullCondition));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertNull(BankCardTokenProviderUtil.getTokenProviderName(condition));

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(condition));

        condition.setTokenServiceIs(null);
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                BankCardTokenProviderUtil.getTokenProviderName(condition)
        );

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertEquals(REF, BankCardTokenProviderUtil.getTokenProviderName(condition));
    }

    @Test
    void getTokenProviderNameTest() {
        assertNull(getTokenProviderName(null, null));
        assertNull(getTokenProviderName(new BankCardTokenServiceRef(), null));
        assertNull(getTokenProviderName(new BankCardTokenServiceRef(EMPTY), null));
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), null)
        );
        assertEquals(
                REF,
                getTokenProviderName(new BankCardTokenServiceRef(REF), LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(null, LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(), LegacyBankCardTokenProvider.yandexpay)
        );
        assertEquals(
                LegacyBankCardTokenProvider.yandexpay.name(),
                getTokenProviderName(new BankCardTokenServiceRef(EMPTY), LegacyBankCardTokenProvider.yandexpay)
        );
    }

    @Test
    void isSetTokenProviderTest_BankCard() {
        BankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        BankCard card = new BankCard();
        assertFalse(isSetTokenProvider(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(card));

        card = new BankCard();
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(card));
    }

    @Test
    void isSetTokenProviderTest_TokenizedBankCard() {
        TokenizedBankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        TokenizedBankCard card = new TokenizedBankCard();
        assertFalse(isSetTokenProvider(card));

        card.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(card));

        card = new TokenizedBankCard();
        card.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(card));
    }

    @Test
    void isSetTokenProviderTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        assertFalse(isSetTokenProvider(method));

        method.setPaymentToken(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(method));

        method = new BankCardPaymentMethod();
        method.setTokenProviderDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(method));
    }

    @Test
    void isSetTokenProviderTest_PaymentSystemCondition() {
        PaymentSystemCondition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetTokenProvider(nullObj));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertFalse(isSetTokenProvider(condition));

        condition.setTokenServiceIs(new BankCardTokenServiceRef(REF));
        assertTrue(isSetTokenProvider(condition));

        condition = new PaymentSystemCondition();
        condition.setTokenProviderIsDeprecated(LegacyBankCardTokenProvider.yandexpay);
        assertTrue(isSetTokenProvider(condition));
    }
}
