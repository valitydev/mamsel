package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.DigitalWallet;
import com.rbkmoney.damsel.domain.DigitalWalletConditionDefinition;
import com.rbkmoney.damsel.domain.LegacyDigitalWalletProvider;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentServiceRef;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.internal.Util.isNotNullOrEmpty;

/**
 * Utility class to help with former DigitalWalletProvider.<br>
 * It can have values in two fields: <br>
 *  -> PaymentServiceRef <br>
 *  -> LegacyDigitalWalletProvider
 */
public class DigitalWalletProviderUtil {

    private DigitalWalletProviderUtil() {
    }

    public static String getDigitalWalletName(@NotNull PaymentMethod method) {
        if (method.isSetDigitalWallet()) {
            return method.getDigitalWallet().getId();
        }
        if (method.isSetDigitalWalletDeprecated()) {
            return method.getDigitalWalletDeprecated().name();
        }
        return null;
    }

    public static String getDigitalWalletName(@NotNull DigitalWallet wallet) {
        return getDigitalWalletName(wallet.getPaymentService(), wallet.getProviderDeprecated());
    }

    public static String getDigitalWalletName(@NotNull DigitalWalletConditionDefinition definition) {
        if (definition.isSetPaymentServiceIs()) {
            return definition.getPaymentServiceIs().getId();
        }
        if (definition.isSetProviderIsDeprecated()) {
            return definition.getProviderIsDeprecated().name();
        }
        return null;
    }

    public static String getDigitalWalletName(PaymentServiceRef paymentServiceRef,
                                              LegacyDigitalWalletProvider legacyDigitalWalletProvider) {
        if (paymentServiceRef != null && isNotNullOrEmpty(paymentServiceRef.getId())) {
            return paymentServiceRef.getId();
        }
        if (legacyDigitalWalletProvider != null) {
            return legacyDigitalWalletProvider.name();
        }

        return null;
    }

    public static boolean isSetDigitalWallet(@NotNull PaymentMethod method) {
        return method.isSetDigitalWallet() || method.isSetDigitalWalletDeprecated();
    }

    public static boolean isSetDigitalWallet(@NotNull DigitalWallet wallet) {
        return wallet.isSetPaymentService() || wallet.isSetProviderDeprecated();
    }

    public static boolean isSetDigitalWallet(@NotNull DigitalWalletConditionDefinition definition) {
        return definition.isSetPaymentServiceIs() || definition.isSetProviderIsDeprecated();
    }

}
