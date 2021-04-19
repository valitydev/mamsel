package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.CryptoCurrencyConditionDefinition;
import com.rbkmoney.damsel.domain.CryptoCurrencyRef;
import com.rbkmoney.damsel.domain.CryptoWallet;
import com.rbkmoney.damsel.domain.LegacyCryptoCurrency;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentTool;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.Util.isEmpty;

/**
 * Utility class to help with former CryptoCurrency.<br>
 * It can have values in two fields: <br>
 *  -> CryptoCurrencyRef <br>
 *  -> LegacyCryptoCurrency
 */
public class CryptoCurrencyUtil {

    private CryptoCurrencyUtil() {
    }

    public static String getCryptoCurrencyName(@NotNull CryptoWallet wallet) {
        return getCryptoCurrencyName(wallet.getCryptoCurrency(), wallet.getCryptoCurrencyDeprecated());
    }

    public static String getCryptoCurrencyName(@NotNull PaymentTool tool) {
        if (tool.isSetCryptoCurrency()) {
            return tool.getCryptoCurrency().getId();
        }
        if (tool.isSetCryptoCurrencyDeprecated()) {
            return tool.getCryptoCurrencyDeprecated().name();
        }
        return null;
    }

    public static String getCryptoCurrencyName(@NotNull PaymentMethod method) {
        if (method.isSetCryptoCurrency()) {
            return method.getCryptoCurrency().getId();
        }
        if (method.isSetCryptoCurrencyDeprecated()) {
            return method.getCryptoCurrencyDeprecated().name();
        }

        return null;
    }

    public static String getCryptoCurrencyName(@NotNull CryptoCurrencyConditionDefinition definition) {
        if (definition.isSetCryptoCurrencyIs()) {
            return definition.getCryptoCurrencyIs().getId();
        }
        if (definition.isSetCryptoCurrencyIsDeprecated()) {
            return definition.getCryptoCurrencyIsDeprecated().name();
        }

        return null;
    }

    public static String getCryptoCurrencyName(CryptoCurrencyRef cryptoCurrency,
                                               LegacyCryptoCurrency cryptoCurrencyDeprecated) {
        if (cryptoCurrency != null && !isEmpty(cryptoCurrency.getId())) {
            return cryptoCurrency.getId();
        }
        if (cryptoCurrencyDeprecated != null) {
            return cryptoCurrencyDeprecated.name();
        }
        return null;
    }

    public static boolean isSetCryptoCurrency(@NotNull CryptoWallet wallet) {
        return wallet.isSetCryptoCurrency() || wallet.isSetCryptoCurrencyDeprecated();
    }

    public static boolean isSetCryptoCurrency(@NotNull PaymentMethod method) {
        return method.isSetCryptoCurrency() || method.isSetCryptoCurrencyDeprecated();
    }

    public static boolean isSetCryptoCurrency(@NotNull CryptoCurrencyConditionDefinition definition) {
        return definition.isSetCryptoCurrencyIs() || definition.isSetCryptoCurrencyIsDeprecated();
    }

    public static boolean isSetCryptoCurrency(@NotNull PaymentTool tool) {
        return tool.isSetCryptoCurrency() || tool.isSetCryptoCurrencyDeprecated();
    }

}
