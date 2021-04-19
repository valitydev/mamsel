package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.BankCard;
import com.rbkmoney.damsel.domain.BankCardPaymentMethod;
import com.rbkmoney.damsel.domain.BankCardTokenServiceRef;
import com.rbkmoney.damsel.domain.LegacyBankCardTokenProvider;
import com.rbkmoney.damsel.domain.PaymentSystemCondition;
import com.rbkmoney.damsel.domain.TokenizedBankCard;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.Util.isEmpty;

/**
 * Utility class to help with former BankCardTokenProvider.<br>
 * It can have values in two fields: <br>
 *  -> BankCardTokenServiceRef <br>
 *  -> LegacyBankCardTokenProvider
 */
public class TokenProviderUtil {

    private TokenProviderUtil() {
    }

    public static String getTokenProviderName(@NotNull BankCard card) {
        return getTokenProviderName(card.getPaymentToken(), card.getTokenProviderDeprecated());
    }

    public static String getTokenProviderName(@NotNull BankCardPaymentMethod method) {
        return getTokenProviderName(method.getPaymentToken(), method.getTokenProviderDeprecated());
    }

    public static String getTokenProviderName(@NotNull TokenizedBankCard card) {
        return getTokenProviderName(card.getPaymentToken(), card.getTokenProviderDeprecated());
    }

    public static String getTokenProviderName(@NotNull PaymentSystemCondition condition) {
        return getTokenProviderName(condition.getTokenServiceIs(), condition.getTokenProviderIsDeprecated());
    }

    public static String getTokenProviderName(BankCardTokenServiceRef bankCardTokenServiceRef,
                                              LegacyBankCardTokenProvider legacyBankCardPaymentSystem) {
        if (bankCardTokenServiceRef != null && !isEmpty(bankCardTokenServiceRef.getId())) {
            return bankCardTokenServiceRef.getId();
        }
        if (legacyBankCardPaymentSystem != null) {
            return legacyBankCardPaymentSystem.name();
        }

        return null;
    }

    public static boolean isSetTokenProvider(@NotNull BankCard card) {
        return card.isSetPaymentToken() || card.isSetTokenProviderDeprecated();
    }

    public static boolean isSetTokenProvider(@NotNull BankCardPaymentMethod method) {
        return method.isSetPaymentToken() || method.isSetTokenProviderDeprecated();
    }

    public static boolean isSetTokenProvider(@NotNull TokenizedBankCard card) {
        return card.isSetPaymentToken() || card.isSetTokenProviderDeprecated();
    }

    public static boolean isSetTokenProvider(@NotNull PaymentSystemCondition condition) {
        return condition.isSetTokenServiceIs() || condition.isSetTokenProviderIsDeprecated();
    }
}
