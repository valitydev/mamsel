package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.LegacyBankCardPaymentSystem;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentSystemRef;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.internal.Util.isNotNullOrEmpty;

/**
 * Utility class to help with former BankCardPaymentSystem (empty_cvv).<br>
 * It can have values in two fields: <br>
 *  -> PaymentSystemRef <br>
 *  -> LegacyBankCardPaymentSystem
 */
public class EmptyCvvBankCardUtil {

    public static String getEmptyBankCardCvvName(@NotNull PaymentMethod method) {
        if (method.isSetBankCard() && method.getBankCard().isIsCvvEmpty()) {
            return method.getBankCard().getPaymentSystem().getId();
        }
        if (method.isSetEmptyCvvBankCardDeprecated()) {
            return method.getEmptyCvvBankCardDeprecated().name();
        }

        return null;
    }

    public static String getEmptyBankCardCvvName(@NotNull BankCardPaymentMethod method) {
        if (!method.isIsCvvEmpty()) {
            return null;
        }
        return getEmptyBankCardCvvName(method.getPaymentSystem(), method.getPaymentSystemDeprecated());
    }

    public static String getEmptyBankCardCvvName(PaymentSystemRef paymentSystemRef,
                                                  LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        if (paymentSystemRef != null && isNotNullOrEmpty(paymentSystemRef.getId())) {
            return paymentSystemRef.getId();
        }
        if (legacyBankCardPaymentSystem != null) {
            return legacyBankCardPaymentSystem.name();
        }

        return null;
    }


    public static boolean isSetCvvEmpty(@NotNull PaymentMethod method) {
        return (method.isSetBankCard() && method.getBankCard().isIsCvvEmpty())
                || method.isSetEmptyCvvBankCardDeprecated();
    }

    public static boolean isSetCvvEmpty(@NotNull BankCardPaymentMethod method) {
        return method.isIsCvvEmpty() && (method.isSetPaymentSystem() || method.isSetPaymentSystemDeprecated());
    }

}
