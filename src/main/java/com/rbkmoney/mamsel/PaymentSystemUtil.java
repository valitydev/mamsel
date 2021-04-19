package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.PaymentSystemRef;
import com.rbkmoney.damsel.domain.TokenizedBankCard;
import com.rbkmoney.damsel.payment_tool_provider.CardInfo;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.Util.isEmpty;

/**
 * Utility class to help with former BankCardPaymentSystem.<br>
 * It can have values in two fields: <br>
 *  -> PaymentSystemRef <br>
 *  -> LegacyBankCardPaymentSystem
 */
public class PaymentSystemUtil {

    private PaymentSystemUtil() {
    }

    public static String getPaymentSystemName(@NotNull PaymentMethod method) {
        if (method.isSetBankCard() && !method.getBankCard().isIsCvvEmpty()) {
            return method.getBankCard().getPaymentSystem().getId();
        }
        if (method.isSetBankCardDeprecated()) {
            return method.getBankCardDeprecated().name();
        }

        return null;
    }

    public static String getPaymentSystemName(@NotNull BankCardPaymentMethod method) {
        if (method.isIsCvvEmpty()) {
            return null;
        }
        return getPaymentSystemName(method.getPaymentSystem(), method.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull BankCard card) {
        return getPaymentSystemName(card.getPaymentSystem(), card.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull TokenizedBankCard card) {
        return getPaymentSystemName(card.getPaymentSystem(), card.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull PaymentSystemCondition condition) {
        return getPaymentSystemName(condition.getPaymentSystemIs(), condition.getPaymentSystemIsDeprecated());
    }

    public static String getPaymentSystemName(@NotNull CardInfo cardInfo) {
        return getPaymentSystemName(cardInfo.getPaymentSystem(), cardInfo.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(PaymentSystemRef paymentSystemRef,
                                              LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        if (paymentSystemRef != null && !isEmpty(paymentSystemRef.getId())) {
            return paymentSystemRef.getId();
        }
        if (legacyBankCardPaymentSystem != null) {
            return legacyBankCardPaymentSystem.name();
        }

        return null;
    }

    public static boolean isSetPaymentSystem(@NotNull BankCard card) {
        return card.isSetPaymentSystem() || card.isSetPaymentSystemDeprecated();
    }

    public static boolean isSetPaymentSystem(@NotNull TokenizedBankCard card) {
        return card.isSetPaymentSystem() || card.isSetPaymentSystemDeprecated();
    }

    public static boolean isSetPaymentSystem(@NotNull PaymentMethod method) {
        return (method.isSetBankCard() && !method.getBankCard().isIsCvvEmpty()) || method.isSetBankCardDeprecated();
    }

    public static boolean isSetPaymentSystem(@NotNull BankCardPaymentMethod method) {
        return !method.isIsCvvEmpty() && (method.isSetPaymentSystem() || method.isSetPaymentSystemDeprecated());
    }

    public static boolean isSetPaymentSystem(@NotNull CardInfo cardInfo) {
        return cardInfo.isSetPaymentSystem() || cardInfo.isSetPaymentSystemDeprecated();
    }

    public static boolean isSetPaymentSystem(@NotNull PaymentSystemCondition condition) {
        return condition.isSetPaymentSystemIs() || condition.isSetPaymentSystemIsDeprecated();
    }

}
