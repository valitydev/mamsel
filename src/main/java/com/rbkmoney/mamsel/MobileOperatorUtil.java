package com.rbkmoney.mamsel;

import com.rbkmoney.damsel.domain.LegacyMobileOperator;
import com.rbkmoney.damsel.domain.MobileCommerce;
import com.rbkmoney.damsel.domain.MobileCommerceConditionDefinition;
import com.rbkmoney.damsel.domain.MobileOperatorRef;
import com.rbkmoney.damsel.domain.PaymentMethod;

import javax.validation.constraints.NotNull;

import static com.rbkmoney.mamsel.internal.Util.isNotNullOrEmpty;

/**
 * Utility class to help with former MobileOperator.<br>
 * It can have values in two fields: <br>
 *  -> MobileOperatorRef <br>
 *  -> LegacyMobileOperator
 */
public class MobileOperatorUtil {

    private MobileOperatorUtil() {
    }

    public static String getMobileOperatorName(@NotNull PaymentMethod method) {
        if (method.isSetMobile()) {
            return method.getMobile().getId();
        }
        if (method.isSetMobileDeprecated()) {
            return method.getMobileDeprecated().name();
        }
        return null;
    }

    public static String getMobileOperatorName(@NotNull MobileCommerce commerce) {
        return getMobileOperatorName(commerce.getOperator(), commerce.getOperatorDeprecated());
    }

    public static String getMobileOperatorName(@NotNull MobileCommerceConditionDefinition definition) {
        if (definition.isSetOperatorIs()) {
            return definition.getOperatorIs().getId();
        }
        if (definition.isSetOperatorIsDeprecated()) {
            return definition.getOperatorIsDeprecated().name();
        }

        return null;
    }

    public static String getMobileOperatorName(MobileOperatorRef mobileOperatorRef,
                                               LegacyMobileOperator legacyMobileOperator) {
        if (mobileOperatorRef != null && isNotNullOrEmpty(mobileOperatorRef.getId())) {
            return mobileOperatorRef.getId();
        }
        if (legacyMobileOperator != null) {
            return legacyMobileOperator.name();
        }

        return null;
    }

    public static boolean isSetMobileOperatorName(@NotNull PaymentMethod method) {
        return method.isSetMobile() || method.isSetMobileDeprecated();
    }

    public static boolean isSetMobileOperatorName(@NotNull MobileCommerce commerce) {
        return commerce.isSetOperator() || commerce.isSetOperatorDeprecated();
    }

    public static boolean isSetMobileOperatorName(@NotNull MobileCommerceConditionDefinition definition) {
        return definition.isSetOperatorIs() || definition.isSetOperatorIsDeprecated();
    }
}
