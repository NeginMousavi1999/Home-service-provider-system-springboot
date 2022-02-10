package ir.maktab.project.data.dto.mapper;

import ir.maktab.project.data.dto.PaymentDto;
import ir.maktab.project.data.entity.order.Payment;

/**
 * @author Negin Mousavi
 */
public class PaymentMapper {
    private static final int suffix = 1000;

    public static Payment mapPaymentDtoToPayment(PaymentDto paymentDto) {
        return Payment.builder()
                .id(paymentDto.getIdentity() - suffix)

                .build();
    }

    public static Payment mapPaymentDtoToPaymentForSaving(PaymentDto paymentDto) {
        return Payment.builder()
                .cardNumber(paymentDto.getCardNumber())
                .order(OrderMapper.mapOrderDtoToOrderWithoutSuggestion(paymentDto.getOrder()))
                .paymentMethod(paymentDto.getPaymentMethod())
                .build();
    }

    public static PaymentDto mapPaymentToPaymentDto(Payment payment) {
        return PaymentDto.builder()
                .identity(payment.getId() + suffix)

                .build();
    }
}
