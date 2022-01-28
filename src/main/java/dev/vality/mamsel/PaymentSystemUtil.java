package dev.vality.mamsel;

import dev.vality.damsel.domain.BankCard;
import dev.vality.damsel.domain.LegacyBankCardPaymentSystem;
import dev.vality.damsel.domain.PaymentSystemRef;
import dev.vality.damsel.payment_tool_provider.CardInfo;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Utility class to help with former BankCardPaymentSystem.<br>
 * It can have values in two fields: <br>
 * -> PaymentSystemRef <br>
 * -> LegacyBankCardPaymentSystem
 */
public class PaymentSystemUtil {

    private PaymentSystemUtil() {
    }

    public static String getPaymentSystemName(@NotNull BankCard bankCard) {
        return getPaymentSystemName(bankCard.getPaymentSystem(), bankCard.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull dev.vality.damsel.merch_stat.BankCard bankCard) {
        return getPaymentSystemName(bankCard.getPaymentSystem(), bankCard.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(@NotNull CardInfo cardInfo) {
        return getPaymentSystemName(cardInfo.getPaymentSystem(), cardInfo.getPaymentSystemDeprecated());
    }

    public static String getPaymentSystemName(
            PaymentSystemRef paymentSystemRef,
            LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return getPaymentSystemNameIfPresent(paymentSystemRef, legacyBankCardPaymentSystem)
                .orElse(null);
    }

    public static String getFistfulPaymentSystemName(@NotNull dev.vality.fistful.base.BankCard bankCard) {
        return getFistfulPaymentSystemName(bankCard.getPaymentSystem(), bankCard.getPaymentSystemDeprecated());
    }

    public static String getFistfulPaymentSystemName(
            dev.vality.fistful.base.PaymentSystemRef paymentSystemRef,
            dev.vality.fistful.base.LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return getFistfulPaymentSystemNameIfPresent(paymentSystemRef, legacyBankCardPaymentSystem)
                .orElse(null);
    }

    public static Optional<String> getFistfulPaymentSystemNameIfPresent(
            dev.vality.fistful.base.PaymentSystemRef paymentSystemRef,
            dev.vality.fistful.base.LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(paymentSystemRef)
                        .map(dev.vality.fistful.base.PaymentSystemRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyBankCardPaymentSystem)
                        .map(Enum::name)
        );
    }

    public static Optional<String> getPaymentSystemNameIfPresent(
            PaymentSystemRef paymentSystemRef,
            LegacyBankCardPaymentSystem legacyBankCardPaymentSystem) {
        return OptionalExtension.isPresentOr(
                () -> Optional.ofNullable(paymentSystemRef)
                        .map(PaymentSystemRef::getId)
                        .filter(Predicate.not(StringUtils::isEmpty)),
                () -> Optional.ofNullable(legacyBankCardPaymentSystem)
                        .map(Enum::name)
        );
    }
}
