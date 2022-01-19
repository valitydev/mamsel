package com.rbkmoney.mamsel;

import dev.vality.damsel.domain.BankCard;
import dev.vality.damsel.domain.BankCardTokenServiceRef;
import dev.vality.damsel.domain.LegacyBankCardTokenProvider;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Utility class to help with former BankCardTokenProvider.<br>
 * It can have values in two fields: <br>
 * -> BankCardTokenServiceRef <br>
 * -> LegacyBankCardTokenProvider
 */
public class TokenProviderUtil {

    private TokenProviderUtil() {
    }

    public static String getTokenProviderName(@NotNull BankCard bankCard) {
        return getTokenProviderName(bankCard.getPaymentToken(), bankCard.getTokenProviderDeprecated());
    }

    public static String getTokenProviderName(@NotNull dev.vality.damsel.merch_stat.BankCard bankCard) {
        return getTokenProviderName(bankCard.getPaymentToken(), bankCard.getTokenProviderDeprecated());
    }

    public static String getTokenProviderName(
            BankCardTokenServiceRef bankCardTokenServiceRef,
            LegacyBankCardTokenProvider legacyBankCardTokenProvider) {
        return getTokenProviderNameIfPresent(bankCardTokenServiceRef, legacyBankCardTokenProvider)
                .orElse(null);
    }

    public static Optional<String> getTokenProviderNameIfPresent(
            BankCardTokenServiceRef bankCardTokenServiceRef,
            LegacyBankCardTokenProvider legacyBankCardTokenProvider) {
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(bankCardTokenServiceRef)
                        .map(BankCardTokenServiceRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyBankCardTokenProvider)
                        .map(Enum::name)
        );
    }
}
