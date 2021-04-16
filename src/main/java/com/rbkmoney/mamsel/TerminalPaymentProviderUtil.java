package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyTerminalPaymentProvider;
import com.rbkmoney.damsel.domain.PaymentMethod;
import com.rbkmoney.damsel.domain.PaymentServiceRef;
import com.rbkmoney.damsel.domain.PaymentTerminal;
import com.rbkmoney.damsel.domain.PaymentTerminalConditionDefinition;

import static com.rbkmoney.mamsel.internal.Util.isNotNullOrEmpty;

/**
 * Utility class to help with former TerminalPaymentProvider.<br>
 * It can have values in two fields: <br>
 *  -> PaymentServiceRef <br>
 *  -> LegacyTerminalPaymentProvider
 */
public class TerminalPaymentProviderUtil {

    private TerminalPaymentProviderUtil() {
    }

    public static String getTerminalPaymentProviderName(PaymentMethod method) {
        if (method.isSetPaymentTerminal()) {
            return method.getPaymentTerminal().getId();
        }
        if (method.isSetPaymentTerminalDeprecated()) {
            return method.getPaymentTerminalDeprecated().name();
        }
        return null;
    }

    public static String getTerminalPaymentProviderName(PaymentTerminal terminal) {
        return getTerminalPaymentProviderName(terminal.getPaymentService(), terminal.getTerminalTypeDeprecated());
    }

    public static String getTerminalPaymentProviderName(PaymentTerminalConditionDefinition definition) {
        if (definition.isSetPaymentServiceIs()) {
            return definition.getPaymentServiceIs().getId();
        }
        if (definition.isSetProviderIsDeprecated()) {
            return definition.getProviderIsDeprecated().name();
        }
        return null;
    }

    public static String getTerminalPaymentProviderName(PaymentServiceRef paymentServiceRef,
                                                        LegacyTerminalPaymentProvider legacyTerminalPaymentProvider) {
        if (paymentServiceRef != null && isNotNullOrEmpty(paymentServiceRef.getId())) {
            return paymentServiceRef.getId();
        }
        if (legacyTerminalPaymentProvider != null) {
            return legacyTerminalPaymentProvider.name();
        }

        return null;
    }

    public static boolean isSetTerminalPaymentProvider(PaymentMethod method) {
        return method.isSetPaymentTerminal() || method.isSetPaymentTerminalDeprecated();
    }

    public static boolean isSetTerminalPaymentProvider(PaymentTerminal terminal) {
        return terminal.isSetPaymentService() || terminal.isSetTerminalTypeDeprecated();
    }

    public static boolean isSetTerminalPaymentProvider(PaymentTerminalConditionDefinition definition) {
        return definition.isSetPaymentServiceIs() || definition.isSetProviderIsDeprecated();
    }

}
