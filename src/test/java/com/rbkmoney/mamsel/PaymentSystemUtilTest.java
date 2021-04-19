package com.rbkmoney.mamsel;


import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.domain.TokenizedBankCard;
import com.rbkmoney.damsel.payment_tool_provider.CardInfo;
import org.junit.jupiter.api.Test;


import static com.rbkmoney.mamsel.PaymentSystemUtil.getPaymentSystemName;
import static com.rbkmoney.mamsel.PaymentSystemUtil.isSetPaymentSystem;
import static com.rbkmoney.mamsel.util.TestConstants.EMPTY;
import static com.rbkmoney.mamsel.util.TestConstants.REF;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PaymentSystemUtilTest {

    @Test
    void getPaymentSystemNameTest_BankCard() {
        BankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCard));

        BankCard card = new BankCard();
        assertNull(getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(card));

        card.setPaymentSystem(null);
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(card));
    }

    @Test
    void getPaymentSystemNameTest_TokenizedBankCard() {
        TokenizedBankCard nullCard = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCard));

        TokenizedBankCard card = new TokenizedBankCard();
        assertNull(getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(card));

        card.setPaymentSystem(null);
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(card));
    }

    @Test
    void getPaymentSystemNameTest_PaymentMethod() {
        PaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullMethod));

        PaymentMethod method = new PaymentMethod();
        BankCardPaymentMethod bankCardPaymentMethod = new BankCardPaymentMethod();
        bankCardPaymentMethod.setPaymentSystem(new PaymentSystemRef(REF));
        method.setBankCard(bankCardPaymentMethod);
        bankCardPaymentMethod.setIsCvvEmpty(true);
        assertNull(getPaymentSystemName(method));

        bankCardPaymentMethod.setIsCvvEmpty(false);
        assertEquals(REF, getPaymentSystemName(method));

        method.setBankCardDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertEquals(LegacyBankCardPaymentSystem.uzcard.name(), getPaymentSystemName(method));
    }

    @Test
    void getPaymentSystemNameTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullMethod = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullMethod));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        assertNull(getPaymentSystemName(method));

        method.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(method));

        method.setPaymentSystem(null);
        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(method));

        method.setPaymentSystem(new PaymentSystemRef(REF));
        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(method));

        method.setIsCvvEmpty(true);
        assertNull(getPaymentSystemName(method));
    }

    @Test
    void getPaymentSystemNameTest_CardInfo() {
        CardInfo nullInfo = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullInfo));

        CardInfo cardInfo = new CardInfo();
        assertNull(getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(null);
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(cardInfo));
    }

    @Test
    void getPaymentSystemNameTest_PaymentSystemCondition() {
        PaymentSystemCondition nullCondition = null;
        assertThrows(NullPointerException.class, () -> getPaymentSystemName(nullCondition));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertNull(getPaymentSystemName(condition));

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        assertEquals(REF, getPaymentSystemName(condition));

        condition.setPaymentSystemIs(null);
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(LegacyBankCardPaymentSystem.ebt.name(), getPaymentSystemName(condition));

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertEquals(REF, getPaymentSystemName(condition));
    }

    @Test
    void getPaymentSystemNameTest() {
        assertNull(getPaymentSystemName(null, null));
        assertNull(getPaymentSystemName(new PaymentSystemRef(), null));
        assertNull(getPaymentSystemName(new PaymentSystemRef(EMPTY), null));
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), null)
        );
        assertEquals(
                REF,
                getPaymentSystemName(new PaymentSystemRef(REF), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(null, LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(), LegacyBankCardPaymentSystem.ebt)
        );
        assertEquals(
                LegacyBankCardPaymentSystem.ebt.name(),
                getPaymentSystemName(new PaymentSystemRef(EMPTY), LegacyBankCardPaymentSystem.ebt)
        );
    }

    @Test
    void isPaymentSystemSetTest_BankCard() {
        BankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        BankCard card = new BankCard();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));

        card = new BankCard();
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));
    }

    @Test
    void isPaymentSystemSetTest_TokenizedBankCard() {
        TokenizedBankCard nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        TokenizedBankCard card = new TokenizedBankCard();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(card));

        card.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));

        card = new TokenizedBankCard();
        card.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(card));
    }

    @Test
    void isPaymentSystemSetTest_PaymentMethod() {
        PaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        PaymentMethod method = new PaymentMethod();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(method));

        BankCardPaymentMethod bankCardPaymentMethod = new BankCardPaymentMethod();
        bankCardPaymentMethod.setPaymentSystem(new PaymentSystemRef(REF));
        bankCardPaymentMethod.setIsCvvEmpty(true);
        method.setBankCard(bankCardPaymentMethod);
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(method));

        bankCardPaymentMethod.setIsCvvEmpty(false);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(method));

        method = new PaymentMethod();
        method.setBankCardDeprecated(LegacyBankCardPaymentSystem.uzcard);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(method));
    }

    @Test
    void isPaymentSystemSetTest_BankCardPaymentMethod() {
        BankCardPaymentMethod nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        BankCardPaymentMethod method = new BankCardPaymentMethod();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(method));

        method.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(method));

        method.setIsCvvEmpty(true);
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(method));

        method = new BankCardPaymentMethod();
        method.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(method));

        method.setIsCvvEmpty(true);
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(method));
    }

    @Test
    void isPaymentSystemSetTest_CardInfo() {
        CardInfo nullObj = null;
        assertThrows(NullPointerException.class, () -> PaymentSystemUtil.isSetPaymentSystem(nullObj));

        CardInfo cardInfo = new CardInfo();
        assertFalse(PaymentSystemUtil.isSetPaymentSystem(cardInfo));

        cardInfo.setPaymentSystem(new PaymentSystemRef(REF));
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(cardInfo));

        cardInfo = new CardInfo();
        cardInfo.setPaymentSystemDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(PaymentSystemUtil.isSetPaymentSystem(cardInfo));
    }

    @Test
    void isPaymentSystemSetTest_PaymentSystemCondition() {
        PaymentSystemCondition nullObj = null;
        assertThrows(NullPointerException.class, () -> isSetPaymentSystem(nullObj));

        PaymentSystemCondition condition = new PaymentSystemCondition();
        assertFalse(isSetPaymentSystem(condition));

        condition.setPaymentSystemIs(new PaymentSystemRef(REF));
        assertTrue(isSetPaymentSystem(condition));

        condition = new PaymentSystemCondition();
        condition.setPaymentSystemIsDeprecated(LegacyBankCardPaymentSystem.ebt);
        assertTrue(isSetPaymentSystem(condition));
    }
}
